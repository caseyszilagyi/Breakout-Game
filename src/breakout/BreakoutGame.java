package breakout;

import breakout.Game;
import breakout.GameObject;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafx.animation.Animation.Status.RUNNING;
import static javafx.animation.Animation.Status.STOPPED;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class BreakoutGame extends Game{

    // Game size and positioning of score/lives text
    private static final int GAME_HEIGHT = 800;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_TEXT_X = 300;
    private static final int GAME_TEXT_Y = 50;

    // GameObjects that we need access to
    private Ball ball;
    private Paddle paddle;
    private Text message;

    // Deals with movement of paddle;
    private boolean goRight = false;
    private boolean goLeft = false;

    // Game Properties
    private int livesRemaining = 3;
    private int currentScore = 0;
    private int currentLevel = 0; // indicates that game hasn't started.
    private final ArrayList<String> LEVEL_FILES = new ArrayList<String>(
            Arrays.asList("FirstLevel.txt", "SecondLevel.txt"));

    //Makes a level creator and level handler
    private LevelCreator levelCreator = new LevelCreator();
    private LevelHandler levelHandler;

    //The various scenes that the game will change between, depending on whether a level is currently being played
    private Scene beforeGameScene;
    private Scene betweenLevelsScene;
    private Scene winScene;
    private Scene loseScene;

    //The game stage
    private Stage gameDisplay;

    /**
     * This constructor calls the super constructor in the "Game" class.
     * @param fps The frames per second that the game will run at
     * @param title The name of the game
     */
    public BreakoutGame(int fps, String title){
        super(fps, title);
    }

    /**
     * Initializes the game world by updating the primaryStage. This method sets the size of the game,
     * and initializes all of the objects to play the game through the call to makeGameComponents
     * @param primaryStage The stage constructed by the platform to be updated
     */
    public void start(final Stage primaryStage){
        gameDisplay = primaryStage;
        primaryStage.setTitle(getWindowTitle());

        makeOtherDisplayScenes();
        primaryStage.setScene(beforeGameScene);

        //Setting the buttons in the other scenes to result in a level being played
        levelHandler.getStartButton().setOnAction(e -> makeGameComponents());
        levelHandler.getBetweenLevelsButton().setOnAction(e -> makeGameComponents());

    }

    /** Makes the start up screen and waits for a key press to begin */
    public void makeOtherDisplayScenes(){
        levelHandler = new LevelHandler(GAME_WIDTH, GAME_HEIGHT);
        beforeGameScene = levelHandler.makeAndReturnStartUpScreen();
        betweenLevelsScene = levelHandler.makeAndReturnBetweenLevelsScreen();
        winScene = levelHandler.makeAndReturnWinScene();
        loseScene = levelHandler.makeAndReturnLoseScene();
    }


    /** Method to update the score and lives
     * Checks to see if the game is in progress before doing anything */
    @Override
    public void updateGameValues() {
        //If game is being played
        if(currentLevel > 0) {
            //If level is done
            if (checkIfFinishedLevel()) {
                //Check to see if it was final level
                if(currentLevel == LEVEL_FILES.size()){
                    gameDisplay.setScene(winScene);
                }
                else {
                    gameDisplay.setScene(betweenLevelsScene);
                }
            }

            //Checks if game should still be playable
            if(livesRemaining <= 0){
                gameDisplay.setScene(loseScene);
            }

            checkIfBallIsInPlay();
            message.setText("Lives: " + Integer.toString(livesRemaining) + " Score: " + Integer.toString(currentScore)
                    + " Level: " + Integer.toString(currentLevel));
        }
    }

    /** Checks if a level is done being played by seeing if there are any bricks
     * @return True if no bricks
     */
    public boolean checkIfFinishedLevel(){
        for(Object singleObject: getObjectManager().getObjects()){
            if(singleObject instanceof Brick){
                return false;
            }
        }
        return true;
    }

    /** Updating the game object. The super method simply calls the update method in the class of the
     * GameObject itself. The rest of this method checks for the ball exiting the screen (to lose a life);
     * @param gameObject
     */
    public void updateObject(GameObject gameObject){
        super.updateObject(gameObject);
    }


    /**
     * Deals with the collision of two GameObjects. Leads to the calling of the collision method
     * of GameObject A, and also checks conditionals for managment of the game
     * @param A - called from checkCollision() method to be compared.
     * @param B - called from checkCollision() method to be compared.
     * @return True if they collide
     */
    @Override
    public boolean collide(GameObject A, GameObject B){
        boolean collide = super.collide(A, B);

        if(collide && (A instanceof Ball || B instanceof Ball)){
            updateScore(A, B);
            updateBallVelocityBasedOnPaddleBounce(A, B);
        }



        return collide;
    }

    /** Checks whether the ball collided with a brick in order to update the score */
    public void updateScore(GameObject A, GameObject B){
        if(A instanceof Brick && B instanceof Ball){
            currentScore += 100;
        }

        //Checks if it is a row destroy brick
        if(A instanceof RowDestroyBrick && B instanceof Ball){
            destroyRow(((RowDestroyBrick) A).gameObject.getY());
            currentScore += 900;
        }
        //Checks if it is a power up brick
        else if(A instanceof DropPowerUpBrick && B instanceof Ball){
            Rectangle currentBrick = ((DropPowerUpBrick) A).gameObject;
            dropPowerUp(currentBrick.getX() + currentBrick.getWidth()/2,
                    currentBrick.getY() + currentBrick.getHeight()/2);
            currentScore += 400;
        }
    }

    /** Changes the speed and direction of the ball based on what part of the paddle it hits */
    public void updateBallVelocityBasedOnPaddleBounce(GameObject A, GameObject B){
        if(A instanceof Paddle || B instanceof Paddle){
            double paddleCenter = paddle.gameObject.getX() + paddle.node.getTranslateX() + paddle.gameObject.getWidth()/2;
            double diffOfBallCenterAndPaddleCenter = (ball.gameObject.getCenterX() + ball.gameObject.getTranslateX()) - paddleCenter;
            ball.XVelocity = diffOfBallCenterAndPaddleCenter/5;
        }
    }


    /** Destroys the row specified by the X position of the RowDestroyBrick by looping through all GameObjects
     * and seeing if they have the same x position */
    public void destroyRow(double yPos){
        for(Object singleObject: getObjectManager().getObjects()){
            if(singleObject instanceof Brick && ((Brick) singleObject).gameObject.getY() == yPos){
                ((Brick) singleObject).isDead = true;
            }
        }
    }

    /** Makes a power up that falls in the location of the destroyed brick */
    public void dropPowerUp(double xPos, double yPos){
        PowerUp powerUp = new PowerUp(xPos, yPos);
        // add to all objects in play
        getObjectManager().addObjects(powerUp);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, powerUp.node);
    }

    /** Checks whether the ball is still in play */
    public void checkIfBallIsInPlay(){
        if(currentLevel > 0 && ball.node.getTranslateY() + ball.node.getLayoutY() > GAME_HEIGHT){
            ball.isDead = true;
            ball = levelCreator.makeBall();
            getObjectManager().addObjects(ball);
            //add node to group of nodes for graphics
            getNodes().getChildren().add(0, ball.node);
            livesRemaining--;
        }
    }


    /**
     * Method to make all of the objects that make up the game
     */
    public void makeGameComponents(){
        setNodes(new Group());
        setGameSurface(new Scene(getNodes(), GAME_WIDTH, GAME_HEIGHT));

        // Reading in the current level
        levelCreator.readNewFile(LEVEL_FILES.get(currentLevel));
        currentLevel++;
        // Making the game components
        levelCreator.makeGameComponents();

        makeAllObjects();
        makePaddleEventHandlers();
        makeGameScoreAndLives();

        gameDisplay.setScene(getGameSurface());
    }

    /** This loop gets all of the gameObjects that were created in the LevelCreator and adds them to
     * the ObjectManager for collision management, and the set of nodes for display
     */
    public void makeAllObjects(){
        ArrayList<GameObject> gameComponent = levelCreator.getGameComponents();
        getObjectManager().getObjects().clear();
        getNodes().getChildren().clear();
        for(int i = 0; i<gameComponent.size(); i++){
            //add to all objects in play for collision detection
            getObjectManager().addObjects(gameComponent.get(i));
            //add node to group of nodes for graphics
            getNodes().getChildren().add(0, gameComponent.get(i).node);

            //Ball is needed for collision detection to make sure it is still in play
            if(gameComponent.get(i) instanceof Ball){
                ball = (Ball) gameComponent.get(i);
            }

            //Paddle is needed for event handlers
            if(gameComponent.get(i) instanceof Paddle){
                paddle = (Paddle) gameComponent.get(i);
            }
        }
    }

    /** Initializes the display of the game score and lives */
    public void makeGameScoreAndLives(){
        message = new Text(GAME_TEXT_X,GAME_TEXT_Y,"Lives: " + Integer.toString(livesRemaining)
                + " Score: " + Integer.toString(currentScore));
        message.setScaleX(5);
        message.setScaleY(5);
        getNodes().getChildren().add(0, message);
    }


    //Below this are all of the components to deal with the movement of the paddle

    /** Method to deal with the creation of our two paddle event handlers. One to deal with key presses,
     * the other to deal with key releases
     */
    private void makePaddleEventHandlers() {
        makeKeyPressedHandler();
        makeKeyRelesedHandler();
        makePaddleAnimation();
    }

    /** Makes the handler that deals with right/left key presses */
    private void makeKeyPressedHandler() {
        // Makes EventHandler that deals with input from left/right keys to move paddle;
        EventHandler paddleMove = new EventHandler(){
            @Override
            public void handle(Event event) {
                if(event instanceof KeyEvent){
                    keyPressResponse((KeyEvent) event);
                }
            }
        };

        // Adds EventHandler to our game
        getGameSurface().setOnKeyPressed(paddleMove);
    }

    /** Makes the handler that deals with right/left key releases */
    private void makeKeyRelesedHandler() {
        // Makes EventHandler to deal with the release of the left/right key
        EventHandler paddleStop = new EventHandler(){
            @Override
            public void handle(Event event) {
                if(event instanceof KeyEvent){
                    keyReleaseResponse((KeyEvent) event);
                }
            }
        };

        // Adds EventHandler to our game
        getGameSurface().setOnKeyReleased(paddleStop);
    }

    /** Makes the animation timer that allows the paddle to move smoothly */
    private void makePaddleAnimation(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(goRight){
                    paddle.moveRight();
                }
                else if(goLeft){
                    paddle.moveLeft();
                }
            }
        };

        timer.start();
    }


    /**
     * Updates the boolean values that determine if the left/right key is currently being pressed
     * @param event The key that is pressed
     */
    public void keyPressResponse(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            goLeft = true;
        }
        else if(event.getCode() == KeyCode.RIGHT){
            goRight = true;
        }
    }

    /**
     * Updates the boolean values that determine if the left/right key is currently being released
     * @param event The key that is released
     */
    public void keyReleaseResponse(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            goLeft = false;
        }
        else if(event.getCode() == KeyCode.RIGHT){
            goRight = false;
        }
    }

}

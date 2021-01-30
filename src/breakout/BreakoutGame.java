package breakout;

import breakout.Game;
import breakout.GameObject;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    private static final int GAME_HEIGHT = 800;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_BORDER_HEIGHT = 99;
    private static final int GAME_TEXT_X = 10;
    private static final int GAME_TEXT_Y = 50;

    // Brick Properties (that are common among all bricks);
    private final int BRICK_WIDTH = 99;
    private final int BRICK_HEIGHT = 20;
    private final int BRICK_GAP = 1;

    // GameObjects that we need access to
    public Ball ball;
    public Paddle paddle;
    public Text message;

    // Deals with movement of paddle;
    private boolean goRight = false;
    private boolean goLeft = false;

    // Game Properties
    private int livesRemaining = 3;
    private int currentScore = 0;

    //Makes a level creator
    LevelCreator levelCreator = new LevelCreator(BRICK_WIDTH, BRICK_HEIGHT, BRICK_GAP, GAME_WIDTH, GAME_BORDER_HEIGHT);

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
        primaryStage.setTitle(getWindowTitle());

        setNodes(new Group());
        setGameSurface(new Scene(getNodes(), GAME_WIDTH, GAME_HEIGHT));
        primaryStage.setScene(getGameSurface());

        makeGameComponents();
        makeGameScoreAndLives();
    }


    public void makeGameScoreAndLives(){
        message = new Text(GAME_TEXT_X,GAME_TEXT_Y,"Score: " + Integer.toString(currentScore) +
                " Lives: " + Integer.toString(livesRemaining));
        message.setScaleX(5);
        message.setScaleY(5);
        getNodes().getChildren().add(0, message);
    }
    @Override
    public void updateGameValues() {
        message.setText("Score: " + Integer.toString(currentScore) +  " Lives: " + Integer.toString(livesRemaining));
        currentScore++;
    }

    /**
     * Deals with the collision of two GameObjects. Leads to the calling of the collision method
     * of GameObject A
     * @param A - called from checkCollision() method to be compared.
     * @param B - called from checkCollision() method to be compared.
     * @return True if they collide
     */
    @Override
    public boolean collide(GameObject A, GameObject B){
        boolean collide = super.collide(A, B);

        //Checks if it is a row destroy brick
        if(collide && A instanceof RowDestroyBrick){
            destroyRow(((RowDestroyBrick) A).gameObject.getY());
        }
        //Checks if it is a power up brick
        else if(collide && A instanceof DropPowerUpBrick){
            Rectangle currentBrick = ((DropPowerUpBrick) A).gameObject;
            dropPowerUp(currentBrick.getX() + currentBrick.getWidth()/2,
                    currentBrick.getY() + currentBrick.getHeight()/2);
        }

        return collide;
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

    public void dropPowerUp(double xPos, double yPos){
        PowerUp powerUp = new PowerUp(xPos, yPos);
        // add to all objects in play
        getObjectManager().addObjects(powerUp);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, powerUp.node);
    }



    //All of this below should be added to the LevelCreator class if there is time


    /**
     * Method to make all of the game components
     */
    public void makeGameComponents(){
        levelCreator.makeGameBorder();
        levelCreator.makeBall();
        levelCreator.makePaddle();

        levelCreator.readNewFile("FirstLevel.txt");
        levelCreator.makeBricks();

        makePaddleEventHandlers();
        makeAllObjects();
    }

    /** This loop gets all of the gameObjects that were created in the levelcreator and adds them to
     * the objectmanager for collision management, and the set of nodes for display
     */
    public void makeAllObjects(){
        ArrayList<GameObject> gameComponent = levelCreator.getGameComponents();
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

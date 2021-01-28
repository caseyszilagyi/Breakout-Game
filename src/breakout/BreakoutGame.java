package breakout;

import breakout.Game;
import breakout.GameObject;

import java.io.FileNotFoundException;
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
import javafx.stage.Stage;
import static javafx.animation.Animation.Status.RUNNING;
import static javafx.animation.Animation.Status.STOPPED;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class BreakoutGame extends Game{

    private static final int GAME_HEIGHT = 800;
    private static final int GAME_WIDTH = 1000;

    //Ball Properties
    private final int BALL_RADIUS = 5;
    private final int BALL_XINITIAL = 500;
    private final int BALL_YINITIAL = 400;
    private final int BALL_XVELOCITY = 1;
    private final int BALL_YVELOCITY = 10;

    //Paddle Properties
    public Paddle paddle;
    private final int PADDLE_XINITIAL = 450;
    private final int PADDLE_YINITIAL = 790;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 20;
    private final int PADDLE_SPEED = 10;
    // Deals with movement of paddle;
    private boolean goRight = false;
    private boolean goLeft = false;

    //Brick Properties (that are common among all bricks);
    private final int BRICK_WIDTH = 99;
    private final int BRICK_HEIGHT = 20;
    private final int BRICK_GAP = 1;

    //Makes a level creator
    LevelCreator levelCreator = new LevelCreator(BRICK_WIDTH, BRICK_HEIGHT, BRICK_GAP, GAME_WIDTH);

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
     * and initializes all of the objects to play the game through calls to the submethod.
     * @param primaryStage The stage constructed by the platform to be updated
     */
    public void start(final Stage primaryStage){
        primaryStage.setTitle(getWindowTitle());

        setNodes(new Group());
        setGameSurface(new Scene(getNodes(), GAME_WIDTH, GAME_HEIGHT));
        primaryStage.setScene(getGameSurface());

        makeGameComponents();

    }

    /**
     * Updates the GameObject that's passed into the method.
     * @param object - The GameObject to update.
     */
    @Override
    public void updateObject(GameObject object){
        object.update();
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
        return super.collide(A,B);
    }

    /**
     * Method to make all of the game components
     */
    public void makeGameComponents(){
        makeGameBorder();
        makeBall();
        makePaddle();
        makeBricks();
    }

    /** Method to make the paddle and add it to our ObjectManager and GameNodes to be rendered.
     */
    public void makePaddle(){
        paddle = new Paddle(PADDLE_XINITIAL, PADDLE_YINITIAL, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED);
        // add to all objects in play
        getObjectManager().addObjects(paddle);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, paddle.node);

        makePaddleEventHandlers();
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

    /**
     * Makes the game border so that collision with the edge is easier. The border is just a GameObject
     * so that the game engine can deal with these collisioins as they do every other collision.
     */
    public void makeGameBorder(){
        ArrayList<GameObject> borders = new ArrayList<GameObject>();
        borders.add(new Border(-1, 0, 1, GAME_HEIGHT)); //left
        borders.add(new Border(GAME_WIDTH, 0, 1, GAME_HEIGHT)); //right
        borders.add(new Border(0, -1, GAME_WIDTH, 1)); //top

        //Adding the borders to necessary groups
        for(int i = 0; i<borders.size(); i++){
            // add to all objects in play
            getObjectManager().addObjects(borders.get(i));
            // add node to group of nodes for graphics
            getNodes().getChildren().add(0, borders.get(i).node);
        }
    }

    /**
     * Initializes the ball object
     */
    public void makeBall(){
        Ball ball = new Ball(BALL_XINITIAL, BALL_YINITIAL, BALL_RADIUS, BALL_XVELOCITY, BALL_YVELOCITY);
        // add to all objects in play for collision detection
        getObjectManager().addObjects(ball);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, ball.node);
    }


    public void makeBricks(){
        levelCreator.readNewFile("FirstLevel.txt");
        levelCreator.makeBricks();
        ArrayList<GameObject> bricks = levelCreator.getBricks();
        for(int i = 0; i<bricks.size(); i++){
            // add to all objects in play for collision detection
            getObjectManager().addObjects(bricks.get(i));
            // add node to group of nodes for graphics
            getNodes().getChildren().add(0, bricks.get(i).node);
        }
    }

}

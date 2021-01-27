package breakout;

import breakout.Game;
import breakout.GameObject;
import java.util.Random;
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

    //Ball Initialization
    private final int BALL_RADIUS = 10;
    private final int BALL_XINITIAL = 500;
    private final int BALL_YINITIAL = 400;
    private final int BALL_XVELOCITY = 5;
    private final int BALL_YVELOCITY = 1;

    //Paddle Initialization
    private final int PADDLE_XINITIAL = 450;
    private final int PADDLE_YINITIAL = 790;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 10;
    private final int PADDLE_SPEED = 3;
    public Paddle paddle;

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
     * Deals with the collision of two GameObjects
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
    }


    public void makePaddle(){
        paddle = new Paddle(PADDLE_XINITIAL, PADDLE_YINITIAL, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED);
        // add to all objects in play
        getObjectManager().addObjects(paddle);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, paddle.node);

        //Makes EventHandler that deals with input from left/right keys to move paddle;
        EventHandler paddleMove = new EventHandler(){
            @Override
            public void handle(Event event) {
                if(event instanceof KeyEvent){
                    keyPressResponse((KeyEvent) event);
                }
            }
        };

        //Adds event handler to our game
        getGameSurface().setOnKeyPressed(paddleMove);


    }

    /**
     * Updates the paddle when the right/left key is pressed
     * @param event The key that is pressed
     */
    public void keyPressResponse(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            paddle.moveLeft();
        }
        else if(event.getCode() == KeyCode.RIGHT){
            paddle.moveRight();
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
        // add to all objects in play
        getObjectManager().addObjects(ball);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, ball.node);
    }




}

package breakout;

import breakout.Game;
import breakout.GameObject;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

        makeGameBorder();
        makeBallAndPaddle();

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
            // add to all  GameObjects in play
            getObjectManager().addObjects(borders.get(i));
            // add GameObjects
            getNodes().getChildren().add(0, borders.get(i).node);
        }
    }

    /**
     * Creating the ball and paddle that the game will be played with.
     */
    public void makeBallAndPaddle(){
        makeBall(BALL_XINITIAL, BALL_YINITIAL, BALL_RADIUS, BALL_XVELOCITY, BALL_YVELOCITY);
    }

    /**
     * Initializes the ball object
     * @param xPos Starting x position of the ball
     * @param yPos Starting y position of the ball
     * @param radius Starting radius of the ball
     * @param xVel Starting x velocity of the ball
     * @param yVel Starting y velocity of the ball
     */
    public void makeBall(int xPos, int yPos, int radius, int xVel, int yVel){
        Ball ball = new Ball(xPos, yPos, radius, xVel, yVel);
        // add to all objects in play
        getObjectManager().addObjects(ball);
        // add node to group of nodes for graphics
        getNodes().getChildren().add(0, ball.node);
    }


}

package breakout;

import breakout.Game;
import breakout.Sprite;
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

public class BreakoutGame extends Game{

    protected static final int GAME_HEIGHT = 800;
    protected final int GAME_WIDTH = 1000;

    //Ball Initialization
    private final int BALL_RADIUS = 10;
    private final int BALL_XINITIAL = 500;
    private final int BALL_YINITIAL = 400;
    private final int BALL_XVELOCITY = 0;
    private final int BALL_YVELOCITY = 5;

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

        setSceneNodes(new Group());
        setGameSurface(new Scene(getSceneNodes(), GAME_WIDTH, GAME_HEIGHT));
        primaryStage.setScene(getGameSurface());

        makeBallAndPaddle();

    }

    /**
     * Creating the ball and paddle that the game will be played with.
     */
    public void makeBallAndPaddle(){
        makeBall(BALL_XINITIAL, BALL_YINITIAL, BALL_RADIUS, BALL_XVELOCITY, BALL_YVELOCITY);
    }

    /**
     * Updates the sprite that is passed into the method. Each sprite has its own updaet method
     * @param sprite - The sprite to update.
     */
    @Override
    protected void handleUpdate(Sprite sprite){
        sprite.update();
    }

    /**
     * Deals with the collision of two sprite objects
     * @param spriteA - called from checkCollision() method to be compared.
     * @param spriteB - called from checkCollision() method to be compared.
     * @return
     */
    protected boolean handleCollision(Sprite spriteA, Sprite spriteB){
        return false;
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
        // add to all sprite objects in play
        getSpriteManager().addSprites(ball);
        // add sprite's
        getSceneNodes().getChildren().add(0, ball.node);
    }


}

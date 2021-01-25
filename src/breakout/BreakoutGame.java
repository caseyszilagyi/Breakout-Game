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

    private final int GAME_HEIGHT = 800;
    private final int GAME_WIDTH = 1000;

    //Ball Initialization
    private final int BALL_RADIUS = 10;
    private final int BALL_XINITIAL = 500;
    private final int BALL_YINITIAL = 400;
    private final int BALL_XVELOCITY = 0;
    private final int BALL_YVELOCITY = 5;


    public BreakoutGame(int fps, String title){
        super(fps, title);
    }

    public void start(final Stage primaryStage){
        primaryStage.setTitle(getWindowTitle());

        setSceneNodes(new Group());
        setGameSurface(new Scene(getSceneNodes(), GAME_WIDTH, GAME_HEIGHT));
        primaryStage.setScene(getGameSurface());

        makeBall(BALL_XINITIAL, BALL_YINITIAL, BALL_RADIUS, BALL_XVELOCITY, BALL_YVELOCITY);

    }

    @Override
    protected void handleUpdate(Sprite sprite){
        sprite.update();
    }

    protected boolean handleCollision(Sprite spriteA, Sprite spriteB){
        return false;
    }



    public void makeBall(int xPos, int yPos, int radius, int xVel, int yVel){
        Ball ball = new Ball(xPos, yPos, radius, xVel, yVel);
        // add to all sprite objects in play
        getSpriteManager().addSprites(ball);
        // add sprite's
        getSceneNodes().getChildren().add(0, ball.node);
    }


}

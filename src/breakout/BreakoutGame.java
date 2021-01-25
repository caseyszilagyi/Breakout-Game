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

    public BreakoutGame(int fps, String title){
        super(fps, title);
    }

    public void initialize(final Stage primaryStage){

    }
}

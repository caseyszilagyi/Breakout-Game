package breakout;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main driver of the game.
 * @author cdea
 */
public class BreakoutDriver extends Application {

    BreakoutGame breakoutGame = new BreakoutGame(60, "My Breakout Game");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // setup title, scene, stats, controls, and actors.
        breakoutGame.initialize(primaryStage);

        // kick off the game loop
        breakoutGame.beginGameLoop();

        // display window
        primaryStage.show();
    }

}

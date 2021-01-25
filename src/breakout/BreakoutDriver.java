package breakout;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main driver of the game.
 * @author cdea
 */
public class BreakoutDriver extends Application {

    // BreakoutGame instance used to play
    BreakoutGame breakoutGame = new BreakoutGame(60, "My Breakout Game");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the game world by updating the primaryStage. We simply construct the stage of our
     * BreakoutGame object, begin the game loop, and show the stage.
     * @param primaryStage The stage constructed by the platform to be updated
     */
    @Override
    public void start(Stage primaryStage) {
        // setup title, scene, stats, controls, and actors.
        breakoutGame.start(primaryStage);

        // kick off the game loop
        breakoutGame.beginGameLoop();

        // display window
        primaryStage.show();
    }

}

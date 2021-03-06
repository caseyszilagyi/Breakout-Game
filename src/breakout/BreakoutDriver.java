package breakout;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main driver of the game. This class just instantiates an instance of BreakoutGame, and makes
 * the necessary calls to it in order to make the game and star the game loop.
 *
 * @author Casey Szilagyi
 */
public class BreakoutDriver extends Application {

  private BreakoutGame breakoutGame = new BreakoutGame(60, "My Breakout Game");

  /**
   * Launches our JavaFX window
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Initializes the game world by updating the primaryStage. We simply construct the stage of our
   * BreakoutGame object, begin the game loop, and show the stage.
   *
   * @param primaryStage The stage constructed by the platform to be updated
   */
  @Override
  public void start(Stage primaryStage) {
    // setup title, scene, stats, controls, and objects.
    breakoutGame.start(primaryStage);

    // start game loop
    breakoutGame.beginGameLoop();

    // display window
    primaryStage.show();
  }

}

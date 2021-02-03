package breakout;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * Makes all of the different scenes that are needed for the game. These include the before level 1
 * scene, the between levels scene, and the win/loss scenes.
 *
 * @author Casey Szilagyi
 */
public class LevelHandler {

  // The start up and between levels scenes
  Scene startUpScene;
  Scene betweenLevelsScene;
  Scene winScene;
  Scene loseScene;

  // The buttons that have an effect on the state of the game
  private Button startButton;
  private Button betweenLevelsButton;

  //Game size
  private int gameWidth;
  private int gameHeight;

  int currentLevel = 0;

  /**
   * The scenes are made based on the width and height of the game, so it takes in those parameters
   *
   * @param gWidth  Game Width
   * @param gHeight Game Height
   */
  public LevelHandler(int gWidth, int gHeight) {
    gameWidth = gWidth;
    gameHeight = gHeight;
  }

  /**
   * Makes the splash screen that is displayed before the first level
   *
   * @return The scene displayed before the first level
   */
  public Scene makeAndReturnStartUpScreen() {
    BorderPane pane = new BorderPane();
    Button moveOn = new Button("Ok, lets play!");
    startButton = moveOn;
    pane.setBottom(moveOn);
    Text displayMessage = new Text("This is the Breakout Game!\n"
        + "You start with 3 lives, and there are 3 levels\n"
        + "Good power ups are green, bad are red\n"
        + "L: Adds life\n"
        + "R: Resets ball and paddle\n"
        + "1-9: Changes level\n"
        + "B: Makes Bigger Paddle\n "
        + "S: Slows the ball down \n");
    displayMessage.setScaleX(3);
    displayMessage.setScaleY(3);
    pane.setCenter(displayMessage);
    startUpScene = new Scene(pane, gameWidth, gameHeight);
    currentLevel++;
    return startUpScene;
  }

  /**
   * Gets the game start button. This is needed to trigger the change in scenes in the BreakoutGame
   * class
   *
   * @return The button
   */
  public Button getStartButton() {
    return startButton;
  }

  /**
   * Makes the screen that is displayed between levels
   *
   * @return The scene displayed between levels
   */
  public Scene makeAndReturnBetweenLevelsScreen() {
    BorderPane pane = new BorderPane();
    currentLevel++;
    Button moveOn = new Button("Next Level" + Integer.toString(currentLevel));
    pane.setBottom(moveOn);
    Text displayMessage = new Text("Level Completed! Now for Level ");
    displayMessage.setScaleX(3);
    displayMessage.setScaleY(3);
    pane.setCenter(displayMessage);
    betweenLevelsButton = moveOn;
    betweenLevelsScene = new Scene(pane, gameWidth, gameHeight);
    return betweenLevelsScene;
  }

  /**
   * Gets the between levels button. This is needed to trigger the change in scenes in the *
   * BreakoutGame class
   *
   * @return The button
   */
  public Button getBetweenLevelsButton() {
    return betweenLevelsButton;
  }

  /**
   * Makes and returns the message shown when the game is won
   *
   * @return The lost game Scene
   */
  public Scene makeAndReturnWinScene() {
    BorderPane pane = new BorderPane();
    Text displayMessage = new Text("You Win Congratulations!");
    displayMessage.setScaleX(5);
    displayMessage.setScaleY(5);
    pane.setCenter(displayMessage);
    winScene = new Scene(pane, gameWidth, gameHeight);
    return winScene;
  }

  /**
   * Makes and returns the message shown when the game is lost
   *
   * @return The lost game Scene
   */
  public Scene makeAndReturnLoseScene() {
    BorderPane pane = new BorderPane();
    Text displayMessage = new Text("You Lose :(");
    displayMessage.setScaleX(10);
    displayMessage.setScaleY(10);
    pane.setCenter(displayMessage);
    loseScene = new Scene(pane, gameWidth, gameHeight);
    return loseScene;
  }


}

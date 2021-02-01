package breakout;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    /** The level handler class makes the scenes that we see between the levels as well as before the first level.
     * The buttons that advance to the next level have getters that allow them to have functionality in the BreakoutGame
     * class
     * @param gWidth Game Width
     * @param gHeight Game Height
     */
    public LevelHandler(int gWidth, int gHeight){
        gameWidth = gWidth;
        gameHeight = gHeight;
    }

    /** Makes the splash screen that is displayed before the first level
     * @return The scene displayed before the first level
     */
    public Scene makeAndReturnStartUpScreen(){
        BorderPane pane = new BorderPane();
        Button moveOn = new Button("Ok, lets play!");
        startButton = moveOn;
        pane.setBottom(moveOn);
        Text displayMessage = new Text("Test");
        pane.setCenter(displayMessage);
        startUpScene = new Scene(pane, gameWidth, gameHeight);
        currentLevel ++;
        return startUpScene;
    }

    /** Gets the game start button
     * @return The button
     */
    public Button getStartButton(){
        return startButton;
    }

    /** Makes the screen that is displayed between levels
     * @return The scene displayed between levels
     */
    public Scene makeAndReturnBetweenLevelsScreen(){
        BorderPane pane = new BorderPane();
        currentLevel++;
        Button moveOn = new Button("Level Completed! Now for Level " + Integer.toString(currentLevel));
        pane.setBottom(moveOn);
        Text displayMessage = new Text("Test");
        pane.setCenter(displayMessage);
        betweenLevelsButton = moveOn;
        betweenLevelsScene = new Scene(pane, gameWidth, gameHeight);
        return betweenLevelsScene;
    }

    /** Gets the between levels button
     * @return The button
     */
    public Button getBetweenLevelsButton(){
        return betweenLevelsButton;
    }

    /** Makes and returns the message shown when the game is won
     * @return The lost game Scene
     */
    public Scene makeAndReturnWinScene(){
        BorderPane pane = new BorderPane();
        Text displayMessage = new Text("You Win Congratulations!");
        displayMessage.setScaleX(5);
        displayMessage.setScaleY(5);
        pane.setCenter(displayMessage);
        winScene = new Scene(pane, gameWidth, gameHeight);
        return winScene;
    }

    /** Makes and returns the message shown when the game is lost
     * @return The lost game Scene
     */
    public Scene makeAndReturnLoseScene(){
        BorderPane pane = new BorderPane();
        Text displayMessage = new Text("You Lose :(");
        displayMessage.setScaleX(10);
        displayMessage.setScaleY(10);
        pane.setCenter(displayMessage);
        loseScene = new Scene(pane, gameWidth, gameHeight);
        return loseScene;
    }


}

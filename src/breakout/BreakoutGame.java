package breakout;

import java.util.Arrays;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/**
 * The BreakoutGame class is the main class that incorporates all the aspects of other classes in
 * order to run a functioning breakout game. All of the instance variables are the core variables
 * and parts needed for the game to function, as well as instances of other classes that we make
 * method calls to. The core of the game loop is in the game class, but this class overrides a few
 * of the methods and also has other methods that deal with the status of the game such as the lives
 * and level. Additionally, event handlers are built to deal with key input
 *
 * @author Casey Szilagyi
 */
public class BreakoutGame extends Game {

  // game size and positioning of score/lives text
  private static final int GAME_HEIGHT = 800;
  private static final int GAME_WIDTH = 1000;
  private static final int GAME_TEXT_X = 300;
  private static final int GAME_TEXT_Y = 50;

  // GameObjects that we need access to
  private Ball ball;
  private Paddle paddle;
  private Text message;

  // deals with movement of paddle;
  private boolean goRight = false;
  private boolean goLeft = false;

  // game Properties
  private int livesRemaining = 3;
  private int currentScore = 0;
  private int currentLevel = 0; // indicates that game hasn't started.
  private final ArrayList<String> LEVEL_FILES = new ArrayList<String>(
      Arrays.asList("FirstLevel.txt", "SecondLevel.txt", "ThirdLevel.txt"));
  private final double GOOD_POWERUP_PROBABILITY = 0.5;

  // makes a level creator,level handler, power up manager, and random
  private LevelCreator levelCreator = new LevelCreator();
  private LevelHandler levelHandler;
  private PowerUpManager powerUpManager;
  private Random random = new Random();

  // thee various scenes that the game will change between, depending on whether a level is currently being played
  private Scene beforeGameScene;
  private Scene betweenLevelsScene;
  private Scene winScene;
  private Scene loseScene;

  // the game stage
  private Stage gameDisplay;

  /**
   * This constructor calls the super constructor in the "Game" class. It creates the game loop with
   * the given frames per second and window title
   *
   * @param fps   The frames per second that the game will run at
   * @param title The name of the game
   */
  public BreakoutGame(int fps, String title) {
    super(fps, title);
  }

  /**
   * Initializes the game world by updating the primaryStage. This method sets the size of the game,
   * and initializes all of the objects to play the game through the call to makeGameComponents. It
   * also makes all of the other scenes to switch between by the call to makeOtherDisplayScenes, and
   * makes the buttons of these other scenes work so that the game can be played properly by
   * advancing from one level to the next
   *
   * @param primaryStage The stage constructed by the platform to be updated
   */
  public void start(final Stage primaryStage) {
    gameDisplay = primaryStage;
    primaryStage.setTitle(getWindowTitle());

    makeOtherDisplayScenes();
    primaryStage.setScene(beforeGameScene);

    //Setting the buttons in the other scenes to result in a level being played
    levelHandler.getStartButton().setOnAction(e -> makeGameComponents());
    levelHandler.getBetweenLevelsButton().setOnAction(e -> makeGameComponents());

  }

  /**
   * Makes all of the display scenes and sets them to the instance variables. This is so that at the
   * end of each level, there is a screen to display that allows the user to wait before beginning
   * the next level. This also includes the splash screen before the game, which is different from
   * the between levels scene
   */
  public void makeOtherDisplayScenes() {
    levelHandler = new LevelHandler(GAME_WIDTH, GAME_HEIGHT);
    beforeGameScene = levelHandler.makeAndReturnStartUpScreen();
    betweenLevelsScene = levelHandler.makeAndReturnBetweenLevelsScreen();
    winScene = levelHandler.makeAndReturnWinScene();
    loseScene = levelHandler.makeAndReturnLoseScene();
  }


  /**
   * Checks to see if a level is done, if the user is out of lives, or if the ball is in play. Also
   * updates our game message. If the user is out of lives, display the lost message. If the user
   * has completed a level, we either move to the nexts or the user has won.
   */
  @Override
  public void updateGameValues() {
    //If game is being played
    if (currentLevel > 0) {
      //If level is done
      if (checkIfFinishedLevel()) {
        //Check to see if it was final level
        if (currentLevel == LEVEL_FILES.size()) {
          gameDisplay.setScene(winScene);
        } else {
          gameDisplay.setScene(betweenLevelsScene);
          getGameLoop().pause();
        }
      }

      //Checks if game should still be playable
      if (livesRemaining <= 0) {
        gameDisplay.setScene(loseScene);
      }

      checkIfBallIsInPlay();
      message.setText(
          "Lives: " + Integer.toString(livesRemaining) + " Score: " + Integer.toString(currentScore)
              + " Level: " + Integer.toString(currentLevel));
    }
  }

  /**
   * checks if a level is done being played by seeing if there are any bricks remaining in the group
   * of all GameObjects
   *
   * @return True if no bricks
   */
  public boolean checkIfFinishedLevel() {
    for (Object singleObject : getObjectManager().getObjects()) {
      if (singleObject instanceof Brick) {
        return false;
      }
    }
    return true;
  }

  /**
   * Updating a game object. The super method simply calls the update method in the class of the
   * GameObject itself, which results in updating the position with the velocity. This is called for
   * all GameObjects that are present in the game.
   *
   * @param gameObject
   */
  public void updateObject(GameObject gameObject) {
    super.updateObject(gameObject);
  }


  /**
   * Deals with the collision of two GameObjects. Leads to the calling of the collision method of
   * GameObject A, in which actions can be taken depending on the collision. There are conditionals
   * in this collide method to update details that are specific the state of the game. All the other
   * necessary collisions such as ball bouncing are handled in the classes of the GameObjects
   *
   * @param A - called from super.checkCollision() method while looping through all GameObjects.
   * @param B - called from super.checkCollision() while looping through all GameObjects.
   * @return True if they collide
   */
  @Override
  public boolean collide(GameObject A, GameObject B) {
    boolean collide = super.collide(A, B);

    // ball collisions
    if (collide && (B instanceof Ball)) {
      updateScore(A, B);
      updateBallVelocityBasedOnPaddleBounce(A, B);
    }

    // power up collisions
    if (collide && A instanceof Paddle && B instanceof PowerUp) {
      powerUpManager.dealWithPowerUp((PowerUp) B);
    }

    return collide;
  }

  /**
   * Checks whether the ball collided with a brick in order to update the score. Different bricks
   * result in different numbers of points.
   *
   * @param A The brick GameObject
   * @param B The ball GameObject
   */
  public void updateScore(GameObject A, GameObject B) {
    if (A instanceof Brick) {
      currentScore += 100;
    }

    //Checks if it is a row destroy brick
    if (A instanceof RowDestroyBrick) {
      destroyRow(((RowDestroyBrick) A).gameObject.getY());
      currentScore += 900;
    }
    //Checks if it is a power up brick
    else if (A instanceof DropPowerUpBrick) {
      Rectangle currentBrick = ((DropPowerUpBrick) A).gameObject;
      dropPowerUp(currentBrick.getX() + currentBrick.getWidth() / 2,
          currentBrick.getY() + currentBrick.getHeight() / 2);
      currentScore += 400;
    }
  }

  /**
   * Changes the speed and direction of the ball based on what part of the paddle it hits
   *
   * @param A The Ball/Paddle
   * @param B The Ball/Paddle
   */
  public void updateBallVelocityBasedOnPaddleBounce(GameObject A, GameObject B) {
    if (A instanceof Paddle || B instanceof Paddle) {
      double paddleCenter =
          paddle.gameObject.getX() + paddle.node.getTranslateX() + paddle.gameObject.getWidth() / 2;
      double diffOfBallCenterAndPaddleCenter =
          (ball.gameObject.getCenterX() + ball.gameObject.getTranslateX()) - paddleCenter;
      ball.XVelocity = Math.sqrt(Math.abs(diffOfBallCenterAndPaddleCenter)) *
          diffOfBallCenterAndPaddleCenter / Math.abs(diffOfBallCenterAndPaddleCenter);
    }
  }

  /**
   * Destroys the row specified by the Y position of the RowDestroyBrick by looping through all
   * GameObjects and seeing if they have the same Y position
   *
   * @param yPos The y value of the row that will be destroyed.
   */
  public void destroyRow(double yPos) {
    for (Object singleObject : getObjectManager().getObjects()) {
      if (singleObject instanceof Brick && ((Brick) singleObject).gameObject.getY() == yPos) {
        ((Brick) singleObject).isDead = true;
      }
    }
  }

  /**
   * Makes a power up that falls in the location of the destroyed brick
   *
   * @param xPos The x position of the power up that is being made
   * @param yPos The y position of the power up that is being made
   */
  public void dropPowerUp(double xPos, double yPos) {
    PowerUp powerUp;
    if (random.nextDouble() > GOOD_POWERUP_PROBABILITY) {
      powerUp = new BadPowerUp(xPos, yPos);
    } else {
      powerUp = new GoodPowerUp(xPos, yPos);
    }

    // add to all objects in play
    getObjectManager().addObjects(powerUp);
    // add node to group of nodes for graphics
    getNodes().getChildren().add(0, powerUp.node);
  }

  /**
   * checks whether the ball is still in play. If not, we take away a life.
   */
  public void checkIfBallIsInPlay() {
    if (currentLevel > 0 && ball.node.getTranslateY() + ball.node.getLayoutY() > GAME_HEIGHT) {
      ball.isDead = true;
      ball = levelCreator.makeBall();
      getObjectManager().addObjects(ball);
      //add node to group of nodes for graphics
      getNodes().getChildren().add(0, ball.node);
      livesRemaining--;
    }
  }

  /**
   * Makes all of the objects that make up the game through the call to the levelCreator. Also makes
   * the EventHandlers used to play the game and the score/lives/level message
   */
  public void makeGameComponents() {
    setNodes(new Group());
    setGameSurface(new Scene(getNodes(), GAME_WIDTH, GAME_HEIGHT));

    // Reading in the current level
    levelCreator.readNewFile(LEVEL_FILES.get(currentLevel));
    currentLevel++;
    // Making the game components
    levelCreator.makeGameComponents();

    makeAllObjects();
    makePaddleEventHandlers();
    makeGameScoreAndLives();

    powerUpManager = new PowerUpManager(paddle, ball);

    gameDisplay.setScene(getGameSurface());
    getGameLoop().play();
  }

  /**
   * Gets all of the gameObjects that were created in the LevelCreator and adds them to the
   * ObjectManager for collision management, and the set of nodes for display
   */
  public void makeAllObjects() {
    ArrayList<GameObject> gameComponent = levelCreator.getGameComponents();
    getObjectManager().getObjects().clear();
    getNodes().getChildren().clear();
    for (int i = 0; i < gameComponent.size(); i++) {
      getObjectManager().addObjects(gameComponent.get(i));
      getNodes().getChildren().add(0, gameComponent.get(i).node);

      //Ball is needed for collision detection to make sure it is still in play
      if (gameComponent.get(i) instanceof Ball) {
        ball = (Ball) gameComponent.get(i);
      }

      //Paddle is needed for event handlers
      if (gameComponent.get(i) instanceof Paddle) {
        paddle = (Paddle) gameComponent.get(i);
      }
    }
  }

  /**
   * Initializes the display of the game score and lives
   */
  public void makeGameScoreAndLives() {
    message = new Text(GAME_TEXT_X, GAME_TEXT_Y, "Lives: " + Integer.toString(livesRemaining)
        + " Score: " + Integer.toString(currentScore));
    message.setScaleX(5);
    message.setScaleY(5);
    getNodes().getChildren().add(0, message);
  }

  //Below this are all of the components to deal with the movement of the paddle, as well as the cheat codes

  /**
   * Deals with the creation of event handlers. One to deal with key presses, the other to deal with
   * key releases. Also an animator to make paddle movement smoother
   */
  private void makePaddleEventHandlers() {
    makeKeyPressedHandler();
    makeKeyRelesedHandler();
    makePaddleAnimation();
  }

  /**
   * Makes the handler that deals with right/left key presses, as well as the cheat code key
   * presses
   */
  private void makeKeyPressedHandler() {
    // Makes EventHandler that deals with input from left/right keys to move paddle;
    EventHandler paddleMove = new EventHandler() {
      @Override
      public void handle(Event event) {
        if (event instanceof KeyEvent) {
          keyPressResponse((KeyEvent) event);
        }
      }
    };

    // Adds EventHandler to our game
    getGameSurface().setOnKeyPressed(paddleMove);
  }

  /**
   * Makes the handler that deals with right/left key releases
   */
  private void makeKeyRelesedHandler() {
    // Makes EventHandler to deal with the release of the left/right key
    EventHandler paddleStop = new EventHandler() {
      @Override
      public void handle(Event event) {
        if (event instanceof KeyEvent) {
          keyReleaseResponse((KeyEvent) event);
        }
      }
    };

    // Adds EventHandler to our game
    getGameSurface().setOnKeyReleased(paddleStop);
  }

  /**
   * Makes the animation timer that allows the paddle to move smoothly
   */
  private void makePaddleAnimation() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (goRight) {
          paddle.moveRight();
        } else if (goLeft) {
          paddle.moveLeft();
        }
      }
    };

    timer.start();
  }


  /**
   * Updates the boolean values that determine if the left/right key is currently being pressed, as
   * well as implementing all of the cheat codes. This is called as the key pressed event handler
   *
   * @param event The key that is pressed
   */
  public void keyPressResponse(KeyEvent event) {
    if (event.getCode() == KeyCode.LEFT) {
      goLeft = true;
    } else if (event.getCode() == KeyCode.RIGHT) {
      goRight = true;
    } else if (event.getCode() == KeyCode.L) {
      livesRemaining++;
    } else if (event.getCode() == KeyCode.R) {
      ball.isDead = true;
      paddle.isDead = true;
      paddle = levelCreator.makePaddle();
      ball = levelCreator.makeBall();
      getObjectManager().addObjects(paddle, ball);
      getNodes().getChildren().add(0, ball.node);
      getNodes().getChildren().add(0, paddle.node);
    } else if (event.getCode().isDigitKey()) {
      int level = '9' - event.getCode().getChar().charAt(0);
      if (level >= LEVEL_FILES.size()) {
        currentLevel = LEVEL_FILES.size() - 1;
        makeGameComponents();
      } else {
        currentLevel = level;
        makeGameComponents();
      }
    } else if (event.getCode() == KeyCode.S) {
      ball.XVelocity /= 1.5;
      ball.YVelocity /= 1.5;
    } else if (event.getCode() == KeyCode.B) {
      paddle.gameObject.setWidth(2000);
      paddle.gameObject.setX(-500);
    }
  }

  /**
   * Updates the boolean values that determine if the left/right key is currently being released.
   * This is called as part of the key released handler.
   *
   * @param event The key that is released
   */
  public void keyReleaseResponse(KeyEvent event) {
    if (event.getCode() == KeyCode.LEFT) {
      goLeft = false;
    } else if (event.getCode() == KeyCode.RIGHT) {
      goRight = false;
    }
  }

}

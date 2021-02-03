package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * The game class sets up a generic game loop that can be used to make a game. The game loop
 * consists of updating game values, checking collisions, and updating locations of objects. An
 * ObjectManager is also created to deal with all of the objects that are a part of the game.
 *
 * @author Casey Szilagyi
 */
public abstract class Game {

  // scene that the game is played on
  private Scene gameSurface;
  // nodes to display
  private Group allNodes;
  // the game loop that keeps running as long as the game is being played
  private Timeline gameLoop;


  private final int FRAMES_PER_SECOND;
  private final String WINDOW_TITLE;

  // object manager to deal with collisions
  private final ObjectManager objectManager = new ObjectManager();

  /**
   * Initializes the game. Simply sets the frames per second and the window title, and then makes
   * the game loop in a separate function
   *
   * @param fps         The frames per second of the game
   * @param windowTitle The title that we want the window to have
   */
  public Game(int fps, String windowTitle) {
    FRAMES_PER_SECOND = fps;
    WINDOW_TITLE = windowTitle;
    makeGameLoop();
  }

  /**
   * Sets up the game loop by making an event handler and keyframe with that event handler. This is
   * made into a timeline and is set to the global gameloop variable
   */
  public void makeGameLoop() {
    final Duration oneFrameAmt = Duration.millis(1000 / getFPS());
    EventHandler handle = event -> {
      //Updates all of the variables that are keeping track of the game status
      updateGameValues();
      //Moves all of our objects
      updateAllObjects();
      //Checks for collisions
      checkCollisions();
      //Gets rid of objects that are no longer alive
      cleanupDeadObjects();
    };
    //Setting the keyframe with a duration and our event handler
    final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, handle);

    //Makes the timeline
    Timeline timeline = new Timeline(oneFrame);
    // allows for enough cycles for an hour of gameplay
    timeline.setCycleCount(216000);
    setGameLoop(timeline);
  }

  /**
   * Initializes the game. This is abstract because any details about initialization of the game
   * should be made in the class of that game, not in the generic game class.
   */
  public abstract void start(final Stage primaryStage);

  /**
   * Starts the gameloop timeline
   */
  public void beginGameLoop() {
    gameLoop.play();
  }

  //The following are parts of the game loop. These methods get called every time we update the frame

  /**
   * Calls the updateObject method for each object
   */
  public void updateAllObjects() {
    for (Object gameObject : objectManager.getObjects()) {
      updateObject((GameObject) gameObject);
    }
  }

  /**
   * Update method called for each object. Calls the update method within the specific object class
   */
  public void updateObject(GameObject gameObject) {
    gameObject.update();
  }

  /**
   * Checks if any 2 objects collide. If they do, we stop checking for more collisions for the
   * current item. This assumes that we don't want any item to have more than 1 collision happening
   * at the time, which may not be the case but will make the code run faster.
   */
  public void checkCollisions() {
    //Reset our list of collisions to check
    objectManager.resetCollisionsToCheck();
    for (Object gameObjectA : objectManager.getCollisionsToCheck()) {
      for (Object gameObjectB : objectManager.getObjects()) {
        if (gameObjectA != gameObjectB && collide((GameObject) gameObjectA,
            (GameObject) gameObjectB)) {
          // can't collide with more than 1 GameObject
          break;
        }
      }
    }
  }

  /**
   * Calls the collide method contained in the class of GameObject A, which checks to see if the
   * items are colliding
   *
   * @param A The GameObject, the collide method from this object is called
   * @param B The other GameObject.
   * @return
   */
  public boolean collide(GameObject A, GameObject B) {
    return A.collide(B);
  }

  /**
   * Calls the cleanup method in ObjectManager, which removes objects that are considered dead
   */
  public void cleanupDeadObjects() {
    // Checks for dead objects using the object manager
    objectManager.checkForDead();

    // Gets rid of the dead object nodes so they won't be displayed
    for (Object gameObject : objectManager.getObjectsToRemove()) {
      removeObjectDisplay((GameObject) gameObject);
    }

    // Removes the dead objects from the object manager
    objectManager.cleanUp();
  }

  /**
   * Removes the display of this gameObject
   */
  public void removeObjectDisplay(GameObject object) {
    getNodes().getChildren().remove(object.node);
  }

  /**
   * Update values that are central to the status of the game, such as lives and score
   */
  public abstract void updateGameValues();

  //Below are all of the getters/setters of the class variables

  /**
   * Gets the Window title
   *
   * @return The window title
   */
  public String getWindowTitle() {
    return WINDOW_TITLE;
  }

  /**
   * Gets the frames per second
   *
   * @return the frames per second
   */
  public int getFPS() {
    return FRAMES_PER_SECOND;
  }

  /**
   * Gets the object manager, which deals with collisions and object cleanup
   *
   * @return the object manager
   */
  public ObjectManager getObjectManager() {
    return objectManager;
  }

  /**
   * Sets the scene that is used to play the game on
   */
  public void setGameSurface(Scene userSurface) {
    gameSurface = userSurface;
  }

  /**
   * Gets the scene that is used to play the game on
   *
   * @return the scene
   */
  public Scene getGameSurface() {
    return gameSurface;
  }

  /**
   * Sets the collection of nodes used to render the game
   */
  public void setNodes(Group nodes) {
    allNodes = nodes;
  }

  /**
   * Gets the collection of nodes used to render the game
   *
   * @return The group of nodes
   */
  public Group getNodes() {
    return allNodes;
  }

  /**
   * Sets the game loop to what was made in the makeGameLoop Class. Updates object movement, checks
   * for collisions, and gets rid of our dead objects
   */
  public void setGameLoop(Timeline timeline) {
    gameLoop = timeline;
  }

  /**
   * Gets the game loop that is currently being used to play the game
   *
   * @return the game loop
   */
  public Timeline getGameLoop() {
    return gameLoop;
  }
}

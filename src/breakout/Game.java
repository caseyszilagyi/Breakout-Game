package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.stage.Stage;

public abstract class Game{
    /** The Scene that the game is played on */
    private Scene gameSurface;
    /** All the nodes to be displayed */
    private Group allNodes;
    /** The GameLoop (keeps running) */
    private Timeline gameLoop;

    /** Frames per second */
    private final int FRAMES_PER_SECOND;
    /** Title */
    private final String WINDOW_TITLE;

    /** Object Manager */
    private final ObjectManager objectManager = new ObjectManager();

    /**
     * Initializes the game. Simply sets the frames per second and the window title,
     * and then makes the game loop in a separate function
     * @param fps The frames per second of the game
     * @param windowTitle The title that we want the window to have
     */
    public Game(int fps, String windowTitle){
        FRAMES_PER_SECOND = fps;
        WINDOW_TITLE = windowTitle;
        makeGameLoop();
    }

    /**
     * Sets up the game loop by making an event handler and keyframe with that event handler.
     * This is made into a timeline and is set to the global gameloop variable
     */
    public void makeGameLoop(){
        final Duration oneFrameAmt = Duration.millis(1000/getFPS());
        EventHandler handle = event -> {
            //Moves all of our objects
            updateAllObjects();
            //Checks for collisions
            checkCollisions();
            //Gets rid of objects that are no longer alive
            cleanupDeadObjects();
            //Updates all of the variables that are keeping track of the game status
            updateGameValues();
        };
        //Setting the keyframe with a duration and our event handler
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, handle);

        //Makes the timeline
        Timeline timeline = new Timeline(oneFrame);
        // allows for enough cycles for an hour of gameplay
        timeline.setCycleCount(216000);
        setGameLoop(timeline);
    }

    /** Initializes the game */
    public abstract void start(final Stage primaryStage);

    /** Starts the timeline */
    public void beginGameLoop(){ gameLoop.play(); }


    //The following are parts of the game loop. These methods get called every time we update the frame

    /** Calls the updateObject method for each object */
    public void updateAllObjects(){
        for(Object gameObject:objectManager.getObjects()){
            updateObject((GameObject) gameObject);
        }
    }
    /** Update method called for each object. Calls the update method within the specific object class */
    public void updateObject(GameObject gameObject){
        gameObject.update();
    }

    /** Checks if any 2 objects collide. If they do, we stop checking for the current item */
    public void checkCollisions(){
        //Reset our list of collisions to check
        objectManager.resetCollisionsToCheck();
        for (Object gameObjectA:objectManager.getCollisionsToCheck()){
            for (Object gameObjectB:objectManager.getObjects()){
                if (gameObjectA != gameObjectB && collide((GameObject) gameObjectA, (GameObject) gameObjectB) ) {
                    //Break once a collision is detected. GameObject therefore can't collide with more than 1 object
                    break;
                }
            }
        }
    }

    /** Calls the collide method contained in the GameObject class */
    public boolean collide(GameObject A, GameObject B){
        return A.collide(B);
    }

    /** Calls the cleanup method in ObjectManager, which removes objects that are no longer needed */
    public void cleanupDeadObjects(){
        // Checks for dead objects using the object manager
        objectManager.checkForDead();

        // Gets rid of the dead object nodes so they won't be displayed
        for(Object gameObject: objectManager.getObjectsToRemove()){
            removeObjectDisplay((GameObject) gameObject);
        }

        // Removes the dead objects from the object manager
        objectManager.cleanUp();
    }

    /** Removes the display of this gameObject */
    public void removeObjectDisplay(GameObject object){
        getNodes().getChildren().remove(object.node);
    }

    public abstract void updateGameValues();


    //Below are all of the getters/setters of the class variables

    /** Gets the Window title */
    public String getWindowTitle(){
        return WINDOW_TITLE;
    }

    /** Gets the frames per second */
    public int getFPS(){
        return FRAMES_PER_SECOND;
    }

    /** Gets the object manager, which deals with collisions and object cleanup */
    public ObjectManager getObjectManager(){
        return objectManager;
    }

    /** Sets the scene that is used to play the game on */
    public void setGameSurface(Scene userSurface){
        gameSurface = userSurface;
    }

    /** Gets the scene that is used to play the game on */
    public Scene getGameSurface(){
        return gameSurface;
    }

    /** Sets the collection of nodes used to render the game */
    public void setNodes(Group nodes){
        allNodes = nodes;
    }

    /** Gets the collection of nodes used to render the game */
    public Group getNodes(){
        return allNodes;
    }

    /** Sets the game loop to what was made in the makeGameLoop Class.
     * Updates object movement, checks for collisions, and gets rid of our dead objects */
    public void setGameLoop(Timeline timeline){
        gameLoop = timeline;
    }

    /** Gets the game loop that is currently being used to play the game */
    public Timeline getGameLoop(){
        return gameLoop;
    }
}

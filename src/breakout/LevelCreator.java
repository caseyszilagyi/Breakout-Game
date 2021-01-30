package breakout;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LevelCreator {
    Scanner scanner;
    private final static ArrayList ALL_GAME_OBJECTS = new ArrayList<GameObject>();


    private static final int GAME_HEIGHT = 800;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_BORDER_HEIGHT = 99;

    // Ball Properties
    public Ball ball;
    private final int BALL_RADIUS = 5;
    private final int BALL_XINITIAL = 500;
    private final int BALL_YINITIAL = 400;
    private final int BALL_XVELOCITY = 1;
    private final int BALL_YVELOCITY = 10;

    // Paddle Properties
    public Paddle paddle;
    private final int PADDLE_XINITIAL = 450;
    private final int PADDLE_YINITIAL = 790;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 20;
    private final int PADDLE_SPEED = 10;
    // Deals with movement of paddle;
    private boolean goRight = false;
    private boolean goLeft = false;

    // Brick Properties (that are common among all bricks);
    private final int BRICK_WIDTH = 99;
    private final int BRICK_HEIGHT = 20;
    private final int BRICK_GAP = 1;


    // All details necessary for the loop to function so that we have bricks being drawn in the right places
    private int brickWidth;
    private int brickHeight;
    private int gameWidth;
    private int brickGap;
    private int brickStart;

    // Current position of the brick we are designing. These get incremented in the makeBricks loop
    private int xPos;
    private int yPos;

    /** The LevelCreator class has everything required to make a level */
    public LevelCreator(int bWidth, int bHeight, int bGap, int gWidth, int bStart){
        brickWidth = bWidth;
        brickHeight = bHeight;
        brickGap = bGap;
        gameWidth = gWidth;
        brickStart = bStart + 1;
    }

    /** Method to change the file that the scanner is reading.
     * The scanner is set to read each character individually.
     * @param fileName The name of the file, which is contained in the data folder.
     */
    public void readNewFile(String fileName){
        File file = new File (fileName);
        scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
        scanner.useDelimiter("");
    }

    /** Method to create all the bricks needed to play our game. */
    public void makeBricks(){
        xPos = 0;
        yPos = brickStart;
        while(scanner.hasNext()){
            makeSingleBrick(scanner.next().charAt(0));
            //Moves the x location along the row
            xPos += (brickWidth + brickGap);
            //If next brick would go off the screen
            if(xPos + brickWidth > gameWidth){
                xPos = 0;
                yPos += (brickHeight+brickGap);
            }
        }
    }

    /**
     * Method to make a single brick in our game
     * @param brickCode The character read in from the text file indicating the type of brick to make
     */
    public void makeSingleBrick(char brickCode){
        //A regular brick. Takes between 1 and 9 hits to destroy
        if(brickCode - '0' > 0 && brickCode - '0' < 10){
            ALL_GAME_OBJECTS.add(new MultiHitBrick(xPos, yPos, brickWidth, brickHeight, brickCode - '0'));
        }
        //A row destroy brick. Destroys the entire row it is a part of.
        else if(brickCode - 'r' == 0){
           ALL_GAME_OBJECTS.add(new RowDestroyBrick(xPos, yPos, brickWidth, brickHeight));
        }
        //A power up brick. Drops a power up when destroyed
        else if(brickCode - 'p' == 0){
            ALL_GAME_OBJECTS.add(new DropPowerUpBrick(xPos, yPos, brickWidth, brickHeight));
        }

    }


    /**
     * Makes the game border so that collision with the edge is easier. The border is just a GameObject
     * so that the game engine can deal with these collisions as they do every other collision.
     */
    public void makeGameBorder(){
        ArrayList<GameObject> borders = new ArrayList<GameObject>();
        borders.add(new Border(-1, 0, 1, GAME_HEIGHT)); //left
        borders.add(new Border(GAME_WIDTH, 0, 1, GAME_HEIGHT)); //right
        borders.add(new Border(0, GAME_BORDER_HEIGHT, GAME_WIDTH, 1)); //top

        //Adding the borders to necessary groups
        for(int i = 0; i<borders.size(); i++){
            // add to all objects in play
            ALL_GAME_OBJECTS.add(borders.get(i));
        }
    }

    /**
     * Initializes the ball object
     */
    public void makeBall(){
        ball = new Ball(BALL_XINITIAL, BALL_YINITIAL, BALL_RADIUS, BALL_XVELOCITY, BALL_YVELOCITY);
        // add to all objects in play for collision detection
        ALL_GAME_OBJECTS.add(ball);
    }

    /** Method to make the paddle and add it to our ObjectManager and GameNodes to be rendered.
     */
    public void makePaddle(){
        paddle = new Paddle(PADDLE_XINITIAL, PADDLE_YINITIAL, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED);
        // add to all objects in play
        ALL_GAME_OBJECTS.add(paddle);
    }


    public ArrayList getGameComponents(){
        return ALL_GAME_OBJECTS;
    }



}

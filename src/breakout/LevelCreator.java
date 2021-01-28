package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LevelCreator {
    Scanner scanner;
    private final static ArrayList BRICKS = new ArrayList<GameObject>();

    // All details necessary for the loop to function so that we have bricks being drawn in the right places
    private int brickWidth;
    private int brickHeight;
    private int gameWidth;
    private int brickGap;
    private int bricksPerRow;

    // Current position of the brick we are designing. These get incremented in the makeBricks loop
    private int xPos;
    private int yPos;

    /** The LevelCreator class has everything required to make a level */
    public LevelCreator(int bWidth, int bHeight, int bGap, int gWidth){
        brickWidth = bWidth;
        brickHeight = bHeight;
        brickGap = bGap;
        gameWidth = gWidth;
        bricksPerRow =  gameWidth / (brickWidth + brickGap);
    }

    /** Method to change the file that the scanner is reading.
     * The scanner is set to read each character individually.
     * @param fileName The name of the file, which is contained in the data folder.
     */
    public void readNewFile(String fileName){
        File file = new File (fileName);
        scanner = new Scanner(fileName);
        scanner.useDelimiter("");
    }

    /** Method to create all the bricks needed to play our game. */
    public void makeBricks(){
        xPos = 0;
        yPos = 0;
        while(scanner.hasNext()){
            makeSingleBrick(scanner.next());
            //Moves the x location along the row
            xPos += (brickWidth + brickGap);
            //If next brick would go off the screen
            if(xPos + brickWidth > gameWidth){
                xPos = 0;
                yPos += (brickHeight+brickGap);
            }
        }
    }

    public void makeSingleBrick(String brickCode){
        if(brickCode.equals("1")){
            BRICKS.add(new BasicBrick(xPos, yPos, brickWidth, brickHeight));
        }
    }

    public ArrayList getBricks(){
        return BRICKS;
    }



}

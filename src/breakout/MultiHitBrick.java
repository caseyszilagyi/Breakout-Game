package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class MultiHitBrick extends Brick{
    private int health;

    // Loading in all of the images in the form of ImagePatterns so that the bricks are updated when health is lost
    ImagePattern health1 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("1HealthBrick.png")));
    ImagePattern health2 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("2HealthBrick.png")));
    ImagePattern health3 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("3HealthBrick.png")));
    ImagePattern health4 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("4HealthBrick.png")));
    ImagePattern health5 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("5HealthBrick.png")));
    ImagePattern health6 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("6HealthBrick.png")));
    ImagePattern health7 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("7HealthBrick.png")));
    ImagePattern health8 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("8HealthBrick.png")));
    ImagePattern health9 = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("9HealthBrick.png")));

    /**
     * This is the most simple brick. It takes anywhere from 1 to 9 hits to destroy
     * @param startX Starting x position of the rectangle
     * @param startY Starting y position of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting height of the rectangle
     * @param userHealth The health of the brick
     */
    public MultiHitBrick(int startX, int startY, int width, int height, int userHealth){
        super(startX, startY, width, height, 0, 0);
        health = userHealth;
        updateColor();
    }

    /**
     * This brick doesn't move, so no need to update the velocity.
     * However, there is a color scheme for the brick health so this method will update the color
     */
    public void update(){
        updateColor();
    }

    /** Updates the color of the brick */
    public void updateColor(){
        switch(health){
            case 1: gameObject.setFill(health1); break;
            case 2: gameObject.setFill(health2); break;
            case 3: gameObject.setFill(health3); break;
            case 4: gameObject.setFill(health4); break;
            case 5: gameObject.setFill(health5); break;
            case 6: gameObject.setFill(health6); break;
            case 7: gameObject.setFill(health7); break;
            case 8: gameObject.setFill(health8); break;
            case 9: gameObject.setFill(health9); break;
        }
    }

    /**
     * Did this GameObject collide into another one.
     * The collide method for this class only checks for collision with the ball
     * It updates the health if it did, and makes the object dead if the health is 0
     * @param other - The other object.
     * @return True if it did
     */
    public boolean collide(GameObject other){
        // Only update health if collides with ball
        if(other instanceof Ball && super.collide(other)){
            health--;
            if(health == 0){
                isDead = true;
            }
        }
        return false;
    }

}

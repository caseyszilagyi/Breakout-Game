package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class BadPowerUp extends PowerUp{
    ImagePattern powerUpImage = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("BadPowerUp.png")));
    /**
     * The powerup class is what is created whenever a power up brick is destroyed.
     * @param xPos Starting x position of the circle
     * @param yPos Starting y position of the circle
     */
    public BadPowerUp(double xPos, double yPos){
        super((int) xPos, (int) yPos);
        gameObject.setFill(powerUpImage);
    }

    /**
     * Updates the position of the circle. This is the most basic movement.
     */
    @Override
    public void update(){
        super.update();
    }

    /**
     * Checks to see if it collided with the paddle. If so, returns true.
     * @param other - The other GameObject.
     * @return True if it did
     */
    public boolean collide(GameObject other){
        return super.collide(other);
    }

}
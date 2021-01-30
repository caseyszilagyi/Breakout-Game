package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class PowerUp extends RectangleGameObject{
    ImagePattern powerUpImage = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("PowerUp.png")));
    /**
     * The powerup class is what is created whenever a power up brick is destroyed.
     * @param xPos Starting x position of the circle
     * @param yPos Starting y position of the circle
     */
    public PowerUp(double xPos, double yPos){
        super((int) xPos, (int) yPos, 20, 20,  0, 3);
        gameObject.setFill(powerUpImage);
    }

    /**
     * Updates the position of the circle. This is the most basic movement, anything beyond this is implemented
     * in the subclasses.
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
        if(other instanceof Paddle && super.collide(other)){
            isDead = true;
            return true;
        }
        return false;
    }

}

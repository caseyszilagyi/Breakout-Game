package breakout;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DropPowerUpBrick extends Brick{
    ImagePattern powerUpBrick = new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("PowerUpBrick.png")));

    /**
     * This brick drops a powerup whenever it is destroyed. The type of powerup is not visible to the user
     * @param startX Starting x position of the rectangle
     * @param startY Starting y position of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting height of the rectangle
     */
    public DropPowerUpBrick(int startX, int startY, int width, int height){
        super(startX, startY, width, height, 0, 0);
        gameObject.setFill(powerUpBrick);
    }

    /**
     * Only returns true if the ball collided with this brick
     * @param other - The other object.
     * @return Whether or not they collided
     */
    public boolean collide(GameObject other){
        if(other instanceof Ball && super.collide(other)){
            isDead = true;
            return true;
        }
        return false;
    }
}

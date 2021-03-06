package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This type brick drops a powerup whenever it is destroyed. It has 1 health and the chances of a
 * good/bad power up being dropped are specified in the PowerUpManager class
 *
 * @author Casey Szilagyii
 */
public class DropPowerUpBrick extends Brick {

  ImagePattern powerUpBrick = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("PowerUpBrick.png")));

  /**
   * Makes a brick of the given position and width/height. Also sets the fill to the provided image
   * above
   *
   * @param startX Starting x position of the brick
   * @param startY Starting y position of the brick
   * @param width  Starting width of the brick
   * @param height Starting height of the brick
   */
  public DropPowerUpBrick(int startX, int startY, int width, int height) {
    super(startX, startY, width, height, 0, 0);
    gameObject.setFill(powerUpBrick);
  }

  /**
   * only returns true if the ball collided with this brick. This type of brick doesn't need to
   * detect any other types of collisions.
   *
   * @param other - The other object.
   * @return Whether or not they collided
   */
  public boolean collide(GameObject other) {
    if (other instanceof Ball && super.collide(other)) {
      isDead = true;
      return true;
    }
    return false;
  }
}

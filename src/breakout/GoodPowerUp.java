package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This is the good powerup class. It is identical to the bad powerup class, just displays a
 * different image.
 *
 * @author Casey Szilagyi
 */
public class GoodPowerUp extends PowerUp {

  ImagePattern powerUpImage = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("GoodPowerUp.png")));

  /**
   * Created when a power up brick is destroyed, and if the random number generator decides that a
   * good power up should be made.
   *
   * @param xPos Starting x position of the power up
   * @param yPos Starting y position of the power up
   */
  public GoodPowerUp(double xPos, double yPos) {
    super((int) xPos, (int) yPos);
    gameObject.setFill(powerUpImage);
  }

  /**
   * Updates the position of the powerUp. This just changes the position based on the velocity using
   * the setTranslate methood.
   */
  @Override
  public void update() {
    super.update();
  }

  /**
   * Checks to see if the powerup collided with the paddle. If so, returns true. Otherwise, it
   * returns false.
   *
   * @param other - The other GameObject.
   * @return True if it collided with the paddle.
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }
}

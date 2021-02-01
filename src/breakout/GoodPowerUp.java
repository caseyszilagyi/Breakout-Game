package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * @author Casey Szilagyi
 */
public class GoodPowerUp extends PowerUp {

  ImagePattern powerUpImage = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("GoodPowerUp.png")));

  /**
   * Created when a power up brick is destroyed
   *
   * @param xPos Starting x position of the power up
   * @param yPos Starting y position of the power up
   */
  public GoodPowerUp(double xPos, double yPos) {
    super((int) xPos, (int) yPos);
    gameObject.setFill(powerUpImage);
  }

  /**
   * Updates the position of the powerUp. Just changes the position based on the velocity
   */
  @Override
  public void update() {
    super.update();
  }

  /**
   * Checks to see if it collided with the paddle. If so, returns true.
   *
   * @param other - The other GameObject.
   * @return True if it did
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }
}

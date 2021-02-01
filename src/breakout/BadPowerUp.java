package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * @author Casey Szilagyi
 */
public class BadPowerUp extends PowerUp {

  private ImagePattern powerUpImage = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("BadPowerUp.png")));

  /**
   * created whenever a power up brick is destroyed.
   *
   * @param xPos starting x position of the powerup
   * @param yPos starting y position of the powerup
   */
  public BadPowerUp(double xPos, double yPos) {
    super((int) xPos, (int) yPos);
    gameObject.setFill(powerUpImage);
  }

  /**
   * Updates the position of the powerup by using the getTranslate methods and the velocity of the
   * GameObject
   */
  @Override
  public void update() {
    super.update();
  }

  /**
   * Checks to see if the powerup collided with the paddle. If so, it returns true.
   *
   * @param other - The other GameObject.
   * @return True if it did
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }

}
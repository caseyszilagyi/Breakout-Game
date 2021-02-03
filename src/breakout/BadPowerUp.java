package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Created whenever a power up brick is destroyed. The location is determined by the game, but it is
 * meant to be created at the location of the destroyed brick with a downwards velocity in order to
 * be caught by the paddle.
 *
 * @author Casey Szilagyi
 */
public class BadPowerUp extends PowerUp {

  private ImagePattern powerUpImage = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("BadPowerUp.png")));

  /**
   * The super constructor makes a circle at the given location, and sets the fill to the image
   * linked above.
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
   * Checks to see if the powerup collided with the paddle. If so, it returns true. Otherwise, it
   * returns false because the powerup collisioins with any other type of GameObject shouldn't alter
   * the state of the game.
   *
   * @param other - The other GameObject.
   * @return True if it collided with the paddle, false otherwise.
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }

}
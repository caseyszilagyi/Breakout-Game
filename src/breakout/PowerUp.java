package breakout;


/**
 * @author Casey Szilagyi
 */
public class PowerUp extends RectangleGameObject {

  /**
   * What is created whenever a power up brick is destroyed.
   *
   * @param xPos Starting x position of the powerup
   * @param yPos Starting y position of the powerup
   */
  public PowerUp(double xPos, double yPos) {
    super((int) xPos, (int) yPos, 20, 20, 0, 3);
  }

  /**
   * Updates the position of the powerup by changing the position based on the velocity
   */
  @Override
  public void update() {
    super.update();
  }

  /**
   * Checks to see if the powerup collided with the paddle. If so, returns true.
   *
   * @param other - The other GameObject.
   * @return True if it did
   */
  public boolean collide(GameObject other) {
    if (other instanceof Paddle && super.collide(other)) {
      isDead = true;
      return true;
    }
    return false;
  }

}

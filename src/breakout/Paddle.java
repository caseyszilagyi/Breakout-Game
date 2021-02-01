package breakout;

/**
 * @author Casey Szilagyii
 */
public class Paddle extends RectangleGameObject {

  private int paddleSpeed = 0;

  /**
   * Constructor to make the paddle GameObject
   *
   * @param startX Starting X position
   * @param startY Starting Y position
   * @param width  Paddle Width
   * @param height Paddle Height
   */
  public Paddle(int startX, int startY, int width, int height, int speed) {
    super(startX, startY, width, height, 0, 0);
    paddleSpeed = speed;
  }

  /**
   * Changes the velocity
   */
  public void update() {
    super.update();
  }

  /**
   * Moves the paddle to the left
   */
  public void moveLeft() {
    node.setTranslateX(node.getTranslateX() - paddleSpeed);
  }

  /**
   * Moves the paddle to the right
   */
  public void moveRight() {
    node.setTranslateX(node.getTranslateX() + paddleSpeed);
  }

  /**
   * Detects if the paddle collides with another GameObject
   *
   * @param other - The other object.
   * @return True if they collide
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }
}

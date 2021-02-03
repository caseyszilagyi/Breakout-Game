package breakout;

/**
 * The paddle that is used to play the game. Is just a rectangle that has a method to move it
 * left or right based on the specified paddle speed.
 * @author Casey Szilagyii
 */
public class Paddle extends RectangleGameObject {

  private int paddleSpeed = 0;

  /**
   * Makes a paddle with the given location and width/height. Also sets the speed that the paddle
   * is able to move at.
   *
   * @param startX Starting X position
   * @param startY Starting Y position
   * @param width  Paddle Width
   * @param height Paddle Height
   * @param speed The speed that the paddle can move
   */
  public Paddle(int startX, int startY, int width, int height, int speed) {
    super(startX, startY, width, height, 0, 0);
    paddleSpeed = speed;
  }

  /**
   * Changes the velocity. This doesn't do anything for the paddle method because all movement
   * is done using the moveLeft/Right methods
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

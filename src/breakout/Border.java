package breakout;


/**
 * Outlines the playing bord. This makes it so that ball collisions with the edge of the screen can
 * be dealt with like any other collision. The border is just a rectangle with width 1 at the top
 * and sides of the display screen. If the ball is moving too fast, the collision detection with the
 * border may not work properly.
 *
 * @author Casey Szilagyi
 */
public class Border extends RectangleGameObject {

  /**
   * The call to the super constructor simply makes a rectangle at the specified location with the
   * specified width and height and with a velocity of 0, because the border doesn't move.
   *
   * @param startX Starting x position of the border
   * @param startY Starting y position of the border
   * @param width  Width of the border
   * @param height Height of the border
   */
  public Border(int startX, int startY, int width, int height) {
    super(startX, startY, width, height, 0, 0);
  }

  /**
   * Update method does nothing because this is the border. It doesn't ever move or change.
   */
  public void update() {
  }

  /**
   * Any interaction with the border is already dealt with in the ball class, and the border doesn't
   * have any other purpose. Therefore, false is returned.
   *
   * @return false;
   */
  public boolean collide() {
    return false;
  }
}

package breakout;


/**
 * @author Casey Szilagyi
 */
public class Border extends RectangleGameObject {

  /**
   * Outlines the playing bord. This makes it so that ball collisions with the edge of the screen
   * can be dealt with like any other collision. The call to the super constructor simply makes a
   * rectangle of the specified sizes, widths, and with a velocity of 0.
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
   * Update method does nothing because this is the border. It doesn't move or change ever;
   */
  public void update() {
  }

  /**
   * Any interaction with the border is already dealt with in the ball class, and the border doesn't
   * have any other purpose
   *
   * @return false;
   */
  public boolean collide() {
    return false;
  }
}

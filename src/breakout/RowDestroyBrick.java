package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * @author Casey Szilagy
 */
public class RowDestroyBrick extends Brick {

  ImagePattern rowDestroy = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("RowDestroyBrick.png")));

  /**
   * The row destroy brick destroys the entire row it is a part of when destroyed.
   *
   * @param startX Starting x position of the brick
   * @param startY Starting y position of the brick
   * @param width  Starting width of the brick
   * @param height Starting height of the brick
   */
  public RowDestroyBrick(int startX, int startY, int width, int height) {
    super(startX, startY, width, height, 0, 0);
    gameObject.setFill(rowDestroy);
  }

  /**
   * Only returns true if the ball collided with this brick
   *
   * @param other - The other object.
   * @return Whether or not they collided
   */
  public boolean collide(GameObject other) {
    if (other instanceof Ball && super.collide(other)) {
      return true;
    }
    return false;
  }
}

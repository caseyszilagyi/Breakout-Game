package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This brick destroys the entire row it is a part of when it is destroyed.
 * @author Casey Szilagy
 */
public class RowDestroyBrick extends Brick {

  ImagePattern rowDestroy = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("RowDestroyBrick.png")));

  /**
   * Makes the brick at the specified location with the given width/height.
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
   * Only returns true if the ball collided with this brick, because the brick doesn't need
   * to detect any other type of collisions.
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

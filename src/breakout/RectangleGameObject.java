package breakout;

import javafx.scene.shape.Rectangle;

/**
 * Represents any GameObject that is a rectangle. Can't be instantiated because any rectangular game
 * object should be specified through a subclass.
 *
 * @author Casey Szilagyi
 */
public abstract class RectangleGameObject extends GameObject {

  public Rectangle gameObject;

  /**
   * Makes a rectangle with the given specifications, as well as setting the x/y velocities
   *
   * @param startX Starting x position of the rectangle
   * @param startY Starting y position of the rectangle
   * @param width  Starting width of the rectangle
   * @param height Starting height of the rectangle
   * @param xVel   Starting x Velocity of the rectangle
   * @param yVel   Starting y Velocity of the rectangle
   */
  public RectangleGameObject(int startX, int startY, int width, int height, int xVel, int yVel) {
    Rectangle rectangle = new Rectangle(startX, startY, width, height);
    XVelocity = xVel;
    YVelocity = yVel;
    node = rectangle;
    gameObject = rectangle;
  }

  /**
   * The most basic form of the update method simply changes the positioning of the rectangle
   */
  public void update() {
    node.setTranslateX(node.getTranslateX() + XVelocity);
    node.setTranslateY(node.getTranslateY() + YVelocity);
  }

  /**
   * Did this GameObject collide into the other one
   *
   * @param other - The other object.
   * @return True if they collided
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }

}

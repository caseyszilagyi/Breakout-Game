package breakout;

import javafx.scene.shape.Circle;

/**
 * Represents any game object that is circular. Is abstract because any specific game object should
 * be made using a subclass as some of the properties may change. This is the most general form of a
 * circular GameObject
 *
 * @author Casey Szilagyi
 */
public abstract class CircleGameObject extends GameObject {

  public Circle gameObject;

  /**
   * Makes a circle of the given specifications.
   *
   * @param xPos   starting x position of the circle
   * @param yPos   starting y position of the circle
   * @param radius starting radius of the circle
   * @param xVel   starting x velocity of the circle
   * @param yVel   starting y velocity of the circle
   */
  public CircleGameObject(int xPos, int yPos, int radius, int xVel, int yVel) {
    Circle circle = new Circle(xPos, yPos, radius);
    XVelocity = xVel;
    YVelocity = yVel;
    node = circle;
    gameObject = circle;
  }

  /**
   * updates the position of the circle.
   */
  @Override
  public void update() {
    node.setTranslateX(node.getTranslateX() + XVelocity);
    node.setTranslateY(node.getTranslateY() + YVelocity);
  }

  /**
   * simply returns the super call of collide, which uses the intersect method and the bounds of the
   * two nodes
   *
   * @param other - The other GameObject.
   * @return True if they collided
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }

}

package breakout;

/**
 * @author Casey Szilagyi
 */
public abstract class Brick extends RectangleGameObject {

  /**
   * The brick class is how all of the bricks in the game are made. The class is abstract so that
   * any specific type of brick must be made through the use of a subclass
   *
   * @param startX starting x position of the brick
   * @param startY starting y position of the brick
   * @param width  starting width of the brick
   * @param height starting height of the brick
   * @param xVel   starting x Velocity of the brick
   * @param yVel   starting y Velocity of the brick
   */
  public Brick(int startX, int startY, int width, int height, int xVel, int yVel) {
    super(startX, startY, width, height, xVel, yVel);
  }

  /**
   * changes the positioning of the brick if it is a moving one
   */
  public void update() {
    super.update();
  }

  /**
   * checks if the brick collided with the other game object
   *
   * @param other - the other object.
   * @return true if it did
   */
  public boolean collide(GameObject other) {
    return super.collide(other);
  }

}

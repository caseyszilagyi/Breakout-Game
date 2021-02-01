package breakout;

import javafx.geometry.Bounds;

/**
 * @author Casey Szilagyii
 */
public class Ball extends CircleGameObject {

  public boolean hasPowerUp = false;

  /**
   * Represents the ball that is used to play the breakout game. Although one ball is all that is
   * initially instantiated, it is possible to instantiate more if needed for a powerup The super
   * constructor makes a circle with the given parameters, and sets the velocity of the game object
   * to the designated velocity
   *
   * @param xPos   Starting x position of the ball
   * @param yPos   Starting y position of the ball
   * @param radius Starting radius of the ball
   * @param xVel   Starting x velocity of the ball
   * @param yVel   Starting y velocity of the ball
   */
  public Ball(int xPos, int yPos, int radius, int xVel, int yVel) {
    super(xPos, yPos, radius, xVel, yVel);
  }


  /**
   * Updates the position of the ball. This is done by using the setTranslate methods and the
   * velocity of the ball
   */
  @Override
  public void update() {
    super.update();
  }

  /**
   * The collide method calls the super method to determine whether the objects are colliding. If
   * they are, it needs to be determined what side of the other object the ball is bouncing off of
   * in order to determine how the velocity should be changed.
   *
   * @param other - The other GameObject.
   * @return Whether or not they are colliding.
   */
  public boolean collide(GameObject other) {
    if (super.collide(other)) {
      //Ball doesn't bounce off of powerups
      if (other instanceof PowerUp) {
        return false;
      }
      //Checks to see if the ball currently has a single-hit powerup
      if (hasPowerUp && other instanceof Brick) {
        other.isDead = true;
      }
      // checking to see if bouncing off left or right side of an object
      if (checkRightWallCollision(other) || checkLeftWallCollision(other)) {
        XVelocity = -XVelocity;
      }
      // else it is bouncing on top or bottom, so can simply reverse y velocity
      else {
        YVelocity = -YVelocity;
      }
    }
    return super.collide(other);
  }

  /**
   * Checks to see if the ball is bouncing off of the right side of an object
   *
   * @param other The other GameObject
   * @return Whether or not it is bouncing off of the right side.
   */
  public boolean checkRightWallCollision(GameObject other) {
    Bounds currentBounds = node.getBoundsInParent();
    Bounds otherBounds = other.node.getBoundsInParent();
    //To see if the right wall is between the left and right edges of the ball
    if (currentBounds.getMaxX() > otherBounds.getMaxX() && currentBounds.getMinX() < otherBounds
        .getMaxX()) {
      return true;
    }
    return false;
  }

  /**
   * Checks to see if the ball is bouncing off of the left side of an object
   *
   * @param other The other GameObject
   * @return Whether or not it is bouncing off of the left side.
   */
  public boolean checkLeftWallCollision(GameObject other) {
    Bounds currentBounds = node.getBoundsInParent();
    Bounds otherBounds = other.node.getBoundsInParent();
    //To see if the right wall is between the left and right edges of the ball
    if (currentBounds.getMaxX() > otherBounds.getMinX() && currentBounds.getMinX() < otherBounds
        .getMinX()) {
      return true;
    }
    return false;
  }

}

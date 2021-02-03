package breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

/**
 * Represents any object that needs to be displayed in the game. Each GameObject has a node in order
 * to display it, velocities in both directions, and a boolean signaling whether or not it is dead.
 * This is an abstract method because any specific object in the game should be instantiated through
 * a subclass
 *
 * @author Casey Szilagyi
 */
public abstract class GameObject {

  // display node
  public Node node;
  public double XVelocity = 0;
  public double YVelocity = 0;

  // is it still alive
  public boolean isDead = false;

  /**
   * Updates the object. This mostly just means updating the position based on the velocity.
   */
  public abstract void update();

  /**
   * Checks whether this object collides with the other. Uses intersects, which bounds each node
   * into a rectangle and checks to see if they intersect.
   *
   * @param other - The other object.
   * @return True if they collided
   */
  public boolean collide(GameObject other) {
    return node.getBoundsInParent().intersects(other.node.getBoundsInParent());
  }
}
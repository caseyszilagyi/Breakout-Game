package breakout;

import java.util.*;

/**
 * Object manager holds all the objects in the game for collision detection and removes/adds new
 * objects
 *
 * @author Casey Szilagyi
 */

public class ObjectManager {

  private final static List ALL_OBJECTS = new ArrayList<GameObject>();
  private final static List CHECK_COLLISIONS = new ArrayList<GameObject>();
  private final static Set REMOVE_OBJECTS = new HashSet<GameObject>();

  /**
   * Gets all of our objects
   *
   * @return A list of the objects
   */
  public List getObjects() {
    return ALL_OBJECTS;
  }

  /**
   * Adds objects into our list of object
   *
   * @param objects The objects that will be added
   */
  public void addObjects(GameObject... objects) {
    ALL_OBJECTS.addAll(Arrays.asList(objects));
  }

  /**
   * Objects to remove from our list of objects
   *
   * @param objects The objects to remove
   */
  public void removeObjects(GameObject... objects) {
    ALL_OBJECTS.removeAll(Arrays.asList(objects));
  }


  /**
   * Gets the set of all objects that need to be removed
   *
   * @return The set of objects to remove
   */
  public Set getObjectsToRemove() {
    return REMOVE_OBJECTS;
  }

  /**
   * Adds an object to remove
   *
   * @param objects The objects that we need too remove
   */
  public void addObjectsToRemove(GameObject... objects) {
    REMOVE_OBJECTS.addAll(Arrays.asList((Object[]) objects));
  }

  /**
   * Returns a list of all the collisions to check, which is simply a copy of all the objects in the
   * game
   *
   * @return Collisions to check
   */
  public List getCollisionsToCheck() {
    return CHECK_COLLISIONS;
  }

  /**
   * Resets the collisions list and sets it to all the objects that we have
   */
  public void resetCollisionsToCheck() {
    CHECK_COLLISIONS.clear();
    CHECK_COLLISIONS.addAll(ALL_OBJECTS);
  }

  /**
   * Checking to see if any of our game objects are dead, if so, add them to the list to be removed
   */
  public void checkForDead() {
    for (Object gameObject : getObjects()) {
      checkDeath((GameObject) gameObject);
    }
  }

  /**
   * Checks if any individual object is dead. If it is, add to objects to be removed
   */
  public void checkDeath(GameObject gameObject) {
    if (gameObject.isDead) {
      addObjectsToRemove(gameObject);
    }
  }

  /**
   * Removes objects from the list of all objects and then clears the set of objects to be removed
   */
  public void cleanUp() {
    ALL_OBJECTS.removeAll(REMOVE_OBJECTS);
    REMOVE_OBJECTS.clear();
  }
}


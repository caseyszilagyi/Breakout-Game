package breakout;

import java.util.*;

/**
 * Sprite manager is responsible for holding all sprite objects, and cleaning up
 * sprite objects to be removed. All collections are used by the JavaFX
 * application thread. During each cycle (animation frame) sprite management
 * occurs. This assists the user of the API to not have to create lists to
 * later be garbage collected. Should provide some performance gain.
 * @author cdea
 */

public class ObjectManager {
    /** All the objects that are in the scene */
    private final static List ALL_OBJECTS = new ArrayList<GameObject>();

    /** Another list used to check collisions */
    private final static List CHECK_COLLISIONS = new ArrayList<GameObject>();

    /** A set to add objects to be removed */
    private final static Set REMOVE_OBJECTS = new HashSet<GameObject>();

    /**
     * Gets all of our objects
     * @return A list of the objects
     */
    public List getObjects() {
        return ALL_OBJECTS;
    }

    /**
     * Adds objects into our list of object
     * @param objects The objects that will be added
     */
    public void addObjects(GameObject... objects) {
        ALL_OBJECTS.addAll(Arrays.asList(objects));
    }

    /**
     * Objects to remove from our list of objects
     * @param objects The objects to remove
     */
    public void removeObjects(GameObject... objects) {
        ALL_OBJECTS.removeAll(Arrays.asList(objects));
    }


    /**
     * Gets the set of all objects that need to be removed
     * @return The set of objects to remove
     */
    public Set getObjectsToRemove() {
        return REMOVE_OBJECTS;
    }

    /**
     * Adds an object to remove
     * @param objects The objects that we need too remove
     */
    public void addObjectsToRemove(GameObject... objects) {
        REMOVE_OBJECTS.addAll(Arrays.asList((Object[]) objects));
    }

    /**
     * Returns a list of all the collisions to check, which is simply a copy of all the objects in the game
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
     * Removes objects from the list of all objects and then clears the set of objects to be removed
     */
    public void cleanUp() {
        ALL_OBJECTS.removeAll(REMOVE_OBJECTS);
        REMOVE_OBJECTS.clear();
    }
}


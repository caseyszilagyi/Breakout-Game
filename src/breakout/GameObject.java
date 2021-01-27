package breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

/**
 * Represents an object to be displayed
 * Update and collide methods are called at every keyframe
 * @author Casey Szilagyi
 */
public abstract class GameObject {
    /** Current display node */
    public Node node;

    /** Velocity vector x direction */
    public double XVelocity = 0;
    /** Velocity vector y direction */
    public double YVelocity = 0;

    /** Does this GameObject exist */
    public boolean isDead = false;

    /**
     * Updates the object. This includes things such as reversing direction and moving a certain distance
     * as determined by the velocity
     */
    public abstract void update();

    /**
     * The collide method simply uses intersects, which bounds each node into a rectangle and checks to see if
     * they intersect.
     * @param other - The other object.
     * @return Whether or not they collided
     */
    public boolean collide(GameObject other){
        return node.getBoundsInParent().intersects(other.node.getBoundsInParent());
    }
}
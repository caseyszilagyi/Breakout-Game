package breakout;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class CircleGameObject extends GameObject{

    public Circle gameObject;
    /**
     * The circle class represents any GameObject that is circular, or has a circular hitbox.
     * @param xPos Starting x position of the circle
     * @param yPos Starting y position of the circle
     * @param radius Starting radius of the circle
     * @param xVel Starting x velocity of the circle
     * @param yVel Starting y velocity of the circle
     */
    public CircleGameObject(int xPos, int yPos, int radius, int xVel, int yVel){
        Circle circle = new Circle(xPos, yPos, radius);
        XVelocity = xVel;
        YVelocity = yVel;
        node = circle;
        gameObject = circle;
    }

    /**
     * Updates the position of the circle. This is the most basic movement, anything beyond this is implemented
     * in the subclasses.
     */
    @Override
    public void update(){
        node.setTranslateX(node.getTranslateX() + XVelocity);
        node.setTranslateY(node.getTranslateY() + YVelocity);
    }

    /**
     * Simply returns the super call of collide, which uses the intersect method and the bounds of the two nodes
     * @param other - The other GameObject.
     * @return True if it did
     */
    public boolean collide(GameObject other){
        return super.collide(other);
    }

}

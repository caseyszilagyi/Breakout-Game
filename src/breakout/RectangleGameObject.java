package breakout;

import javafx.scene.shape.Rectangle;

public abstract class RectangleGameObject extends GameObject{

    public Rectangle gameObject;
    /**
     * The rectangle class represents any GameObject that is rectangular, or has a rectuangular hitbox.
     * Also includes the borders.
     * @param startX Starting x position of the rectangle
     * @param startY Starting y position of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting height of the rectangle
     * @param xVel Starting x Velocity of the rectangle
     * @param yVel Starting y Velocity of the rectangle
     */
    public RectangleGameObject(int startX, int startY, int width, int height, int xVel, int yVel){
        Rectangle rectangle = new Rectangle(startX, startY, width, height);
        XVelocity = xVel;
        YVelocity = yVel;
        node = rectangle;
        gameObject = rectangle;
    }

    /**
     * The most basic form of the update method simply changes the positioning of the rectangle
     */
    public void update(){
        node.setTranslateX(node.getTranslateX() + XVelocity);
        node.setTranslateY(node.getTranslateY() + YVelocity);
    }

    /**
     * Did this GameObject collide into the other one
     * @param other - The other object.
     * @return True if it did
     */
    public boolean collide(GameObject other){
        return super.collide(other);
    }

}

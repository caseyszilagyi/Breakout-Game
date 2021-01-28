package breakout;

public abstract class Brick extends RectangleGameObject{

    /**
     * The brick class is how all of the bricks in the game are made. The class is abstract so that
     * any specific type of brick must be made through the use of a subclass
     * @param startX Starting x position of the rectangle
     * @param startY Starting y position of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting height of the rectangle
     * @param xVel Starting x Velocity of the rectangle
     * @param yVel Starting y Velocity of the rectangle
     */
    public Brick(int startX, int startY, int width, int height, int xVel, int yVel){
        super(startX, startY, width, height, xVel, yVel);
    }

    /**
     * The most basic form of the update method simply changes the positioning of the rectangle
     */
    public void update(){
        super.update();
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

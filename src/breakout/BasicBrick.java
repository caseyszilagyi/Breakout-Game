package breakout;

public class BasicBrick extends Brick{

    /**
     * The basic brick class is the most simple type of brick. It takes one hit to destroy, and doesn't
     * drop any power ups.
     * @param startX Starting x position of the rectangle
     * @param startY Starting y position of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting height of the rectangle
     * @param xVel Starting x Velocity of the rectangle
     * @param yVel Starting y Velocity of the rectangle
     */
    public BasicBrick(int startX, int startY, int width, int height, int xVel, int yVel){
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

package breakout;

import javafx.scene.shape.Circle;
import javafx.geometry.Bounds;

public class Ball extends CircleGameObject{

    /**
     * The ball class represents the ball that is used to play the breakout game. Although one ball
     * is all that is initially instantiated, it is possible to instantiate more if needed for
     * a powerup
     * @param xPos Starting x position of the ball
     * @param yPos Starting y position of the ball
     * @param radius Starting radius of the ball
     * @param xVel Starting x velocity of the ball
     * @param yVel Starting y velocity of the ball
     */
    public Ball(int xPos, int yPos, int radius, int xVel, int yVel){
        super(xPos, yPos, radius, xVel, yVel);
    }

    /**
     * Updates the position of the ball. Also checks for collision with the edge of the playing field
     */
    @Override
    public void update(){
        super.update();

        //Need to set wall bouncing
    }

    /**
     * The collide method calls the super method to determine whether the objects are colliding
     * if they are, it needs to be determined what side of the other object the ball is bouncing off of
     * in order to determine how the velocity should be changed.
     * @param other - The other GameObject.
     * @return Whether or not it is colliding.
     */
    public boolean collide(GameObject other){
        if (super.collide(other)){
            // checking to see if bouncing off left or right wall
            if(checkRightWallCollision(other) || checkLeftWallCollision(other)){
                    XVelocity = -XVelocity;
            }
            // else it is bouncing on top or bottom, so can simply reverse y velocity
            else{
                YVelocity = -YVelocity;
            }
        }
        return super.collide(other);
    }

    public boolean checkRightWallCollision(GameObject other){
        Bounds currentBounds = node.getBoundsInParent();
        Bounds otherBounds = other.node.getBoundsInParent();
        //To see if the right wall is between the left and right edges of the ball
        if(currentBounds.getMaxX() > otherBounds.getMaxX() && currentBounds.getMinX() < otherBounds.getMaxX()) {
            return true;
        }
        return false;
    }

    public boolean checkLeftWallCollision(GameObject other){
        Bounds currentBounds = node.getBoundsInParent();
        Bounds otherBounds = other.node.getBoundsInParent();
        //To see if the right wall is between the left and right edges of the ball
        if(currentBounds.getMaxX() > otherBounds.getMinX() && currentBounds.getMinX() < otherBounds.getMinX()) {
            return true;
        }
        return false;
    }


    //Need to add separate collide methods for the paddle and the bricks.
}

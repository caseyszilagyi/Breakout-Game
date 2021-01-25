package breakout;

import javafx.scene.shape.Circle;

public abstract class CircleSprite extends Sprite{

    protected int xDirection = 1;
    protected int yDirection = 1;

    /**
     * The circle class represents any sprite that is circular.
     * @param xPos Starting x position of the circle
     * @param yPos Starting y position of the circle
     * @param radius Starting radius of the circle
     * @param xVel Starting x velocity of the circle
     * @param yVel Starting y velocity of the circle
     */
    public CircleSprite(int xPos, int yPos, int radius, int xVel, int yVel){
        Circle circle = new Circle(xPos, yPos, radius);
        vX = xVel;
        vY = yVel;
        node = circle;
    }

    /**
     * Updates the position of the circle. This is the most basic movement, anything beyond this is implemented
     * in the subclasses.
     */
    @Override
    public void update(){
        node.setTranslateX(node.getTranslateX() + vX * xDirection);
        node.setTranslateY(node.getTranslateY() + vY * yDirection);
    }

    public boolean collide(Sprite other){
        return false;
    }

    //Need to add separate collide methods for the paddle and the bricks.
}

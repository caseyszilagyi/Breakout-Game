package breakout;

import javafx.scene.shape.Circle;

public class Ball extends Sprite{

    private int xDirection = 1;
    private int yDirection = 1;
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
        Circle ball = new Circle(xPos, yPos, radius);
        vX = xVel;
        vY = yVel;
        node = ball;
    }

    /**
     * Updates the position of the ball. Also checks for collision with the edge of the playing field
     */
    @Override
    public void update(){
        node.setTranslateX(node.getTranslateX() + vX * xDirection);
        node.setTranslateY(node.getTranslateY() + vY * yDirection);

        //Need to set wall bouncing
        if(node.getTranslateX() > GAME_HEIGHT){

        }
    }

    //Need to add separate collide methods for the paddle and the bricks.
}

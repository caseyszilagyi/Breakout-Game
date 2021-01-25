package breakout;

import javafx.scene.shape.Circle;

public class Ball extends Sprite{

    public Ball(int xPos, int yPos, int radius, int xVel, int yVel){
        Circle ball = new Circle(xPos, yPos, radius);
        vX = xVel;
        vY = yVel;
        node = ball;
    }

    @Override
    public void update(){
        node.setTranslateX(node.getTranslateX() + vX);
        node.setTranslateY(node.getTranslateY() + vY);

        //Need to set wall bouncing
    }

    //Need to add separate collide methods for the paddle and the bricks.
}

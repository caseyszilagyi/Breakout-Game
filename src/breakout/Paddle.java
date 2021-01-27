package breakout;

public class Paddle extends RectangleGameObject{
    private int paddleSpeed = 0;
    /**
     * Constructor to make the paddle GameObject
     * @param startX Starting X position
     * @param startY Starting Y position
     * @param width Paddle Width
     * @param height Paddle Height
     */
    public Paddle(int startX, int startY, int width, int height, int speed) {
        super(startX, startY, width, height, 0, 0);
        paddleSpeed = speed;
    }

    /** Paddle update method */
    public void update(){
        super.update();
    }

    public void moveLeft(){
        node.setTranslateX(node.getTranslateX() - paddleSpeed);
    }

    public void moveRight(){
        node.setTranslateX(node.getTranslateX() + paddleSpeed);
    }

    /**
     * Detects if the paddle collides with another GameObject
     * @param other - The other object.
     * @return True if they collide
     */
    public boolean collide(GameObject other){
        return super.collide(other);
    }
}

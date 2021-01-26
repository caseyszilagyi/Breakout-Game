package breakout;

import javafx.scene.shape.Rectangle;

public class Border extends RectangleSprite{

    /**
     * Border class to outline the playing board. This makes it easier to deal with ball/powerup collisions with the
     * edge of the playing space
     * @param startX Starting x position of the rectangle
     * @param startY Startinig y positioin of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting heiight of the rectangle
     */
    public Border(int startX, int startY, int width, int height){
        super(startX, startY, width, height, 0, 0);
    }

    /**
     * Update method does nothing because this is the border. It doesn't move or change.
     */
    public void update(){
    }

    public boolean collide(){
        return false;
    }
}

package breakout;

import javafx.scene.shape.Rectangle;

public abstract class RectangleSprite extends Sprite{

    /**
     * The rectangle class represents any sprite that is rectangular, or has a rectuangular hitbox.
     * Also includes the borders.
     * @param startX Starting x position of the rectangle
     * @param startY Starting y position of the rectangle
     * @param width Starting width of the rectangle
     * @param height Starting height of the rectangle
     */
    public RectangleSprite(int startX, int startY, int width, int height, int xVel, int yVel){
        Rectangle rectangle = new Rectangle(startX, startY, width, height);
        vX = xVel;
        vY = yVel;
        node = rectangle;
    }

    /**
     * The most basic form of the update method simply changes the positioning of the rectangle
     */
    public void update(){
        node.setTranslateX(node.getTranslateX() + vX);
        node.setTranslateY(node.getTranslateY() + vY);
    }

    /**
     * Did this sprite collide into the other sprite?
     * @param other - The other sprite.
     * @return True if it did
     */
    public boolean collide(Sprite other){
        return false;
    }
}

package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * The brick that can take anywhere from 1-9 hits to destroy.
 *
 * @author Casey Szilagyi
 */
public class MultiHitBrick extends Brick {

  private int health;

  // Loading in all of the images in the form of ImagePatterns so that the bricks are updated when health is lost
  ImagePattern health1 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("1HealthBrick.png")));
  ImagePattern health2 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("2HealthBrick.png")));
  ImagePattern health3 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("3HealthBrick.png")));
  ImagePattern health4 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("4HealthBrick.png")));
  ImagePattern health5 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("5HealthBrick.png")));
  ImagePattern health6 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("6HealthBrick.png")));
  ImagePattern health7 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("7HealthBrick.png")));
  ImagePattern health8 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("8HealthBrick.png")));
  ImagePattern health9 = new ImagePattern(
      new Image(getClass().getClassLoader().getResourceAsStream("9HealthBrick.png")));

  /**
   * The constructor makes a rectangle of the given specifications, as well as designating the
   * health of the brick. Also calls the updateColor method, which changes the design of the brick
   * based on the health
   *
   * @param startX     Starting x position of the brick
   * @param startY     Starting y position of the brick
   * @param width      Starting width of the brick
   * @param height     Starting height of the brick
   * @param userHealth The health of the brick
   */
  public MultiHitBrick(int startX, int startY, int width, int height, int userHealth) {
    super(startX, startY, width, height, 0, 0);
    health = userHealth;
    updateColor();
  }

  /**
   * This brick doesn't move, so no need to update the velocity. However, there is a color scheme
   * for the brick health so this method will update the color
   */
  public void update() {
    updateColor();
  }

  /**
   * Updates the design of the brick based on the brick health.
   */
  public void updateColor() {
    switch (health) {
      case 1:
        gameObject.setFill(health1);
        break;
      case 2:
        gameObject.setFill(health2);
        break;
      case 3:
        gameObject.setFill(health3);
        break;
      case 4:
        gameObject.setFill(health4);
        break;
      case 5:
        gameObject.setFill(health5);
        break;
      case 6:
        gameObject.setFill(health6);
        break;
      case 7:
        gameObject.setFill(health7);
        break;
      case 8:
        gameObject.setFill(health8);
        break;
      case 9:
        gameObject.setFill(health9);
        break;
    }
  }

  /**
   * The collide method for this class only checks for collision with the ball It updates the health
   * if it does collide, and makes the object dead if the health is 0
   *
   * @param other - The other object.
   * @return True if it collides and the other object is a ball.
   */
  public boolean collide(GameObject other) {
    // Only update health if collides with ball
    if (other instanceof Ball && super.collide(other)) {
      health--;
      if (health == 0) {
        isDead = true;
        return true;
      }
    }
    return false;
  }

}

package breakout;

/**
 * This class is the generic form of how a power up class is made. It has variables to update the
 * game objects that the power ups rely on(game/ball), as well as a method to actually enact the
 * power up in the game. This class is abstract because this is just a generic version of a power
 * up, and it doesn't actually do anything except for represent the framework of what is required to
 * make a powerup
 * <p>
 * This code is well designed because it encompasses all the things that power ups need without
 * adding any details of any specific power up. This reduces code duplication and more easily allows
 * for changing things related to all power ups.
 *
 * @author Casey Szilagyi
 */
public abstract class PowerUpGeneric {

  private int powerUpDuration;
  private Ball ball;
  private Paddle paddle;

  /**
   * The one thing that all powerUps have in common is the power up duration, so it is set here
   *
   * @param userPowerUpDuration
   */
  public PowerUpGeneric(int userPowerUpDuration) {
    powerUpDuration = userPowerUpDuration;
  }

  /**
   * Gets the power up duration
   *
   * @return the power up duration
   */
  public int getPowerUpDuration() {
    return powerUpDuration;
  }

  /**
   * This method is meant to be called when we want a certain power up to be put into play
   */
  public abstract void enactPowerUp();

  /**
   * Sets the paddle that is currently being played with so that the power up can modify it
   */
  public void setPaddle(Paddle newPaddle) {
    paddle = newPaddle;
  }

  /**
   * Sets the ball that is currently being played with so that the power up can modify it
   */
  public void setBall(Ball newBall) {
    ball = newBall;
  }

  /**
   * Gets the paddle that is currently being played with
   *
   * @return the paddle
   */
  public Paddle getPaddle() {
    return paddle;
  }

  /**
   * Gets the ball that is currently being played with
   *
   * @return the ball
   */
  public Ball getBall() {
    return ball;
  }
}

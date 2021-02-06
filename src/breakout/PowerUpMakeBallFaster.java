package breakout;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Power up that makes the ball move faster. Called through the enactPowerUp method, and there is
 * also a method to update the ball that the game is being played with
 * <p>
 * Is well designed due to short methods, good method names, and is a good usage of abstraction. It
 * also only has one function, which is to make this power up work
 * @author Casey Szilagyi
 */
public class PowerUpMakeBallFaster extends PowerUpGeneric {

  private final double BALL_SPEED_UP_MULTIPLIER = 3;

  /**
   * makes an instance of this powerup with the given game ball and makes the power up last as long
   * as specified
   *
   * @param userBall            The game ball
   * @param userPowerUpDuration The duration of the powerup (in milliseconds)
   */
  public PowerUpMakeBallFaster(Ball userBall, int userPowerUpDuration) {
    super(userPowerUpDuration);
    setBall(userBall);
  }

  /**
   * Actually enacts the power up by making the ball move faster and then making it slower, after a
   * delay
   */
  public void enactPowerUp() {
    makeBallFaster();
    makeBallSlower();
  }

  // Makes the ball move faster
  private void makeBallFaster() {
    getBall().YVelocity = getBall().YVelocity * BALL_SPEED_UP_MULTIPLIER;
    getBall().XVelocity = getBall().XVelocity * BALL_SPEED_UP_MULTIPLIER;

  }

  // Turns the ball back to normal, after a delay
  private void makeBallSlower() {
    PauseTransition endPowerUp = new PauseTransition(Duration.millis(getPowerUpDuration()));
    endPowerUp.setOnFinished(makeBallSlowerEventHandler());
    endPowerUp.play();
  }

  // The event handler that turns the ball back to normal.
  private EventHandler makeBallSlowerEventHandler() {
    EventHandler handle = new EventHandler() {
      @Override
      public void handle(Event event) {
        getBall().YVelocity = getBall().YVelocity / BALL_SPEED_UP_MULTIPLIER;
        getBall().XVelocity = getBall().XVelocity / BALL_SPEED_UP_MULTIPLIER;
      }
    };
    return handle;
  }
}

package breakout;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Power up that makes the ball destroy bricks in 1 hit. Called through the enactPowerUp method, and
 * there is also a method to update the ball that the game is being played with
 * <p>
 * Is well designed due to short methods, good method names, and is a good usage of abstraction. It
 * also only has one function, which is to make this power up work
 *
 * @author Casey Szilagyi
 */
public class PowerUpMakeBallDestroyIn1Hit extends PowerUpGeneric {

  /**
   * Makes an instance of this powerup with the given game ball and makes the power up last as long
   * as specified
   *
   * @param userBall            The game ball
   * @param userPowerUpDuration The duration of the powerup (in milliseconds)
   */
  public PowerUpMakeBallDestroyIn1Hit(Ball userBall, int userPowerUpDuration) {
    super(userPowerUpDuration);
    setBall(userBall);
  }

  /**
   * Actually enacts the power up by making the ball destroy bricks in 1 hit and then taking away
   * this power after some time passes
   */
  public void enactPowerUp() {
    makeBallDestroyBricksIn1Hit();
    stopBallDestroyBricksIn1Hit();
  }

  // Allows the ball to destroy bricks in 1 hit
  private void makeBallDestroyBricksIn1Hit() {
    getBall().hasPowerUp = true;
  }

  // Makes the ball weaker, after a delay
  private void stopBallDestroyBricksIn1Hit() {
    PauseTransition endPowerUp = new PauseTransition(Duration.millis(getPowerUpDuration()));
    endPowerUp.setOnFinished(makeBallDestroyBricksIn1HitEventHandler());
    endPowerUp.play();
  }

  // The event handler that turns the ball back to normal
  public EventHandler makeBallDestroyBricksIn1HitEventHandler() {
    EventHandler handle = new EventHandler() {
      @Override
      public void handle(Event event) {
        getBall().hasPowerUp = false;
      }
    };
    return handle;
  }

}

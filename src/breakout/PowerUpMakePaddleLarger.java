package breakout;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Power up that makes the paddle larger. Called through the enactPowerUp method, and there is also
 * a method to update the paddle that the game is being played with
 * <p>
 * Is well designed due to short methods, good method names, and is a good usage of abstraction. It
 * also only has one function, which is to make this power up work
 *
 * @author Casey Szilagyi
 */
public class PowerUpMakePaddleLarger extends PowerUpGeneric {

  private double normalPaddleWidth;
  private final double PADDLE_POWERUP_WIDTH = 300;

  /**
   * Makes an instance of this powerup with the given game paddle and makes the power up last as
   * long as specified
   *
   * @param userPaddle          The game ball
   * @param userPowerUpDuration The duration of the powerup (in milliseconds)
   */
  public PowerUpMakePaddleLarger(Paddle userPaddle, int userPowerUpDuration) {
    super(userPowerUpDuration);
    setPaddle(userPaddle);
    normalPaddleWidth = userPaddle.gameObject.getWidth();
  }

  /**
   * Makes the paddle larger and sets a timer to make the paddle smaller again after some time
   * passes
   */
  public void enactPowerUp() {
    makePaddleBigger();
    makePaddleSmaller();
  }

  // makes the paddle larger
  private void makePaddleBigger() {
    getPaddle().gameObject.setWidth(PADDLE_POWERUP_WIDTH);
  }

  // Turns the paddle back to normal, after a delay
  private void makePaddleSmaller() {
    PauseTransition endPowerUp = new PauseTransition(Duration.millis(getPowerUpDuration()));
    endPowerUp.setOnFinished(makePaddleSmallerEventHandler());
    endPowerUp.play();
  }

  // The event handler that turns the paddle back to normal
  private EventHandler makePaddleSmallerEventHandler() {
    EventHandler handle = new EventHandler() {
      @Override
      public void handle(Event event) {
        getPaddle().gameObject.setWidth(normalPaddleWidth);
      }
    };
    return handle;
  }
}

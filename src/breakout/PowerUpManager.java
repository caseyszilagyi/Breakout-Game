package breakout;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Random;

/**
 * Has all of the methods that enact powerups, as well as times them and reverts the game back to
 * its original state when they are done. Can set the probability of a good/bad powerup, as well as
 * the individual probabilities of each good/bad powerup. Assumes that the probabilites add up to 1
 * for both good and bad powerups,, or else a powerup may not have any effect on the state of the
 * game.
 *
 * @author Casey Szilagyi
 */
public class PowerUpManager {

  private Paddle paddle;
  private Ball ball;
  private Random random;

  private double normalPaddleWidth;
  private double normalBallSpeed;

  //Probabilities of various power ups
  // good
  private double PADDLE_BIGGER_CHANCE = 0.5;
  private double BALL_1HIT_BRICKS = 0.5;

  // bad
  private double SPEED_UP_BALL = 1.0;

  // Power up details
  private final int PADDLE_POWERUP_WIDTH = 300;
  private final double BALL_SPEED_UP_MULTIIPLIER = 3;
  private int POWER_UP_DURATION = 10000;

  /**
   * Need the ball and paddle that the game is being played with in order to update the state of the
   * game when a power up is gotten.
   *
   * @param gamePaddle The paddle being used to play the game
   * @param gameBall   The ball being used to play the game
   */
  public PowerUpManager(Paddle gamePaddle, Ball gameBall) {
    paddle = gamePaddle;
    ball = gameBall;
    normalPaddleWidth = paddle.gameObject.getWidth();
    normalBallSpeed = ball.YVelocity;
    random = new Random();
  }

  /**
   * Deals with any powerup by getting a random good/bad powerup depending on the random number
   *
   * @param powerUp The power up that is activated
   */
  public void dealWithPowerUp(PowerUp powerUp) {
    if (powerUp instanceof GoodPowerUp) {
      getRandomGoodPowerUp();
    } else {
      getRandomBadPowerUp();
    }
  }

  /**
   * Gives the user a random good power up, based on the odds established in the instance variables
   */
  public void getRandomGoodPowerUp() {
    double randomNum = random.nextDouble();
    makeBallDestroyBricksIn1Hit();
    if (randomNum < PADDLE_BIGGER_CHANCE) {
      makePaddleBigger();
    } else if (PADDLE_BIGGER_CHANCE < randomNum) {
      makeBallDestroyBricksIn1Hit();
    }
  }

  /**
   * Gives the user a random bad power up, (but there is only 1 right now)
   */
  public void getRandomBadPowerUp() {
    makeBallFaster();
  }

  /**
   * Power up to make the ball move faster
   */
  public void makeBallFaster() {
    ball.YVelocity = ball.YVelocity * BALL_SPEED_UP_MULTIIPLIER;
    ball.XVelocity = ball.XVelocity * BALL_SPEED_UP_MULTIIPLIER;
    PauseTransition endPowerUp = new PauseTransition(Duration.millis(POWER_UP_DURATION));
    endPowerUp.setOnFinished(makeBallFasterEventHandler());
    endPowerUp.play();
  }

  /**
   * Stops the ball from moving faster after a certain period of time
   *
   * @return The event handler that stops the ball from moving faster
   */
  public EventHandler makeBallFasterEventHandler() {
    EventHandler handle = new EventHandler() {
      @Override
      public void handle(Event event) {
        ball.YVelocity = ball.YVelocity / BALL_SPEED_UP_MULTIIPLIER;
        ball.XVelocity = ball.XVelocity / BALL_SPEED_UP_MULTIIPLIER;
      }
    };
    return handle;
  }

  /**
   * Makes the paddle bigger
   */
  public void makePaddleBigger() {
    paddle.gameObject.setWidth(PADDLE_POWERUP_WIDTH);
    PauseTransition endPowerUp = new PauseTransition(Duration.millis(POWER_UP_DURATION));
    endPowerUp.setOnFinished(makePaddleBiggerEventHandler());
    endPowerUp.play();
  }

  /**
   * Makes the paddle smaller again
   *
   * @return The eventhandler that makes the paddle smaller
   */
  public EventHandler makePaddleBiggerEventHandler() {
    EventHandler handle = new EventHandler() {
      @Override
      public void handle(Event event) {
        paddle.gameObject.setWidth(normalPaddleWidth);
      }
    };
    return handle;
  }

  /**
   * Allows the ball to destroy bricks in a single hit
   */
  public void makeBallDestroyBricksIn1Hit() {
    ball.hasPowerUp = true;
    PauseTransition endPowerUp = new PauseTransition(Duration.millis(POWER_UP_DURATION));
    endPowerUp.setOnFinished(makeBallDestroyBricksIn1HitEventHandler());
    endPowerUp.play();
  }

  /**
   * Stops the ball from having the break bricks in 1 hit power up
   *
   * @return The event handler that takes away the power up
   */
  public EventHandler makeBallDestroyBricksIn1HitEventHandler() {
    EventHandler handle = new EventHandler() {
      @Override
      public void handle(Event event) {
        ball.hasPowerUp = false;
      }
    };
    return handle;
  }

}

package breakout;

import java.util.Random;

/**
 * Meant to manage all of the different power ups that are present in the game. Works by making
 * instances of all the power ups present in the game. When dealWithPowerUp is called, a random good
 * or bad powerup is put into play by calling that powerup's enactPowerUp method. There are also
 * methods to update the ball/paddle in case the game is being played with a different one than when
 * this object was instantiated.
 * <p>
 * It is designed well because it "tells the other guy" to enact the power ups, rather than doing it
 * in this class. All of the methods are also very short and readable. Additionally, private methods
 * hide the implementation of choosing power ups and making the power ups from the user
 *
 * @author Casey Szilagyi
 */
public class PowerUpManager {

  private Paddle paddle;
  private Ball ball;
  private Random random;

  private int POWER_UP_DURATION = 10000;

  //Probabilities of various power ups
  // good
  private double PADDLE_BIGGER_CHANCE = 0.5;
  private double BALL_1HIT_BRICKS = 0.5;
  // bad
  private double SPEED_UP_BALL = 1.0;

  // the power ups
  PowerUpGeneric ballFaster;
  PowerUpGeneric paddleLarger;
  PowerUpGeneric ball1Hit;

  /**
   * Need the ball and paddle that the game is being played with in order to update the state of the
   * game when a power up is gotten. Also makes the random number generator, as well as calling a
   * method to make all the different powerups
   *
   * @param gamePaddle The paddle being used to play the game
   * @param gameBall   The ball being used to play the game
   */
  public PowerUpManager(Paddle gamePaddle, Ball gameBall) {
    ball = gameBall;
    paddle = gamePaddle;
    makePowerUps();
    random = new Random();
  }

  // makes all of the instances of power ups
  private void makePowerUps() {
    ball1Hit = new PowerUpMakeBallDestroyIn1Hit(ball, POWER_UP_DURATION);
    ballFaster = new PowerUpMakeBallFaster(ball, POWER_UP_DURATION);
    paddleLarger = new PowerUpMakePaddleLarger(paddle, POWER_UP_DURATION);
  }

  /**
   * Set a new ball, for when the ball goes out of bounds/a cheat code is used
   *
   * @param newBall the new ball
   */
  public void setBall(Ball newBall) {
    ball = newBall;
    ballFaster.setBall(newBall);
    ball1Hit.setBall(newBall);
  }

  /**
   * Set a new paddle, for when the paddle is reset using a cheat code
   *
   * @param newPaddle the new ball
   */
  public void setPaddle(Paddle newPaddle) {
    paddle = newPaddle;
    paddleLarger.setPaddle(newPaddle);
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

  // enacts a random good powerup
  private void getRandomGoodPowerUp() {
    double randomNum = random.nextDouble();
    if (randomNum < PADDLE_BIGGER_CHANCE) {
      paddleLarger.enactPowerUp();
    } else if (PADDLE_BIGGER_CHANCE < randomNum) {
      ball1Hit.enactPowerUp();
    }
  }

  // enacts a random bad powerup
  private void getRandomBadPowerUp() {
    ballFaster.enactPowerUp();
  }
}

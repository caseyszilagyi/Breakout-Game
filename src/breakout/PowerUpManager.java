package breakout;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Random;

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

    public PowerUpManager(Paddle gamePaddle, Ball gameBall){
        paddle = gamePaddle;
        ball = gameBall;
        normalPaddleWidth = paddle.gameObject.getWidth();
        normalBallSpeed = ball.YVelocity;
        random = new Random();
    }

    public void dealWithPowerUp(PowerUp powerUp){
        if(powerUp instanceof GoodPowerUp) {
            getRandomGoodPowerUp();
        }
        else {
            getRandomBadPowerUp();
        }
    }


    public void getRandomGoodPowerUp(){
        double randomNum = random.nextDouble();
            makeBallDestroyBricksIn1Hit();
        if(randomNum<PADDLE_BIGGER_CHANCE){
            makePaddleBigger();
        }
        else if(PADDLE_BIGGER_CHANCE < randomNum) {
            makeBallDestroyBricksIn1Hit();
        }
    }

    public void getRandomBadPowerUp(){
        makeBallFaster();
    }


    public void makeBallFaster(){
        ball.YVelocity = ball.YVelocity * BALL_SPEED_UP_MULTIIPLIER;
        ball.XVelocity = ball.XVelocity * BALL_SPEED_UP_MULTIIPLIER;
        PauseTransition endPowerUp = new PauseTransition(Duration.millis(POWER_UP_DURATION));
        endPowerUp.setOnFinished(makeBallFasterEventHandler());
        endPowerUp.play();
    }

    public EventHandler makeBallFasterEventHandler(){
        EventHandler handle = new EventHandler() {
            @Override
            public void handle(Event event) {
                ball.YVelocity = ball.YVelocity / BALL_SPEED_UP_MULTIIPLIER;
                ball.XVelocity = ball.XVelocity / BALL_SPEED_UP_MULTIIPLIER;
            }
        };
        return handle;
    }

    public void makePaddleBigger(){
        paddle.gameObject.setWidth(PADDLE_POWERUP_WIDTH);
        PauseTransition endPowerUp = new PauseTransition(Duration.millis(POWER_UP_DURATION));
        endPowerUp.setOnFinished(makePaddleBiggerEventHandler());
        endPowerUp.play();
    }

    public EventHandler makePaddleBiggerEventHandler(){
        EventHandler handle = new EventHandler() {
            @Override
            public void handle(Event event) {
                paddle.gameObject.setWidth(normalPaddleWidth);
            }
        };
        return handle;
    }

    public void makeBallDestroyBricksIn1Hit(){
        ball.hasPowerUp = true;
        PauseTransition endPowerUp = new PauseTransition(Duration.millis(POWER_UP_DURATION));
        endPowerUp.setOnFinished(makeBallDestroyBricksIn1HitEventHandler());
        endPowerUp.play();
    }

    public EventHandler makeBallDestroyBricksIn1HitEventHandler(){
        EventHandler handle = new EventHandler() {
            @Override
            public void handle(Event event) {
                ball.hasPowerUp = false;
            }
        };
        return handle;
    }

}

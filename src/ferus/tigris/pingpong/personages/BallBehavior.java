package ferus.tigris.pingpong.personages;

import ferus.tigris.pingpong.AbstractBehavior;
import ferus.tigris.pingpong.AbstractView;
import ferus.tigris.pingpong.GameView;
import ferus.tigris.pingpong.Vector2D;
import android.graphics.Point;
import android.graphics.Rect;

public class BallBehavior extends DefaultBehavior {
	GameView gameView;
	private AbstractView ball; 
	private Point position = new Point(0, 20);
	private Vector2D vector = new Vector2D(10, 0);
	private long lastDecelerationTime = 0;
	private int rotate = 0;
	
	final private int defaultSpeed = 4; 
	final private int minSpeed = 3; 
	final private int maxSpeed = 30; 
	
	public BallBehavior(GameView view, AbstractView ball) {
		this.ball = ball;
		gameView = view;

		position = new Point(gameView.getWidth()/2, 0);
		vector = new Vector2D(defaultSpeed, defaultSpeed);
	}

	private void deceleration() {
		if((vector.y > defaultSpeed)||(vector.y < -defaultSpeed))
			vector.y *= 0.9;
	}

	private void rotate() {
		vector.x += vector.x*rotate;
	}
	
	public void update() {
		position.offset(vector.x, vector.y);
		validate();
		ball.setPosition(position);
		ball.setVector(vector);
		
		long startTime = System.currentTimeMillis();
		if(startTime - lastDecelerationTime > 300) {
			lastDecelerationTime = System.currentTimeMillis();
			deceleration();
			rotate();
		}
	}
	
	private int limitMaxSpeed(int speed) {
		int result = speed;
		if(speed > maxSpeed) {
			result = maxSpeed;
		} else if(speed < -maxSpeed) {
			result = -maxSpeed;
		}
		return result;
	}

	private int limitMinSpeed(int speed) {
		int result = speed;
		if((speed > -minSpeed)&&(speed < 0)) {
			result = -minSpeed;
		} else if((speed < minSpeed)&&(speed > 0)) {
			result = minSpeed;
		}
		return result;
	}
	
	private int limitSpeed(int speed) {
		return limitMinSpeed(limitMaxSpeed(speed));
	}
	
	private void checkOutRange() {
		if(position.x + ball.rect().width() > gameView.getWidth()) {
			vector.x = -vector.x;
			position.x = gameView.getWidth() - ball.rect().width();
		} else
		if(position.x < 0) {
			vector.x = -vector.x;
			position.x = 0;
		}
		
		if(position.y + ball.rect().height() > gameView.getHeight()) {
			vector.y = 0;
			vector.x = 0;
			position.y = gameView.getHeight() - ball.rect().height();
		} else
		if(position.y < 0) {
			vector.y = -vector.y;
			position.y = 0;
			rotate = 0;
		}		
	}
	
	private void validate() {
		vector.x = limitMaxSpeed(vector.x);
		vector.y = limitSpeed(vector.y);
		checkOutRange();
	}

	public AbstractView view() {
		return ball;
	}
	
	public void toHitTheBall(RacketBehavior racket) {
		if(vector.y > 0) {
			vector.y = -vector.y - racket.strength();
			vector.x -= racket.speed();
			validate();
		}
	}

	public Rect rect() {
		return new Rect(position.x, position.y, position.x + ball.rect().width(), position.y + ball.rect().height());
	}

	public void stop() {
		vector.y = 0;
	}

	public void collide(AbstractBehavior personage) {
		personage.collideWithBall(this);
	}

	public void collideWithOther(AbstractBehavior racket) {
		vector.y = -vector.y;
		vector.x = -vector.x;
		validate();
	}

	public void collideWithRacket(RacketBehavior racket) {
		toHitTheBall(racket);
	}

	public boolean isDied() {
		return vector.y == 0;
	}

	public void onTouch(int x, int y) {
	}
}

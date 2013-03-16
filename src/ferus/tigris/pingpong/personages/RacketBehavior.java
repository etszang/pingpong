package ferus.tigris.pingpong.personages;

import ferus.tigris.pingpong.AbstractBehavior;
import ferus.tigris.pingpong.AbstractView;
import ferus.tigris.pingpong.GameView;
import android.graphics.Point;
import android.graphics.Rect;

public class RacketBehavior extends DefaultBehavior {
	GameView gameView;
	private AbstractView racket; 
	private Point position = new Point(0, 20);
	private Point lastPosition = new Point(0, 20);
	private int speed;
	private int strength;
	private long lastDecelerationTime = 0;

	public RacketBehavior(GameView view, AbstractView racketView) {
		racket = racketView;
		gameView = view;

		position = new Point(gameView.getWidth()/2, gameView.getHeight() - racket.rect().height());
	}

	public void update() {
		validateCoordsAndChangeVector();
		racket.setPosition(position);
		if(!position.equals(lastPosition)) {
			long startTime = System.currentTimeMillis();
			if(startTime - lastDecelerationTime > 300) {
				speed = strength = 0;
				lastPosition.set(position.x, position.y); 
				lastDecelerationTime = startTime;
			}
		}
	}

	private void validateCoordsAndChangeVector() {
		if(position.x + racket.rect().width() > gameView.getWidth()) {
			position.x = gameView.getWidth() - racket.rect().width();
		}
		if(position.x < 0) {
			position.x = 0;
		}
		int minY = 2*gameView.getHeight()/3;
		if(position.y < minY) {
			position.y = minY;
		}
	}

	public void moveTo(int x, int y) {
		speed = lastPosition.x - x;
		strength = lastPosition.y - y;
		lastPosition.set(x,  y);
		
		position.set(x - racket.rect().width()/2, y - 40);
		update();
	}
	
	public AbstractView view() {
		return racket;
	}

	public Rect rect() {
		return new Rect(position.x, position.y, position.x + racket.rect().width(), position.y + racket.rect().height());
	}

	public int speed() {
		return speed;
	}

	public int strength() {
		return strength;
	}

	public void collide(AbstractBehavior ball) {
		ball.collideWithRacket(this);
	}

	public void collide(RacketBehavior racket) {
	}

	public boolean isDied() {
		return false;
	}

	public void onTouch(int x, int y) {
		moveTo(x, y);
	}

	public void collideWithBall(BallBehavior ball) {
		collideWithOther(ball);
	}

}

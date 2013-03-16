package ferus.tigris.pingpong.personages;

import java.util.Random;

import ferus.tigris.pingpong.AbstractView;
import ferus.tigris.pingpong.GameView;
import ferus.tigris.pingpong.Vector2D;

import android.graphics.Point;

public class EasyMarkBehavior extends MarkBehavior {
	GameView gameView;
	
	private Vector2D vector = new Vector2D(10, 0);
	private int maxSpeed = 10;

	public EasyMarkBehavior(GameView gameView, AbstractView view, int maxSpeed) {
		super(view);
		this.gameView = gameView;
		this.maxSpeed = maxSpeed;

		Random rnd = new Random();
		position = new Point(rnd.nextInt(gameView.getWidth()), rnd.nextInt(gameView.getHeight()));
		vector = new Vector2D(rnd.nextInt(maxSpeed) - maxSpeed/2, rnd.nextInt(maxSpeed) - maxSpeed/2);
	}

	@Override
	public void update() {
		position.offset(vector.x, vector.y);
		validateCoordsAndChangeVector();
		view().setPosition(position);
		view().setVector(vector);
	}

	private void validateCoordsAndChangeVector() {
		Random rnd = new Random();
		int xSpeed = -rnd.nextInt(maxSpeed);
		int ySpeed = -rnd.nextInt(maxSpeed);

		if(rect().right > gameView.getWidth()) {
			vector.x = xSpeed;
			vector.y = ySpeed;
		}
		if(rect().bottom > gameView.getHeight()) {
			vector.y = ySpeed;
		}
		if(position.x < 0) {
			vector.x = -xSpeed;
		}
		if(position.y < 0) {
			vector.y = -ySpeed;
		}
	}

}

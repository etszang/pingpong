package ferus.tigris.pingpong.personages;

import java.util.Random;

import ferus.tigris.pingpong.AbstractView;
import ferus.tigris.pingpong.GameView;

import android.graphics.Point;

public class StaticMarkBehavior extends MarkBehavior {
	GameView gameView;

	public StaticMarkBehavior(GameView gameView, AbstractView view) {
		super(view);
		this.gameView = gameView;

		Random rnd = new Random();
		position = new Point(rnd.nextInt(gameView.getWidth() - view().rect().width()), rnd.nextInt(gameView.getHeight()/2));
	}

	@Override
	public void update() {
		view().setPosition(position);
	}
}

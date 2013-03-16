package ferus.tigris.pingpong.personages;

import ferus.tigris.pingpong.AbstractView;
import ferus.tigris.pingpong.personages.BallBehavior;
import android.graphics.Point;
import android.graphics.Rect;

public class MarkBehavior extends DefaultBehavior{

	private AbstractView view;
	protected Point position = new Point(0, 20);
	private boolean die = false;
	
	public MarkBehavior(AbstractView view) {
		super();
		this.view = view;
	}

	@Override
	public AbstractView view() {
		return view;
	}

	@Override
	public Rect rect() {
		return new Rect(position.x, position.y, position.x + view.rect().width(), position.y + view.rect().height());
	}

	@Override
	public void update() {
		view().setPosition(position);
	}

	@Override
	public void collideWithBall(BallBehavior ball) {
		die = true;
	}

	@Override
	public boolean isDied() {
		return die ;
	}

}
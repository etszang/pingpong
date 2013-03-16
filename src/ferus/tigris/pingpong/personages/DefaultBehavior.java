package ferus.tigris.pingpong.personages;

import ferus.tigris.pingpong.AbstractBehavior;
import ferus.tigris.pingpong.AbstractView;
import ferus.tigris.pingpong.personages.BallBehavior;
import ferus.tigris.pingpong.personages.RacketBehavior;
import android.graphics.Rect;

public class DefaultBehavior implements AbstractBehavior {

	public void update() {
	}

	public AbstractView view() {
		return null;
	}

	public Rect rect() {
		return null;
	}

	public boolean isDied() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onTouch(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void collide(AbstractBehavior element) {
		element.collideWithOther(this);
	}

	public void collideWithOther(AbstractBehavior element) {
		// TODO Auto-generated method stub
	}

	public void collideWithBall(BallBehavior ball) {
		collideWithOther(ball);
	}

	public void collideWithRacket(RacketBehavior racket) {
		collideWithOther(racket);
	}



}

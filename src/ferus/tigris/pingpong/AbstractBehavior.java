package ferus.tigris.pingpong;

import ferus.tigris.pingpong.personages.BallBehavior;
import ferus.tigris.pingpong.personages.RacketBehavior;
import android.graphics.Rect;

public interface AbstractBehavior {
	abstract public void update();
	abstract public AbstractView view();
	abstract public Rect rect();
	abstract public boolean isDied();
	
	public abstract void onTouch(int x, int y);
	abstract public void collide(AbstractBehavior element);
	
	abstract public void collideWithOther(AbstractBehavior element);
	abstract public void collideWithBall(BallBehavior ball);
	abstract public void collideWithRacket(RacketBehavior racket);
}

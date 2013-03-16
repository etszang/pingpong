package ferus.tigris.pingpong;

import ferus.tigris.pingpong.personages.BallBehavior;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BallBuilder implements AbstractBehaviorBuilder {

	public BallBuilder() {
	}

	public BallBehavior create(GameManager manager) {
		GameView view = manager.view();
		Bitmap sprite = BitmapFactory.decodeResource(view.getResources(), R.drawable.ball);
		AbstractView personage = new Sprite(sprite, 200, 4, 4, (float) 1.5 * view.getScale());
		BallBehavior behavior = new BallBehavior(view, personage);
		view.addView(behavior);

		manager.addListenerOnCollideEvent(behavior);
		return behavior;
	}
}

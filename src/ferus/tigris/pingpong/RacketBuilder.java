package ferus.tigris.pingpong;

import ferus.tigris.pingpong.personages.RacketBehavior;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RacketBuilder implements AbstractBehaviorBuilder {

	public RacketBuilder() {
	}

	public RacketBehavior create(GameManager manager) {
		GameView view = manager.view();
		Bitmap sprite = BitmapFactory.decodeResource(view.getResources(), R.drawable.racket);
		AbstractView personage = new RacketView(sprite);
		RacketBehavior behavior = new RacketBehavior(view, personage);
		manager.addListenerOnTouchEvent(behavior);
		manager.addListenerOnCollideEvent(behavior);
		view.addView(behavior);
		return behavior;
	}

}

package ferus.tigris.pingpong;

import ferus.tigris.pingpong.personages.MarkBehavior;
import ferus.tigris.pingpong.personages.StaticMarkBehavior;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class StaticMarkBuilder implements AbstractBehaviorBuilder {

	public StaticMarkBuilder() {
	}

	public MarkBehavior create(GameManager manager) {
		GameView view = manager.view();
		Bitmap sprite = BitmapFactory.decodeResource(view.getResources(), R.drawable.girl);
		AbstractView personage = new Sprite(sprite, 200, 4, 3, 1 * view.getScale());
		MarkBehavior behavior = new StaticMarkBehavior(view, personage);
		view.addView(behavior);
		
		manager.addListenerOnCollideEvent(behavior);
		return behavior;
	}
}

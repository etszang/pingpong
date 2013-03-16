package ferus.tigris.pingpong;

import ferus.tigris.pingpong.personages.EasyMarkBehavior;
import ferus.tigris.pingpong.personages.MarkBehavior;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DynamicBehaviorBuilder implements AbstractBehaviorBuilder {

	private int maxSpeed = 10;
	
	public MarkBehavior create(GameManager manager) {
		GameView view = manager.view();
		Bitmap sprite = BitmapFactory.decodeResource(view.getResources(), R.drawable.angel);
		AbstractView personage = new Sprite(sprite, 200, 4, 3, 1 * view.getScale());
		EasyMarkBehavior behavior = new EasyMarkBehavior(view, personage, maxSpeed );
		view.addView(behavior);

		manager.addListenerOnCollideEvent(behavior);
		return behavior;
	}

}

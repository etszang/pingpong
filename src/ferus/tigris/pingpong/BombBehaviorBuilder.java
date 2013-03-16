package ferus.tigris.pingpong;

import ferus.tigris.pingpong.personages.BombBehavior;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BombBehaviorBuilder implements AbstractBehaviorBuilder {

	public BombBehavior create(GameManager manager) {
		GameView view = manager.view();
		Bitmap sprite = BitmapFactory.decodeResource(view.getResources(), R.drawable.bomb);
		AbstractView personage = new Sprite(sprite, 80, 4, 8, (float) 0.3);
		BombBehavior behavior = new BombBehavior(view, personage);
		view.addView(behavior);
		
		manager.addListenerOnCollideEvent(behavior);
		return behavior;
	}

}

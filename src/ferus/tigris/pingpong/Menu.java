package ferus.tigris.pingpong;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Menu implements AbstractView{
	private GameView view;
	private Bitmap bmp;
	private int x;
	private int y;
	private int width;
	private int height;

	public Menu(GameView gameView) {
		view = gameView;
		bmp = BitmapFactory.decodeResource(view.getResources(), R.drawable.menu);

		width = bmp.getWidth();
		height = bmp.getHeight();
	}

	public void draw(Canvas canvas) {
		x = view.getWidth() - width;
		y = 0;

		Rect src = new Rect(0, 0, width, height);
		Rect dst = new Rect(x, 0, x + width, y + height);
		canvas.drawBitmap(bmp, src, dst, null);
	}

	public boolean isCollition(float xTouch, float yTouch) {
		return xTouch > x && xTouch < x + width && yTouch > y && yTouch < y + height;
	}

	public void setPosition(Point position) {
		x = position.x;
		y = position.y;
	}

	public void setVector(Vector2D vector) {
	}

	public Rect rect() {
		return new Rect(x, y, x + width, y + height);
	}

}

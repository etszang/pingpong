package ferus.tigris.pingpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class RacketView implements AbstractView{
	private int width;
	private int height;
	private Point position = new Point(0, 0);
	private Bitmap sprite;

	public RacketView(Bitmap bmp) {
		sprite = bmp;

		width = sprite.getWidth();
		height = sprite.getHeight();
	}

	private void update() {
	}

	private Rect spriteRect() {
		int srcX = 0;
		int srcY = 0;
		return new Rect(srcX, srcY, srcX + width, srcY + height);
	}

	private Rect animationRect() {
		return new Rect(position.x, position.y, position.x + width, position.y + height);
	}

	public void draw(Canvas canvas) {
		update();

		Rect src = spriteRect();
		Rect dst = animationRect();
		canvas.drawBitmap(sprite, src, dst, null);
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setVector(Vector2D vector) {
	}

	public Rect rect() {
		return new Rect(position.x, position.y, position.x + width, position.y + height);
	}


}

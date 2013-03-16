package ferus.tigris.pingpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class BallView implements AbstractView{
	private int width;
	private int height;
	private Point position = new Point(0, 0);
	private Vector2D vector = new Vector2D(0, 0);
	private Bitmap sprite;

	private int currentFrame = 0;
	private static final int BMP_ROWS = 4;
	private static final int BMP_COLUMNS = 3;

	public BallView(Bitmap bmp) {
		sprite = bmp;

		width = sprite.getWidth() / BMP_COLUMNS;
		height = sprite.getHeight() / BMP_ROWS;
}

	private void update() {
		currentFrame  = ++currentFrame % BMP_COLUMNS;
	}

	private int getAnimationRow() {
		int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
		double dirDouble = (Math.atan2(vector.x, vector.y) / (Math.PI / 2) + 2);
		int direction = (int) Math.round(dirDouble) % BMP_ROWS;
		return DIRECTION_TO_ANIMATION_MAP[direction];
	}

	private Rect spriteRect() {
		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
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
		this.vector = vector;
	}

	public Rect rect() {
		return new Rect(position.x, position.y, position.x + width, position.y + height);
	}

}

package ferus.tigris.pingpong;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Sprite implements AbstractView{
	private int width;
	private int height;
	private Point position = new Point(0, 0);
	private Vector2D vector = new Vector2D(0, 0);

	private Bitmap sprite;

	private int currentFrame = 0;
	private int spriteRowCount = 4;
	private int spriteColumnCount = 3;
	
	private long delay = 100;
	private float scale;

	public Sprite(Bitmap bmp, long delay, int rowCount, int columnCount, float scale) {
		sprite = bmp;
		spriteRowCount = rowCount;
		spriteColumnCount = columnCount;
		this.delay = delay;
		this.scale = scale;
		
		width = sprite.getWidth() / spriteColumnCount;
		height = sprite.getHeight() / spriteRowCount;
		
}

	private long lastTimeOfHitBall = 0; 
	private void update() {
		long time = System.currentTimeMillis();
		if(time - lastTimeOfHitBall > delay) {
			currentFrame  = ++currentFrame % spriteColumnCount;
			lastTimeOfHitBall = time;
		}
	}

	private int getAnimationRow() {
		int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
		double dirDouble = (Math.atan2(vector.x, vector.y) / (Math.PI / 2) + 2);
		int direction = (int) Math.round(dirDouble) % spriteRowCount;
		return DIRECTION_TO_ANIMATION_MAP[direction];
	}

	private Rect spriteRect() {
		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
		return new Rect(srcX, srcY, srcX + width, srcY + height);
	}

	private Rect animationRect(float scale) {
		return new Rect(position.x, position.y, position.x + (int) (width * scale), position.y + (int)(height * scale));
	}

	public void draw(Canvas canvas) {
		update();

		Rect src = spriteRect();
		Rect dst = animationRect(scale);
		canvas.drawBitmap(sprite, src, dst, null);
	}

	public void setPosition(Point position) {
		this.position.set(position.x, position.y);
	}

	public void setVector(Vector2D vector) {
		this.vector.set(vector.x, vector.y);
	}

	public Rect rect() {
		return animationRect(scale);
	}

}

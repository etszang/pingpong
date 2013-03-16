package ferus.tigris.pingpong;

import ferus.tigris.pingpong.GameManager;
import ferus.tigris.pingpong.Vector2D;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class StatisticView implements AbstractView {
	private GameManager gameManager;
	private float scaledDensity;

	public StatisticView(GameManager gameManager, float scaledDensity) {
		this.gameManager = gameManager;
		this.scaledDensity = scaledDensity;
	}

	public Bitmap getBitmap() {
		Bitmap bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		paint.setTextSize(20);
		canvas.drawText(String.valueOf(gameManager.scope()), 10, 20, paint);
		return bitmap;
	}

	public void draw(Canvas canvas) {
		Bitmap bmp = getBitmap();
		Rect src = new Rect(0, 0, (int)(bmp.getWidth() * scaledDensity), (int)(bmp.getHeight()*scaledDensity));
		canvas.drawBitmap(bmp, src, src, null);
	}

	public void setPosition(Point position) {
		
	}

	public void setVector(Vector2D vector) {
		
	}

	public Rect rect() {
		return new Rect(0,0, 0, 0);
	}

}

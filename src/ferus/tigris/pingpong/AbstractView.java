package ferus.tigris.pingpong;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public interface AbstractView {
	abstract public void draw(Canvas canvas);
	abstract public void setPosition(Point position);
	abstract public void setVector(Vector2D vector);
	abstract public Rect rect();
}

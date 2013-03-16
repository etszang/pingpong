package ferus.tigris.pingpong;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameView extends SurfaceView implements OnTouchListener {
	private SurfaceHolder holder;
	private Context gameField;
	
	final private GameDrawManager gameDrawManager = new GameDrawManager(this);
	final private GameManager gameManager = new GameManager(this);
	
	private List<AbstractBehavior> sprites = new ArrayList<AbstractBehavior>();
	private Menu menu = new Menu(this);
	private StatisticView statistic = null;
		
	private SoundPool sounds;
	private int sExplosion;

	private Rect backgroundSrcRect;
	private Rect backgroundDstRect;
	private Bitmap backgroundImage;
	
	private float scaledDensity = 1;

	public GameView(Context context, float scaledDensity) {
		super(context);
		gameField = context;
		this.scaledDensity = scaledDensity;
		
		holder = getHolder();
		setOnTouchListener(this);
		holder.addCallback(new SurfaceHolder.Callback() {

			public void surfaceDestroyed(SurfaceHolder holder) {
				onSufaceDestroy();
			}

			public void surfaceCreated(SurfaceHolder holder) {
				onSufaceCreate();
			}

			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			}
		});
		
		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
		sExplosion = sounds.load(context, R.raw.explosion, 1);
	}
	
	private void onSufaceCreate() {
		statistic = new StatisticView(gameManager, scaledDensity);

		backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		backgroundSrcRect = new Rect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
		backgroundDstRect = new Rect(0, 0, getWidth(), getHeight());

		gameDrawManager.setRunning(true);
		gameDrawManager.start();
		gameManager.setRunning(true);
		gameManager.start();
		
		
	}
	
	private void waitThread(Thread th) {
		boolean retry = true;
		while (retry) {
			try {
				th.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	private void onSufaceDestroy() {
		gameDrawManager.setRunning(false);
		gameManager.setRunning(false);
		waitThread(gameDrawManager);
		waitThread(gameManager);
	}
	
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(backgroundImage, backgroundSrcRect, backgroundDstRect, null);
		
		for(AbstractBehavior sprite: sprites) {
			sprite.view().draw(canvas);
		}
		menu.draw(canvas);
		statistic.draw(canvas);
	}

	public void addView(AbstractBehavior sprite) {
		sprites.add(sprite);
	}

	public void removeView(AbstractBehavior view) {
		sprites.remove(view);
		sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
	}

	public boolean onTouch(View v, MotionEvent event) {
		gameManager.onTouch(event.getX(), event.getY());
		if(menu.isCollition(event.getX(),event.getY())) {
			((GameFieldActivity)gameField).menu();
		}
		return true;
	}

	public float getScale() {
		return scaledDensity;
	}
}
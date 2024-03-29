package ferus.tigris.pingpong;

import android.graphics.Canvas;

public class GameDrawManager extends Thread {
	static final long FPS = 50;
	private GameView view;
	private boolean running = false;

	public GameDrawManager (GameView view) {
		this.view = view;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	public void run() {
		long ticksPS = 1000 / FPS;

		while (running) {
			long startTime = System.currentTimeMillis();
			draw();
			long sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
			try {
				sleep(sleepTime < 10 ? 10 : sleepTime);
			} catch (InterruptedException e) {

			}
		}
	}
	
	private void draw() {
		Canvas canvas = null;
		try {
			canvas = view.getHolder().lockCanvas();
			synchronized (view.getHolder()) {
				if(canvas != null)
					view.onDraw(canvas);
			}
		} finally {
			if (canvas  != null) {
				view.getHolder().unlockCanvasAndPost(canvas);
			}
		}
		
	}
}  
package ferus.tigris.pingpong;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Rect;

import ferus.tigris.pingpong.levels.LevelManager;


public class GameManager extends Thread {
	private GameView view;
	private boolean running = false;
	
	private List<AbstractBehavior> updateListeners = new ArrayList<AbstractBehavior>();
	private List<AbstractBehavior> collideListeners = new ArrayList<AbstractBehavior>();
	private LevelManager levels = new LevelManager(this);
	private List<AbstractBehavior> touchListeners = new ArrayList<AbstractBehavior>();
	
	public void setRunning(boolean run) {
		running = run;
	}

	public GameManager(GameView gameView) {
		view = gameView;
	}
	
	public void addPersonage(AbstractBehavior mark) {
		updateListeners.add(mark);
	}
	
	public void removePersonage(AbstractBehavior mark) {
		view.removeView(mark);
		updateListeners.remove(mark);
		collideListeners.remove(mark);
		touchListeners.remove(mark);
	}
		
	public void clear() {
		for(AbstractBehavior mark: updateListeners) {
			view.removeView(mark);
		}
		updateListeners.clear();
		touchListeners.clear();
		collideListeners.clear();
	}
	
	private void trySleep(long l) {
		try {
			sleep(l);
		} catch (InterruptedException e) {

		}		
	}
	
	public void run() {
		long ticksPS = 50;

		levels.start();
			
		while (running) {
	
				long startTime = System.currentTimeMillis();
				synchronized (view.getHolder()) {
					update();
					levels.check();
				}
				long sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
				trySleep(sleepTime < 10 ? 10 : sleepTime);
		}
	}

	private void update() {
		for(AbstractBehavior ball: updateListeners) {
			ball.update();
		}
		removeDiedPersonages();	
		checkCollisions();
	}

	private void removeDiedPersonages() {
		for(AbstractBehavior mark: updateListeners) {
			if(mark.isDied()) {
				levels.died(mark);
				break;
			}
		}
	}

	public void onTouch(float x, float y) {
		for(AbstractBehavior b: touchListeners) {
			b.onTouch((int)x, (int)y);
		}
		synchronized (view.getHolder()) {
			levels.check();
		}
		checkCollisions();
	}
	
	protected void checkCollisions() {
		for(AbstractBehavior element: updateListeners) {
			for(AbstractBehavior listener: collideListeners) {
				if(element != listener) {
					if(Rect.intersects(element.rect(), listener.rect())) {
						element.collide(listener);
					}
				}
			}
		}
	}

	public int scope() {
		return 0;
	}

	public void addListenerOnTouchEvent(AbstractBehavior listener) {
		touchListeners.add(listener);
	}

	public void addListenerOnCollideEvent(AbstractBehavior listener) {
		collideListeners.add(listener);
	}

	public GameView view() {
		return view;
	}

}

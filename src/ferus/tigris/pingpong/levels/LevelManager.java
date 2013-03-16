package ferus.tigris.pingpong.levels;

import ferus.tigris.pingpong.AbstractBehavior;
import ferus.tigris.pingpong.GameManager;

public class LevelManager {
	private AbstractLevel level;
	private GameManager gameManager;
	private int speed = 1;
	private int marksCount = 2;

	public LevelManager(GameManager gameManager) {
		level = new StaticLevel(this, gameManager);
		this.gameManager = gameManager;
	}

	public void start() {
		level.start();
	}

	public void setLevel(AbstractLevel newLevel) {
		level = newLevel;
	}

	public void complite() {
		setLevel(level.nextLevel());
		gameManager.clear();
		level.start();
	}

	public void fail() {
		setLevel(level.clone());
		gameManager.clear();
		level.start();
	}

	public void check() {
		level.check();
	}
	
	public int maxSpeed() {
		return speed;
	}
	
	public int marksCount() {
		return marksCount;
	}
	
	public void incComplexity() {
		marksCount += 2;
		speed += 5;
	}

	public void died(AbstractBehavior personage) {
		level.died(personage);
	}

}

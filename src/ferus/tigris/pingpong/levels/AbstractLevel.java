package ferus.tigris.pingpong.levels;

import ferus.tigris.pingpong.AbstractBehavior;

public interface AbstractLevel {
	abstract public AbstractLevel nextLevel();
	abstract public AbstractLevel clone();
	abstract public void start();
	abstract public void check();
	public abstract void died(AbstractBehavior personage);
}

package ferus.tigris.pingpong.levels;

import java.util.ArrayList;
import java.util.List;

import ferus.tigris.pingpong.AbstractBehavior;
import ferus.tigris.pingpong.BallBuilder;
import ferus.tigris.pingpong.GameManager;
import ferus.tigris.pingpong.RacketBuilder;
import ferus.tigris.pingpong.StaticMarkBuilder;
import ferus.tigris.pingpong.personages.BallBehavior;
import ferus.tigris.pingpong.personages.MarkBehavior;
import ferus.tigris.pingpong.personages.RacketBehavior;

public class StaticLevel implements AbstractLevel {
	private GameManager gameManager;
	private LevelManager levelManager;
	private List<MarkBehavior> marks = new ArrayList<MarkBehavior>();
	private BallBehavior ball;
	private RacketBehavior racket;

	public StaticLevel(LevelManager levelManager, GameManager gameManager) {
		this.gameManager = gameManager;
		this.levelManager = levelManager;
	}

	public AbstractLevel nextLevel() {
		return new DynamicAngelsLevel(levelManager, gameManager);
	}

	public AbstractLevel clone() {
		return new StaticLevel(levelManager, gameManager);
	}

	public void start() {
		RacketBuilder racketBuilder = new RacketBuilder();
		StaticMarkBuilder markBuilder = new StaticMarkBuilder();
		BallBuilder ballBuilder = new BallBuilder();

		ball = ballBuilder.create(gameManager);
		gameManager.addPersonage(ball);
		
		racket = racketBuilder.create(gameManager);
		gameManager.addPersonage(racket);
		
		for(int i = 0; i < levelManager.marksCount(); i++) {
			MarkBehavior mark = markBuilder.create(gameManager);
			marks.add(mark);
			gameManager.addPersonage(mark);
		}
	}

	public void check() {
		if(isLevelComplited()) {
			levelManager.complite();
			return;
		}
	}

	private boolean isLevelComplited() {
		if(ball.isDied()) {
			return false;
		}
		return isAllMarksAreDied(marks);
	}

	protected boolean isAllMarksAreDied(List<MarkBehavior> marks) {
		for(MarkBehavior mark: marks) {
			if(!mark.isDied()) {
				return false;
			}
		}
		return true;
	}

	public void died(AbstractBehavior personage) {
		for(MarkBehavior mark: marks) {
			if(personage == mark) {
				marks.remove(mark);
				gameManager.removePersonage(mark);
				break;
			}
		}
		if(personage == ball) {
			levelManager.fail();
		}
		
	}

}

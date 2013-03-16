package ferus.tigris.pingpong.levels;

import java.util.ArrayList;
import java.util.List;

import ferus.tigris.pingpong.AbstractBehavior;
import ferus.tigris.pingpong.BallBuilder;
import ferus.tigris.pingpong.BombBehaviorBuilder;
import ferus.tigris.pingpong.DynamicBehaviorBuilder;
import ferus.tigris.pingpong.GameManager;
import ferus.tigris.pingpong.RacketBuilder;
import ferus.tigris.pingpong.personages.BallBehavior;
import ferus.tigris.pingpong.personages.BombBehavior;
import ferus.tigris.pingpong.personages.MarkBehavior;
import ferus.tigris.pingpong.personages.RacketBehavior;

public class BadMarksLevel extends StaticLevel {
	private GameManager gameManager;
	private LevelManager levelManager;
	private List<MarkBehavior> marks = new ArrayList<MarkBehavior>();
	private List<BombBehavior> bombs = new ArrayList<BombBehavior>();
	private BallBehavior ball;
	private RacketBehavior racket;

	public BadMarksLevel(LevelManager levelManager, GameManager gameManager) {
		super(levelManager, gameManager);
		this.gameManager = gameManager;
		this.levelManager = levelManager;
	}

	@Override
	public AbstractLevel nextLevel() {
		return new StaticLevel(levelManager, gameManager);
	}

	@Override
	public AbstractLevel clone() {
		return new BadMarksLevel(levelManager, gameManager);
	}

	@Override
	public void start() {
		BombBehaviorBuilder bombBuilder = new BombBehaviorBuilder();
		
		RacketBuilder racketBuilder = new RacketBuilder();
		DynamicBehaviorBuilder markBuilder = new DynamicBehaviorBuilder();
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
		
		for(int i = 0; i < 2; i++) {
			BombBehavior mark = bombBuilder.create(gameManager);
			bombs.add(mark);
			gameManager.addPersonage(mark);
		}
	}

	@Override
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

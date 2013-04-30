package vooga.towerdefense.model.rules;

import vooga.towerdefense.model.GameModel;

public class WinAtScoreRule extends Rule {

	/**
	 * A rule that causes the player to win once they reach 1000 points.
	 * @author JLongley
	 *
	 */
	public WinAtScoreRule(GameModel model) {
		super(model);
	}

	@Override
	protected boolean condition() {
		return (myModel.getPlayer().getAttributeManager().getAttribute("Score").getValue() > 1000);
	}

	@Override
	protected void execute() {
		myModel.win();
	}

}

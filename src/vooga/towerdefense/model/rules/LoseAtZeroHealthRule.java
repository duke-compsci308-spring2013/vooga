package vooga.towerdefense.model.rules;

import vooga.towerdefense.model.GameModel;

/**
 * A rule that causes the player to lose when he runs out of health
 * @author JLongley
 *
 */
public class LoseAtZeroHealthRule extends Rule {

	public LoseAtZeroHealthRule(GameModel model) {
		super(model);
	}

	@Override
	protected boolean condition() {
		return (myModel.getPlayer().getAttributeManager().getAttribute("Health").getValue() <= 0);
	}

	@Override
	protected void execute() {
		myModel.lose();
	}

}

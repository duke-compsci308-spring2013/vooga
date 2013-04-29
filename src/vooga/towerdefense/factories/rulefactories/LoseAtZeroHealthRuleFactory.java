package vooga.towerdefense.factories.rulefactories;

import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.rules.LoseAtZeroHealthRule;
import vooga.towerdefense.model.rules.Rule;

/**
 * A rule that causes a player to lose when he runs out of health.
 * @author JLongley
 *
 */
public class LoseAtZeroHealthRuleFactory extends RuleFactory {

	@Override
	public Rule create(GameModel model) {
		return new LoseAtZeroHealthRule(model);
	}

}

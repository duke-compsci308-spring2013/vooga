package vooga.towerdefense.factories.rulefactories;

import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.rules.NextLevelRule;
import vooga.towerdefense.model.rules.Rule;

/**
 * A Factory used to make NextLevelRules
 * @author JLongley
 *
 */
public class NextLevelRuleFactory extends RuleFactory {

	@Override
	public Rule create(GameModel model) {
		return new NextLevelRule(model);
	}

}

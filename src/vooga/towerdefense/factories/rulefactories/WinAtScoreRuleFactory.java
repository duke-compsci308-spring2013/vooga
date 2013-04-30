package vooga.towerdefense.factories.rulefactories;

import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.rules.Rule;
import vooga.towerdefense.model.rules.WinAtScoreRule;

public class WinAtScoreRuleFactory extends RuleFactory {

	@Override
	public Rule create(GameModel model) {
		return new WinAtScoreRule(model);
	}

}

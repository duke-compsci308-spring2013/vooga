package vooga.towerdefense.factories.rulefactories;

import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.rules.EnemyReachedEndRule;
import vooga.towerdefense.model.rules.Rule;

public class EnemyReachedEndRuleFactory extends RuleFactory {

	@Override
	public Rule create(GameModel model) {
		return new EnemyReachedEndRule(model);
	}

}

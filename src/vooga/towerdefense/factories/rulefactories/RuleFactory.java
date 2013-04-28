package vooga.towerdefense.factories.rulefactories;

import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.rules.Rule;

/**
 * A factory used by the loaders to make rules.
 * @author JLongley
 *
 */
public abstract class RuleFactory {

	public abstract Rule create(GameModel model);
	
}

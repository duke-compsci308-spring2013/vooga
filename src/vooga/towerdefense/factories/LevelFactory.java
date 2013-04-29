package vooga.towerdefense.factories;

import java.util.List;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.model.levels.Level;
import vooga.towerdefense.model.rules.Rule;

/**
 * A factory used by the loaders to make levels.
 * @author JLongley
 *
 */
public class LevelFactory {

	public Level create(List<Action> actions, List<Rule> rules) {
		return new Level(actions, rules);
	}
}

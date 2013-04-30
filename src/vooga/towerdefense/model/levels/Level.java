package vooga.towerdefense.model.levels;

import java.util.List;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.LevelTimerAction;
import vooga.towerdefense.model.GameModel;
import vooga.towerdefense.model.rules.Rule;

/**
 * A basic level within the game.
 * @author JLongley
 *
 */
public class Level {

	List<Action> myActions;
	List<Rule> myRules;

	/**
	 * The Level is a container for a list of actions and a list of rules that
	 * apply when this level is active. For instance, a level could have an
	 * action that spawns a wave of monsters, and a rule that gives the player
	 * extra money for every kill during this level.
	 * 
	 * @param actions
	 * @param rules
	 */
	public Level(List<Action> actions, List<Rule> rules) {
		myActions = actions;
		myRules = rules;
	}

	public void start(GameModel model) {
		model.addActions(myActions);
	}

	public List<Rule> getRules() {
		return myRules;
	}

	public List<Action> getActions() {
		return myActions;
	}

	public Integer getRemainingTime() {
		for (Action action : myActions) {
			if (action instanceof LevelTimerAction) {
				return ((LevelTimerAction) action).getRemainingTime();
			}
		}
		return null;
	}
}

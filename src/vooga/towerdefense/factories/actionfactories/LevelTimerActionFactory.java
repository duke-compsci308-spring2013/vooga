package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.LevelTimerAction;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This creates an level timer action.
 * 
 * @author Jimmy Longley
 * 
 */
public class LevelTimerActionFactory extends ActionFactory {

	private int myDuration;

	public LevelTimerActionFactory(
			@ActionAnnotation(name = "duration", value = "int") String duration) {
		super();
		myDuration = Integer.parseInt(duration);
	}

	@Override
	protected Action buildAction(GameElement e) {
		return new LevelTimerAction(myDuration);
	}

}

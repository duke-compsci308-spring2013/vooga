package vooga.towerdefense.action.actionlist;

import util.Sound;
import vooga.towerdefense.action.Action;

/**
 * An action that plays a sound whenever executed
 * 
 * @author Matthew Roy
 * 
 */
public class SoundAction extends Action {

	Sound mySound;

	public SoundAction(Sound soundAction) {
		super();
		mySound = soundAction;
	}

	/**
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void executeAction(double elapsedTime) {
		mySound.play();
	}

}

package vooga.towerdefense.factories.actionfactories;

import util.Sound;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.SoundAction;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory that creates a sound action.
 * 
 * @author Matthew Roy
 * 
 */
public class SoundActionFactory extends ActionFactory {

	Sound mySound;

	public SoundActionFactory(
			@ActionAnnotation(name = "sound path", value = "attribute") String soundPath) {
		super();
		mySound = new Sound(soundPath);
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	@Override
	protected Action buildAction(GameElement e) {
		return new SoundAction(mySound);
	}

}

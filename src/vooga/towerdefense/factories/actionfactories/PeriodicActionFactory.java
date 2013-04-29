package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.PeriodicAction;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory that creates periodicAction, the String in constructor is the desired
 * reference to cooldown e.g. maybe ATTACK_INTERVAL
 * 
 * @author Zhen Gou
 * 
 */

public class PeriodicActionFactory extends ActionFactory {

	private String myCooldownReference;

	public PeriodicActionFactory(
			@ActionAnnotation(name = "cool down", value = "attribute") String cooldownReference) {
		super();
		myCooldownReference = cooldownReference;

	}

	@Override
	protected Action buildAction(GameElement e) {
		return new PeriodicAction(e.getAttributeManager().getAttribute(
				myCooldownReference));
	}

}

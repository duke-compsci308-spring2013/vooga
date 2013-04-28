package vooga.towerdefense.action.response;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.gameelements.GameElement;

/**
 * 
 * Defines how something acts on death
 * 
 * @author Matthew Roy
 * @author Zhen Gou
 */
public class OnDeath extends Action {
	private GameElement myResponsibleElement;

	/**
	 * @param initiator
	 */
	public OnDeath(GameElement responsibleElement) {
		super();
		myResponsibleElement = responsibleElement;
		setEnabled(false);
	}

	/**
	 * Overrides from superclasses
	 * 
	 * @param elapseTime
	 */
	@Override
	public void executeAction(double elapseTime) {
		if (myResponsibleElement.getAttributeManager()
				.getAttribute(AttributeConstantsEnum.HEALTH.getStatusCode())
				.getValue() <= 0) {
			setEnabled(true);
		}

	}

	@Override
	public void update(double elapsedTime) {
		executeAction(elapsedTime);
		if (isEnabled())
			updateFollowUpActions(elapsedTime);
	}

}

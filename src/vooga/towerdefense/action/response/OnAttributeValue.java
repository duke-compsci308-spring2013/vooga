package vooga.towerdefense.action.response;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;

/**
 * Defines a game element's actions when a certain attribute value meets a
 * condition e.g. when health reaches 0 or when experience points reach 100
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * 
 */
public class OnAttributeValue extends Action {

	private Attribute myMonitoredAttribute;
	private Attribute myConditionAttribute;

	/**
	 * constructor
	 * 
	 * @param attributeWatched
	 * @param conditionAttribute
	 */
	public OnAttributeValue(Attribute attributeWatched,
			Attribute conditionAttribute) {
		myMonitoredAttribute = attributeWatched;
		myConditionAttribute = conditionAttribute;
	}

	/**
	 * Update follow up actions when conditions are met
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void executeAction(double elapsedTime) {
		if (myMonitoredAttribute.getValue() == myConditionAttribute.getValue()) {
			updateFollowUpActions(elapsedTime);
			setEnabled(false);
		}
	}

	/**
	 * Overrides from superclasses to prevent follow up action from executing
	 * prematurely.
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void update(double elapsedTime) {
		if (isEnabled()) {
			executeAction(elapsedTime);
		}
	}

}

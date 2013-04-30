package vooga.towerdefense.action.attack;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameelements.GameElement;


/**
 * Action that adds a temporary buff value to the desired attribute by an amount
 * of the reference attribute. note: the buff will be removed at the end of
 * update() in game element
 * 
 * @author Zhen Gou
 * 
 */
public class ApplyAttributeBuff extends TargetedAction {

	private Attribute myAppliedAttribute;
	private String myTargetAttribute;

	public ApplyAttributeBuff(Attribute attributeToApply,
			String targetAttributeName) {
		super();
		myTargetAttribute = targetAttributeName;
		myAppliedAttribute = attributeToApply;

	}

	public void executeAction(double elapsedTime) {
		for (GameElement e : getTargets()) {
			Attribute toChange = e.getAttributeManager().getAttribute(
					myTargetAttribute);
			if (toChange != null) {
				toChange.addToBuffValue(myAppliedAttribute.getValue());
			}
		}
	}

}

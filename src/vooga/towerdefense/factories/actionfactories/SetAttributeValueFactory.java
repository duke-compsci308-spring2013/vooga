package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.SetAttributeValue;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory that creates setAttributeValue action, takes in two Strings:
 * reference and target.
 * 
 * @author Zhen Gou
 * 
 */
public class SetAttributeValueFactory extends ActionFactory {
	private String myAttributeReference;
	private String myTargetAttribute;

	public SetAttributeValueFactory(String attributeReference,
			String targetAttribute) {
		super();
		myAttributeReference = attributeReference;
		myTargetAttribute = targetAttribute;
	}

	@Override
	protected Action buildAction(GameElement e) {
		return new SetAttributeValue(e.getAttributeManager().getAttribute(
				myAttributeReference), myTargetAttribute);
	}

}

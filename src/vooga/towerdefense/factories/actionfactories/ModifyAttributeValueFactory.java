package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.ModifyAttributeValue;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This action factory builds a ModifyAttributeValue action.
 * 
 * @author Matthew Roy
 * 
 */
public class ModifyAttributeValueFactory extends ActionFactory {

	private String myAttributeToApply;
	private String myTargetId;

	/**
	 * 
	 * @param attributeToApply
	 *            string of the attribute value it is using
	 * @param attributeIdToApply
	 */
	public ModifyAttributeValueFactory(
			@ActionAnnotation(name = "attribute to apply", value = "reference") String attributeToApply,
			@ActionAnnotation(name = "attribute id to apply", value = "reference") String attributeIdToApply) {
		super();
		myAttributeToApply = attributeToApply;
		myTargetId = attributeIdToApply;
	}

	/**
	 * Builds a ModifyAttributeValueAction that applies modifies value of
	 * targets with corresponding targetID.
	 * 
	 * @return
	 */
	@Override
	protected Action buildAction(GameElement e) {
		Attribute toApply = e.getAttributeManager().getAttribute(
				myAttributeToApply);
		return new ModifyAttributeValue(toApply, myTargetId);
	}

}

package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.ModifyPlayerAttribute;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory that builds a modify player attribute action.
 * 
 * @author Matthew Roy
 * 
 */
public class ModifyPlayerAttributeFactory extends ActionFactory {

	private String myAttributeToApply;
	private String myTargetId;

	public ModifyPlayerAttributeFactory(
			@ActionAnnotation(name = "attribute to apply", value = "reference") String attributeToApply,
			@ActionAnnotation(name = "attribute id to apply", value = "reference") String nameToApply) {
		super();

		myAttributeToApply = attributeToApply;
		myTargetId = nameToApply;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	@Override
	protected Action buildAction(GameElement e) {
		Attribute toApply = e.getAttributeManager().getAttribute(
				myAttributeToApply);
		return new ModifyPlayerAttribute(getPlayer(), toApply, myTargetId);
	}

}

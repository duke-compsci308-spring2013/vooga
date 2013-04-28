package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.ApplyAttributeBuff;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Action factory that creates an applyAttributeBuf action
 * 
 * @author Zhen Gou
 * 
 */
public class ApplyAttributeBuffFactory extends ActionFactory {
	private String myAttributeToApply;
	private String myTargetAttribute;

	/**
	 * 
	 * @param attributeToApply
	 *            string of the attribute value it is using
	 * @param targetAttribute
	 *            target attribute to apply the buff
	 */
	public ApplyAttributeBuffFactory(
			@ActionAnnotation(name = "attribute to apply", value = "attribute") String attributeToApply,
			@ActionAnnotation(name = "target attribute", value = "attribute") String targetAttribute) {
		super();
		myAttributeToApply = attributeToApply;
		myTargetAttribute = targetAttribute;
	}

	/**
	 * Builds a ApplyAttributeBuffAction that applies a temporary buff to the
	 * targets with corresponding targetID.
	 * 
	 * @return
	 */
	@Override
	public Action buildAction(GameElement e) {
		return new ApplyAttributeBuff(e.getAttributeManager().getAttribute(
				myAttributeToApply), myTargetAttribute);
	}

}

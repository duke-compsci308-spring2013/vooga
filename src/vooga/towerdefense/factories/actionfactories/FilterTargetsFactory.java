package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.FilterTargets;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory for creating filter targets action.
 * 
 * @author Zhen Gou
 * 
 */
public class FilterTargetsFactory extends ActionFactory {
	private String myAffiliationReference;
	private String myNumTargetsReference;

	/**
	 * 
	 * @param affiliationReference
	 * @param numTargetsReference
	 */
	public FilterTargetsFactory(
			@ActionAnnotation(name = "target affiliation", value = "attribute") String affiliationReference,
			@ActionAnnotation(name = "num targets", value = "attribute") String numTargetsReference) {
		super();
		myAffiliationReference = affiliationReference;
		myNumTargetsReference = numTargetsReference;

	}

	@Override
	protected Action buildAction(GameElement e) {
		return new FilterTargets(e.getAttributeManager().getAttribute(
				myAffiliationReference), e.getAttributeManager().getAttribute(
				myNumTargetsReference));
	}

}

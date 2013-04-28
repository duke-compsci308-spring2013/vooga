package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.response.OnCollision;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory that creates onCollision action.
 * 
 * @author Xu Rui
 * 
 */
public class OnCollisionFactory extends ActionFactory {

	private String myAffiliationReference;

	public OnCollisionFactory(
			@ActionAnnotation(name = "target affiliation", value = "attribute") String affiliationReference) {
		super();
		myAffiliationReference = affiliationReference;
	}

	@Override
	protected Action buildAction(GameElement e) {
		return new OnCollision(e, e.getAttributeManager().getAttribute(
				myAffiliationReference));
	}

}

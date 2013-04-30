package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.response.SelfDestruct;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This is an action factory that builds SelfDestruct actions.
 * 
 * @author Xu Rui
 * 
 */
public class SelfDestructFactory extends ActionFactory {

	private String myLifespan;

	public SelfDestructFactory(
			@ActionAnnotation(name = "lifespan", value = "attribute") String lifespan) {
		super();
		myLifespan = lifespan;
	}

	@Override
	public Action buildAction(GameElement e) {
		System.out.println("self destruct built");
		return new SelfDestruct(getMap(), e, e.getAttribute(myLifespan));
	}
}

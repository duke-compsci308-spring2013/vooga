package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.CreateElement;
import vooga.towerdefense.action.attack.LaunchProjectile;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This is an action factory that builds a CreateElement action.
 * 
 * @author Matthew Roy
 * 
 */

public class CreateElementFactory extends ActionFactory {

	private String myFactoryName;

	/**
	 * constructor
	 * 
	 * @param factory
	 */
	public CreateElementFactory(
			@ActionAnnotation(name = "Element", value = "name") String factory) {
		super();
		myFactoryName = factory;
	}

	/**
	 * Builds a LaunchProjectile action with GameElementFactory already added
	 * into corresponding attribute manager.
	 */
	@Override
	protected Action buildAction(GameElement e) {
		return new CreateElement(getMap(), e, e
				.getAttributeManager().getGameElementFactory(myFactoryName));
	}

}

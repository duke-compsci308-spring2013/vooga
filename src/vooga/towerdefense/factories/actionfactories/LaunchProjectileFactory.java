package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.LaunchProjectile;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This is an action factory that builds a LaunchProjectile action.
 * 
 * @author XuRui
 * 
 */

public class LaunchProjectileFactory extends ActionFactory {

	private String myFactoryName;

	/**
	 * constructor
	 * 
	 * @param factory
	 */
	public LaunchProjectileFactory(
			@ActionAnnotation(name = "projectile", value = "name") String factory) {
		super();
		myFactoryName = factory;
	}

	/**
	 * Builds a LaunchProjectile action with GameElementFactory already added
	 * into corresponding attribute manager.
	 */
	@Override
	protected Action buildAction(GameElement e) {
		return new LaunchProjectile(getMap(), e.getCenter(), e
				.getAttributeManager().getGameElementFactory(myFactoryName));
	}

}

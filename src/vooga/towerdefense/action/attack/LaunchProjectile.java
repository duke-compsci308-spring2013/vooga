package vooga.towerdefense.action.attack;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;
import util.Location;

/**
 * Creates a projectile aimed at a target, target needs to be predefined by FindTarget action.
 * Projectile created continues the follow up actions.
 * 
 * @author Matthew Roy
 * @author Zhen Gou
 * @author Xu Rui
 */
public class LaunchProjectile extends TargetedAction {

	private GameElementFactory myProjectileFactory;
	private Location myStart;
	private GameMap myMap;

    public LaunchProjectile (GameMap map, Location startLocation, GameElementFactory projectileFactory) {
    	myProjectileFactory = projectileFactory;
    	myStart = startLocation;
    	myMap = map;
    }

    /**
     * Creates a projectile with action of shooting at targets. 
     * 
     * @param elapsedTime 
     */
    @Override
    public void executeAction (double elapsedTime) {
    	for (GameElement target : getTargets()) {
            GameElement projectile = myProjectileFactory.createElement(myStart, target);
            myMap.addGameElement(projectile);
        }
    }
}

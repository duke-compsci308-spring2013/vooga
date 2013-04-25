package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;

/**
 * Creates a projectile aimed at a target, target needs to be predefined by FindTarget action.
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
     * Needs to figure out way to pass target information (one per follow up action or all targets for all follow up actions etc.)
     * 
     * @param elapsedTime 
     */
    @Override
    public void executeAction (double elapsedTime) {
        for (GameElement target : getTargets()) {
            GameElement projectile = myProjectileFactory.createElement(myStart, target);
            if (myMap.getAllGameElements().contains(projectile)){
            	myMap.addGameElement(projectile);
            }
        }
    }
}

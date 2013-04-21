package vooga.towerdefense.action;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;

/**
 * Creates a projectile aimed at a target
 * @author Matthew Roy
 * @author Zhen Gou
 */
public class LaunchProjectile extends Action {

	private ProjectileFactory myProjectileFactory;
	private GameElement myTarget;
	private Location myStart;
	private GameMap myMap;
	
    /**
     * @param GameMap 
     * @param Location
     * @param ProjectileFacotry
     * @param GameElement
     */
    public LaunchProjectile (GameMap map, Location startLocation, ProjectileFactory projectileFactory, GameElement target) {
    	myProjectileFactory = projectileFactory;
    	myTarget = target;
    	myStart = startLocation;
    	myMap = map;
    }

    /**
     * Overrides from superclasses
     * @param elapseTime 
     */
    @Override
    public void executeAction (double elapsedTime) {
    	System.out.print("shooted!!!");
        GameElement projectile = myProjectileFactory.createProjectile(myTarget, myStart);
        projectile.addAction(new MoveToDestination(myStart, myTarget.getCenter(), 
    			projectile.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
        myMap.addGameElement(projectile);
        for (GameElement e : getTargets()) {
            GameElement projectile2 = myProjectileFactory.createProjectile(myStart, e);
            projectile2.addAction(new MoveToDestination(myStart, e.getCenter(), 
                            projectile2.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
            myMap.addGameElement(projectile2);
        }
        
    	//hard coded to add move to destination as follow up action
    	/*addFollowUpAction(new MoveToDestination(myTarget.getCenter(), myStart, 
    			projectile.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
    	getFollowUpAction().update(elapsedTime);*/
    }


}

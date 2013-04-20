package vooga.towerdefense.action;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.gameElements.Projectile;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;

/**
 * 
 * Creates a projectile aimed at a target
 * @author Matthew Roy
 *
 */
public class LaunchProjectile extends Action {

	private ProjectileFactory myProjectileFactory;
	private GameElement myTarget;
	private Location myStart;
	private GameMap myMap;
    /**
     * @param initiator
     */
    public LaunchProjectile (Location startLocation, ProjectileFactory projectileFactory, GameElement target, GameMap map) {
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
        Projectile projectile = myProjectileFactory.createProjectile(myTarget, myStart);
        projectile.addAction(new MoveToDestination(myTarget.getCenter(), myStart, 
    			projectile.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
        myMap.addGameElement(projectile);
        
    	//hard coded to add move to destination as follow up action
    	/*addFollowUpAction(new MoveToDestination(myTarget.getCenter(), myStart, 
    			projectile.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
    	getFollowUpAction().update(elapsedTime);*/
    }

    /**
     * Overrides from superclasses
     * @param elapsedTime 
     */
    @Override
    public void update (double elapsedTime) {
        if (isEnabled()){
        	executeAction(elapsedTime);
        	
        }
    }

}

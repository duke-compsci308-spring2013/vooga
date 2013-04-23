package vooga.towerdefense.action;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.factories.examples.ExampleDosProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;

/**
 * Creates a projectile aimed at a target
 * @author Matthew Roy
 * @author Zhen Gou
 */
public class LaunchProjectile extends Action {

	private ExampleDosProjectileFactory myProjectileFactory;
	private GameElement myTarget;
	private GameElement myStart;
	private GameMap myMap;
	
    /**
     * @param GameMap 
     * @param Location
     * @param ProjectileFacotry
     * @param GameElement
     */
    public LaunchProjectile (GameMap map, GameElement startLocation, ExampleDosProjectileFactory exampleDosProjectileFactory, GameElement target) {
    	myProjectileFactory = exampleDosProjectileFactory;
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
        GameElement projectile = myProjectileFactory.createProjectile(myStart, myTarget);
        projectile.addAction(new MoveToDestination(myStart.getCenter(), myTarget.getCenter(), 
    			projectile.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
        myMap.addGameElement(projectile);
        for (GameElement e : getTargets()) {
            GameElement projectile2 = myProjectileFactory.createElement(myStart, e, myMap);
            projectile2.addAction(new MoveToDestination(myStart.getCenter(), e.getCenter(), 
                            projectile2.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
            myMap.addGameElement(projectile2);
        }
        
    	//hard coded to add move to destination as follow up action
    	/*addFollowUpAction(new MoveToDestination(myTarget.getCenter(), myStart, 
    			projectile.getAttributeManager().getAttribute(AttributeConstants.MOVE_SPEED)));
    	getFollowUpAction().update(elapsedTime);*/
    }


}

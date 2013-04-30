package vooga.towerdefense.action.movement;

import util.Location;
import util.Vector;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;

/**
 * Uses the sprite move method
 * 
 * @author Matthew Roy
 * @author Jimmy Longley
 * @author Xu Rui
 * @author Zhen Gou
 *
 */
public class Move extends Action {
    private Location myCenter;
    private Attribute mySpeed;
    private Attribute myDirection;

    public Move (Location location, Attribute movespeed, Attribute direction) {
        super();
    	mySpeed = movespeed;
    	myCenter = location;
    	myDirection = direction;
    }
    
    @Override
    public void executeAction(double elapsedTime) {
        Vector v = new Vector(myDirection.getValue(), mySpeed.getValue());
        v.scale(elapsedTime / 1000);
        myCenter.translate(v);
    }
}

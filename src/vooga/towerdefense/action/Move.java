
package vooga.towerdefense.action;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Vector;

/**
 * Uses the sprite move method
 * @author Matthew Roy
 * @author Xu Rui
 *
 */
public class Move extends Action {
	//private static final AttributeConstants ATTRIBUTE_CONSTANTS = new AttributeConstants();//used for testing
    private Vector myHeading;
    private Location myCenter;
    private Attribute mySpeed;
    private Attribute myDirection;

    public Move (Attribute movespeed, Attribute direction, Location location) {
    	mySpeed = movespeed;
    	myCenter = location;
    	myDirection = direction;
        myHeading = new Vector(movespeed.getValue(), direction.getValue());
    }
    
    @Override
    public void executeAction(double elapsedTime) {
        Vector v = new Vector(myDirection.getValue(), mySpeed.getValue());
        v.scale(elapsedTime / 1000);
        //System.out.print("moved from " + getInitiator().getCenter() + " ");
        myCenter.translate(v);
        //System.out.println("to " + getInitiator().getCenter());
    }

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

}


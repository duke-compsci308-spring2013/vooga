package vooga.towerdefense.action.movement;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.Attribute;
import util.Location;
import util.Vector;

/**
 * Action to move from one location to another location, or to a target. If
 * location is undefined, move to target.
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * 
 */
public class MoveToTarget extends TargetedAction {

	private Vector myHeading;
	private Location myCenter;
	private Location myDestination;
	private Attribute mySpeed;
	private boolean headingSet;

	public MoveToTarget(Location start, Location destination,
			Attribute movespeed) {
		super();
		mySpeed = movespeed;
		myCenter = start;
		myDestination = destination;
		headingSet = false;
	}

	@Override
	public void executeAction(double elapsedTime) {
		myDestination = getFirstTarget().getCenter();
		setFollowTarget(false);
		Vector v = new Vector(myHeading.getDirection(), mySpeed.getValue());
		v.scale(elapsedTime / 1000);
		myCenter.translate(v);
		updateTargetedFollowUpActions(getTargets());
	}

	/**
	 * Set whether projectile follows path of target, or shoots off in initial
	 * target direction.
	 * 
	 * @param follow
	 */
	public void setFollowTarget(boolean follow) {
		if (headingSet && !follow){
			return;
		}else{
			myHeading = new Vector(Vector.angleBetween(myDestination, myCenter), mySpeed.getValue());
			headingSet = true;
		}
	}
}

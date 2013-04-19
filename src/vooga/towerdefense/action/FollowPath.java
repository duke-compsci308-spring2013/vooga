package vooga.towerdefense.action;

import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.Path;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Vector;

public class FollowPath extends Action {

	private static final double DISTANCE_OFFSET = 5;
	private Path myPath;
	private Location myCurrentPathNode;
	private GameElement myInitiator;
	
	public FollowPath(GameElement initiator, Path path) {
		super();
		myInitiator = initiator;
		myPath = path;
		changeNode();
	}

	@Override
	public void update(double elapsedTime) {
		//to-do
	}

	@Override
	public void executeAction(double elapseTime) {
		if (myInitiator.getCenter().distance(myCurrentPathNode) < DISTANCE_OFFSET) {
			System.out.println("changed node to " + myCurrentPathNode);
			changeNode();
		}
	}

	/**
	 * movement logic, if path has next, change movement direction towards next
	 * node, if not, stop moving
	 */
	private void changeNode() {
		if (myPath.hasNext()) {
			myCurrentPathNode = myPath.next();
			Vector newDirection = myInitiator
					.getCenter().difference(myCurrentPathNode);

			// for some reason, the above method gives the wrong sign on the
			// angle. so have to manually reverse its direction. tried negate(), doesn't work
			newDirection=new Vector(-1*newDirection.getDirection(),newDirection.getMagnitude());

			myInitiator.getAttributeManager()
					.getAttribute(AttributeConstants.DIRECTION)
					.setValue(newDirection.getDirection());
		}

		else {
			myInitiator.getAttributeManager()
					.getAttribute(AttributeConstants.MOVE_SPEED).setValue(0);
		}

	}

	public void setPath(Path path) {
		myPath = path;
	}

}

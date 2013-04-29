package vooga.towerdefense.action.movement;

import util.Location;
import util.Vector;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.Path;

/**
 * Makes the action initiator follow a path.
 * 
 * @author Mattew Roy
 * @author Zhen Gou
 * 
 * 
 */

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

        public void executeAction(double elapsedTime) {
                if (myInitiator.getCenter().distance(myCurrentPathNode) < DISTANCE_OFFSET) {
                        changeNode();
                }
        }

        /**
         * Movement logic, if path has next, change movement direction towards next
         * node, if not, stop moving.
         */
        private void changeNode() {
                if (myPath.hasNext()) {
                        myCurrentPathNode = myPath.next();
                        Vector newDirection = myInitiator.getCenter().difference(myCurrentPathNode);
                        newDirection = new Vector(-1 * newDirection.getDirection(),
                                        newDirection.getMagnitude());

                        myInitiator.getAttributeManager()
                                        .getAttribute(AttributeConstantsEnum.DIRECTION.toString())
                                        .setValue(newDirection.getDirection());
                }

                else {
                        myInitiator.getAttributeManager()
                                        .getAttribute(AttributeConstantsEnum.MOVE_SPEED.toString())
                                        .setValue(0);
                }
        }

        public void setPath(Path path) {
                myPath = path;
        }

}

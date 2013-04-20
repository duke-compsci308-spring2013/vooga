package vooga.towerdefense.gameElements;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.model.Path;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Vector;


/**
 * 
 * unit e.g. an enemy. It will have a state manager to display different
 * animations under different situations/ **but for testing purpose now, it's
 * not added yet!**
 * 
 * also, needs to have a move action in its actions in order to move according
 * to path;
 * 
 * @author Matthew Roy
 * @author Zhen Gou
 * 
 */
public class Unit extends GameElement {
    private static final AttributeConstants ATTRIBUTE_CONSTANTS = null;
    private Path myPath;
    private Location myCurrentPathNode;
    /**
     * @param image
     * @param center
     * @param size
     * @param attributes
     * @param actions
     */
    public Unit (Pixmap image, Location center, Dimension size,
                 AttributeManager attributes, List<Action> actions) {
        super(image, center, size, attributes, actions);
    }

    /**
     * @param image
     * @param center
     * @param size
     * @param actions
     */
    public Unit (Pixmap image, Location center, Dimension size,
                 List<Action> actions) {
        super(image, center, size, actions);
    }

    /**
     * @param image
     * @param center
     * @param size
     * @param am
     */
    public Unit (Pixmap image, Location center, Dimension size,
                 AttributeManager am) {
        super(image, center, size, am);
    }

    /**
     * this follow path logic has been transferred to a followPath action which will do exactly the same thing
     */
    @Deprecated
    private void changeNode() {
        if (myPath.hasNext()){
			myCurrentPathNode=myPath.next();
			Vector newDirection= getCenter().difference(myCurrentPathNode);
			//for some reason, this method gives the wrong sign on the angle
			newDirection = new Vector(-1*newDirection.getDirection(), newDirection.getMagnitude());
			getAttributeManager().getAttribute(ATTRIBUTE_CONSTANTS.DIRECTION).setValue(newDirection.getDirection());
		}
		else{
			getAttributeManager().getAttribute(ATTRIBUTE_CONSTANTS.MOVE_SPEED).setValue(0);
		}
    }
    /**
     * @param image
     * @param center
     * @param size
     */
    public Unit (Pixmap image, Location center, Dimension size) {
        super(image, center, size);
    }

    @Override
    public void update (double elapsedTime) {
        executeActions(elapsedTime);
       

    }
    @Override
    public void paint(Graphics2D pen){
    	super.paint(pen);
    	getAttributeManager().getAttribute(AttributeConstants.HEALTH).paint(pen, getCenter(),new Dimension((int)getWidth(),(int)getHeight()));
    	
    }

    private void executeActions (double elapsedTime) {
        for (Action act : getActions()) {
            act.executeAction(elapsedTime);
        }
    }
}

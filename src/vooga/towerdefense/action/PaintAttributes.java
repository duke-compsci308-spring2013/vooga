package vooga.towerdefense.action;

import java.awt.Color;
import java.awt.Graphics2D;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.util.Location;

/**
 * @author Matthew Roy
 *
 */
public class PaintAttributes extends Action {

    private Location myLocation;
    private Attribute myAttribute;
    
    /**
     * 
     */
    public PaintAttributes (Location unitLoc, Attribute toPaint) {
        myLocation = unitLoc;
        myAttribute = toPaint;
    }

    /**
     * Paints health bar or equivalent
     * @param elapseTime 
     */
    public void executeAction (double elapseTime, Graphics2D pen) {
        pen.setColor(Color.red);
        //THIS IS VERY SPECIFIC FOR TESTING, WE WILL FIGURE OUT BETTER WAY TO FIT THE BAR
        pen.fillRect((int)myLocation.getX(), (int)myLocation.getY()-size.height/2, (int)(size.getWidth()*(getValue()/getOriginalValue())), (int)size.getHeight()/10);
}
    }

    /**
     * Overrides from superclasses
     * @param elapsedTime 
     */
    @Override
    public void update (double elapsedTime) {
        // TODO Auto-generated method stub

    }

    /**
     * Overrides from superclasses
     * @param elapseTime 
     */
    @Override
    public void executeAction (double elapseTime) {
        // TODO Auto-generated method stub
        
    }
    
}

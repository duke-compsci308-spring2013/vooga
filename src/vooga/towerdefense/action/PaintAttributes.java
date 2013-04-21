package vooga.towerdefense.action;

import java.awt.Color;
import java.awt.Graphics2D;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;

/**
 * @author Matthew Roy
 *
 */
public class PaintAttributes extends Action {

    private GameElement myElement;
    private Attribute myAttribute;
    
    /**
     * 
     */
    public PaintAttributes (GameElement element, Attribute toPaint) {
        myElement = element;
        myAttribute = toPaint;
    }

    /**
     * Paints health bar or equivalent
     * @param elapseTime 
     */
    public void executeAction (double elapseTime, Graphics2D pen) {
        pen.setColor(Color.red);
        
        //THIS IS VERY SPECIFIC FOR TESTING, WE WILL FIGURE OUT BETTER WAY TO FIT THE BAR
        pen.fillRect((int)myElement.getX(), (int)myElement.getY()-(int)myElement.getHeight()/2, (int)(myElement.getWidth()*(myAttribute.getValue()/myAttribute.getOriginalValue())), (int)myElement.getHeight()/10);

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

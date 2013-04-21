package vooga.towerdefense.gameElements;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * Baseline Tower class. Has no special functionality.
 * 
 * @author Matthew Roy
 * 
 */
public class Tower extends GameElement {

    
    /**
     * @param image
     * @param center
     * @param size
     */
    public Tower (Pixmap image,
                  Location center,
                  Dimension size,
                  AttributeManager attributes) {
        super(image, center, size, attributes);
    }
    
    
    /**
     * @param image
     * @param center
     * @param size
     * @param actions
     */
    public Tower (Pixmap image,
                  Location center,
                  Dimension size,
                  AttributeManager attributes,
                  List<Action> actions) {
        super(image, center, size, attributes, actions);
    }

}

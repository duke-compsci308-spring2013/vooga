package vooga.towerdefense.factories;


import java.awt.Dimension;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

/**
 * Example unit class
 * @author Matthew Roy
 *
 */
public class TrollUnitDefinition extends GameElementDefinition {
    
    private static final Pixmap TROLL_IMAGE = new Pixmap("Troll_Sprite.png");
    private static final Location DEFAULT_TOWER_LOCATION = new Location(200, 100);
    private static final Dimension DEFAULT_TOWER_SIZE = new Dimension(150, 150);
    private static final double DEFAULT_MOVE_SPEED = 50;
    private static final double DEFAULT_DIRECTION = 50;
    
    public Pixmap myImage;
    public Location myCenter;
    public Dimension mySize;

    /**
     * 
     */
    public TrollUnitDefinition () {
        myImage = TROLL_IMAGE;
        myCenter = DEFAULT_TOWER_LOCATION;
        mySize = DEFAULT_TOWER_SIZE;
    }
    
    public Dimension getSize() {
        return DEFAULT_TOWER_SIZE;
    }

    
    
}

package vooga.towerdefense.factories;

import java.awt.Dimension;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * Class that defines a game element as created by the designer
 * Created from xml data parser, used to create the factory object
 * Functionally, it bundles the states the define an object
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * 
 */
public class GameElementDefinition {
    protected Pixmap myImage;
    protected Location myCenter;
    protected Dimension mySize;

    public GameElementDefinition () {

    }

    public Pixmap getImage () {
        return myImage;
    }

    public Location getCenter () {
        return myCenter;
    }

    public Dimension getSize () {
        return mySize;
    }

    /**
     * @param name
     * @return
     */
    public String get (String name) {
        // TODO Auto-generated method stub
        return null;
    }

}

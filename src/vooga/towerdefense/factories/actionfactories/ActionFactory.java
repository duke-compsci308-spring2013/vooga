package vooga.towerdefense.factories.actionfactories;

import java.awt.Graphics2D;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.model.GameMap;


/**
 * Creates actions based on definitions of the ActionFactory
 * 
 * @author Matthew Roy
 * 
 */
public abstract class ActionFactory {

    protected GameMap myMap;
    protected Graphics2D myPen;

    public ActionFactory () {
    }

    /**
     * Places in all of the objects that the factory could need to function
     * Cannot create actions until initialized
     */
    public void initialize (GameMap map, Graphics2D pen) {
        myMap = map;
        myPen = pen;
    }

    public abstract Action createAction ();

}

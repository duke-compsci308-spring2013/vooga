package vooga.towerdefense.action;

import vooga.towerdefense.factories.examples.ExampleDosProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;

/**
 * @author Matthew Roy
 *
 */
public class MakeElement extends Action {
    GameMap myMap;
    Location myLoc;
    ExampleDosProjectileFactory myFactory;
    
    //TODO: Fix from the testing value
    public MakeElement(GameMap map, Location place, ExampleDosProjectileFactory factory) {
        super();
        myMap = map;
        myLoc = place;
        myFactory = factory;
    }

    /**
     * Overrides from superclasses
     * @param elapsedTime 
     */
    public void executeAction (double elapsedTime) {
        for (GameElement e : getTargets()) {
            myMap.addGameElement(myFactory.createElement(myLoc, e.getCenter(), myMap));
        }
        
    }

}

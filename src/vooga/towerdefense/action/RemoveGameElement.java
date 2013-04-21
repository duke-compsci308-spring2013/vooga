package vooga.towerdefense.action;

import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;

/**
 * @author Matthew Roy
 *
 */
public class RemoveGameElement extends Action {

    private GameMap myMap;
    private GameElement myElement;
    
    /**
     * 
     */
    public RemoveGameElement (GameMap map, GameElement elementToRemove) {
        myMap = map;
        myElement = elementToRemove;
    }
    
    public void executeAction (double elapsedTime) {
        execute();
    }

   
    public void execute () {
        myMap.removeElement(myElement);
    }

    /**
     * Overrides from superclasses
     * @param elapsedTime 
     */
    @Override
    public void update (double elapsedTime) {
    }

}

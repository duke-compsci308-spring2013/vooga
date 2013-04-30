package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;

/**
 * Creates a new game element and confers upon it an affiliation code that identifies it with its creator. 
 * 
 * @author Matthew Roy
 *
 */
public class CreateElement extends Action {
    
    private GameElementFactory myFactory;
    private GameElement myCreator;
    private GameMap myMap;
    
    public CreateElement(GameMap map, GameElement creator, GameElementFactory factory) {
        super();
        myMap = map;
        myCreator = creator;
        myFactory = factory;
    }

    /**
     * 
     * @param elapsedTime 
     */
    @Override
    public void executeAction (double elapsedTime) {
        GameElement element = myFactory.createElement(myCreator.getCenter());
        Attribute affiliation = element.getAttributeManager().getAttribute(AttributeConstantsEnum.AFFILIATION.getStatusCode());
        affiliation.modifyValue((int)myCreator.getAttributeManager().getAttribute(AttributeConstantsEnum.AFFILIATION.getStatusCode()).getValue());
        myMap.addGameElement(element);
    }

}

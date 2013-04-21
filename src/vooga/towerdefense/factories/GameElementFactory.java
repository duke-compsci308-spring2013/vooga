package vooga.towerdefense.factories;

import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;

/**
 * A factory that creates game elements based on preset data
 * Reads from an xmlfile (eventually)
 * @author Matthew Roy
 * @author Xu Rui
 *
 */
public class GameElementFactory {
    /**
     * Name of the element that is defined in this class. For convenience.
     */
    private String myName;
    private GameElementDefinition def; 
    //private AttributeFactory myFactory;
    
    public GameElementFactory() {
    // TowerFactory myTowerFactory = new TowerFactory(TowerDefinition);
     //Tower tower1 = myTowerFactory.make(Location);
    }
    
    public GameElementFactory(String name, GameElementDefinition definition) {
        myName = name;
        def = definition;
    }
    
    public GameElementFactory(GameElementDefinition definition) {
        this(definition.get(AttributeConstants.NAME), definition);
    }
    
    public GameElementDefinition getDefinition() {
        return def;
    }
    
    public AttributeManagerFactory createAttributeFactory() {
        AttributeManagerFactory factory = new AttributeManagerFactory();
        return factory;
    }
    
    public ActionFactory createActionFactory() {
        ActionFactory actFactory = new ActionFactory();
        return actFactory;
    }
    
    public GameElement createGameElement(){
        if (def == null) {
            return null;
        }
		return new GameElement(def.getImage(), 
				def.getCenter(), 
				def.getSize(), 
				createAttributeFactory().makeAttributeManager(), 
				createActionFactory().createActions());
    }

    /**
     * @param spawnLocation
     * @return 
     */
    public GameElement createGameElement (Location spawnLocation) {
        // TODO Auto-generated method stub
        return null;
    }

}

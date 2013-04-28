package vooga.towerdefense.factories.attributefactories;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;


/**
 * Basic attribute factory that creates and populates new attribute for specific elements
 * 
 * @author Matthew Roy
 * 
 */
public class AttributeManagerFactory {

    private List<AttributeFactory> myInputAttributes;
    private List<GameElementFactory> myGameElementFactories;

    /**
     * Dangerous because it uses instantiated attributes
     * @param gameElementAttributes
     */
    public AttributeManagerFactory (List<AttributeFactory> gameElementAttributes) {
        myInputAttributes = new ArrayList<AttributeFactory>();
        myInputAttributes.addAll(gameElementAttributes);
        myGameElementFactories = new ArrayList<GameElementFactory>();
    }

    /**
     * Another constructor just in case this is easier
     * @param attributeName
     * @param attributeValue
     */
    public AttributeManagerFactory (List<String> attributeName, List<Double> attributeValue) {
        myInputAttributes = new ArrayList<AttributeFactory>();
        for (int i = 0; i<attributeName.size(); i++) {
            myInputAttributes.add(new AttributeFactory(attributeName.get(i), attributeValue.get(i)));
        }
    }

    public AttributeManagerFactory () {
        myInputAttributes = new ArrayList<AttributeFactory>();
    }
    
    public void addAttributeFactory(AttributeFactory attribute) {
        myInputAttributes.add(attribute);
    }
    
    public void addGameElementFactory(GameElementFactory factory){
    	myGameElementFactories.add(factory);
    }

    /**
     * Creates attributes at default values
     * @return
     */
    public AttributeManager makeAttributeManager () {
        return makeAttributeManager(1);
    }
    
    /**
     * Creates attributes at a multiple of the default values
     * @param modifier
     * @return
     */
    public AttributeManager makeAttributeManager (double modifier) {
        AttributeManager manager = new AttributeManager();
        for (AttributeFactory fact : myInputAttributes) {
            manager.addAttribute(fact.create());
        }
        for (GameElementFactory factory: myGameElementFactories){
        	manager.addGameElementFactory(factory.getName(), factory);
        }
        	
        return manager;
    }

}

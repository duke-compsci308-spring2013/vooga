package vooga.towerdefense.factories;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeManager;


/**
 * Basic attribute factory that creates and populates new attribute for specific elements
 * 
 * @author Matthew Roy
 * 
 */
public class AttributeManagerFactory {

    private List<Attribute> myInputAttributes;

    /**
     * Dangerous because it uses instantiated attributes
     * @param gameElementAttributes
     */
    public AttributeManagerFactory (List<Attribute> gameElementAttributes) {
        myInputAttributes = new ArrayList<Attribute>();
        myInputAttributes.addAll(gameElementAttributes);
    }

    /**
     * Another constructor just in case this is easier
     * @param attributeName
     * @param attributeValue
     */
    public AttributeManagerFactory (List<String> attributeName, List<Double> attributeValue) {
        myInputAttributes = new ArrayList<Attribute>();
        for (int i = 0; i<attributeName.size(); i++) {
            myInputAttributes.add(new Attribute(attributeName.get(i), attributeValue.get(i)));
        }
    }

    public AttributeManagerFactory () {
        myInputAttributes = new ArrayList<Attribute>();
    }

    public AttributeManager makeAttributeManager () {
        AttributeManager manager = new AttributeManager();
        manager.addAttributes(myInputAttributes);
        return manager;
    }

}

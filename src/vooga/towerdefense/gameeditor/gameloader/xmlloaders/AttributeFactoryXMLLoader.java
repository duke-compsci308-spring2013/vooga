package vooga.towerdefense.gameeditor.gameloader.xmlloaders;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import util.XMLTool;
import vooga.towerdefense.factories.attributefactories.AttributeFactory;

/**
 * This class is responsible for loading AttributeFactory
 * objects from an XML file.
 * 
 * @author Erick Gonzalez
 */
public class AttributeFactoryXMLLoader {
    
    private XMLTool myXMLTool;
    
    /**
     * 
     * @param xmlTool an xmlTool object containing an xml document
     */
    public AttributeFactoryXMLLoader(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * Given an attributes xml element, returns a list of 
     * AttributeFactory objects.
     * 
     * @param attributesElement an attributes xml element 
     * @return a list of AttributeFactory objects
     */
    public List<AttributeFactory> loadAttributeFactories (Element attributesElement) {
        List<AttributeFactory> attributes = new ArrayList<AttributeFactory>();
        
        List<Element> subElements = myXMLTool.getChildrenList(attributesElement);
        for (Element subElement : subElements) {
            AttributeFactory attribute = loadAttributeFactory(subElement);
            attributes.add(attribute);
        }
        return attributes;
    }
    
    private AttributeFactory loadAttributeFactory (Element attributeElement) {
        return new AttributeFactory(myXMLTool.getTagName(attributeElement),
                                    loadAttributeValue(attributeElement));
    }
    
    private double loadAttributeValue (Element valueElement) {
        return Double.parseDouble(myXMLTool.getContent(valueElement));
    }
}

package vooga.towerdefense.gameeditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import vooga.towerdefense.util.XMLTool;
import vooga.towerdefense.factories.actionfactories.ActionFactory;
import vooga.towerdefense.factories.attributefactories.AttributeFactory;
import vooga.towerdefense.factories.attributefactories.AttributeManagerFactory;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.util.Pixmap;

public class GameElementXMLLoader {
    private static final String GAME_ELEMENTS_TAG = "GameElements";
    private static final String ATTRIBUTES_TAG = "Attributes";
    private static final String IMAGE_TAG = "image";
    private static final String VALUE_TAG = "value";

    private XMLTool myXMLTool;    
    
    public GameElementXMLLoader(XMLTool xmlTool, String xmlFilePath) {
        myXMLTool = xmlTool;
        myXMLTool.readDoc(xmlFilePath);
    }
    
    public List<GameElementFactory> loadGameElementFactories() {
        Element gameElementsElement = myXMLTool.getElement(GAME_ELEMENTS_TAG);        
        Map<String, Element> subElements = myXMLTool.getChildrenElementMap(gameElementsElement);
        
        List<GameElementFactory> gameElementFactories = new ArrayList<GameElementFactory>();
        for (Element subElement : subElements.values()) {
            gameElementFactories.add(loadGameElementFactory(subElement));
        }
        return gameElementFactories;
    }
    
    public GameElementFactory loadGameElementFactory(Element gameElement) {
        Map<String, Element> subElements = myXMLTool.getChildrenElementMap(gameElement);
        
        String gameElementName = myXMLTool.getTagName(gameElement);
        Pixmap elementImage = loadElementImage(subElements.get(IMAGE_TAG));
        List<AttributeFactory> attributeFactories = loadAttributeFactories(subElements.get(ATTRIBUTES_TAG));
        
        ActionXMLLoader loader = new ActionXMLLoader(myXMLTool);
        List<ActionFactory> actionFactories = loader.loadActions();
        
        AttributeManagerFactory managerFactory = new AttributeManagerFactory(attributeFactories); 
        
        GameElementFactory geFactory = new GameElementFactory(gameElementName,
                                                              elementImage,
                                                              managerFactory,
                                                              actionFactories);
        return geFactory;
    }     
    
    private Pixmap loadElementImage(Element imageElement) {
        String imagePath = myXMLTool.getContent(imageElement);        
        return new Pixmap(imagePath);
    }
    
    private List<AttributeFactory> loadAttributeFactories(Element attributesElement) {
        List<AttributeFactory> attributes = new ArrayList<AttributeFactory>();
        Map<String, Element> subElements = myXMLTool.getChildrenElementMap(attributesElement);
        for (String attributeName : subElements.keySet()) {
            AttributeFactory attribute = loadAttributeFactory(subElements.get(attributeName));
            attributes.add(attribute);
        }
        return attributes;
    }
    
    private AttributeFactory loadAttributeFactory(Element attributeElement) {
        Map<String, Element> subElements = myXMLTool.getChildrenElementMap(attributeElement);
        return new AttributeFactory(myXMLTool.getTagName(attributeElement), loadAttributeValue(subElements.get(VALUE_TAG)));
    }
    
    private double loadAttributeValue(Element valueElement) {
        return Double.parseDouble(myXMLTool.getContent(valueElement));
    }
}

package vooga.towerdefense.gameeditor.gameloader.xmlloaders;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import util.Pixmap;
import util.XMLTool;
import vooga.towerdefense.factories.actionfactories.ActionFactory;
import vooga.towerdefense.factories.attributefactories.AttributeFactory;
import vooga.towerdefense.factories.attributefactories.AttributeManagerFactory;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Player;

/**
 * This class is responsible for loading GameElementFactory
 * objects from an XML file.
 * 
 * @author Erick Gonzalez
 */
public class GameElementXMLLoader {
    private static final String IMAGE_PATH = "/vooga/towerdefense/images/";
    
    private static final String GAME_ELEMENTS_TAG = "gameelements";
    private static final String ATTRIBUTES_TAG = "attributes";
    private static final String IMAGE_TAG = "image";
    private static final String TYPE_TAG = "type";
    private static final String DIMENSION_TAG = "dimension";
    private static final String ACTIONS_TAG = "actions";
    
    private XMLTool myXMLTool;
    
    /**
     * 
     * @param xmlTool an xml tool containing the xml document
     */
    public GameElementXMLLoader(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * Loads a map from GameElementFactory name to GameElementFactory objects.
     * 
     * @param map a game map
     * @param player a player object
     * @return a map from the name of a GameElementFactory to its object
     */
    public Map<String, GameElementFactory> loadGameElementFactories (GameMap map, Player player) {
        //TODO: This needs to be passed in as a parameter
        Element gameElementsElement = myXMLTool.getElement(GAME_ELEMENTS_TAG);        
        List<Element> subElements = myXMLTool.getChildrenList(gameElementsElement);
        
        Map<String, GameElementFactory> gameElementFactories = 
                new HashMap<String, GameElementFactory>();
        for (Element subElement : subElements) {
            gameElementFactories.put(myXMLTool.getTagName(subElement), 
                                     loadGameElementFactory(subElement, map, player));
        }
        
        return gameElementFactories;
    }
    
    
    private GameElementFactory loadGameElementFactory (Element gameElement, 
                                                       GameMap map, Player player) {
        Map<String, Element> subElementMap = myXMLTool.getChildrenElementMap(gameElement);
        String gameElementName = myXMLTool.getTagName(gameElement);
        String gameElementType = loadType(subElementMap.get(TYPE_TAG));
        Pixmap elementImage = loadElementImage(subElementMap.get(IMAGE_TAG));
        Dimension elementDimension = loadDimension(subElementMap.get(DIMENSION_TAG));
        AttributeFactoryXMLLoader attributeLoader = new AttributeFactoryXMLLoader(myXMLTool);
        
        List<AttributeFactory> attributeFactories = 
                attributeLoader.loadAttributeFactories(subElementMap.get(ATTRIBUTES_TAG));
        
        ActionXMLLoader loader = new ActionXMLLoader(myXMLTool);
        List<ActionFactory> actionFactories = loader.
                loadActionFactories(subElementMap.get(ACTIONS_TAG), map, player);
        
        AttributeManagerFactory managerFactory = new AttributeManagerFactory(attributeFactories);
        
        GameElementFactory geFactory = new GameElementFactory(gameElementName,
                                                              gameElementType,
                                                              elementImage,
                                                              elementDimension,
                                                              managerFactory,
                                                              actionFactories);
        return geFactory;
    }
    
    private Pixmap loadElementImage (Element imageElement) {
        String imageName = myXMLTool.getContent(imageElement);
        return new Pixmap(IMAGE_PATH + imageName);
    }
    
    private String loadType (Element typeElement) {
        return myXMLTool.getContent(typeElement);
    }
        
    private Dimension loadDimension(Element dimensionElement) {
        String dimensionString = myXMLTool.getContent(dimensionElement);
        String[] dimensionStringArray = dimensionString.split(",\\s+");
        return new Dimension(Integer.parseInt(dimensionStringArray[0]), 
                             Integer.parseInt(dimensionStringArray[1]));
    }
}

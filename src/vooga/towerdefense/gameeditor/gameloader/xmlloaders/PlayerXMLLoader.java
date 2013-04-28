package vooga.towerdefense.gameeditor.gameloader.xmlloaders;

import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.factories.attributefactories.AttributeFactory;
import vooga.towerdefense.factories.attributefactories.AttributeManagerFactory;
import vooga.towerdefense.model.Player;

/**
 * This class is responsible for loading the Player
 * object from an XML file.
 *
 * @author Angelica Schwartz
 * @author Erick Gonzalez
 */
public class PlayerXMLLoader {
    
    private static final String PLAYER_TAG = "player";
    private static final String ATTRIBUTES_TAG = "attributes";
    
    private XMLTool myXMLTool;
    
    /**
     * constructor.
     * @param xmlTool
     */
    public PlayerXMLLoader(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * loads the player into the xml file.
     * @param controller
     * @return the player
     */
    public Player loadPlayer(Controller controller) {
        Element playerElement = myXMLTool.getElement(PLAYER_TAG);
        Element attributeParent = myXMLTool.getElement(ATTRIBUTES_TAG);
        Map<String, Element> attributes = myXMLTool.getChildrenElementMap(attributeParent);
        AttributeFactoryXMLLoader attributeLoader = new AttributeFactoryXMLLoader(myXMLTool);
        List<AttributeFactory> attributeFactories = 
                attributeLoader.loadAttributeFactories(attributeParent);        
        AttributeManagerFactory attributeManagerFactory
            = new AttributeManagerFactory(attributeFactories);
        AttributeManager attributeManager = attributeManagerFactory.makeAttributeManager();
        return new Player(controller, attributeManager);
        
    }
}

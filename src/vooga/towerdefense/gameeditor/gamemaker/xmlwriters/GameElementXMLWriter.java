package vooga.towerdefense.gameeditor.gamemaker.xmlwriters;

import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;

/**
 * GameElementXMLWriter writes the game element properties
 *      to the specified XML doc in the correct format.
 *
 * @author Angelica Schwartz
 */
public class GameElementXMLWriter {
    
    private XMLTool myXMLTool;
    private ActionXMLWriter myActionParser;
    
    /**
     * Constructor.
     * @param xmlTool
     */
    public GameElementXMLWriter(XMLTool xmlTool) {
        myXMLTool = xmlTool;
        myActionParser = new ActionXMLWriter(myXMLTool);
    }
    
    /**
     * writes the game element to the file.
     * @param parent the game element parent element
     * @param name of this game element
     * @param type of this game element
     * @param path to the background image
     * @param attributes is a map from attribute to value
     * @param actions is the string of all the actions
     */
    public void write (Element parent,
                       String type,
                       String name, String path, 
                       String dimension,
                       Map<String, String> attributes,
                       String actions) {
        Element gameElement = myXMLTool.makeElement(name);
        myXMLTool.addChild(parent, gameElement);
        myXMLTool.addChild(gameElement, XMLWriter.DIMENSION_TAG, dimension);
        myXMLTool.addChild(gameElement, XMLWriter.IMAGE_TAG, path);
        myXMLTool.addChild(gameElement, XMLWriter.TYPE_TAG, type);
        Element attributeElement = myXMLTool.makeElementsFromMap(XMLWriter.ATTRIBUTES_TAG, attributes);
        myXMLTool.addChild(gameElement, attributeElement);
        Element actionElement = myXMLTool.makeElement(XMLWriter.ACTIONS_TAG);
        myXMLTool.addChild(gameElement, myActionParser.parse(actionElement, actions));
    }

}

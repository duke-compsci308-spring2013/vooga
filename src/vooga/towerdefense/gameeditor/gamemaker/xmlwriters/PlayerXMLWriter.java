package vooga.towerdefense.gameeditor.gamemaker.xmlwriters;

import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;

/**
 * PlayerXMLWriter writes the player information
 *      to the specified XML doc in the correct format.
 *
 * @author Angelica Schwartz
 */
public class PlayerXMLWriter {
    
    private XMLTool myXMLTool;
    
    /**
     * Constructor.
     * @param xmlTool
     */
    public PlayerXMLWriter(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * writes the player information to the file.
     * @param parent the parent tag for this player
     * @param attributes is the map representing the player's attributes
     */
    public void write (Element parent, Map<String, String> attributes) {
        Element attributeParent = myXMLTool.makeElement(XMLWriter.ATTRIBUTES_TAG);
        myXMLTool.addChild(parent, attributeParent);
        myXMLTool.addChildrenFromMap(attributeParent, attributes);
    }

}

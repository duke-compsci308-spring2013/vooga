package vooga.towerdefense.gameeditor.gamemaker.xmlwriters;

import org.w3c.dom.Element;
import util.XMLTool;

/**
 * MapXMLWriter writes the map information to the
 *      specified XML doc in the correct format.
 *
 * @author Leonard Ng'eno
 */
public class MapXMLWriter {
    
    private XMLTool myXMLTool;
    
    /**
     * Constructor.
     * @param xmlTool
     */
    public MapXMLWriter(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * writes the map to the file.
     * @param parent the map parent element
     * @param name of this map
     * @param image path to the background image
     * @param width of the map
     * @param height of the map
     * @param tileSize
     * @param map is the grid representation
     */
    public void write (Element parent, String name, String image, String width, String height, String tileSize, String map) {
        Element thisMap = myXMLTool.makeElement(name.trim());
        myXMLTool.addChild(thisMap, XMLWriter.IMAGE_TAG, image);
        Element dimensionElement = myXMLTool.makeElement(XMLWriter.DIMENSION_TAG);
        myXMLTool.addChild(thisMap, dimensionElement);
        myXMLTool.addChild(dimensionElement, XMLWriter.WIDTH_TAG, width);
        myXMLTool.addChild(dimensionElement, XMLWriter.HEIGHT_TAG, height);
        myXMLTool.addChild(thisMap, XMLWriter.TILE_SIZE_TAG, tileSize);
        myXMLTool.addChild(thisMap, XMLWriter.GRID_TAG, map);
        myXMLTool.addChild(parent, thisMap);
    }

}

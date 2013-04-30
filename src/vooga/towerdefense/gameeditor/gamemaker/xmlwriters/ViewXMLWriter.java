package vooga.towerdefense.gameeditor.gamemaker.xmlwriters;

import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;

/**
 * ViewXMLWriter writes the view properties of the game
 *      to the specified XML doc in the correct format.
 *
 * @author Angelica Schwartz
 * @author Leonard Ng'eno
 */
public class ViewXMLWriter {
    
    private XMLTool myXMLTool;
    public static final String MULTIPLE_SCREEN_PANEL_NAME = "MultipleScreenPanel";
    
    /**
     * constructor.
     * @param xmlTool
     */
    public ViewXMLWriter(XMLTool xmlTool) {
        myXMLTool = xmlTool;
    }
    
    /**
     * writes the view to the XML file.
     * @param parent is the parent element.
     * @param dimension is the dimension as a string
     * @param viewInfo
     * @param map
     */
    public void write(Element parent, String dimension,
                      List<String> viewInfo,
                      Map<String, List<String>> map) {
        
        myXMLTool.addChild(parent, XMLWriter.DIMENSION_TAG, dimension);
        for (String s : viewInfo) {
            if (!s.equals("")) {
                String[] characteristics = s.split(" ");
                if (!characteristics[0].equals("")) {
                    Element screen = myXMLTool.makeElement(characteristics[0]);
                    myXMLTool.addChild(parent, screen);
                    myXMLTool.addChild(screen, XMLWriter.DIMENSION_TAG, characteristics[1] + " " + characteristics[2]);
                    myXMLTool.addChild(screen, XMLWriter.SCREEN_LOCATION_TAG, characteristics[3]);
                    if (characteristics[0].equals(MULTIPLE_SCREEN_PANEL_NAME)) {
                        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                            if (entry.getKey().equals(characteristics[4])) {
                                for (String str : entry.getValue()) {
                                    String[] values = str.split(" ");
                                    Element screen2 = myXMLTool.makeElement(values[1]);
                                    myXMLTool.addChild(screen, screen2);
                                    myXMLTool.addChild(screen2, XMLWriter.DIMENSION_TAG, values[2] + " " + values[3]);
                                    myXMLTool.addChild(screen2, XMLWriter.SCREEN_LOCATION_TAG, values[0]);
                                }
                            }
                        }
                    }

                }
            }
        }
    }

}

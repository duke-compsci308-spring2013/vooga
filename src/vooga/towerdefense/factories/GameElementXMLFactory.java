package vooga.towerdefense.factories;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * This Game Element Factory create different types of game elements
 * through an XML file and some predefined actions.
 * 
 * @author Yoshida
 * 
 */
public class GameElementXMLFactory {
    private static final String PIXMAP_TAG = "pixmap";
    private static final String SIZE_TAG = "size";
    private static final String HEIGHT_TAG = "height";
    private static final String WIDTH_TAG = "width";
    private static final String ACTION_TAG = "action";
    private static final String ATTRIBUTE_TAG = "attribute";
    private static final Location INITIAL_LOCATION = new Location(0, 0);
    
    private XMLTool myXMLDoc;
    private Element myGEElement;
    private Map<String, String> myGEDataMap = new HashMap<String, String>();
    private String myGEName;
    private Pixmap myGEPixmap;
    private Dimension myGESize;
    private Location myGELocation = INITIAL_LOCATION;
    private List<Action> myGEActions;
    private List<Attribute> myGEAttributes;
    private GameElement myGameElement;
    
    public GameElementXMLFactory (String name, String path) {
        loadDataFromXML(name, path);
        myGEPixmap = makePixmap();
        myGESize = setSize();
        myGEAttributes = setAttributeList();
        myGEActions = setActionList();
    }
    
    private void loadDataFromXML (String name, String path) {
        myXMLDoc = new XMLTool();
        myXMLDoc.setDoc(path);
        myGEElement = myXMLDoc.getElementFromTag(name);
        myGEDataMap = myXMLDoc.getMapFromParentTag(name);
    }
    
    private Pixmap makePixmap () {
        return new Pixmap(myGEDataMap.get(PIXMAP_TAG));
    }
    
    private Dimension setSize () {
        int height =
                Integer.parseInt(myGEElement.getElementsByTagName(HEIGHT_TAG).item(0)
                        .getTextContent());
        int width =
                Integer.parseInt(myGEElement.getElementsByTagName(WIDTH_TAG).item(0)
                        .getTextContent());
        return new Dimension(width, height);
    }
    
    private List<Attribute> setAttributeList () {
        // TODO: make this method.
        return null;
    }
    
    private List<Action> setActionList () {
        // TODO: make this method.
        return null;
    }
    
    public void setLocation (Location location) {
        myGELocation = location;
    }
    
    public GameElement makeGameElement () {
        AttributeManager attributeManager = new AttributeManager();
        GameElement gameElement =
                new GameElement(myGEPixmap, myGELocation, myGESize, attributeManager, myGEActions);
        return gameElement;
    }
}

package vooga.towerdefense.gameeditor.gamemaker.xmlwriters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import util.XMLTool;


/**
 * Class that creates the xml document for saving the game in.
 * Uses input from the game editor classes.
 * 
 * @author Leonard K. Ng'eno
 * @author Angelica Schwartz
 * 
 */
public class XMLWriter {
    
    /**
     * xml extension constant.
     */
    public static final String XML_EXTENSION = ".xml";
    /**
     * tag constants.
     */
    public static final String GAME_TAG = "game";
    public static final String VIEW_TAG = "view";
    public static final String PLAYER_TAG = "player";
    public static final String GAME_ELEMENT_TAG = "gameelements";
    public static final String LEVELS_TAG = "levels";
    public static final String IMAGE_TAG = "image";
    public static final String TYPE_TAG = "type";
    public static final String MAP_TAG = "map";
    public static final String DIMENSION_TAG = "dimension";
    public static final String WIDTH_TAG = "width";
    public static final String HEIGHT_TAG = "height";
    public static final String TILE_SIZE_TAG = "tile_size";
    public static final String GRID_TAG = "grid";
    public static final String RULES_TAG = "rules";
    public static final String PARAMETER_TAG = "parameter";
    public static final String SCREEN_LOCATION_TAG = "location";
    public static final String ATTRIBUTES_TAG = "attributes";
    public static final String ACTIONS_TAG = "actions";
    public static final String RESOURCE_PATH = "/src/vooga/towerdefese/resources/";
    public static final String UNIT_INDICATOR = "Unit";

    private XMLTool myXMLDoc;
    private Element myRoot;
    private Element myViewParent;
    private Element myMapParent;
    private Element myPlayerParent;
    private Element myGameElementParent;
    private Element myLevelParent;
    private Element myRuleParent;
    private List<String> myCreatedUnits;
    
    private ViewXMLWriter myViewXMLWriter;
    private MapXMLWriter myMapXMLWriter;
    private GameElementXMLWriter myGameElementXMLWriter;
    private PlayerXMLWriter myPlayerXMLWriter;
    private RuleXMLWriter myRuleXMLWriter;
    private LevelXMLWriter myLevelXMLWriter;

    /**
     * constructor.
     */
    public XMLWriter () {
        myCreatedUnits = new ArrayList<String>();
        myXMLDoc = new XMLTool();
        initializeXMLWriters();
        initializeXML();
    }
    
    /**
     * helper method to create the writers.
     */
    private void initializeXMLWriters() {
        myViewXMLWriter = new ViewXMLWriter(myXMLDoc);
        myMapXMLWriter = new MapXMLWriter(myXMLDoc);
        myPlayerXMLWriter = new PlayerXMLWriter(myXMLDoc);
        myGameElementXMLWriter = new GameElementXMLWriter(myXMLDoc);
        myRuleXMLWriter = new RuleXMLWriter(myXMLDoc);
        myLevelXMLWriter = new LevelXMLWriter(myXMLDoc);
    }

    /**
     * Starts a new XML file & initializes the main parent elements
     */
    private void initializeXML () {
        myRoot = myXMLDoc.makeRoot(GAME_TAG);
        myViewParent = initializeSubParent(myRoot, myViewParent, VIEW_TAG);
        myMapParent = initializeSubParent(myRoot, myMapParent, MAP_TAG);
        myPlayerParent = initializeSubParent(myRoot, myPlayerParent, PLAYER_TAG);
        myGameElementParent = initializeSubParent(myRoot, myGameElementParent, GAME_ELEMENT_TAG);
        myRuleParent = initializeSubParent(myRoot, myRuleParent, RULES_TAG);
        myLevelParent = initializeSubParent(myRoot, myLevelParent, LEVELS_TAG);
    }
    
    /**
     * helper method to add the parent sections to the XML file.
     * @param root is the root of the file
     * @param parent is the parent of the sub section
     * @param tag is the name of this section
     */
    private Element initializeSubParent(Element root, Element parent, String tag) {
        parent = myXMLDoc.makeElement(tag);
        myXMLDoc.addChild(root, parent);
        return parent;
    }

    /**
     * 
     * @param dimension
     * @param viewInfo
     * @param map
     */
    public void addViewToGame (String dimension,
                               List<String> viewInfo,
                               Map<String, List<String>> map) {
        myViewXMLWriter.write(myViewParent, dimension, viewInfo, map);
    }

    /**
     * Adds a map to the XML file.
     * 
     * @param name The name of the map, used as a tag. This name will be formatted.
     * @param image A string URL to the background image
     * @param width The width determined by the user
     * @param height The height of the map determined by the user
     * @param tile Size The size of a tile
     * @param map The grid
     */
    public void addMapToGame (String name,
                              String image,
                              String width,
                              String height,
                              String tileSize,
                              String map) {
        myMapXMLWriter.write(myMapParent, name, image, width, height, tileSize, map);
    }
    
    /**
     * writes the player to the XML file.
     * @param attributes
     */
    public void addPlayerToGame(Map<String, String> attributes) {
        myPlayerXMLWriter.write(myPlayerParent, attributes);
    }

    /**
     * Creates a new game element based on user input and adds it to the game.
     * 
     * @param type Determines the type of element
     * @param name is the game element's name
     * @param path is the image path
     * @param attributes is the map of attribute name to value
     * @param actions is the map of action name to value
     */
    public void addGameElementToGame (String type,
                                      String name,
                                      String path, String dimension,
                                      Map<String, String> attributes,
                                      String actions) {
        if (type.equals(UNIT_INDICATOR)) {
            myCreatedUnits.add(name);
        }
        myGameElementXMLWriter.write(myGameElementParent, type, name, path, dimension, attributes, actions);
    }

    /**
     * adds the rules to the game.
     * 
     * @param rulesText
     */
    public void addRulesToGame (String rulesText) {
        myRuleXMLWriter.write(myRuleParent, rulesText);
    }

    /**
     * Creates a new level in the game and adds it to the XML file.
     * 
     * @param name The name of the level
     * @param rules A map of rules, which controls different instructions for updating scores and
     *        determining other game states
     * @param actions Any action present in the game.
     */
    public void addLevelToGame (String name, String rules, String actions) {
        myLevelXMLWriter.write(myLevelParent, name, rules, actions);
    }

    /**
     * Saves the XML file.
     */
    public void saveFile (String name) {
        myXMLDoc.writeFile(RESOURCE_PATH + name + XML_EXTENSION);
    }

    /**
     * Gets the list of already created units.
     * 
     * @return a list of unit names as strings.
     */
    public List<String> getUnits () {
        return myCreatedUnits;
    }

}

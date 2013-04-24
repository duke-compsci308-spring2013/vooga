package vooga.towerdefense.factories;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.attributes.DefaultAttributeManager;
import vooga.towerdefense.factories.actionfactories.ActionFactory;
import vooga.towerdefense.factories.definitions.GameElementDefinition;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

/**
 * A factory that creates game elements based on preset data
 * Reads from an xmlfile (eventually)
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * @author Zhen Gou
 */
public class GameElementFactory {
	
	private static DefaultAttributeManager DEFAULT_ATTRIBUTE_MANAGER = new DefaultAttributeManager();

    /**
     * Name of the element that is defined in this class. For convenience.
     */
    private String myName;
    private GameElementDefinition myDef; 
    private List<ActionFactory> myActionsToMake;
    private GameMap myMap;
    
    private Pixmap myImage;
    private Location myCenter;
    private Dimension mySize;
    private String myType;
    private AttributeManagerFactory myAttributeManager;
    
    public GameElementFactory() {
        myActionsToMake = new ArrayList<ActionFactory>();
    }
    
    /**
     * complete constructor
     * @param name
     * @param image
     * @param location
     * @param size
     * @param type
     * @param attrManager
     */
    
    public GameElementFactory(String name,Pixmap image, Location location, String type, AttributeManagerFactory attrManager){
    	myName=name;
    	myImage=image;
    	myCenter=location;
    	mySize=new Dimension(image.getWidth(),image.getHeight());
    	myType=type;
    	myAttributeManager=attrManager;
    	
    }
    
    @Deprecated
    public GameElementFactory(String name, GameElementDefinition definition) {
        this();
        myName = name;
        myDef = definition;
    }
    /*
    public GameElementFactory(GameElementDefinition definition) {
        this(definition.get(AttributeConstants.NAME), definition);
    }*/
    /**
     * must be called before create element
     * @param map
     */
    public void initialize(GameMap map) {
        myMap = map;
    }
    
    public GameMap getMap() {
        return myMap;
    }
    
    public String getName() {
        return myName;
    }
    @Deprecated
    public GameElementDefinition getDefinition() {
        return myDef;
    }
    
    /**
     * For testing only
     * @param def
     */
    @Deprecated
    public void setDefinition(GameElementDefinition def) {
        myDef = def;
    }

    public AttributeManagerFactory createAttributeFactory() {
        AttributeManagerFactory factory = new AttributeManagerFactory();
        return factory;
    }
    
    public void setActionFactories(List<ActionFactory> actionsToMake) {
        myActionsToMake = actionsToMake;
    }
    
    /**
     * Creates a list of actions for a specific element
     * @param e element to base actions around
     * @return list of the actions for that element
     */
    public List<Action> createActions(GameElement element) {
        List<Action> actions = new ArrayList<Action>();
        for (ActionFactory a : myActionsToMake) {
            actions.add(a.createAction(element));
        }
        return actions;
    }
    
    /**
     * Creates a new game element
     * @return
     */
    public GameElement createElement(){
        if (myDef == null) {
            return null;
        }
        /*GameElement element = new GameElement(myDef.getImage(), 
                                              myDef.getCenter(), 
                                              myDef.getSize(), 
                                              createAttributeFactory().makeAttributeManager(),
                                              myDef.getType());
        element.addActions(createActions(element));*/
        GameElement element = new GameElement(myImage, 
                myCenter, 
                mySize, 
                myAttributeManager,
                myType);
        element.addActions(createActions(element));
        return element;
    }

    /**
     * @param spawnLocation
     * @return 
     */
    public GameElement createElement (Location spawnLocation) {
        GameElement element = new GameElement(myDef.getImage(), 
                                              spawnLocation, 
                                              myDef.getSize(), 
                                              createAttributeFactory().makeAttributeManager(),
                                              myDef.getType());
        element.addActions(createActions(element));
        return element;
    }

    public AttributeManager getDefaultAM(){
    	return DEFAULT_ATTRIBUTE_MANAGER;
    }
}
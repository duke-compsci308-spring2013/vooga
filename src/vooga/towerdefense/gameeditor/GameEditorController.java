package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.w3c.dom.Element;
import util.XMLTool;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.gameElements.Wave;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

/**
 * Controls the game editor & makes the XML
 *      file based on input from the game editor.
 *
 * @author Angelica Schwartz
 */
public class GameEditorController extends JFrame {
    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String TITLE_KEYWORD = "GAME EDITOR";
    private static final String UNIT_TAG = "Unit";
    private static final String TOWER_TAG = "Tower";
    private static final String PROJECTILE_TAG= "Projectile";
    private static final String IMAGE_TAG = "Image";
    private static final String ATTRIBUTES_TAG = "Attributes";
    private static final String ACTIONS_TAG = "Actions";
    private static final Dimension SIZE = new Dimension(700, 700);
    private static final String RESOURCE_PATH = "vooga.src.vooga.towerdefense.resources.";
    public static final String CLASS_INDICATOR_STRING = ".class";
    private Dimension mySize;
    private Dimension myMapSize;
    private List<GameElement> myCreatedUnits;
    private List<Wave> myCreatedWaves;
    private String myName;
    private XMLTool myXMLDoc;
    private Element myRoot;
    private Element myUnitParent;
    private Element myTowerParent;
    private Element myProjectileParent;
    
    /**
     * Constructor.
     * 
     * @param size
     */
    public GameEditorController (Dimension size) {
        this.setTitle(TITLE_KEYWORD);
        myCreatedUnits = new ArrayList<GameElement>();
        myCreatedWaves = new ArrayList<Wave>();
        mySize = size;
        setSize(mySize);
        setPreferredSize(mySize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myXMLDoc = new XMLTool();
        myRoot = myXMLDoc.makeRoot("Game");
        myUnitParent = myXMLDoc.makeElement(UNIT_TAG);
        myTowerParent = myXMLDoc.makeElement(TOWER_TAG);
        myProjectileParent = myXMLDoc.makeElement(PROJECTILE_TAG);
        myXMLDoc.addChildElement(myRoot, myUnitParent);
        myXMLDoc.addChildElement(myRoot, myTowerParent);
        myXMLDoc.addChildElement(myRoot, myProjectileParent);       
        initializeGUI();
        
        //TODO: remove, this is just for testing
        GameElement temp = new GameElement(new Pixmap("tower.gif"), new Location(0,0), new Dimension(0,0), null, null);
        myCreatedUnits.add(temp);
        myCreatedUnits.add(temp);
        myCreatedUnits.add(temp);
        myCreatedUnits.add(temp);
        myCreatedUnits.add(temp);
        myCreatedUnits.add(temp);
    }

    /**
     * starts the visual for the game editor.
     */
    public void initializeGUI () {
        StartUpScreen screen = new StartUpScreen(SIZE, this);
        getContentPane().add(screen, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    /**
     * saves the xml file.
     */
    public void saveFile() {
        myXMLDoc.writeFile(myName);
    }
    
    /**
     * sets the name of this game.
     */
    public void setNameOfGame(String name) {
        myName = name;
    }
    
    /**
     * adds a level to the XML file.
     */
    public void addLevelToGame () {
        // TODO: implement
    }

    /**
     * adds a map to the XML file.
     */
    public void addMapToGame () {
        // TODO: implement
    }

    /**
     * adds a projectile to the XML file.
     * @param parent is the parent element
     * @param name is the game element's name
     * @param path is the image path
     * @param attributes is the map of attribute name to value
     * @param actions is the map of action name to value
     */
    public void addProjectileToGame(String name, String path, Map<String, String> attributes, Map<String, String> actions) {
        addGameElementToGame(myProjectileParent, name, path, attributes, actions);
        System.out.println("added projectile to game");
    }

    /**
     * adds a unit to the XML file.
     * @param parent is the parent element
     * @param name is the game element's name
     * @param path is the image path
     * @param attributes is the map of attribute name to value
     * @param actions is the map of action name to value
     */
    public void addUnitToGame(String name, String path, Map<String, String> attributes, Map<String, String> actions) {
        addGameElementToGame(myUnitParent, name, path, attributes, actions);
        System.out.println("added unit to game");        
    }
    
    /**
     * adds a tower to the XML file.
     * @param parent is the parent element
     * @param name is the game element's name
     * @param path is the image path
     * @param attributes is the map of attribute name to value
     * @param actions is the map of action name to value
     */
    public void addTowerToGame(String name, String path, Map<String, String> attributes, Map<String, String> actions) {
        addGameElementToGame(myTowerParent, name, path, attributes, actions);
        System.out.println("added tower to game");
    }
    
    /**
     * adds a game element to the XML file.
     * @param parent is the parent element
     * @param name is the game element's name
     * @param path is the image path
     * @param attributes is the map of attribute name to value
     * @param actions is the map of action name to value
     */
    private void addGameElementToGame(Element parent, String name, String path, Map<String, String> attributes, Map<String, String> actions) {
        Element unitElement = myXMLDoc.makeElement(name);
        myXMLDoc.addChildElement(parent, unitElement);
        myXMLDoc.addChild(unitElement, IMAGE_TAG, path);
        Element attributeElement = myXMLDoc.makeElementsFromMap(ATTRIBUTES_TAG, attributes);
        myXMLDoc.addChildElement(unitElement, attributeElement);
        Element actionElement = myXMLDoc.makeElementsFromMap(ACTIONS_TAG, actions);
        myXMLDoc.addChildElement(unitElement, actionElement);
    }

    /**
     * gets the list of already created units.
     * 
     * @return a list of gameelements
     */
    public List<GameElement> getUnits () {
        List<GameElement> g = new ArrayList<GameElement>();
        for (GameElement unit : myCreatedUnits) {
            g.add(unit);
        }
        return g;
    }

    /**
     * gets the list of already created waves.
     * 
     * @return a list of units
     */
    public List<Wave> getWaves () {
        return myCreatedWaves;
    }
    
    /**
     * adds a view to the XML file.
     */
    public void addViewToGame () {
        // TODO: implement
    }

    /**
     * adds a wave to the XML file.
     */
    public void addWaveToGame () {
        // TODO: implement
    }

    public void setMapSize (Dimension mapSize) {
        myMapSize = mapSize;
    }

    public Dimension getMapSize () {
        return myMapSize;
    }

    /**
     * Get icons for the game elements in the list.
     * 
     * @param gameElementsCreated
     * @return a list of images
     */
    public List<Image> getIconsForUnits () {
        List<Image> images = new ArrayList<Image>();
        for (GameElement unit : myCreatedUnits) {
            images.add(unit.getPixmap().getImage());
        }
        return images;
    }
    
    /**
     * write the xml file.
     */
    public void writeFile() {
        myXMLDoc.writeFile(myName + ".xml");
    }

    /**
     * uses reflection to display the next screen in the sequence.
     * 
     * @param nextScreenName is the next screen
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void displayNextScreen (String nextScreenName) throws ClassNotFoundException,
                                                         InstantiationException,
                                                         IllegalAccessException, SecurityException,
                                                         NoSuchMethodException,
                                                         IllegalArgumentException,
                                                         InvocationTargetException {
        if (nextScreenName != null) {
            Class[] args = { Dimension.class, GameEditorController.class };
            Class theClass = Class.forName(nextScreenName);
            Constructor cons = theClass.getConstructor(args);
            GameEditorScreen screen = (GameEditorScreen) cons.newInstance(mySize, this);
            getContentPane().add(screen);
            screen.display();
    
            pack();
            setVisible(true);
        }
        else {
            System.exit(0);
        }
    }

    /**
     * Get the classes in this package.
     * 
     * @param packageName
     * @return list of classes in the package
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    public List<Class> getClassesInPackage (String packageName) throws IOException,
                                                               ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        URL resource = classLoader.getResource(path);
        File directory = new File(resource.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(CLASS_INDICATOR_STRING)) {
                    classes.add(Class
                            .forName(packageName +
                                     "." +
                                     file.getName().subSequence(0,
                                                                file.getName().length()
                                                                        -
                                                                        CLASS_INDICATOR_STRING
                                                                                .length())));
                }
            }
        }
        return classes;
    }

    /**
     * gets the list of class names in the desired package.
     * 
     * @param packageName
     * @return a list of strings
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public List<String> getClassNamesInPackage (String packageName) throws IOException,
                                                                   ClassNotFoundException {
        List<String> names = new ArrayList<String>();
        List<Class> classes = getClassesInPackage(packageName);
        for (Class c : classes) {
            if (!c.getName().contains("$")) {
                names.add(c.getName().substring(packageName.length()+1,
                        c.getName().length()));
            }
            names.add(c.getName().substring(packageName.length() + 1,
                                            c.getName().length()));
        }

        // TODO: get rid of this general term
        if (names.contains("Action")) {
            names.remove("Action");
        }
        return names;
    }

    /**
     * gets the list of field names in the desired class.
     * 
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    public List<String> getFieldsInClass (String className) throws ClassNotFoundException {
        List<String> fields = new ArrayList<String>();
        Class attributesClass = Class.forName(className);
        Field fieldList[] = attributesClass.getDeclaredFields();
        for (Field field : fieldList) {
            fields.add(field.getName());
        }
        return fields;
    }
}

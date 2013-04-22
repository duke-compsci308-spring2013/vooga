package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.w3c.dom.Element;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;
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
    private static final String GAME_ELEMENT_TAG = "GameElement";
    private static final String IMAGE_TAG = "Image";
    private static final String ATTRIBUTES_TAG = "Attributes";
    private static final String ACTIONS_TAG = "Actions";
    private static final Dimension SIZE = new Dimension(700, 700);
    private static final String RESOURCE_PATH = "vooga.src.vooga.towerdefense.resources.";
    private static final String ACTION_PACKAGE_PATH = "vooga.towerdefense.factories.actionfactories";
    private static final String ATTRIBUTES_CLASS_PATH =
            "vooga.towerdefense.attributes.AttributeConstants";
    public static final String CLASS_INDICATOR_STRING = ".class";
    private Dimension mySize;
    private Dimension myMapSize;
    private List<GameElement> myCreatedUnits;
    private List<Wave> myCreatedWaves;
    private String myName;
    private XMLTool myXMLDoc;
    private Element myRoot;
    private Element myGameElementParent;
    
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
        myGameElementParent = myXMLDoc.makeElement(GAME_ELEMENT_TAG);
        myXMLDoc.addChildElement(myRoot, myGameElementParent);       
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
    
    public void addGameElementToGame(String type, String name, String path, Map<String, String> attributes, String actions) {
        addGameElementToFile(myGameElementParent, type, name, path, attributes, actions);
    }
    
    /**
     * adds a game element to the XML file.
     * @param parent is the parent element
     * @param name is the game element's name
     * @param path is the image path
     * @param attributes is the map of attribute name to value
     * @param actions is the map of action name to value
     */
    private void addGameElementToFile(Element parent, String type, String name, String path, Map<String, String> attributes, String actions) {
        Element unitElement = myXMLDoc.makeElement(name);
        myXMLDoc.addChildElement(parent, unitElement);
        myXMLDoc.addChild(unitElement, IMAGE_TAG, path);
        Element attributeElement = myXMLDoc.makeElementsFromMap(ATTRIBUTES_TAG, attributes);
        myXMLDoc.addChildElement(unitElement, attributeElement);
        //TODO: fix this for actions 
        //Element actionElement = myXMLDoc.makeElementsFromMap(ACTIONS_TAG, actions);
        //myXMLDoc.addChildElement(unitElement, actionElement);
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
     * returns the parameters of the constructor in the desired class.
     * @param className the class path
     * @return a list of parameters as strings
     * @throws ClassNotFoundException
     */
    public List<String> getParametersForAction(String className) throws ClassNotFoundException {
        List<String> parameters = new ArrayList<String>();
        //TODO: get rid of magic string
        Class c = Class.forName(ACTION_PACKAGE_PATH + "." + className + "Factory");
        Constructor cons = c.getConstructors()[0];
        Class[] parameterClasses = cons.getParameterTypes();
        Annotation[] annotations = cons.getDeclaredAnnotations();
        for (Class parameterClass: parameterClasses) {
            parameters.add(parameterClass.getSimpleName().toString());
        }
        return parameters;
    }
    
    /**
     * gets the available actions for the gam developer.
     * @param actionPackageName is the package where the action factories are.
     * @return 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<String> getAvailableActions() throws IOException, ClassNotFoundException {
        List<String> actions = new ArrayList<String>();
        List<Class> actionClasses = getClassesInPackage(ACTION_PACKAGE_PATH);
        for (Class actionClass : actionClasses) {
            String action = actionClass.getSimpleName().toString();
            action = action.substring(0, action.length()-"Factory".length());
            actions.add(action);
        }
        return actions;        
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
        return names;
    }
    
    /**
     * gets the attributes as strings for the game developer.
     * @return the attributes as a list of strings
     * @throws ClassNotFoundException
     */
    public List<String> getAttributes() throws ClassNotFoundException {
        List<String> fields = new ArrayList<String>();
        Field[] fieldList = getFieldsInClass(ATTRIBUTES_CLASS_PATH);
        for (Field field : fieldList) {
            fields.add(field.getName());
        }
        return fields;
    }

    /**
     * gets the list of field names in the desired class.
     * 
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    private Field[] getFieldsInClass (String className) throws ClassNotFoundException {
        Class attributesClass = Class.forName(className);
        Field fieldList[] = attributesClass.getDeclaredFields();
        return fieldList;
    }
}

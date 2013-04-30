package vooga.towerdefense.gameeditor.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import vooga.towerdefense.gameeditor.gamemaker.editorscreens.GameEditorScreen;
import vooga.towerdefense.gameeditor.gamemaker.editorscreens.StartUpScreen;
import vooga.towerdefense.gameeditor.gamemaker.xmlwriters.XMLWriter;


/**
 * Controls the game editor & makes the XML
 * file based on input from the game editor.
 * 
 * @author Angelica Schwartz
 * @author Leonard Ng'eno
 */
public class GameEditorController extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TITLE_KEYWORD = "GAME EDITOR";
    private static final String FACTORY_INDICATOR = "Factory";
    private static final Dimension SIZE = new Dimension(700, 700);
    private static final String CLASS_INDICATOR_STRING = ".class";
    private Dimension mySize;
    private Dimension myMapSize;
    private String myName;
    private XMLWriter myXMLWriter;

    /**
     * Constructor.
     * 
     * @param size The preferred size of this game editor.
     */
    public GameEditorController (Dimension size) {
        setTitle(TITLE_KEYWORD);
        mySize = size;
        setSize(mySize);
        setPreferredSize(mySize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeXML();
        initializeGUI();
    }

    /**
     * Starts a new XML file & initializes the main parent elements
     */
    private void initializeXML () {
        myXMLWriter = new XMLWriter();
    }

    /**
     * Starts the visual for the game editor.
     */
    private void initializeGUI () {
        StartUpScreen screen = new StartUpScreen(SIZE, this);
        getContentPane().add(screen, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    /**
     * Saves the XML file.
     */
    public void saveFile () {
        myXMLWriter.saveFile(myName);
    }

    /**
     * Sets the name of this game.
     * 
     * @param name The desired name of the game.
     */
    public void setNameOfGame (String name) {
        myName = name;
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
        myXMLWriter.addLevelToGame(name, rules, actions);
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
        myXMLWriter.addMapToGame(name, image, width, height, tileSize, map);
    }
    
    /**
     * adds the player information to the game.
     * @param attributes is the map of the attributes
     */
    public void addPlayerToGame(Map<String, String> attributes) {
        myXMLWriter.addPlayerToGame(attributes);
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
        myXMLWriter.addGameElementToGame(type, name, path, dimension, attributes, actions);
    }

    /**
     * adds the rules to the game.
     * 
     * @param rulesText
     */
    public void addRulesToGame (String rulesText) {
        myXMLWriter.addRulesToGame(rulesText);
    }

    /**
     * Gets the list of already created units.
     * 
     * @return a list of unit names as strings.
     */
    public List<String> getUnits () {
        return myXMLWriter.getUnits();
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
        myXMLWriter.addViewToGame(dimension, viewInfo, map);
    }

    /**
     * sets the map size for this game.
     * 
     * @param mapSize
     */
    public void setMapSize (Dimension mapSize) {
        myMapSize = mapSize;
    }

    /**
     * gets the map size for this game.
     * 
     * @return Dimension that is the map size
     */
    public Dimension getMapSize () {
        return myMapSize;
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
     * 
     * @param className the class path
     * @return a list of parameters as strings
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    public List<String> getParametersForAction (String className) throws ClassNotFoundException {
        List<String> parameters = new ArrayList<String>();
        Class c = Class.forName(className + FACTORY_INDICATOR);
        Constructor cons = c.getConstructors()[0];
        Class[] parameterClasses = cons.getParameterTypes();
        for (Class parameterClass : parameterClasses) {
            parameters.add(parameterClass.getSimpleName().toString());
        }
        return parameters;
    }

    /**
     * gets the available actions for the gam developer.
     * 
     * @param actionPackageName is the package where the action factories are.
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<String> getAvailableActions (String packageName) throws IOException,
                                                                ClassNotFoundException {
        List<String> actions = new ArrayList<String>();
        List<Class> actionClasses = getClassesInPackage(packageName);
        for (Class actionClass : actionClasses) {
            String action = actionClass.getSimpleName().toString();
            action = action.substring(0, action.length() - FACTORY_INDICATOR.length());
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
    public static List<Class> getClassesInPackage (String packageName) {
        List<Class> classes = new ArrayList<Class>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        URL resource = classLoader.getResource(path);
        path = resource.toString().replaceAll("%20", " ");
        try {
            resource = new URL(path);
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        File directory = new File(resource.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(CLASS_INDICATOR_STRING)) {
                    try {
                        classes.add(Class
                                .forName(packageName +
                                         "." +
                                         file.getName().subSequence(0,
                                                                    file.getName().length()
                                                                            -
                                                                            CLASS_INDICATOR_STRING
                                                                                    .length())));
                    }
                    catch (ClassNotFoundException e) {
                    }
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
                names.add(c.getName().substring(packageName.length() + 1,
                                                c.getName().length()));
            }
            names.add(c.getName().substring(packageName.length() + 1,
                                            c.getName().length()));
        }
        return names;
    }

    /**
     * gets the attributes as strings for the game developer.
     * 
     * @return the attributes as a list of strings
     * @throws ClassNotFoundException
     */
    public List<String> getAttributes (String attributeClassName) throws ClassNotFoundException {
        List<String> fields = new ArrayList<String>();
        Field[] fieldList = getFieldsInClass(attributeClassName);
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
    
    public List<String> getFiles(String packagePath) {
        List<String> fileNames = new ArrayList<String>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packagePath.replace(".", "/");
        URL resource = classLoader.getResource(path);
        path = resource.toString().replaceAll("%20", " ");
        try {
            resource = new URL(path);
        }
        catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        File directory = new File(resource.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
}

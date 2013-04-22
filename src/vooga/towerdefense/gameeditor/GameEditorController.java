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
import javax.swing.JFrame;
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

    public static final String CLASS_INDICATOR_STRING = ".class";
    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String TITLE_KEYWORD = "GAME EDITOR";
    private static final Dimension SIZE = new Dimension(700, 700);
    private static final String RESOURCE_PATH = "vooga.src.vooga.towerdefense.resources.";
    private Dimension mySize;
    private Dimension myMapSize;
    private List<GameElement> myCreatedUnits;
    private List<Wave> myCreatedWaves;
    private XMLTool myXMLTool;
    
    /**
     * Constructor.
     * 
     * @param size
     */
    public GameEditorController (Dimension size) {
        this.setTitle(TITLE_KEYWORD);
        myCreatedUnits = new ArrayList<Unit>();
        myCreatedWaves = new ArrayList<Wave>();
        mySize = size;
        setSize(mySize);
        setPreferredSize(mySize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeGUI();
        
        //TODO: remove, this is just for testing
        GameElement temp = new GameElement(new Pixmap("tower.gif"), new Location(0,0), new Dimension(0,0), null, null);

        // TODO: remove, this is just for testing
        Unit temp =
                new Unit(new Pixmap("tower.gif"), new Location(0, 0), new Dimension(0, 0), null, null);
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
     * sets the name of this game.
     */
    public void setNameOfGame(String name) {
        String path = RESOURCE_PATH.replace(".", "/");
        path = path.replace("%20", " ");
        myXMLTool = new XMLTool(path + name + ".xml");
    }
    
    /**
     * adds a level to the XML file.
     */
    public void addLevelToGame () {
        // TODO: implement
        System.out.println("added level to game");
    }

    /**
     * adds a map to the XML file.
     */
    public void addMapToGame () {
        // TODO: implement
        System.out.println("added map to game");
    }

    /**
     * adds a projectile to the XML file.
     */
    public void addProjectileToGame () {
        // TODO: implement
        System.out.println("added projectile to game");
    }

    /**
     * adds a unit to the XML file.
     */
    public void addUnitToGame () {
        // TODO: implement
        System.out.println("added unit to game");
        //update myCreatedUnits to contain the new unit
        
    }
    
    /**
     * adds a game element to the XML file.
     */
    public void addGameElementToGame() {
        //TODO: make xml file for an element. use this
        //    method for other parts. 
        // update myCreatedUnits to contain the new unit
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
     * adds a tower to the XML file.
     */
    public void addTowerToGame () {
        // TODO: implement
        System.out.println("added tower to game");
    }

    /**
     * adds a view to the XML file.
     */
    public void addViewToGame () {
        // TODO: implement
        System.out.println("add this view to game");
    }

    /**
     * adds a wave to the XML file.
     */
    public void addWaveToGame () {
        // TODO: implement
        System.out.println("add wave to game");
    }

    public void setMapSize (Dimension mapSize) {
        myMapSize = mapSize;
    }

    public Dimension getMapSize () {
        // return myMapSize; // TODO FIX THIS
        return new Dimension(500, 500);
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
        Class[] args = { Dimension.class, GameEditorController.class };
        Class theClass = Class.forName(nextScreenName);
        Constructor cons = theClass.getConstructor(args);
        GameEditorScreen screen = (GameEditorScreen) cons.newInstance(mySize, this);
        getContentPane().add(screen);
        screen.display();

        pack();
        setVisible(true);
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

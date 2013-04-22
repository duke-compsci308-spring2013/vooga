package arcade.view.forms.payment.factory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 * Reusable utility class submission for Ellango Jothimurugesan from SLogo
 *
 *
 * Creates a map for all of the keywords to a prototype of the corresponding
 * class.
 * 
 * In the context of SLogo, this means both "forward" and "fd" will be mapped
 * to a Forward object. In general, multiple keywords can be mapped to any
 * object the user of this class would like. These objects should all inherit
 * from the <V> that this class is defined with.
 * 
 * 
 * To generate keys for the map, a ResourceBundle is used so that multiple
 * languages can be supported. If more than one key is desired, they should
 * be comma separated.
 * 
 * Example for keys:
 * Classname = key1,key2
 * Forward = forward,fd
 * 
 * To generate values for the map, a text file or Reader is needed that contains
 * all of the different class names for the desired objects. One class per line.
 * 
 * Example for values:
 * Classpath
 * instructions.turtle.forward
 * 
 * 
 * @param <V> is the Class of the prototypes that the properties file maps to.
 * 
 * @author Scott Valentine
 * @author Ryan Fishel
 * @author Ellango Jothimurugesan
 * 
 */
public class PrototypeMapFactory<V> {

    private static final String DOT = "[.]";
    private static final String USER_DIRECTORY = "user.dir";
    private static final String PROPERTIES_SEPERATOR = "[,]";
    private static final char COMMENT_CHARACTER = '#';
    private static final String MISSING_FILE_ERROR_MESSAGE =
            "Error: File is missing";
    private static final String INCORRECT_FORMATTING_ERROR_MESSAGE =
            "Error: File is missing class data";

    private ResourceBundle myResources;
    private Scanner myScanner;

    /**
     * Constructor that creates new factory based on the resource bundle of
     * class keywords and a string containing the path of a text file for
     * class names.
     * 
     * @param resources is a resource bundle that contains keywords
     * @param indexFile is the location of the text file that contains
     *        prototyping class info.
     */
    public PrototypeMapFactory (ResourceBundle resources, String indexFile) {
        this(resources, makeReaderFromFile(indexFile));
    }

    /**
     * Constructor that creates new factory based on the resource bundle of
     * class keywords and a Reader containing class names.
     * 
     * 
     * @param resources is a resource bundle that contains keywords
     * @param reader is a Reader that contains prototyping class info.
     */
    public PrototypeMapFactory (ResourceBundle resources, Reader reader) {
        myResources = resources;
        myScanner = new Scanner(reader);
    }

    /**
     * Creates a Reader from the file to read in
     * 
     * @return Reader
     */
    private static Reader makeReaderFromFile (String filename) {
        String currentDirectory = System.getProperty(USER_DIRECTORY);
        try {
            return new FileReader(currentDirectory + filename);
        }
        catch (FileNotFoundException e) {
            throw new MissingResourceException(MISSING_FILE_ERROR_MESSAGE, "", "");
        }
    }

    /**
     * Builds the prototype map that maps keyword strings to their objects
     * 
     * @return Map of keywords to objects.
     */
    public Map<String, V> buildStringMap () {
        Map<String, V> protoMap = new HashMap<String, V>();
        while (myScanner.hasNextLine()) {
            String line = myScanner.nextLine();
            if (isValidLine(line)) {
                String[] keyWords = getKeys(line);
                V value = getValue(line);
                for (String keyword : keyWords) {
                    if (keyword.length() > 0) {
                        protoMap.put(keyword, value);
                    }
                }
            }
        }
        myScanner.close();
        return protoMap;
    }

    /**
     * Returns if line should be read.
     * Commented lines should return false.
     * 
     * @param line to read
     * @return true if valid line
     */
    private boolean isValidLine (String line) {
        return line.charAt(0) != COMMENT_CHARACTER && line.length() > 0;
    }

    /**
     * Gets the keywords that should be mapped to a given object.
     * 
     * @param line is the current line that contains a class name
     * @return all the keywords for that object
     */
    private String[] getKeys (String line) {
        String className = getClassName(line);
        String entry = myResources.getString(className);
        String[] keys = entry.split(PROPERTIES_SEPERATOR);
        for (String key : keys) {
            key = key.trim();
        }
        return keys;
    }

    /**
     * Determines the name of the class from the class path.
     * 
     * @param classPath is the classPath to determine the class name.
     * @return The name of the class at the given classPath.
     */
    private String getClassName (String classPath) {
        String[] path = classPath.split(DOT);
        return path[path.length - 1];
    }

    /**
     * Gets the value that should be mapped
     * 
     * @param className
     * @return the objects to put in the map
     */
    @SuppressWarnings("unchecked")
    protected V getValue (String className) {
        try {
            Class<?> genericClass = Class.forName(className);
            return (V) genericClass.newInstance();
        }
        catch (IllegalAccessException e) {
            throw new MissingResourceException(INCORRECT_FORMATTING_ERROR_MESSAGE, "", "");
        }
        catch (InstantiationException e) {
            throw new MissingResourceException(INCORRECT_FORMATTING_ERROR_MESSAGE, "", "");
        }
        catch (ClassNotFoundException e) {
            throw new MissingResourceException(INCORRECT_FORMATTING_ERROR_MESSAGE, "", "");
        }
    }
}

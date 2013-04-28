package vooga.scroller.level_editor.commands;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import vooga.scroller.level_editor.model.LevelEditor;


/**
 * Contains the Map of @Command Methods to their names.
 * 
 * @author Danny Goodman
 * 
 */
public class CommandLibrary {

    private static Map<String, Method> ourCommandMap;

    static {
        ourCommandMap = new HashMap<String, Method>();
        fillMap();
    }

    private CommandLibrary () {
        // Not Instantiated.
    }

    /**
     * Gets the Method represented by the key. If key does not represent the
     * name of a @Command Method, returns null.
     * 
     * @param key - name of the Method
     * @return Method represented by key
     */
    public static Method get (String key) {
        return ourCommandMap.get(key);
    }

    /**
     * Populates myCommandMap with @Command Methods from the LevelEditor
     * mapped to their Method names.
     */
    private static void fillMap () {
        Method[] allMethods = LevelEditor.class.getMethods();
        for (int i = 0; i < allMethods.length; i++) {
            Method currentMethod = allMethods[i];
            if (currentMethod.isAnnotationPresent(Command.class)) {
                ourCommandMap.put(currentMethod.getName(), currentMethod);
            }
        }
    }
}

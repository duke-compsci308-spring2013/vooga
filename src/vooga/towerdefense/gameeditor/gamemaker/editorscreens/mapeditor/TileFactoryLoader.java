package vooga.towerdefense.gameeditor.gamemaker.editorscreens.mapeditor;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.gameeditor.controller.GameEditorController;
import vooga.towerdefense.model.tiles.factories.TileFactory;


/**
 * This class uses reflection to get the files and classes contained in a
 * package and instantiates the classes.
 * 
 * @author Leonard K. Ng'eno
 * 
 */
public class TileFactoryLoader {

    private GameEditorController myController;

    TileFactoryLoader (GameEditorController controller) {
        myController = controller;
    }

    /**
     * 
     * @param packageName name of the package
     * @return array of file names
     */
    public File[] getImages (String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(packageName);
        File directory = new File(resource.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            return files;
        }
        return null;
    }

    /**
     * instantiates tile factories
     * 
     * @param packageName name of the package
     */
    @SuppressWarnings({ "rawtypes", "static-access" })
    public List<TileFactory> initTileFactories (String packageName) {
        List<Class> classes = new ArrayList<Class>();
        List<TileFactory> tilefactories = new ArrayList<TileFactory>();
        classes = myController.getClassesInPackage(packageName);
        for (Class myClass : classes) {
            try {
                if (myClass != vooga.towerdefense.model.tiles.factories.TileFactory.class) {
                    try {
                        @SuppressWarnings("unchecked")
                        Constructor ctor = myClass.getConstructor();
                        try {
                            tilefactories.add((TileFactory) ctor.newInstance());
                        }
                        catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return tilefactories;
    }

}

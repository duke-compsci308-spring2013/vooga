package vooga.rts.resourcemanager;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import vooga.rts.resourcemanager.exceptions.FileNotSupportedException;
import vooga.rts.util.TimeIt;


/**
 * This class manages the loading of all resources in the game.
 * It supports any kind of file and uses the correct ResourceLoader
 * in order to load the files of a certain type. The type of the file
 * is determined by the extension.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ResourceManager {

    private static final String EXTENSION_NOT_SUPPORTED = "The file %s has extension %s which is not supported by the Resource Manager.";

    private static ResourceManager myInstance = new ResourceManager();

    /**
     * Maps the extension of a file to the appropriate Resource Loader.
     */
    private Map<String, ResourceLoader> myLoaderMap;

    /**
     * Stores all of the information that the Resource Manager has loaded.
     */
    private Map<URL, Object> myResourceStorage;

    /**
     * The queue of files to process.
     */
    private Queue<URL> myLoadQueue;

    /**
     * Thread used to load all the files.
     */
    private Thread myLoadThread;
    
    private TimeIt myTime;

    private ResourceManager () {
        myLoaderMap = new HashMap<String, ResourceLoader>();
        myResourceStorage = new HashMap<URL, Object>();
        myLoadQueue = new LinkedList<URL>();
        myLoadThread = new Thread();
    }

    /**
     * Adds a Resource Loader to the Resource Manager and registers
     * the types of files that the loader can handle.
     * 
     * @param loader
     */
    public void registerResourceLoader (ResourceLoader loader) {
        for (String ext : loader.getSupportedExtensions()) {
            myLoaderMap.put(ext, loader);
        }
    }

    /**
     * Returns whether the Manager is still loading files.
     * 
     * @return whether it is still loading.
     */
    public boolean isLoading () {
        synchronized (myLoadThread) {
            return myLoadThread.isAlive();
        }
    }

    /**
     * Queues a file for loading. Adds it to the list but only
     * loads the file from the disk when load() is called.
     * 
     * @param filename The file name of the file to load.
     * @return Whether it was able to queue the file or not.
     * @throws FileNotSupportedException 
     */
    public boolean queueFile (String filename) throws FileNotSupportedException {
        URL file = getFileName(filename);
        return queueFile(file);
    }

    /**
     * Queues a file for loading. Adds it to the list but only
     * loads the file from the disk when load() is called.
     * 
     * @param filename The file name of the file to load.
     * @return Whether it was able to queue the file or not.
     * @throws FileNotSupportedException 
     */
    public boolean queueFile (URL filename) throws FileNotSupportedException {
        if (filename == null) {
            return false;
        }
        
        String ext = getExtension(filename.getPath());
        if (!myLoaderMap.containsKey(ext)) {
            throw new FileNotSupportedException(String.format(EXTENSION_NOT_SUPPORTED, filename.getPath(), ext));
        }
        
        synchronized (myResourceStorage) {
            if (myResourceStorage.containsKey(filename)) {
                System.out.println("Load Saved");
                return false;
            }
        }
        synchronized (myLoadQueue) {
            myLoadQueue.add(filename);
            return true;
        }
    }

    /**
     * Starts loading the resources that have been queued.
     */
    public void load () {
        System.out.println("Starting Load" );
        myTime = new TimeIt();
        if (!isLoading()) {
            myLoadThread = new Thread(new Runnable() {
                @Override
                public void run () {
                    loadFiles();
                }
            });
            myLoadThread.start();
        }
    }
    
    private void loadFiles() {
        synchronized (myLoadQueue) {
            while (!myLoadQueue.isEmpty()) {
                URL nextFile = myLoadQueue.poll();
                loadFile(nextFile);
            }
            System.out.println("Loaded!");
            myTime.printTime();
        }
    }
    
    private void loadFile(URL filename) {
        String ext = getExtension(filename.getPath());
        ResourceLoader rl = myLoaderMap.get(ext);
        Object loaded = rl.loadFile(filename);
        synchronized (myResourceStorage) {
            myResourceStorage.put(filename, loaded);
        }
    }

    public <T> T getFile (String filename, Class<T> resourceType) { //  throws FileNotSupportedException {
        URL file = getFileName(filename);
        try {
            if (queueFile(file)) {
                load();
                try {
                    // Wait for loading to complete
                    myLoadThread.join();
                }
                catch (InterruptedException e) {
                    System.out.println("Error waiting for threads.");
                }
            }
        }
        catch (FileNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Object loadedFile = myResourceStorage.get(file);
        if (resourceType.isAssignableFrom(loadedFile.getClass())) {
            return resourceType.cast(loadedFile);
        }
        return null;
    }

    /**
     * Converts the string filename to a URL.
     * 
     * @param filename The filename of the file to convert
     * @return The converted URL
     */
    private URL getFileName (String filename) {
        URL f = getClass().getResource("/vooga/rts/resources/" + filename);
        return f;
    }

    public static ResourceManager getInstance () {
        return myInstance;
    }
    
    /**
     * Returns the extension of a particular filename.
     * 
     * @param filename The file name including the extension
     * @return The extension
     */
    public static String getExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index > 0) {
            return filename.substring(index + 1);
        }
        return "";
    }
}

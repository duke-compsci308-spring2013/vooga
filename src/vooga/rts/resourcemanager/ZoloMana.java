/**
 * 
 */
package vooga.rts.resourcemanager;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.imageio.ImageIO;


/**
 * 
 * 
 * @author Jonathan Schmidt
 * 
 */
public class ZoloMana {

    private static final String RESOURCES = "../resources/";

    private Map<URL, BufferedImage> myResources;

    private Queue<URL> myLoadQueue;

    private Thread loadThread;

    private static ZoloMana myInstance;

    /**
     * 
     */
    private ZoloMana () {
        myResources = new HashMap<URL, BufferedImage>();
        myLoadQueue = new LinkedList<URL>();
        loadThread = new Thread();
    }
    
    private URL getFileName(String file) {
        URL f = getClass().getResource(RESOURCES + file);
        return f;
    }
    
    public boolean isLoading () {
        return loadThread.isAlive();
    }

    public BufferedImage loadFile (String filename) {
        if (queueFile(filename)) {
            load();
            try {
                loadThread.join();
            }
            catch (InterruptedException e) {
                System.out.println("Error waiting for threads.");
            }
        }
        return myResources.get(getFileName(filename));        
    }

    public boolean queueFile (String filename) {
        URL f = getFileName(filename);
        if (!myResources.containsKey(f)) {
            if (f != null) {
                myLoadQueue.add(f);
            }
            else {
                System.out.println("No such file");
            }
            return true;
        }
        System.out.println("File already stored.");
        return false;
    }

    public void load () {
        if (!isLoading()) {
            loadThread = new Thread(new Runnable() {
                @Override
                public void run () {
                    loadFiles();
                }
            });
            loadThread.start();
        }
    }

    private synchronized void loadFiles () {
        while (!myLoadQueue.isEmpty()) {
            URL next = myLoadQueue.poll();
            loadSingleFile(next);
        }
    }

    private void loadSingleFile (URL filename) {
        try {
            BufferedImage image = ImageIO.read(filename);            
            myResources.put(filename, image);
        }
        catch (IOException e) {
            System.out.println("This is not an image.");
        }
    }

    public static ZoloMana instance () {
        if (myInstance == null) {
            myInstance = new ZoloMana();
        }
        synchronized (myInstance) {
            return myInstance;
        }
    }

}

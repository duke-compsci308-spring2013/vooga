package vooga.fighter.model.utils;

import java.awt.Dimension;
import util.Location;
import util.Pixmap;

/**
 * Data object for an image representing a game object. Includes size of the 
 * object, location of the object on a canvas, and the image itself.
 *  
 * @author James Wei
 *
 */
public class ImageDataObject {

    private Pixmap myImage;
    private Location myLocation;
    private Dimension mySize;
    
    public ImageDataObject(Pixmap image, Location location, Dimension size) {
        myImage = image;
        myLocation = location;
        mySize = size;
    }
    
    public Pixmap getMyImage () {
        return myImage;
    }
    
    public Location getMyLocation () {
        return myLocation;
    }    
    
    public Dimension getMySize () {
        return mySize;
    }        
    
}

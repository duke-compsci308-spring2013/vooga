package util;

import java.awt.Dimension;
import util.Location;
import util.Paintable;
/**
 * @author Jack Matteucci
 *This interface can be implemented by a Controller or Model that is talking
 *the View Element in charge of gathering the painting information.  Just have 
 *the View Element take this interface as a parameter in its constructor, pass 
 *in your Controller/Model class, implement these methods, and you have a fairly
 *extensible/detached View and Controller systme
 *
 */
	
public interface ViewDataSource {

    /**
     * @return Number of Paintable objects
     */
    public int numberOfpaintables();

    /**
     * @param index
     * @return The Paintable object with given index
     */
    public Paintable getPaintable (int index);

    /**
     * @param index
     * @return The Location required the Paintable object with given index
     */
    public Location getLocation (int index);

    /**
     * @param index
     * @return The size as a Dimension for the Paintable object with given index.
     */
    public Dimension getSize (int index);
}

package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
/**
 * @author Jack Matteucci
 * 
 *This interface can be implemented by any class that is, well, paintable.
 *The first class that comes to mind is Pixmap, but essentially it to generalize 
 *the concept of something that is paintable, so that you don't have to assume you
 *are dealing with a Pixmap, or be constrained to using a pixmap.
 *
 */

public interface Paintable {

    /**
     * @param pen, Graphics2D pen, like we're used to
     * @param center, center of the image
     * @param size, using the dimension, dictates the size of the image painted
     * paints the image stored within the paintable at particular location with particular size
     */
    public void paint (Graphics2D pen, Point2D center, Dimension size);
    
}

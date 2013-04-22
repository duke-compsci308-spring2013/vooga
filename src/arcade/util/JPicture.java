package arcade.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import util.Location;

/**
 * JPicture is a Pixmap that is contained within a JComponent.  
 * 
 * This basically allows Pixmaps to be added to a panel.
 * 
 * @author Ellango, David
 *
 */
@SuppressWarnings("serial")
public class JPicture extends JComponent {
    
    private Pixmap myImage;
    private Dimension mySize;
    
    /**
     * Constructs a JPicture with the filename containing where the image is
     * stored, and the desired size of the picture on the panel.
     * 
     * @param filename
     * @param size
     */
    public JPicture(String filename, Dimension size) {
        this(new Pixmap(filename), size);
    }
    
    /**
     * Constructs a JPicture with the Pixmap containing the image, and the 
     * desired size of the picture on the panel.
     * 
     * @param filename
     * @param size
     */
    public JPicture(Pixmap pixmap, Dimension size) {
        myImage = pixmap;
        mySize = size;
    }

    @Override
    public void paintComponent (Graphics pen) {
        Location center = new Location(mySize.getWidth()/2, mySize.getHeight()/2);
        myImage.paint((Graphics2D) pen, center, mySize);
    }
}

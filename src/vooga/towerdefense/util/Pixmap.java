package vooga.towerdefense.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;


/**
 * This class represents an image on the screen and
 * adds some utility functions to the Image class.
 * 
 * Note, Java only supports the formats: png, jpg, gif.
 * 
 * @author Robert C. Duvall
 */
public class Pixmap {
    // OS-independent relative resource locations (like URLs)
    private static final String RESOURCE_LOCATION = "/vooga/towerdefense/images/";
    // underlying implementation
    private java.awt.Image myImage;
    private String myFileName;
    private int myWidth;
    private int myHeight;

    /**
     * Create an image from the given filename.
     */
    public Pixmap (String fileName) {
        setImage(fileName);
    }

    /**
     * Create a copy of image from the given other image.
     */
    public Pixmap (Pixmap other) {
        this(other.myFileName);
    }

    /**
     * Set this image to the image referred to by the given filename.
     */
    public void setImage (String fileName) {
        myImage = new ImageIcon(getClass().getResource(RESOURCE_LOCATION + fileName)).getImage();
        myFileName = fileName;
    }

    /**
     * Describes how to draw the image on the screen.
     */
    public void paint (Graphics2D pen, Point2D center, Dimension size) {
        paint(pen, center, size, 0);
    }

    /**
     * Describes how to draw the image rotated on the screen.
     */
    public void paint (Graphics2D pen, Point2D center, Dimension size, double angle) {
        myWidth = size.width;
        myHeight = size.height;
        // save current state of the graphics area
        AffineTransform old = new AffineTransform(pen.getTransform());
        // move graphics area to center of this shape
        pen.translate(center.getX(), center.getY());
        // rotate area about this shape
        pen.rotate(angle);
        // draw as usual (i.e., rotated)
        pen.drawImage(myImage, -size.width / 2, -size.height / 2, size.width, size.height, null);
        // restore graphics area to its old state, so our changes have no lasting effects
        pen.setTransform(old);
    }

    /**
     * 
     * @return String. The name of the Pixmap's image
     */
    public String getFileName () {
        return myFileName;
    }

    /**
     * 
     * @return the Image used by the Pixmap
     */
    public Image getImage () {
        return myImage;
    }

    // /**
    // *
    // * @return the center location of where the Pixmap image is painted
    // */
    // public Point2D getCenter () {
    // System.out.println("returned center:" + myCenter);
    // return myCenter;
    // }

    /**
     * 
     * @return the width of the Pixmap image
     */
    public int getWidth () {
        return myWidth;
    }

    /**
     * 
     * @return the height of the Pixmap image
     */
    public int getHeight () {
        return myHeight;
    }
}

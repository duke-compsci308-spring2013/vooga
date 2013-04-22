package util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Class with static methods that help handling images
 * @author Henrique Moraes
 *
 */
public class ImageHelper {
    
    /**
     * Resizes the image to to square size from a given path
     * @param path path of the image to scale
     * @param size The height and width of the scaled image
     * @return square Image scaled to the user parameter
     */
    public static Image getScaledImage(String path, int size){
        return getScaledImage(getImageIcon(path).getImage(),size);
    }
    
    /**
     * Resizes image from a given path
     * @param path path of the image to scale
     * @param width The new width of the scaled image
     * @param height The new height of the scaled image
     * @return Image scale to the user parameter
     */
    public static Image getScaledImage(String path, int width, int height){
        return getScaledImage(getImageIcon(path).getImage(),width, height);
    }
    
    /**
     * Gets an image icon from the given path
     * @param path path of the image
     * @return The image icon for this image
     */
    public static ImageIcon getImageIcon(String path) {
        return new ImageIcon(ImageHelper.class.getResource(path));
    }
         
    /**
     * Resizes an Image into square dimensions
     * @param image the image to scale
     * @param size The height and width of the scaled image
     * @return Image scale to the user parameter
     */
    public static Image getScaledImage(Image image,int size){
        return getScaledImage(image, size, size);
    }
    
    /**
     * Resizes an Image
     * @param image the image to scale
     * @param width The new width of the scaled image
     * @param height The new height of the scaled image
     * @return Image scale to the user parameter
     */
    public static Image getScaledImage(Image image,int width, int height){
        BufferedImage buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return buffer;
    }
}
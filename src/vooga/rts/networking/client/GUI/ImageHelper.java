package vooga.rts.networking.client.GUI;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageHelper {
    
    public static Image getScaledImage(String path, int size){
        return getScaledImage(getImageIcon(path).getImage(),size);
    }
    
    public static ImageIcon getImageIcon(String path) {
        return new ImageIcon(ImageHelper.class.getResource(path));
    }
            
    public static Image getScaledImage(Image image,int size){
        BufferedImage buffer = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, size, size, null);
        g2.dispose();
        return buffer;
    }
}

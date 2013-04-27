package vooga.rts.util;

import java.awt.Dimension;
import vooga.rts.gui.Window;

public class Scale {

    
    public static Location scaleLocation (int x, int y) {
        double xFactor = (double) Window.SCREEN_SIZE.getWidth() / (double) Window.D_X;
        double yFactor = (double) Window.SCREEN_SIZE.getHeight() / (double) Window.D_Y;
        int newHeight = (int) (yFactor * y);
        int newWidth = (int) (xFactor * x);
        return new Location(newWidth, newHeight);
    }
    
    public static Location scaleLocation (Location l) {
        return scaleLocation((int) l.getX(), (int) l.getY());
    }
    
    public static Dimension scaleDimension (int x, int y) {
        double xFactor = (double) Window.SCREEN_SIZE.getWidth() / (double) Window.D_X;
        double yFactor = (double) Window.SCREEN_SIZE.getHeight() / (double) Window.D_Y;
        int newHeight = (int) (yFactor * y);
        int newWidth = (int) (xFactor * x);
        return new Dimension(newWidth, newHeight);
    }
    
    public static Dimension scaleDimension (Dimension d) {
        return scaleDimension((int) d.getWidth(), (int) d.getHeight());
    }
    
    public static int scaleX (int x) {
        return (int) ((Window.SCREEN_SIZE.getWidth() / Window.D_X) * x);
    }
    
    public static int scaleY (int y) {
        return (int) ((Window.SCREEN_SIZE.getHeight() / Window.D_Y) * y);
    }
    
    public static Dimension unScaleDimension(Dimension d) {
        double xFactor = (double) Window.D_X / (double) Window.SCREEN_SIZE.getWidth();
        double yFactor = (double) Window.D_Y / (double) Window.SCREEN_SIZE.getHeight();
        int newHeight = (int) (yFactor * d.getHeight());
        int newWidth = (int) (xFactor * d.getWidth());
        return new Dimension(newWidth, newHeight);
    }
    
}

package vooga.rts.util;

import java.awt.Dimension;
import vooga.rts.gui.Window;


public class SDimension extends java.awt.Dimension {

    private static final long serialVersionUID = 1L;

    public SDimension () {
        super(0, 0);
    }

    public SDimension (int x, int y) {
        super(x, y);
        scaleXY();
    }

    public SDimension (Dimension d) {
        super(d);
        scaleXY();
    }

    public void scaleXY () {
        width = (int) ((Window.SCREEN_SIZE.getWidth() / Window.D_X) * width);
        height = (int) ((Window.SCREEN_SIZE.getHeight() / Window.D_Y) * height);
    }

}

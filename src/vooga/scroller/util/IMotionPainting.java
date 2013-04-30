package vooga.scroller.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Objects handling motion painting are able to do basic motion painting and paint
 * themselves with varying sizes, angles, and position.
 * @author Dagbedji Fagnisse
 *
 */
public interface IMotionPainting {

    
    public void paint (Graphics2D pen, Point2D center, Dimension size, double angle);
    
}

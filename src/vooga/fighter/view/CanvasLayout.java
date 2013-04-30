package vooga.fighter.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import vooga.fighter.controller.interfaces.ViewDataSource;

/**
 * Defines a layout for paintable elements for the canvas.
 * 
 * @author Wayne You
 * @author Bill Muensterman
 * 
 */
public abstract class CanvasLayout {
    public abstract void paintComponents(Graphics2D pen, ViewDataSource data,
            Dimension screenSize);
    
    /**
     * Default paint of a Paintable to the location specified.
     * 
     * @param pen
     *            Graphics pen given by Java
     * @param data
     *            Data source containing the paintables
     * @param objectNumber
     *            The specific object being referenced and painted
     */
    protected void defaultPaint(Graphics2D pen, ViewDataSource data,
            int objectNumber) {
        data.getPaintable(objectNumber).paint(pen,
                data.getLocation(objectNumber), data.getSize(objectNumber));
    }
    
    protected void horizontalReversePaint(Graphics2D pen, ViewDataSource data,
            int objectNumber) {
        AffineTransform saveAT = pen.getTransform();
        pen.transform(AffineTransform.getScaleInstance(1, -1));
        data.getPaintable(objectNumber).paint(pen,
                data.getLocation(objectNumber), data.getSize(objectNumber));
        pen.setTransform(saveAT);
    }
}

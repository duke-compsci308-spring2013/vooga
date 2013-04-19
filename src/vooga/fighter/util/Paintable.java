package vooga.fighter.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public interface Paintable {

    public void paint (Graphics2D pen, Point2D center, Dimension size);
}
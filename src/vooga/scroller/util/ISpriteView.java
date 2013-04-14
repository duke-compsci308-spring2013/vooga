package vooga.scroller.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

public interface ISpriteView extends IPaintable {

    ISpriteView reset ();

    void paint (Graphics2D pen, Point2D myCenter, Dimension mySize);

    Image getDefaultImg ();

    void paint (Graphics2D pen, Point2D center, Dimension size, double angle);

}

package vooga.towerdefense.gameeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import vooga.towerdefense.model.tiles.Tile;


/**
 * 
 * This class is a representation of one tile on the MapMaker screen. Enables the
 * creation of paths.
 * 
 * @author Leonard K. Ng'eno
 * 
 */
public class Grid extends Rectangle {
    private static final long serialVersionUID = 1L;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private Tile myTile;

    public Grid (int x, int y, int width, int height, Tile tile) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        myTile = tile;
    }

    public void setTile (Tile tile) {
        myTile = tile;
    }

    public void paint (Graphics2D pen) {
        double thickness = 5;
        pen.setStroke(new BasicStroke((float) thickness));
        pen.setColor(DEFAULT_COLOR);
        pen.drawRect(x, y, width, height);
        myTile.getPixmap().paint(pen, new Point(x + (width / 2), y + (height / 2)),
                                 new Dimension(width, height));
    }

}

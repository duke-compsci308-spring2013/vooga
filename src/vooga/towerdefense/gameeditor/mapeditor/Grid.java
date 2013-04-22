package vooga.towerdefense.gameeditor.mapeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import vooga.towerdefense.model.tiles.Tile;


/**
 * 
 * This class holds a tile element that is drawn on the MapMaker screen. It enables the
 * creation of paths. The tile is snapped to the grid when the game maker clicks on the grid.
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

    /**
     * 
     * @param tile      the tile contained in this grid
     */
    public void setTile (Tile tile) {
        myTile = tile;
    }

    /**
     * This method paints the map tile contained in this grid
     * 
     * @param pen
     */
    public void paint (Graphics2D pen) {
        double thickness = 2;
        pen.setStroke(new BasicStroke((float) thickness));
        pen.setColor(DEFAULT_COLOR);
        pen.drawRect(x, y, width, height);
        myTile.getPixmap().paint(pen, new Point(x + (width / 2), y + (height / 2)),
                                 new Dimension(width, height));
    }

}

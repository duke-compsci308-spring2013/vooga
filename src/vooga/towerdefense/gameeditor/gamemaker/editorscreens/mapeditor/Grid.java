package vooga.towerdefense.gameeditor.gamemaker.editorscreens.mapeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import util.Pixmap;
import vooga.towerdefense.model.tiles.factories.TileFactory;


/**
 * 
 * This class holds a tile factory for the tile element
 * that is drawn on the MapMakerScreen. It enables the
 * creation of paths and other types of tiles.
 * The tile factory image is snapped to the grid
 * when the game maker clicks on the grid after clicking
 * on the type of tile that they would like to add to that
 * particular section.
 * 
 * @author Leonard K. Ng'eno
 */
public class Grid extends Rectangle {

    private static final long serialVersionUID = 1L;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final String TILE_IMAGES_CLASS_PATH = "/vooga/towerdefense/images/map/";
    private TileFactory myTileFactory;
    private Pixmap myPixmap;
    private int myCenterX;
    private int myCenterY;
    private Point myCenter;
    private Dimension mySize;

    /**
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param tile
     */
    public Grid (int x, int y, int width, int height, TileFactory tile) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        myTileFactory = tile;
        myPixmap = new Pixmap(TILE_IMAGES_CLASS_PATH + myTileFactory.getName());
        myCenterX = x + (width / 2);
        myCenterY = y + (height / 2);
        myCenter = new Point(myCenterX, myCenterY);
        mySize = new Dimension(width, height);
    }

    /**
     * set the Tile for this grid
     * 
     * @param tile the tile to be contained in this grid
     */
    public void setTile (TileFactory tile) {
        myTileFactory = tile;
        myPixmap.setImage(TILE_IMAGES_CLASS_PATH + myTileFactory.getName());
    }

    /**
     * This method paints the pixmap associated with the map tile
     * (to be created by the factory) contained in this grid.
     * 
     * @param pen java graphics component
     */
    public void paint (Graphics2D pen) {
        double thickness = 2;
        pen.setStroke(new BasicStroke((float) thickness));
        pen.setColor(DEFAULT_COLOR);
        pen.drawRect(x, y, width, height);
        myPixmap.paint(pen, myCenter, mySize);
    }

    /**
     * Get the origin of the grid
     * 
     * @return top left corner of the grid
     */
    public Point getTopLeftCorner () {
        return new Point(x, y);
    }

    /**
     * Get the tile factory id of this grid
     * 
     * @return tile factory's id
     */
    public String getTileId () {
        return myTileFactory.getTileId();
    }

    /**
     * Get the tile factory of this grid
     * 
     * @return The tile factory instance of this grid
     */
    public TileFactory getTile () {
        return myTileFactory;
    }

}

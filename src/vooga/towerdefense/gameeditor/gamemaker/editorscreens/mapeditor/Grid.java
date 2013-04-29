package vooga.towerdefense.gameeditor.gamemaker.editorscreens.mapeditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.model.tiles.factories.TileFactory;


/**
 *
 * This class holds a tile element that is drawn on the
 * MapMaker screen. It enables the creation of paths.
 * The tile is snapped to the grid when
 * the game maker clicks on the grid.
 *
 * @author Leonard K. Ng'eno
 */
public class Grid extends Rectangle {
    
    private static final long serialVersionUID = 1L;
    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final String TILE_IMAGES_CLASS_PATH = "/vooga/towerdefense/images/map/";
    private TileFactory myTileFactory;
    private Pixmap myPixmap;

    public Grid (int x, int y, int width, int height, TileFactory tile) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        myTileFactory = tile;
        myPixmap = new Pixmap(TILE_IMAGES_CLASS_PATH + myTileFactory.getName());
    }

    /**
     * set the Tile for this grid
     * @param tile the tile to be contained in this grid
     */
    public void setTile (TileFactory tile) {
        myTileFactory = tile;
        myPixmap.setImage(TILE_IMAGES_CLASS_PATH + myTileFactory.getName());
    }

    /**
     * This method paints the map tile (to be created by the factory) contained in this grid
     * 
     * @param pen
     */
    public void paint (Graphics2D pen) {
        double thickness = 3;
        pen.setStroke(new BasicStroke((float) thickness));
        pen.setColor(DEFAULT_COLOR);
        pen.drawRect(x, y, width, height);
        myPixmap.paint(pen, new Point(x+(width/2),y+(height/2)), new Dimension(width, height));
    }

    /**
     * 
     * @return top left corner of the grid
     */
    public Point getTopLeftCorner () {
        return new Point(x, y);
    }

    /**
     * 
     * @return tile factory's id
     */
    public String getTileId () {
        return myTileFactory.getTileId();
    }

    /**
     * 
     * @return  The tile factory instance of this grid
     */
    public TileFactory getTile () {
        return myTileFactory;
    }

}

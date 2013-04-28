package vooga.rts.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import vooga.rts.IGameLoop;
import vooga.rts.gamedesign.sprite.map.Tile;
import vooga.rts.util.Camera;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;


/**
 * This class is responsible for the underlying tile system of the map.
 * The tiles are the images that only provide a visual representation
 * of the lowest level of the game map.
 * These are things such as grass, water, etc.
 * 
 * @author Jonathan Schmidt
 * 
 */
public class TileMap implements IGameLoop {

    private int myWidth;
    private int myHeight;

    private Dimension myTileSize;

    private Map<Integer, BufferedImage> myTileTypes;

    private Tile[][] myTiles;

    /**
     * Creates a new Tile Map with the specified properties.
     * 
     * @param tileSize The size of each tile in the map.
     *        All tiles are the same size.
     * @param width The number of tiles in the X direction.
     * @param height The number of tiles in the Y direction.
     */
    public TileMap (Dimension tileSize, int width, int height) {
        myWidth = width;
        myHeight = height;
        myTileSize = tileSize;
        myTileTypes = new HashMap<Integer, BufferedImage>();
        myTiles = new Tile[myWidth][myHeight];
    }

    /**
     * Adds a new type of tile to the tiles that the map
     * supports.
     * 
     * @param id The ID of the tile.
     * @param image The image of the tile that will be used to
     *        visually represent the tile.
     */
    public void addTileType (int id, BufferedImage image) {
        if (image != null) {
            myTileTypes.put(id, image);
        }
    }

    /**
     * Creates an actual tile for use in the map.
     * Uses the image already loaded from the addTileType method.
     * 
     * @param id The type of tile to create.
     * @param x The X index that this tile represents on the map.
     * @param y The Y index that this tile represents on the map.
     */
    public void createTile (int tiletype, int x, int y) {
        if (x < 0 || y < 0 || x >= myWidth || y >= myHeight) {
            return;
        }
        BufferedImage pic = myTileTypes.get(tiletype);
        Pixmap image = new Pixmap(pic);

        Location3D position =
                new Location3D(x * myTileSize.width / 2 /* + myTileSize.width / 2 */,
                               y * myTileSize.height / 2
                               /* + myTileSize.height / 2 */, 0);

        Tile newTile = new Tile(image, position, myTileSize);
        setTile(x, y, newTile);
    }

    /**
     * Helper method to get the tile at a specific location. <br />
     * This removes the way that tiles are actually stored from
     * the rest of the classes.
     * 
     * @param x The X index of the tile
     * @param y The Y index of the tile
     * @return The Tile at the specified location.
     */
    private Tile getTile (int x, int y) {
        if (x < 0 || y < 0 || x >= myWidth || y >= myHeight) {
            return null;
        }
        return myTiles[x][y];
    }

    /**
     * Helper method to set the tile of a specific location. <br />
     * This removes the way that tiles are actually stored from
     * the rest of the classes.
     * 
     * @param x The X index of the tile
     * @param y The Y index of the tile
     * @param toset The tile to be placed at the location.
     */
    private void setTile (int x, int y, Tile toset) {
        if (x < 0 || y < 0 || x >= myWidth || y >= myHeight) {
            return;
        }
        myTiles[x][y] = toset;
    }

    @Override
    public void update (double elapsedTime) {
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {
                Tile cur = myTiles[x][y];
                if (cur != null) {
                    cur.update(elapsedTime);
                }
            }
        }
    }

    @Override
    public void paint (Graphics2D pen) {
        Rectangle view = Camera.instance().getWorldVision().getBounds();

        // Get the start index of what is visible by the cameras.
        int startX = (int) (view.getMinX() > 0 ? view.getMinX() : 0);
        startX /= myTileSize.getWidth();
        startX /= Camera.ISO_HEIGHT;

        int startY = (int) (view.getMinY() > 0 ? view.getMinY() : 0);
        startY /= myTileSize.getHeight();
        startY /= Camera.ISO_HEIGHT;

        // Get the end index of what is visible
        int endX = (int) (view.getMaxX() / myTileSize.getWidth() + 1);
        endX /= Camera.ISO_HEIGHT;

        int endY = (int) (view.getMaxY() / myTileSize.getHeight() + 1);
        endY /= Camera.ISO_HEIGHT;
        /*
         * startX = 0;
         * startY = 0;
         * endX = myWidth;
         * endY = myHeight;
         */

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile cur = myTiles[x][y];
                if (cur != null) {
                    cur.paint(pen);
                }
            }
        }
    }
}

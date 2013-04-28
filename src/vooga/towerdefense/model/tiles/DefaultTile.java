package vooga.towerdefense.model.tiles;

import java.awt.Dimension;

import util.Location;
import util.Pixmap;
import vooga.towerdefense.model.Tile;

/**
 * A blank tile.
 * 
 * @author Jimmy Longley
 * @author Leonard K. Ng'eno
 */
public class DefaultTile extends Tile {
    private static final String NAME = "blank_tile.png";
    private static final String TILE_IMAGES_CLASS_PATH = "/vooga/towerdefense/images/map/";
    private static final Pixmap PIXMAP = new Pixmap(TILE_IMAGES_CLASS_PATH + NAME); 
    /**
     * 
     * @param id a tile id
     * @param image an image
     * @param center the center of the tile
     * @param size the size of the tile
     */	// 
    public DefaultTile (Location center, Dimension size) {	 
        super(PIXMAP, center, size);	
        setBuildable(true);
        setWalkable(true);
    }
    
    /**
     * String name of the image used in the Pixmap of this tile 
     */
    @Override
    public String getName () {
        return NAME;
    }
}

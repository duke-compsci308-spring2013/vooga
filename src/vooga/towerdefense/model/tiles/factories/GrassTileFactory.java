package vooga.towerdefense.model.tiles.factories;

import vooga.towerdefense.model.tiles.GrassTile;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

/**
 * A tile factory that creates GrassTile objects.
 * 
 * @author Erick Gonzalez
 */
public class GrassTileFactory extends TileFactory {
    /**
     * Image for a grass tile
     */
    public static final Pixmap GRASS_TILE_IMAGE = new Pixmap("map/grass_tile.png");
    
    /**
     * Default constructor.
     */
    public GrassTileFactory() {
        
    }

    @Override
    public Tile createTile (int id, Location center) {
        return new GrassTile(id, GRASS_TILE_IMAGE, center, TileFactory.TILE_DIMENSIONS);
    }
    
}

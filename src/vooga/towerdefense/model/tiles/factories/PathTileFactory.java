package vooga.towerdefense.model.tiles.factories;

import vooga.towerdefense.model.tiles.PathTile;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

/**
 * A tile factory that creates PathTile objects.
 * 
 * @author Erick Gonzalez
 */
public class PathTileFactory extends TileFactory {
    
    /**
     * Image for a path tile
     */
    public static final Pixmap PATH_TILE_IMAGE = new Pixmap("map/path_tile.png");

    @Override
    public Tile createTile (int id, Location center) {
        return new PathTile(id, PATH_TILE_IMAGE, center, TileFactory.TILE_DIMENSIONS);
    }

}

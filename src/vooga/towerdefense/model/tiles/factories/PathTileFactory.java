package vooga.towerdefense.model.tiles.factories;

import util.Location;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Tile;
import vooga.towerdefense.model.tiles.PathTile;

/**
 * A tile factory that creates PathTile objects.
 * 
 * @author Erick Gonzalez
 * @author Leonard K. Ng'eno
 */
public class PathTileFactory extends TileFactory {

    public static final String ID = "2";
    private static final String NAME = "path_tile.png";

    public PathTileFactory() {
        super();
    }

    @Override
    public Tile createTile (Location center, GameMap map) {
        return new PathTile(center, map.getTileSize());
    }
    
    public String getName(){
        return NAME;
    }
    
    @Override
    public String getTileId () {
        return ID;
    }
}

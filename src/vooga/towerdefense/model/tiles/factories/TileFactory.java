package vooga.towerdefense.model.tiles.factories;

import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Tile;
import util.Location;

/**
 * An abstract TileFactory.
 * 
 * @author Erick Gonzalez
 * @author Leonard K. Ng'eno
 */
public abstract class TileFactory {

    public TileFactory() {       
    }
    
    /**
     * 
     * @param id a tile id.
     * @param center the center of this tile on the map
     * @return the corresponding tile
     */
    public abstract Tile createTile(Location center, GameMap map);

    public abstract String getName(); 
    
    public abstract String getTileId();
}

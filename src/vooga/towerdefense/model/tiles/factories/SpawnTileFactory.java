package vooga.towerdefense.model.tiles.factories;

import util.Location;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Tile;

/**
 * @author Erick Gonzalez
 * @author Leonard K. Ng'eno
 *
 */
public class SpawnTileFactory extends PathTileFactory { 
    
    public static final String ID = "s";
    private static final String NAME = "spawn_tile.png";

    public SpawnTileFactory() {
        super();
    }

    @Override
    public Tile createTile (Location center, GameMap map) {
        map.setSpawnLocation(center);
        return super.createTile(center, map);
    } 
    
    public String getName(){
        return NAME;
    }
    
    @Override
    public String getTileId () {
        return ID;
    }
}

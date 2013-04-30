package vooga.towerdefense.model.tiles.factories;

import util.Location;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Tile;

/**
 * @author Erick Gonzalez
 * @author Leonard K. Ng'eno
 *
 */
public class DestinationTileFactory extends PathTileFactory {
    
    public static final String ID = "d";
    private static final String NAME = "end_tile.png";

    public DestinationTileFactory() {
        super();
    }

    @Override
    public Tile createTile (Location center, GameMap map) {        
        map.setDestinationLocation(center);
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

package vooga.towerdefense.model.tiles.factories;

import util.Location;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Tile;
import vooga.towerdefense.model.tiles.GrassTile;

/**
 * A tile factory that creates GrassTile objects.
 * 
 * @author Erick Gonzalez
 * @author Leonard K. Ng'eno
 */
public class GrassTileFactory extends TileFactory {

    public static final String ID = "1";
    private static final String NAME = "grass_tile.png";

    public GrassTileFactory() {
        super();
    }

    @Override
    public Tile createTile (Location center, GameMap map) {
        return new GrassTile(center, map.getTileSize());
    }
    
    public String getName(){
        return NAME;
    }
    
    @Override
    public String getTileId () {
        return ID;
    }
}

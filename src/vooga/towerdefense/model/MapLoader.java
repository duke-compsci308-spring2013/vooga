package vooga.towerdefense.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.model.tiles.factories.GrassTileFactory;
import vooga.towerdefense.model.tiles.factories.PathTileFactory;
import vooga.towerdefense.model.tiles.factories.TileFactory;
import vooga.towerdefense.util.Location;

/**
 * A class responsible for loading a map from a text file.
 * 
 * The text file is formatted as follows:
 * 
 * Ex: 
 * 
 * 0 0 0 0
 * 1 1 0 0
 * 0 1 1 1
 * 0 0 0 0
 * 
 * In this text file, there will be a grid of numbers. Each number
 * corresponds to a tile id. In order to create a new map, all you need
 * to do is create this text file, and make tiles with appropriate ids.
 * For example, if we let the id of grass tiles be 0, and the id of 
 * path tiles be 1. The 1s on the above grid outline the path on the map
 * that will be created.
 * 
 * @author Erick Gonzalez
 */
public class MapLoader {
    /**
     * Relative path to the map file.
     */
    public static final String MAPTILES_FILENAME = "/vooga/towerdefense/resources/map.txt";
    
    private Map<Integer, TileFactory> myTileIdMap;
    
    /**
     * Initializes map from tile ids to tile factories.
     */
    public MapLoader() {
        initTileIdMap();
    }
    
    private void initTileIdMap() {
        myTileIdMap = new HashMap<Integer, TileFactory>();
        myTileIdMap.put(0, new GrassTileFactory());
        myTileIdMap.put(1, new PathTileFactory());
    }
    
    /**
     * 
     * @param mapWidth width of the map
     * @param mapHeight height of the map
     * @return two dimensional array of tiles
     */
    public Tile[][] loadTiles(int mapWidth, int mapHeight) {
        Scanner reader = getScanner();
        
        int horizontalTileCount = (int) (mapWidth / TileFactory.TILE_DIMENSIONS.getWidth());
        int verticalTileCount = (int) (mapHeight / TileFactory.TILE_DIMENSIONS.getHeight());

        Tile[][] grid = new Tile[horizontalTileCount][verticalTileCount];

        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int xCenter = (int) (j * TileFactory.TILE_DIMENSIONS.getWidth() + 
                        TileFactory.TILE_DIMENSIONS.getWidth() / 2);
                int yCenter = (int) (i * TileFactory.TILE_DIMENSIONS.getHeight() + 
                        TileFactory.TILE_DIMENSIONS.getHeight() / 2);
                // TODO: replace booleans with parsed values from file
                int tileId = reader.nextInt();
                grid[j][i] = getTileFactory(tileId).createTile(tileId, 
                                                               new Location(xCenter, yCenter));
            }
        }
        return grid;
    }
    
    
    
    private Scanner getScanner() {
        String path = getClass().getResource(MAPTILES_FILENAME).getFile();
        
        Scanner s = null;
        path = path.replace("%20", " ");
        try {
            s = new Scanner(new File(path));
        }
        catch (FileNotFoundException e) {
            //TODO: Handle Exception
            e.printStackTrace();
        }
        return s;
    }
    
    private TileFactory getTileFactory(int tileId) {
        return myTileIdMap.get(tileId);
    }
}

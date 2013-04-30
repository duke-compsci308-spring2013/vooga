package vooga.rts.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import util.Location;
import vooga.rts.IGameLoop;
import vooga.rts.ai.Path;
import vooga.rts.ai.PathFinder;
import vooga.rts.gamedesign.sprite.gamesprites.GameEntity;
import vooga.rts.gamedesign.sprite.gamesprites.GameSprite;
import vooga.rts.gamedesign.sprite.gamesprites.Resource;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gamedesign.sprite.map.Terrain;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Camera;
import vooga.rts.util.Location3D;
import vooga.rts.util.TimeIt;


/**
 * The GameMap manages all aspects of the map on the screen.
 * This includes the underlying tiles, the terrain objects on
 * the map and the nodes used for pathfinding.
 * 
 * In addition to this, the map will be responsible for painting
 * everything that is in the world. This is to ensure that objects
 * are painted in the correct order.
 * 
 * @author Challen Herzberg-Brovold
 * @author Jonathan Schmidt
 * 
 */

public class GameMap implements IGameLoop {

    private static final int CLICK_RADIUS = 100;
    private NodeMap myNodeMap;
    private TileMap myTiles;
    private GameSpriteManager<Terrain> myTerrain;
    private GameSpriteManager<Resource> myResources;
    private Dimension mySize;

    private String myMapName;
    private String myMapDescription;
    private List<Location3D> myPlayerLocations;

    /**
     * calculates how many nodes there are
     * 
     * @param mapSize This is the size of the map in pixels
     */
    public GameMap (Dimension size) {
        mySize = size;
        NodeFactory factory = new NodeFactory();
        myNodeMap = factory.makeMap(Node.NODE_SIZE, size);
        myTerrain = new GameSpriteManager<Terrain>();
        myResources = new GameSpriteManager<Resource>();
        myPlayerLocations = new ArrayList<Location3D>();
        Camera.instance().setMapSize(size);
    }

    public GameMap (Dimension size, boolean random) {
        this(size);
        Dimension dou = new Dimension((int) size.getWidth(), (int) (size.getHeight() * 2));
        randomGenMap(dou);
    }

    public GameMap (Dimension tileSize, int width, int height) {
        this(new Dimension((int) tileSize.getWidth() * width, (int) tileSize.getHeight() * height));
        myTiles = new TileMap(tileSize, width, height);
    }

    /**
     * @return The Terrain manager
     */
    public GameSpriteManager<Terrain> getTerrain () {
        return myTerrain;
    }

    /**
     * @return The Resource manager
     */
    public GameSpriteManager<Resource> getResources () {
        return myResources;
    }

    public Path getPath (PathFinder finder, Location3D start, Location3D finish) {
        return finder.calculatePath(getNodeMap().getNode(start), getNodeMap().getNode(finish),
                                    myNodeMap);
    }

    public NodeMap getNodeMap () {
        return myNodeMap;
    }

    /**
     * Returns a list of Interactive Entities that are within a specified radius of
     * a central point. This can be used by the units to find targets in the area.
     * 
     * @param loc The Location to search from
     * @param radius The radius of the circle to search in
     * @param type The type of unit to search for. This will compare on the name of the unit.
     * @param teamID The team ID.
     * @param same Whether to search for things of the same team or of the other teams.
     * @return
     */
    public <T extends GameEntity> List<T> getInArea (Location3D loc,
                                                     double radius,
                                                     Class<T> type,
                                                     int teamID,
                                                     boolean same) {
        List<T> inRange = new ArrayList<T>();
        List<Node> nodesinArea = myNodeMap.getNodesinArea(loc, radius);
        for (Node n : nodesinArea) {
            inRange.addAll(n.<T> filterGameSprites(type, teamID, same));
        }
        return GameMap.sortByDistance(inRange, loc);
    }

    @Override
    public void update (double elapsedTime) {
        myTerrain.update(elapsedTime);
        myResources.update(elapsedTime);
    }

    @Override
    public void paint (Graphics2D pen) {
        myTiles.paint(pen);

        myNodeMap.paint(pen);
    }

    /**
     * Returns a Game Entity that is at the specified location.
     * 
     * @param loc The location to search for an interactive entity.
     * @return The Game Entity if it exists, otherwise null.
     */
    public GameEntity getEntity (Location3D loc) {
        List<Node> myNodes = myNodeMap.getNodesinArea(loc, CLICK_RADIUS);
        for (Node n : myNodes) {
            for (GameSprite gs : n.getContents()) {
                if (gs instanceof GameEntity) {
                    if (gs.intersects(loc)) {
                        return (GameEntity) gs;
                    }
                }
            }
        }
        return null;
    }

    private void randomGenMap (Dimension size) {
        int tileWidth = 64;
        int tileHeight = 64;
        int tilesX = (int) size.getWidth() / tileWidth;
        int tilesY = (int) size.getHeight() / tileHeight;

        myTiles = new TileMap(new Dimension(tileWidth, tileHeight), tilesX, tilesY);

        BufferedImage banana =
                ResourceManager.getInstance()
                        .<BufferedImage> getFile("images/tiles/iso-64x64-outside.png",
                                                 BufferedImage.class);

        myTiles.addTileType(1, banana.getSubimage(6 * tileWidth, 0, tileWidth, tileHeight));
        myTiles.addTileType(2, banana.getSubimage(4 * tileWidth, 0, tileWidth, tileHeight));

        for (int i = 0; i < tilesX; i++) {
            for (int j = 0; j < tilesY; j++) {
                if (Math.random() < 0.2) {
                    myTiles.createTile(2, i, j);
                }
                else {
                    myTiles.createTile(1, i, j);
                }
            }
        }
        Camera.instance().setMapSize(size);
    }

    public void setTileMap (TileMap map) {
        myTiles = map;
    }

    public Dimension getSize () {
        return mySize;
    }

    /**
     * @return the name of the map
     */
    public String getMapName () {
        return myMapName;
    }

    /**
     * Set the name of the map
     * 
     * @param mapName the name of the map
     */
    public void setMapName (String mapName) {
        myMapName = mapName;
    }

    /**
     * @return the description of the map
     */
    public String getMapDescription () {
        return myMapDescription;
    }

    /**
     * Set the description of the map.
     * 
     * @param mapDescription the description of the map
     */
    protected void setMapDescription (String mapDescription) {
        myMapDescription = mapDescription;
    }

    /**
     * @return a list of locations that players can start at
     */
    public List<Location3D> getPlayerLocations () {
        return myPlayerLocations;
    }

    /**
     * Adds a location that a player can start at on the map.
     */
    protected void addPlayerLocation (Location3D loc) {
        myPlayerLocations.add(loc);
    }

    private static <T extends GameEntity> List<T> sortByDistance (List<T> items,
                                                                  Location3D fromPosition) {
        Map<Double, T> myDistances = new TreeMap<Double, T>();
        List<T> mySorted = new ArrayList<T>();
        for (T t : items) {
            double distance = t.getWorldLocation().getDistance(fromPosition);
            myDistances.put(distance, t);
        }
        mySorted.addAll(myDistances.values());
        return mySorted;

    }
}

package vooga.towerdefense.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vooga.rts.util.Vector;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FollowPath;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.tiles.Tile;
import vooga.towerdefense.model.tiles.factories.TileFactory;
import vooga.towerdefense.util.Location;


/**
 * 
 * 
 * @author Erick Gonzalez
 * @author Jimmy Longley
 */
public class GameMap {

    private List<GameElement> myGameElements;
    private Tile[][] myGrid;
    private Location myDestination;
    private Dimension myDimension;
    private Path myPath;
    private Pathfinder myPathfinder;
    public Location default_end_location;

    /**
     * 
     * @param background
     *        a background image
     * @param width
     *        the width of the map, in pixels
     * @param height
     *        the height of the map, in pixels
     * @param destination
     *        the destination point of all units
     */
    public GameMap (Image background, int width, int height, Location destination) {
        myGameElements = new ArrayList<GameElement>();
        myDestination = destination;
        myDimension = new Dimension(width, height);
        MapLoader loader = new MapLoader();
        myGrid = loader.loadTiles(width, height);
        myPathfinder = new Pathfinder(myGrid);
        default_end_location = new Location(width, height / 2);
        updatePaths();

        // ExampleUnitFactory myTrollFactory = new ExampleUnitFactory("Troll",
        // new TrollUnitDefinition());
        // GameElement troll1 = myTrollFactory.createUnit(new Location(500,
        // 500), new TrollUnitDefinition());
        // GameElement troll2 = myTrollFactory.createUnit(new Location(350,
        // 250), new TrollUnitDefinition());
        // addGameElement(troll1);
        // addGameElement(troll2);
    }

    /**
     * 
     * @param elapsedTime
     *        time elapsed since last game clock tick.
     */
    public void update (double elapsedTime) {
        for (int i = 0; i < myGameElements.size(); ++i) {
            myGameElements.get(i).update(elapsedTime);
        }
    }

    /**
     * Adds a game element to the given tile t.
     * 
     * @param e a game element
     * @param t the tile in which to add the game element
     */
    public void addToMap (GameElement e, Tile t) {
        e.setCenter(t.getCenter().getX(), t.getCenter().getY());
        myGameElements.add(e);
    }

    /**
     * Given a point on the map, returns the Tile enclosing that point.
     * 
     * @param point
     *        a point (x, y) on the game map, where x and y are measured in
     *        pixels.
     * @return a Tile object containing this point (x, y)
     */
    public Tile getTile (Point point) {
        return myGrid[(int) (point.getX() / TileFactory.TILE_DIMENSIONS.getWidth())]
                [(int) (point.getY() / TileFactory.TILE_DIMENSIONS.getHeight())];
    }

    /**
     * Given a location on the map, returns the Tile enclosing that point.
     * 
     * @param location
     *        a location (x, y) on the game map, where x and y are measured
     *        in pixels.
     * @return a Tile object containing this point (x, y)
     */
    public Tile getTile (Location location) {
        return myGrid[(int) (location.getX() / TileFactory.TILE_DIMENSIONS.getWidth())]
                [(int) (location.getY() / TileFactory.TILE_DIMENSIONS.getHeight())];
    }

    /**
     * 
     * @param pen
     *        a pen used to draw elements on this map.
     */
    public void paint (Graphics2D pen) {
        paintTiles(pen);
        paintGameElements(pen);
    }

    private void paintTiles (Graphics2D pen) {
        for (int i = 0; i < myGrid.length; ++i) {
            for (int j = 0; j < myGrid[i].length; ++j) {
                myGrid[i][j].paint(pen);
            }
        }
    }

    private void paintGameElements (Graphics2D pen) {
        for (int i = 0; i < myGameElements.size(); ++i) {
            myGameElements.get(i).paint(pen);
        }
    }

    /**
     * 
     * @return a list of all available game elements.
     */
    public List<GameElement> getAllGameElements () {
        return myGameElements;
    }

    /**
     * 
     * @param gameElement simply adds a game element to the map.
     */
    public void addGameElement (GameElement gameElement) {
        myGameElements.add(gameElement);
    }
    
    /**
     * 
     * @param gameElement game element to be removed
     */
    public void removeGameElement (GameElement gameElement) {
        myGameElements.remove(gameElement);
    }
    
    /**
     * Gets howMany number of the closest targets within a radius of a circle centered at
     * source.
     * 
     * @param source the center of the circle 
     * @param radius the radius of the circle
     * @param howMany the number of units closest to source, within radius
     * @return howMany number of elements within radius sorted by distance from source
     */
    public List<GameElement> getTargetsWithinRadius (Location source,
                                                     double radius, int howMany) {
        List<GameElement> elementsWithinRadius = getTargetsWithinRadius(source, radius);
        int lastIndex = howMany > elementsWithinRadius.size()
                ? elementsWithinRadius.size() : howMany;
        return elementsWithinRadius.subList(0, lastIndex);
    }
    
    /**
     * Gets the targets within a radius of a circle centered at source.
     * 
     * @param source the center of the circle 
     * @param radius the radius of the circle 
     * @return all elements within radius sorted by distance from source
     */
    public List<GameElement> getTargetsWithinRadius(Location source, double radius) {
        List<GameElement> elementsWithinRadius = getElementsWithinRadius(source, radius);
        sortGameElementsByDistanceToSource(elementsWithinRadius, source);
        return elementsWithinRadius;
    }
    
    private void sortGameElementsByDistanceToSource(List<GameElement> elementsWithinRadius, 
                                                    Location source) {
        
        class GameElementComparator implements Comparator<GameElement> {
            private Location mySource;

            public GameElementComparator (Location source) {
                mySource = source;
            }

            @Override
            public int compare (GameElement o1, GameElement o2) {
                return Vector.distanceBetween(mySource, o1.getCenter())
                       - Vector.distanceBetween(mySource, o2.getCenter()) > 0 ? 1
                                                                             : -1;
            }
        }

        Collections.sort(elementsWithinRadius,
                         new GameElementComparator(source));
    }

    private List<GameElement> getElementsWithinRadius (Location source, double radius) {
        List<GameElement> elementsWithinRadius = new ArrayList<GameElement>();

        for (GameElement gameElement : myGameElements) {
            if (Vector.distanceBetween(source, gameElement.getCenter()) <= radius) {
                elementsWithinRadius.add(gameElement);
            }
        }
        return elementsWithinRadius;
    }
    

    /**
     * Returns a Path object representing the shortest path between
     * two locations.
     * 
     * @param start the start location
     * @param finish the end location
     * @return the shortest path between these two locations
     */
    public Path getShortestPath (Location start, Location finish) {
        int x1 = (int) (start.getX() / TileFactory.TILE_DIMENSIONS.getWidth());
        int x2 = (int) (finish.getX() / TileFactory.TILE_DIMENSIONS.getWidth());
        int y1 = (int) (start.getY() / TileFactory.TILE_DIMENSIONS.getHeight());
        int y2 = (int) (finish.getY() / TileFactory.TILE_DIMENSIONS.getHeight());
        Path thePath = myPathfinder.getShortestPath(x1, y1, x2, y2);
        // thePath.add(finish);
        return thePath;
    }

    /**
     * When the grid is modified, every element that follows a path must
     * recalculate it's path.
     */
    public void updatePaths () {
        for (GameElement e : myGameElements) {
            for (Action action : e.getActions())
                if (action.getClass().equals(FollowPath.class)) {
                    Path p = getShortestPath(e.getCenter(),
                                             default_end_location);
                    ((FollowPath) action).setPath(p);
                }
        }

    }
}

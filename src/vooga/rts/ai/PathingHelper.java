package vooga.rts.ai;

import java.util.Queue;
import vooga.rts.map.*;
import util.Location;

public class PathingHelper {
    
    private Pathfinder myFinder;
    private GameMap myMap;
    private Queue<MapNode> myPath;
    private MapNode myNext;
    
    public PathingHelper (GameMap map) {
        myFinder = new AstarFinder();
        myMap = map;
    }
    
    public void constructPath (Location start, Location desired) {
        MapNode startNode = myMap.getNode(start);
        MapNode desiredNode = myMap.getNode(desired);
        constructPath(startNode, desiredNode);
    }
    
    public void constructPath (MapNode startNode, MapNode endNode) {
        myPath = myFinder.findPath(startNode, endNode, myMap);
        myNext = myPath.poll();
    }
    
    public Location getNext(Location current) {
        MapNode currentNode = myMap.getNode(current);
        return getNext(currentNode);
    }
    
    public Location getNext(MapNode currentNode) {
        if (!currentNode.equals(myNext)) {
            myNext = myPath.poll();
        }
        return myNext.getCenter();
    }

    public int size () {
        return myPath.size();
    }
}

package vooga.rts.ai;

import vooga.rts.map.NodeMap;
import vooga.rts.map.Node;

/**
 * Interface which is used to find paths. Different implementations will use
 * different algorithms for determining the pathing of units. If the developer 
 * wants different units to have different pathing algorithms, they can create
 * as many as they want. Each unit will have its own pathfinder.
 * 
 * @author Challen Herzberg-Brovold
 * 
 */
public interface PathFinder {
    
    public abstract Path calculatePath(Node start, Node finish, 
                                            NodeMap map);
}

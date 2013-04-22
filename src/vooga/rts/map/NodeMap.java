package vooga.rts.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


/**
 * This class stores all the nodes that will be used for pathfinding.
 * 
 * @author Challen Herzberg-Brovold
 *
 */
public class NodeMap {

    private int myWidth;
    private int myHeight;
    private Node[][] myMap;

    public NodeMap (int width, int height) {
        myMap = new Node[width][height];
        myWidth = width;
        myHeight = height;
    }

    /**
     * Returns all the neighboring nodes of a specified node.
     * @param current the node for which we want the neighbors 
     * @return a list of neighbors of the node
     */
    public List<Node> getNeighbors (Node current) {
        List<Node> neighbors = new ArrayList<Node>();
        int x = current.getX();
        int y = current.getY();
        for (int i = -1; i < 2; i += 2) {
            neighbors.add(get(x + i, y));
            neighbors.add(get(x, y + i));
        }
        return neighbors;
    }

    /**
     * Returns the node at the specified coordinates
     * @param x-coordinate
     * @param y-coordinate
     * @return node at the coordinates
     */
    public Node get (int x, int y) {
        return myMap[x][y];
    }

    /**
     * 
     * @return width of the map in nodes
     */
    public int getWidth () {
        return myWidth;
    }

    /**
     * 
     * @return height of the map in nodes.
     */
    public int getHeight () {
        return myHeight;
    }

    public void put (Node node, int x, int y) {
        myMap[x][y] = node;
    }
    
    public void paint(Graphics2D pen) {
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {                
                pen.drawRect(x * 10, y * 10, 10, 10);                
            }
        }        
    }
}

package vooga.rts.ai;

/**
 * When an implementation of this interface is in a node, it marks it as being 
 * unable to be passed by ground units for the sake of pathfinding.
 * 
 * @author Challen Herzberg-Brovold
 *
 */
public interface IObstruction { //Possibly some observer, observable at some point, but for now this is unclear
    
    /**
     * 
     * @return the height of the object (if we want obstructions to block 
     * off multiple levels, eg. block ground units and flying units)
     * 
     */
    public int getHeight();
}

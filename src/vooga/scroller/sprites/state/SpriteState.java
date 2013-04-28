
package vooga.scroller.sprites.state;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * This is our implementation of the State design pattern. This 
 * pattern is made to elegantly handle the multitude of states a 
 * sprite may be in. 
 * 
 * If you are feeling ambitious, please read up on this design pattern - 
 * http://www.cs.duke.edu/courses/compsci308/current/readings/a2-gestwicki.pdf.
 * 
 * For example: Mario may be in a number of states - 
 *      1. Invisible 
 *      2. Invincible  
 *      3. Dead 
 *      4. Huge 
 *   
 * Most sprites will not implement such a rich variety of states. 
 *   
 * @author Jay Wang
 *
 */


public abstract class SpriteState<T> implements Comparable<SpriteState<T>>{
    
    private T myUnit;
    
    public SpriteState (T unit){
        myUnit = unit;
    }
    

    /**
     * Updates the current state.
     * @param elapsedTime since the last update.
     * @param bounds of the current view.
     */
    public abstract void update (double elapsedTime, Dimension bounds);

    /**
     * Paints the current state.
     * 
     * @param pen is the graphics2d used to paint.
     */
    public abstract void paint(Graphics2D pen, double angle);
    
    /**
     * Gives the painting priority of this state.
     * @return the int that represents the priority of painting this state. 0 = highest priority. The higher
     * the integer, the lower the priority.
     */
    public abstract int getPaintPriority();
        
    public abstract void activate();
    
    public abstract void deactivate();
    

    protected T getUnit(){
        return myUnit;
    }
    
    
    @Override
    public int compareTo (@SuppressWarnings("rawtypes") SpriteState other) {
        return this.getPaintPriority() - other.getPaintPriority();
    }
      
}

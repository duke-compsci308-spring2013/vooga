package vooga.towerdefense.gameElements;

import java.awt.Graphics2D;


/**
 * designer should create customized object to implement this interface for creating multiple states
 * for a unit e.g. normal state/dead state/slowed state, the state is responsible for painting
 * image for the unit, set parameters as needed.
 * 
 * @author gouzhen-1
 * 
 */
public interface State {
    /**
     * paint the image of this state
     */
    public void paint (Graphics2D pen, GameElement object);

    /**
     * set the parameters as needed (to units/towers)
     */
    public void setSate ();

}

package vooga.fighter.controller.interfaces;

import util.input.Input;

/**
 * 
 * @author Matt Parides
 *
 */
public interface ControllerDelegate {

    /**
     * Notifies delegate
     * @param string
     */
    public void notifyEndCondition(String string);

    /**
     * exits program
     */
    public void exit();

    /**
     * returns input
     * @return
     */
    public Input getInput();

}

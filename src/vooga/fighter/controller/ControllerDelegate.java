package vooga.fighter.controller;

import util.input.Input;

/**
 * 
 * @author matthewparides
 *
 */
public interface ControllerDelegate {

    public void notifyEndCondition(String string);
    
    public void exit();
    
    public Input getInput();

}

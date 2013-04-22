package vooga.rts.action;

import vooga.rts.commands.Command;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.units.Unit;


/**
 * The action interface for the command design pattern, which allows us to 
 * link inputs (keyboard, mouse, etc) to the appropriate actions they invoke 
 * on the selected units or managers
 * 
 * @author Challen Herzberg-Brovold (and whoever else worked with Actions)
 *
 */
public interface Action {
    /**
     * Applies or executes the Action. This runs the code that was
     * defined for the apply method in any concrete classes.
     */
    public void apply ();
    
    /**
     * Updates the action with the command that created the action.
     * This lets the apply method take in parameters or other things
     * that are related to the command.
     * 
     * @param command The command that triggered this action.
     */
    public void update (Command command);
}

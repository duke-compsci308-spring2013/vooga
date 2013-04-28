package vooga.rts.action;

import vooga.rts.commands.Command;

/**
 * The IActOn interface is for anything that can have an action alter its state.
 * This interface is mostly for gamesprites that have behavior in the game.
 * 
 * @author Challen Herzberg-Brovold (and whoever else worked on this)
 *
 */

public interface IActOn {
    /**
     * Adds an action to the IActOn's action map, which links inputs to actions
     * 
     * @param input which will invoke the action when the unit is selected.
     * @param action which will be called when the input is given.
     */
    public void addAction (String input, Action action);

    /**
     * Checks the action map to see if the unit can handle this commands, then
     * updates the action with the appropriate information from the command.
     * 
     * @param command input with information that can be used to update the action
     */
    public void updateAction (Command command);
}

package vooga.rts.controller;

import vooga.rts.commands.Command;


/**
 * The controller will be responsible for reading the input, and applying it to 
 * its manager, which then changes its state accordingly. 
 * 
 * @author Challen Herzberg-Brovold
 *
 */
public interface Controller {
    
    /**
     * Sends the command to the appropriate receiver.
     * 
     * @param command the command to be sent
     */
    public void sendCommand(Command command); // Should this not take a parameter?
    
}

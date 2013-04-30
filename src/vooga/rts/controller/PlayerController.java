package vooga.rts.controller;

import java.util.Map;
import vooga.rts.commands.Command;

/**
 * The Player needs to do make the following decisions:
 * - does the command affect units or managers (eg. select units)?
 *      - if so, does it need to be sent to server/be bounced back. If it affects
 *      a manager, it can be executed immediately, but if it affects units, it
 *      needs to be sent to the server. 
 * - does the command affect the view or other non-gameplay related things?
 * 
 * This versin of Player will eventually be called HumanPlayer, and Player will
 * also be used with AI and the network to control other sets of managers.
 *      
 * @author Challen Herzberg-Brovold
 *
 */
public class PlayerController implements Controller, Controllable {
    
    private Controllable myManager;

    
    public PlayerController (Controllable manager) {
        myManager = manager;
    }
    
    @Override
    public void sendCommand (Command command) {
        
    }
 
    /**
     * 
     * @param manager
     * @param command
     */
    public void receiveCommand (Command command) { 
        // Do something with a command
    }
}

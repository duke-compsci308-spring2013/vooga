package vooga.rts.networking.communications.gamemessage;

import vooga.rts.action.InteractiveAction;
import vooga.rts.commands.Command;

public class RTSMessage extends GameMessage {

    private Command myCommand;
    private int myUnitId;
    
    public RTSMessage (Command command, int playerId, int unitId) {
        super(playerId);
        myCommand = command;
        myUnitId = unitId;
    }
   
    public Command getCommand () {
        return myCommand;
    }
    
    public int getUnitId() {
        return myUnitId;
    }
}

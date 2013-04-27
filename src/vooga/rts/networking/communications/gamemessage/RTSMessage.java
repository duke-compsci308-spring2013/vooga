package vooga.rts.networking.communications.gamemessage;

import vooga.rts.action.InteractiveAction;

public class RTSMessage extends GameMessage {

    private InteractiveAction myAction;
    
    public RTSMessage (InteractiveAction action, int id) {
        super(id);
        myAction = action;
    }
    
    
    public InteractiveAction getAction () {
        return myAction;
    }
}

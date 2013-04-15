 package vooga.rts.state;

import java.util.Observable;
import java.util.Observer;
import vooga.rts.controller.Command;
import vooga.rts.IGameLoop;

public abstract class SubState extends Observable implements IGameLoop {
    
    public SubState (Observer observer) {
        addObserver(observer);
    }
    
    public abstract void receiveCommand(Command command);
}

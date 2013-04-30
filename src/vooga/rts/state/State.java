package vooga.rts.state;

import vooga.rts.IGameLoop;
import vooga.rts.controller.Controllable;

/**
 * The state interface, which extends to interfaces. Still doesn't have much 
 * behavior, and is convenient. States need to have the behavior of both 
 * interfaces.
 * @author Challen Herzberg-Brovold
 * 
 */
public interface State extends Controllable, IGameLoop {
    
}

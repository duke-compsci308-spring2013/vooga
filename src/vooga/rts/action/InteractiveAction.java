package vooga.rts.action;

import java.io.Serializable;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;

/**
 * Interactive actions are an implementation of actions designed to be used 
 * with interactive entities (i.e. entities that can be controlled by the player).
 * The important aspect is that it contains the IE which it will act upon when 
 * called from that entities action list.
 * 
 * @author Challen Herzberg-Brovold
 */
public abstract class InteractiveAction implements Action, Serializable {

    private static final long serialVersionUID = 4194527430419830812L;
    private InteractiveEntity myEntity;

    public InteractiveAction (InteractiveEntity ie) {
        myEntity = ie;
    }
    
    /**
     * 
     * @return returns the entity to be acted on by the actions apply() method
     */
    public InteractiveEntity getEntity () {
        return myEntity;
    }
    
    public void setEntity (InteractiveEntity ie) { // this will be called by unit manager right before it applies it. 
        myEntity = ie;
    }
}

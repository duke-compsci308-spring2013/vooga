package vooga.fighter.model.utils;

import java.util.ArrayList;
import java.util.List;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.GameObject;

/**
 * Represents an effect placed on a character (or several).
 * 
 * @author james, alanni
 *
 */
public abstract class Effect {
    
    private Counter myCounter;
    private CharacterObject myOwner;
    
    /**
     * Constructs a new Effect with no owner.
     */
    public Effect() {
        this(null);
    }    
    
    /**
     * Constructs a new Effect with the given owner.
     */
    public Effect(CharacterObject owner) {
        myCounter = new Counter();
        myOwner = owner;
    }
    
    /**
     * Designates the owner of this effect, aka the target.
     */
    public void setOwner(CharacterObject owner) {
        myOwner = owner;
    }
    
    /**
     * Returns the owner of this effect, aka the target.
     */
    public GameObject getOwner() {
        return myOwner;
    }
    
    /**
     * Returns the current count on the effect, or -1 if the counter is not yet active.
     */
    public int getCount() {
        return myCounter.getCount();
    }
    
    /**
     * Creates a clone of this effect. The clone should be a deep copy.
     */
    public abstract Effect getCloneOfEffect();    
    
    /**
     * Applies the effect to its targets for one game loop cycle. This method should
     * be overridden by subclasses, and should call the superclass method after
     * handling its own updates.   
     */
    public void update() {
        myCounter.decrementCounter();
        if (!myCounter.hasCountRemaining()) {
            myOwner.removeActiveEffect(this);
        }
    }

}

package vooga.fighter.model.utils;

import java.util.HashMap;
import java.util.Map;
import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.CharacterObject;

/**
 * Represents an effect placed on a character (or several).
 * 
 * @author James Wei, alanni
 *
 */
public abstract class Effect {
    
    private Counter myCounter;
    private CharacterObject myOwner;
    private Map<String, Integer> myProperties;
    
    /**
     * Constructs a new Effect with no owner and zero duration.
     */
    public Effect() {
        this(null, 0);
    }
    
    /**
     * Constructs a new Effect with no owner and given duration.
     * @param duration is the length of the effect
     */
    public Effect(int duration) {
        this(null, duration);
    }    
    
    /**
     * Constructs a new Effect with the given owner.
     * @param owner is the object to which the effect is applied
     * @param duration is the length of the effect
     */
    public Effect(CharacterObject owner, int duration) {
        myCounter = new Counter(duration);
        myOwner = owner;
        myProperties = new HashMap<String, Integer>();
        addProperty(ModelConstants.EFFECT_PROPERTY_DURATION, duration);
    }
    
    /**
     * Initializes effects
     */
    public void initialize() {
        setDuration();
    }
    
    /**
     * Sets the duration 
     */
    public void setDuration() {
        myCounter = new Counter(getProperty("duration"));
    }
    

    /**
     * Adds a property for this object. Overwrites any existing value.
     * @param key is the name of the property
     * @param value is the int value
     */
    public void addProperty(String key, int value) {
        myProperties.put(key, value);
    }

    /**
     * Returns a property for this object. Returns -1 if property does not exist.
     * @param key is the name of the property
     */
    public int getProperty(String key) {
        if (myProperties.containsKey(key)) {
            return myProperties.get(key);
        }
        else {
            return -1;
        }
    }
    
    /**
     * Clears all properties.
     */
    public void clearProperties() {
        myProperties.clear();
    }
    
    /**
     * Designates the owner of this effect, aka the target.
     * @param owner is the owner of the effect
     */
    public void setOwner(CharacterObject owner) {
        myOwner = owner;
    }
    
    /**
     * Returns the owner of this effect, aka the target.
     */
    public CharacterObject getOwner() {
        return myOwner;
    }
    
    /**
     * Returns the current count on the effect, or -1 if the counter is not yet active.
     */
    public int getCount() {
        return myCounter.getCount();
    }
    
    /**
     * Updates the counter of the effect and calls applyEffect().
     */
    public void update() {
        applyEffect();
        myCounter.decrementCounter();
    }
    
    /**
     * Returns true if the effect has ended.
     */
    public boolean hasEnded() {
        return !myCounter.hasCountRemaining();
    }
    
    /**
     * Applies the effect to its target. Called every update cycle.
     */
    public abstract void applyEffect();

    
    /**
     * Creates a clone of this effect. The clone should be a deep copy.
     */
    public abstract Effect getCloneOfEffect();    
    
}

package games.fighter.MattJimmyExample.effects;

import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.utils.Effect;

/**
 * Example of an implementation of effect. Burn effects do damage over time to
 * their target after they are applied.
 * 
 * @author James Wei
 * 
 */
public class FreezeEffect extends Effect {

    private int myOriginalMovespeed;
    
    /**
     * Calls the superclass effect constructor.
     */
    public FreezeEffect() {
        super();
    }
    
    /**
     * Constructs a freeze effect with given duration and damage over time, and null owner.
     * @param duration is length of burn
     * @param damage is damage over time
     */
    public FreezeEffect (int duration, int damage) {
        super(duration);
        addProperty(ModelConstants.EFFECT_PROPERTY_BURN_DAMAGE, damage);
    }

    /**
     * Constructs a freeze effect with given owner, duration, and damage over time.
     * @param owner is target of freeze
     * @param duration is length of freeze
     * @param damage is damage over time
     */
    public FreezeEffect (CharacterObject owner, int duration, int damage) {
        super(owner, duration);
        addProperty(ModelConstants.EFFECT_PROPERTY_BURN_DAMAGE, damage);
    }

    /**
     * Applies a freeze effect of some damage over time.
     */
    @Override
    public void applyEffect () {
        int damage = getProperty(ModelConstants.EFFECT_PROPERTY_BURN_DAMAGE);
        getOwner().changeHealth(-damage);
        myOriginalMovespeed = getOwner().getProperty(ModelConstants.MOVESPEED_PROPERTY);
        int movespeed = (int) (myOriginalMovespeed * 0.5);
        getOwner().addProperty(ModelConstants.MOVESPEED_PROPERTY, movespeed);
    }

    /**
     * Returns a deep copy of this freeze effect.
     */
    @Override
    public Effect getCloneOfEffect () {
        int duration = getProperty(ModelConstants.EFFECT_PROPERTY_DURATION);
        int damage = getProperty(ModelConstants.EFFECT_PROPERTY_BURN_DAMAGE);
        FreezeEffect result = new FreezeEffect(getOwner(), duration, damage);
        return result;
    }
    
    /**
     * Overrides update to remove buff when effect ends.
     */
    @Override
    public void update() {
        super.update();
        if (hasEnded()) {
            getOwner().addProperty(ModelConstants.MOVESPEED_PROPERTY, myOriginalMovespeed);
        }
    }

}


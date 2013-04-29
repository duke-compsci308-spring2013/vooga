package vooga.fighter.model.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Location;
import util.Vector;
import vooga.fighter.model.ModelConstants;
import vooga.fighter.model.loaders.CharacterLoader;
import vooga.fighter.model.utils.Effect;
import vooga.fighter.model.utils.Health;
import vooga.fighter.model.utils.UpdatableLocation;



/**
 * Represents a character in the game, as well as the character's current state
 * in the given session.
 * 
 * @author James Wei, edited by alanni, edited by David Le
 * 
 */
public class CharacterObject extends GameObject {
    
    private static final int DEGREES_180 = 180;
    private Map<String, AttackObject> myAttacks;
    private Vector myForcesApplied;
    private List<Effect> myActiveEffects;
    private Health myHealth; 

    private List<AttackObject> myCurrentAttacks; 
    private int myMovingDirection; 

    private Vector myVelocity;  
    
    /**
     * Constructs a new CharacterObject.
     * @param charName is the String name of the character (for use by loader)
     * @param center is the location to create the character
     * @param pathHierarchy is the path to the game's resources folder
     */
    public CharacterObject(String charName, UpdatableLocation center, String pathHierarchy) {
        super();
        myAttacks = new HashMap<String, AttackObject>();
        myActiveEffects = new ArrayList<Effect>();
        myHealth = new Health();
        myMovingDirection = ModelConstants.RIGHT; 
        myCurrentAttacks = new ArrayList<AttackObject>();
        setLoader(new CharacterLoader(charName, this, pathHierarchy));
        setHealth(getProperty(ModelConstants.MAXHEALTH_PROPERTY));
        setToDefaultState();
        getCurrentState().setLooping(true);
        setLocation(center);
        myVelocity = getLocation().getVelocity();
        setImageData();
        
    }

    /**
     * Updates the character for one game loop cycle. Applies effects currently
     * active on the character.
     */
    @Override
	public void completeUpdate() {
        for (int i=0; i<myActiveEffects.size(); i++) {
            Effect effect = myActiveEffects.get(i);
            effect.update();
            if (effect.hasEnded()) {                
                removeActiveEffect(effect);
            }
        }
    }
    
    /**
     * Calls GameObject's updateState() method as well as sets the default state to stand
     * if no other state actions are going on.
     */
    @Override
	public void updateState() {
        super.updateState();
        if (getCurrentState().hasCompleted()) {
            setCurrentState(ModelConstants.STAND);            
        }
    }

    /**
     * Adds an effect to this character's list of active effects.
     * @param effect is the effect to add
     */
    public void addActiveEffect(Effect effect) {
        effect.setOwner(this);
        myActiveEffects.add(effect);
    }

    /**
     * Removes an effect from this character's list of active effects.
     * @param effect is the effect to remove
     */
    public void removeActiveEffect(Effect effect) {
        myActiveEffects.remove(effect);
    }

    /**
     * Returns list of currently active effects on this character.
     */
    public List<Effect> getActiveEffects() {
        return myActiveEffects;
    }

    
    /**
     * Returns the mass for character
     */
    public int getMass() {
        return getProperty(ModelConstants.MASS_PROPERTY);
    }
    
    /**
     * Returns the speed of the character 
     */
    public Vector getVelocity() {
        return myVelocity;
    }
    
 
    /**
     * Adds an AttackObject to the list of attacks available for this character.
     * Note that this attack will not be added to the list of game objects in a
     * level, thus it will not update and should be used solely for generating
     * other attack objects as needed. Overwrites any existing attack.
     * @param key is the String key used to identify the attack
     * @param object is the AttackObject to add
     */
    public void addAttack(String key, AttackObject object) {
        myAttacks.put(key, object);
    }

    
    /**
     * Creates and returns an attack object based on a given identifier. Returns null
     * if the specified attack does not exist.
     * 
     * @param key is the String key used to identify the attack
     */
    public AttackObject createAttack(String key) {
        if (myAttacks.containsKey(key)) {
            Location charLocation = getLocation().getLocation();
            UpdatableLocation newLocation = 
                    new UpdatableLocation(charLocation.getX(), charLocation.getY());
            return new AttackObject(myAttacks.get(key), newLocation);
        }
        else {
            return null;
        }
    }

    /**
     * Returns the health of the character.
     */
    public Health getHealth() {
        return myHealth;
    }

    /**
     * Returns whether or not the character has remaining health.
     */
    public boolean hasHealthRemaining() {
        return myHealth.hasHealthRemaining();
    }

    /**
     * Sets the health of the character
     * @param amount is the amount of health to set to
     */
    public void setHealth(int amount) {
        myHealth.setHealth(amount);
    }

    /**
     * Changes the player's health by a given amount. Positive input raises it, and
     * negative input decreases it. Returns health remaining.
     * @param amount is the amount to change health by
     */
    public int changeHealth(int amount) {
        return myHealth.changeHealth(amount);
    }

    /**
     * Creates and returns a new AttackObject by cloning the object identified
     * by the given key in the attacks map.
     * @param key is the String key identifying the attack to create
     */
    public AttackObject attack(String key) {
        setCurrentState(key);
        AttackObject newAttack = createAttack(key);
        newAttack.setOwner(this);
        return newAttack;
    }

    /**
     * Moves in given direction at speed of character
     * @param direction is the angle in degrees to move in
     */
    public void move(int direction) {
        myMovingDirection = direction; 
        getLocation().translate(new Vector(direction, getProperty(ModelConstants.MOVESPEED_PROPERTY)));

    }

    /**
     * Makes the character move back if it runs into another character or 
     * environment object.
     */
    public void moveBack() {
        myVelocity.setMagnitude(myForcesApplied.getMagnitude()); 
        reverseVelocity(); 
        getLocation().translate(myVelocity);
    }
    
    /**
     * Gets the direction the character is moving
     */
    public int getMovingDirection() {
        return myMovingDirection;
    }
    
    /**
     * Will add jump method
     */
    public void jump() {        
        getLocation().addAcceleration(new Vector(ModelConstants.UP, getProperty(ModelConstants.JUMPFACTOR_PROPERTY)));
    } 
    

    /**
     * Returns list of all attackObjects
     */
    public List<AttackObject> getAttackObjects() {
        return myCurrentAttacks; 
    }
    
    /**
     * Reverse the direction of the given vector
     */
    public void reverseVelocity() {
        myVelocity.setDirection(myVelocity.getDirection() - DEGREES_180);
    }
    
    /**
     * Sets the applied forces acting on a character object
     * @param sumOfForces is the total set of forces being applied to this object
     */
    public void setAppliedForces(Vector sumOfForces) {
        myForcesApplied = sumOfForces;
    }
    
}

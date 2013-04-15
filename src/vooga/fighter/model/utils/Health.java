package vooga.fighter.model.utils;

/**
 * Represents a health value for an object.
 * 
 * @author james
 * 
 */
public class Health {

    /**
     * Representation of the current level of health.
     */
    private int myHealth;

    /**
     * Returns the current value of the object's health.
     */
    public int getHealth () {
        return myHealth;
    }

    /**
     * Sets the health to an absolute number. Must not be negative.
     */
    public void setHealth (int amount) {
        if (amount < 0) { return; }
        myHealth = amount;
    }

    /**
     * Modifies the object's health by a certain amount. Positive values
     * increase health, and negative values decrease health. Will not modify
     * health to be a negative value; instead health will decrease to zero
     * and remain at zero. Returns the amount of health remaining after the change.
     */
    public int changeHealth (int amount) {
        myHealth += amount;
        if (myHealth < 0) {
            myHealth = 0;
        }
        return myHealth;
    }

    /**
     * Returns true if the object has any remaining health.
     */
    public boolean hasHealthRemaining () {
        return (myHealth > 0);
    }

}

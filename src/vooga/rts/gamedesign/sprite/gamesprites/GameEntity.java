package vooga.rts.gamedesign.sprite.gamesprites;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.rts.gamedesign.state.EntityState;
import vooga.rts.gamedesign.state.MovementState;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Vector;


/**
 * This class encompasses all classes that can affect the game directly through
 * specific behavior. This class has the health behavior (can �die�) and
 * velocity (can move and collide), and belong to a certain team. Setting the
 * health to zero sets the isVisible boolean to false, not allowing the
 * GameEntity to be painted.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu
 * 
 */
public class GameEntity extends GameSprite {

    // Default velocity magnitude
    public static int DEFAULT_SPEED = 100;
    private Vector myVelocity;
    private int myMaxHealth;
    private int myCurrentHealth;
    private int myPlayerID;
    private Location3D myGoal;
    private Vector myOriginalVelocity;
    private EntityState myEntityState;
    private int mySpeed;

    public GameEntity (Pixmap image, Location3D center, Dimension size, int playerID, int health) {
        super(image, center, size);
        myMaxHealth = health;
        myCurrentHealth = myMaxHealth;
        myPlayerID = playerID;
        // ALERT THIS IS JUST FOR TESTING
        myOriginalVelocity = new Vector(0, 0);
        myVelocity = new Vector(0, 0);
        myGoal = new Location3D(center);
        myEntityState = new EntityState();
        mySpeed = DEFAULT_SPEED;
    }

    public boolean reachedGoal () {
        Vector v = getWorldLocation().difference(myGoal.to2D());
        return (v.getMagnitude() < Location3D.APPROX_EQUAL);
    }

    /**
     * Updates the shape's location.
     */
    // TODO: make Velocity three dimensional...
    public void update (double elapsedTime) {
        Vector v = getWorldLocation().difference(myGoal.to2D());
        if (reachedGoal()) {
            setVelocity(v.getAngle(), 0);
            myEntityState.stop();
        }
        else {
            setVelocity(v.getAngle(), getSpeed());
            myEntityState.setMovementState(MovementState.MOVING);
        }

        Vector velocity = new Vector(myVelocity);
        velocity.scale(elapsedTime);
        translate(velocity);
        stopMoving();
        myEntityState.update(elapsedTime);
        super.update(elapsedTime);
    }

    /**
     * Moves the Unit only. Updates first the angle the Unit is facing, and then
     * its location. Possible design choice error.
     */
    public void move (Location3D loc) {
        myGoal = loc;
    }

    /**
     * Returns shape's velocity.
     */
    public Vector getVelocity () {
        return myVelocity;
    }

    /**
     * Resets shape's velocity.
     */
    public void setVelocity (double angle, double magnitude) {
        myVelocity = new Vector(angle, magnitude);
    }

    /**
     * Returns the current health of the entity.
     * 
     * @return the current health of the entity
     */
    public int getHealth () {
        return myCurrentHealth;
    }

    public EntityState getState () {
        return myEntityState;
    }

    /**
     * Sets the health of the entity.
     * 
     * @param health
     *        is the amount of health the entity will have
     */
    public void setHealth (int health) {
        myCurrentHealth = health;
    }

    /**
     * Increases the max health of the entity.
     * 
     * @param health
     *        is the amount of additional health the entity will get
     */
    public void addMaxHealth (int health) {
        myMaxHealth += health;
    }

    /**
     * Returns the max health of the entity.
     * 
     * @return the max health of the entity
     */
    public int getMaxHealth () {
        return myMaxHealth;
    }

    /**
     * Returns the teamID the shape belongs to.
     */
    public int getPlayerID () {
        return myPlayerID;
    }

    /**
     * Sets which team the entity will be on.
     * 
     * @param playerID
     *        is the ID for the team that the entity is on
     */
    public void setPlayerID (int playerID) {
        myPlayerID = playerID;
    }

    /**
     * Rotates the Unit by the given angle.
     * 
     * @param angle
     */
    public void turn (double angle) {
        myVelocity.turn(angle);
    }

    /**
     * Specifies whether or not two entities collide.
     * 
     * @param gameEntity
     *        is the entity that is being checked for a collision
     * @return true if the bounds of the two entites intersect and false if the
     *         bounds of the entities do not interesct
     */
    public boolean collidesWith (GameEntity gameEntity) {
        return getBounds().intersects(gameEntity.getBounds());
    }

    /**
     * Translates the current center by vector v
     * 
     * @param vector
     */
    public void translate (Vector vector) {
        if (vector.getMagnitude() != 0) {
            getWorldLocation().translate(vector);
            resetBounds();
            setChanged();
            notifyObservers(getWorldLocation());
        }
    }

    /**
     * Reset shape back to its original values.
     */
    @Override
    public void reset () {
        super.reset();
        myVelocity = myOriginalVelocity;
    }

    /**
     * Returns the speed of the entity.
     * 
     * @return the speed of the entity
     */
    public int getSpeed () {
        return mySpeed;
    }

    public void setSpeed (int speed) {
        mySpeed = speed;
    }

    public void changeHealth (int change) {
        myCurrentHealth -= change;
    }

    /**
     * Checks to see if an GameEntity is dead.
     * 
     * @return true if the GameEntity has been killed and false if the
     *         GameEntity is still alive.
     */
    public boolean isDead () {
        return myCurrentHealth <= 0;
    }

    /**
     * Kills the GameEntity by setting its current health value to zero.
     */
    public void die () {
        myCurrentHealth = 0;
    }

    @Override
    public void paint (Graphics2D pen) {
        if (!isDead()) {
            super.paint(pen);
        }
    }

    /**
     * Returns the state of the entity such as its attacking state or movement
     * state.
     * 
     * @return the state of the entity.
     */
    public EntityState getEntityState () {
        return myEntityState;
    }

    /**
     * If the entity is in a stationary state, it stops moving.
     */
    public void stopMoving () {
        if (!myEntityState.canMove()) {
            setVelocity(getVelocity().getAngle(), 0);
            myGoal = new Location3D(getWorldLocation());
        }
    }
}

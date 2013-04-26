package vooga.rts.gamedesign.state;

import vooga.rts.util.DelayedTask;

/**
 * This class represents the state of the entity in the game. For example, this
 * class will keep track of whether or not a unit can attack or can currently
 * move.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu
 * 
 */

public class EntityState {

	private AttackingState myAttackingState;
	private OccupyState myOccupyState;
	private ProducingState myProducingState;
	private MovementState myMovementState;
	private DetectableState myDetectableState;
	private UnitState myUnitState;

	private double myAttackingCooldown;
	private DelayedTask myAttackingDelay;

	private static final double DEFAULT_ATTACKING_INTERVAL = .25;

	/**
	 * Creates a new state for the entity with that is set to not attacking, not
	 * occupying, not producing, and stationary. The interval for attacking
	 * after moving is also set to the default attacking interval
	 */
	public EntityState() {
		myAttackingState = AttackingState.NOT_ATTACKING;
		myOccupyState = OccupyState.NOT_OCCUPYING;
		myProducingState = ProducingState.NOT_PRODUCING;
		myMovementState = MovementState.STATIONARY;
		myDetectableState = DetectableState.DETECTABLE;
		myAttackingCooldown = DEFAULT_ATTACKING_INTERVAL;
		myUnitState = UnitState.NOTHING;
	}

	/**
	 * Returns the attacking state of the game entity.
	 * 
	 * @return the attacking state of the entity
	 */
	public AttackingState getAttackingState() {
		return myAttackingState;
	}

	/**
	 * This method is used to set the attacking state (either attacking or not
	 * attacking) for the game entity.
	 * 
	 * @param attackingState
	 *            is the attacking state that the game entity will be set to
	 * 
	 */
	public void setAttackingState(AttackingState attackingState) {
		myAttackingState = attackingState;
	}

	/**
	 * This method is used to set the occupying state (either occupying or not
	 * occupying) for the game entity.
	 * 
	 * @param occupyState
	 *            is the occupying state that the game entity will be set to
	 */
	public void setOccupyState(OccupyState occupyState) {
		myOccupyState = occupyState;
	}

	public OccupyState getOccupyState() {
		return myOccupyState;
	}

	/**
	 * This method is used to set the producing state (either producing or not
	 * producing) for the game entity.
	 * 
	 * @param producingState
	 *            is the producing state that the game entity will be set to
	 */
	public void setProducingState(ProducingState producingState) {
		myProducingState = producingState;
	}

	/**
	 * This method is used to set the movement state (either moving or
	 * stationary) for the game entity.
	 * 
	 * @param movementState
	 *            is the movement state that the game entity will be set to
	 */
	public void setMovementState(MovementState movementState) {
		myMovementState = movementState;
	}

	/**
	 * This method returns the movement state (either moving or stationary) of
	 * the game entity.
	 * 
	 * @return the movement state of the game entity
	 */
	public MovementState getMovementState() {
		return myMovementState;
	}

	/**
	 * returns the unit's state.
	 * 
	 * @return the state of the unit
	 */
	public UnitState getUnitState() {
		return myUnitState;
	}

	/**
	 * Sets the state of the unit.
	 * 
	 * @param unitState
	 *            is the state of the unit
	 */
	public void setUnitState(UnitState unitState) {
		myUnitState = unitState;
	}

	/**
	 * Returns whether the entity is currently in the move state.
	 * 
	 * @return true if the unit can move and false if the unit is stationary
	 */
	public boolean canMove() {
		return myMovementState == MovementState.MOVING;
	}

	/**
	 * Returns whether or not the entity can be selected.
	 * 
	 * @return true if the entity can be selected and false if it can not be
	 *         selected
	 */
	public boolean canSelect() {
		return myOccupyState == OccupyState.NOT_OCCUPYING;
	}

	/**
	 * Sets the state of an entity to producing. When an entity produces, it
	 * does not move attack, move, or occupy.
	 */
	public void produce() {
		myProducingState = ProducingState.PRODUCING;
		myMovementState = MovementState.STATIONARY;
		myAttackingState = AttackingState.NOT_ATTACKING;
		myOccupyState = OccupyState.NOT_OCCUPYING;
		myUnitState = UnitState.PRODUCE;
	}

	/**
	 * Returns whether or not an entity can produce other entities
	 * 
	 * @return true if the entity can produce other entities and false if it
	 *         cannot produce other entities
	 */
	public boolean canProduce() {
		return myProducingState == ProducingState.PRODUCING
				&& myUnitState == UnitState.PRODUCE;
	}

	/**
	 * Returns whether the entity can attack. In order to attack, the entity
	 * must be in the attacking state.
	 * 
	 * @return true if the entity can attack (in the attacking state and false
	 *         if the entity cannot attack
	 */
	public boolean canAttack() {
		return myAttackingState == AttackingState.ATTACKING;
	}

	/**
	 * Checks to see whether an entity has an attacking state other than not
	 * attacking.
	 * 
	 * @return true if the entity state is either attacking or waiting and false
	 *         if the entity is not attacking
	 */
	public boolean isAttacking() {
		return myAttackingState != AttackingState.NOT_ATTACKING;
	}

	/**
	 * Stops the movement of the entity. This means that the entity's movement
	 * state is set to stationary.
	 */
	public void stop() {
		myMovementState = MovementState.STATIONARY;
	}

	/**
	 * Holds the entity. This means that the entity is set to not attack and not
	 * move (it is set to stationary).
	 */
	public void hold() {
		myAttackingState = AttackingState.NOT_ATTACKING;
		myMovementState = MovementState.STATIONARY;
	}

	/**
	 * This method sets the entity to the attacking state.
	 */
	public void attack() {
		myAttackingState = AttackingState.WAITING;
		// myUnitState = UnitState.ATTACK;
		myAttackingDelay = new DelayedTask(myAttackingCooldown, new Runnable() {
			@Override
			public void run() {
				myAttackingState = AttackingState.ATTACKING;
			}
		});
	}

	/**
	 * This method is used to update the cooldown of the attacking delayed task.
	 * This delayed task is used to make the entity delay attacking after moving
	 * so that the entity does not look "buggy" as it moves and shoots. If the
	 * entity is moving, its cooldown is reset to the max cooldown. If the
	 * entity is not moving the cooldown gets decremented.
	 */
	public void update(double elapsedTime) {

		if (myAttackingDelay != null) {
			if (myMovementState == MovementState.MOVING) {
				myAttackingDelay.cancel();
				myAttackingState = AttackingState.NOT_ATTACKING;
			} else {
				// System.out.println("Not Moving!");
				myAttackingDelay.update(elapsedTime);
			}
		}
	}
}

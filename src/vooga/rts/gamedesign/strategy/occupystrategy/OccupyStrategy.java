package vooga.rts.gamedesign.strategy.occupystrategy;

import java.util.ArrayList;
import java.util.List;

import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.units.Unit;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.buildings.Building;
import vooga.rts.gamedesign.strategy.Strategy;


/**
 * This interface is implemented by the classes CanBeOccupied and
 * CannotBeOccupied that are then used as instance variables in the classes
 * that could possibly be occupied by Units. If the unit currently can be
 * occupied, it will have an instance of CanBeOccupied, otherwise it will have
 * an instance of CannotBeOccupied. In this way, occupying specific features
 * can be refactored out of the Sprite hierarchy.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti 
 * @author Wenshun Liu
 * 
 */
public interface OccupyStrategy extends Strategy {
	
	/**
	 * Allows the entity that implements a occupy strategy to get occupied by a unit or entity. 
	 * @param entity
	 * @param u
	 */
    public void getOccupied (InteractiveEntity entity, Unit u);

    /**
     * Creates all the actions that this occupystrategy can carry out. 
     * @param entity
     */
    public void createOccupyActions (final InteractiveEntity entity);

    /**
     * Sets the current occupier to the one specified by the id. 
     * @param id
     */
    public void setOccupierID (int id);
    
    /**
     * Returns the list of occupiers. 
     * @return
     */
    public List<Integer> getOccupiers ();

    /**
     * Returns the maximum amount of entities this strategy can hold. 
     * @return
     */
    public int getMaxOccupiers ();

    /**
     * Returns the id of this occupier. 
     * @return
     */
    public int getOccupierID ();

}

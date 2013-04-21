package vooga.rts.gamedesign.sprite.rtsprite;

import vooga.rts.util.Location;

/**
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu 
 *
 */
public interface IMovable {

	/**
	 * Moves the Object to the given location.
	 * @param loc
	 */
	public void move(Location loc);
}
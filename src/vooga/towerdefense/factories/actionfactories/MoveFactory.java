package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.movement.Move;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory for creating move action. User needs to specify which attribute to be
 * taken as a reference for move speed in the constructor.
 * 
 * @author Zhen Gou
 * 
 */

public class MoveFactory extends ActionFactory {

	private String myMoveSpeedReference;
	private String myDirectionReference;

	/**
	 * constructor
	 * 
	 * @param moveSpeedReference
	 * @param directionReference
	 */
	public MoveFactory(
			@ActionAnnotation(name = "move speed", value = "attribute") String moveSpeedReference,
			@ActionAnnotation(name = "move direction", value = "direction") String directionReference) {
		super();
		myMoveSpeedReference = moveSpeedReference;
		myDirectionReference = directionReference;
	}

	/**
	 * create a move action for the game element given
	 * 
	 * @param elementToMove
	 * @return
	 */
	@Override
	protected Action buildAction(GameElement elementToMove) {
		Action action = new Move(elementToMove.getCenter(), elementToMove
				.getAttributeManager().getAttribute(myMoveSpeedReference),
				elementToMove.getAttributeManager().getAttribute(
						myDirectionReference));
		return action;
	}

}

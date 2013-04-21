package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.Move;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.gameElements.GameElement;

/**
 * Factory for creating move action. User needs to specify which attribute to be taken
 * as a reference for move speed in the constructor. 
 * @author Zhen Gou
 * 
 */

public class MoveFactory extends ActionFactory {
	private String myMoveSpeedReference;

	public MoveFactory(String moveSpeedReference) {
		myMoveSpeedReference=moveSpeedReference;
	}
	/**
	 * create a move action for the game element given
	 * @param elementToMove
	 * @return
	 */

	public Action createAction(GameElement elementToMove) {
		return new Move(elementToMove.getCenter(), elementToMove
				.getAttributeManager().getAttribute(
						myMoveSpeedReference), elementToMove
				.getAttributeManager().getAttribute(
						AttributeConstants.DIRECTION));
	}
	
	//temporary
	@Override
	public Action createAction() {
		return null;
	}

}

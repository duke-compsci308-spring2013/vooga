package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.movement.MoveToTarget;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Factory that creates a MoveToTarget action, constructor needs a String
 * referencing which attribute to be used as move speed. 
 * Also set boolean to indicate whether projectile should follow target upon hit,
 * or just move in a straight line in general direction.
 * 
 * @author Xu Rui
 * 
 */
public class MoveToTargetFactory extends ActionFactory {
	private String myMoveSpeedReference;
	private int isTargetTracking;

	public MoveToTargetFactory(
			@ActionAnnotation(name = "move speed", value = "attribute") String moveSpeedReference,
			@ActionAnnotation(name = "move speed", value = "attribute") String targetTracking) {
		super();
		myMoveSpeedReference = moveSpeedReference;
		isTargetTracking = Integer.parseInt(targetTracking);

	}

	@Override
	protected Action buildAction(GameElement e) {
		return new MoveToTarget(e.getCenter(), e.getCenter(), e
				.getAttributeManager().getAttribute(myMoveSpeedReference),
				(isTargetTracking == 1));
	}

}

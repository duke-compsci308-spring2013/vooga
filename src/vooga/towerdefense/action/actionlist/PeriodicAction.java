package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.Attribute;

/**
 * Defines an action that executes periodically.
 * 
 * @author Matthew Roy
 * @author Zhen Gou
 * 
 */
public class PeriodicAction extends TargetedAction {
	private Attribute myCoolDown;
	private double myTimer;

	public PeriodicAction(Attribute cd) {
		myCoolDown = cd;
		resetTimer();
	}

	public void update(double elapsedTime) {
		if (isEnabled()) {
			executeAction(elapsedTime);
		}
	}

	/**
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void executeAction(double elapsedTime) {
		updateTimer(elapsedTime);
		if (myTimer > myCoolDown.getValue()) {
			updateFollowUpActions(elapsedTime);
			resetTimer();
		}

	}

	private void updateTimer(double elapsedTime) {
		myTimer += elapsedTime;
	}

	private void resetTimer() {
		myTimer = 0;
	}

}

package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.Action;

/**
 * A simple Action that disables itself after a certain amount of time.
 * @author JLongley
 *
 */
public class LevelTimerAction extends Action {

	private int myTimer;
	private int myDuration;
	
	public LevelTimerAction(int duration) {
		super();
		myTimer = 0;
		myDuration = duration;
		
	}
	
	@Override
	public void executeAction(double elapsedTime) {
		myTimer+=elapsedTime;
		if(myTimer>myDuration)
			setEnabled(false);
	}
	
	public int getRemainingTime() {
	    return myDuration - myTimer;
	}
}

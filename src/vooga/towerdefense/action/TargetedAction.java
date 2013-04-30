package vooga.towerdefense.action;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.gameelements.GameElement;

/**
 * Actions that require a target or multiple targets. Allows targets to be set
 * within the action.
 * 
 * @author XuRui
 * 
 */

public abstract class TargetedAction extends Action {

	private List<GameElement> myTargets;

	public TargetedAction() {
		super();
		setTargetTracking(true);
		myTargets = new ArrayList<GameElement>();
	}
	
	/**
	 * Overrides update by passing on target information to all relevant follow up actions
	 * in addition to updating follow up actions.
	 */
	@Override
	public void update(double elapsedTime){
		super.update(elapsedTime);
		if (isEnabled()){
			updateTargetedFollowUpActions(myTargets);
		}
	}
	
	/**
	 * Adds one game element to the current list of targets
	 * 
	 * @param target
	 */
	public void addTarget(GameElement target) {
		myTargets.add(target);
	}

	/**
	 * Adds a list of targets to the current targets of this action
	 * 
	 * @param targets
	 */
	public void addTargets(List<GameElement> targets) {
		myTargets.addAll(targets);
	}

	/**
	 * Sets the target list to a new list of targets
	 * 
	 * @param newTargets
	 */
	public void setTargets(List<GameElement> newTargets) {
		myTargets = newTargets;
	}
	
	/**
	 * Sets single target, used usually for acting on one single target.
	 * @param newTarget
	 */
	public void setSingleTarget(GameElement newTarget){
		List<GameElement> target = new ArrayList<GameElement>();
		target.add(newTarget);
		setTargets(target);
	}

	/**
	 * Returns current targets list
	 * 
	 * @return
	 */
	public List<GameElement> getTargets() {
		return myTargets;
	}
	
	/**
	 * Returns first target in current target list. 
	 * Removes target from current list.
	 * @return
	 */
	public GameElement getSingleTarget() {
		if (!myTargets.isEmpty()){
			return myTargets.get(0);
		}
		return null;
	}


	/**
	 * Updates all targeted follow up action accordingly; adds all targets to
	 * targeted actions. To be overridden if action wants to add targets in a
	 * different manner.
	 * 
	 * @param targets
	 */
	public void updateTargetedFollowUpActions(List<GameElement> targets) {
		for (TargetedAction a : getTargetedFollowUpActions()) {
			a.setTargets(targets);
			System.out.printf("update targets size to be %s\n", targets.size());
		}
	}
}

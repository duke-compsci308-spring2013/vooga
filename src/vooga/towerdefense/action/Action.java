package vooga.towerdefense.action;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.gameElements.GameElement;


/**
 * An AbstractAction is superclassed to define specific Actions that can be taken by game elements.
 * This includes attacks, upgrades, path creation and anything the developer may wish to implement.
 * 
 * @author Matthew Roy
 * @author XuRui
 * @author Zhen Gou
 * 
 */
public abstract class Action {
    private boolean myEnabledState;
    private List<Action> myFollowUpActions;
    private List<GameElement> myTargets;

    public Action () {
        myFollowUpActions = new ArrayList<Action>();
        myTargets = new ArrayList<GameElement>();
        myEnabledState = true;
    }

    /**
     * Executes action after update clears the execute condition.
     */
    public abstract void executeAction (double elapsedTime);
    
    /**
     * Executes action after update clears the execute condition.
     */
    public void updateFollowupActions(double elapsedTime) {
        for (Action a : getFollowUpActions()) {
            a.update(elapsedTime);
        }
    }

    /**
     * Update action status and executes action if it needs to
     * 
     * @param elapsedTime
     */
    public void update (double elapsedTime) {
        if (isEnabled()) {
            executeAction(elapsedTime);
            updateFollowupActions(elapsedTime);
        }
    }

    /**
     * Adds a new action to occur after action is done executing
     * @param action
     */
    public void addFollowUpAction (Action action) {
        myFollowUpActions.add(action);
    }

    public void addFollowUpActions (List<Action> action) {
        myFollowUpActions.addAll(action);
    }

    /**
     * Adds a game element to the current list of targets
     * 
     * @param target
     */
    public void addTarget (GameElement target) {
        myTargets.add(target);
    }

    /**
     * Adds a list of targets to the current targets of this action
     * 
     * @param targets
     */
    public void addTarget (List<GameElement> targets) {
        myTargets.addAll(targets);
    }

    /**
     * Sets the target list to a new target
     * 
     * @param newTargets
     */
    public void setTargets (GameElement newTarget) {
        myTargets.clear();
        myTargets.add(newTarget);
    }
    
    public List<GameElement> getTargets() {
        return myTargets;
    }

    /**
     * Sets the target list to a new list
     * 
     * @param newTargets
     */
    public void setTargets (List<GameElement> newTargets) {
        myTargets = newTargets;
    }

    /**
     * Returns list of followup actions
     * @return
     */
    public List<Action> getFollowUpActions () {
        return myFollowUpActions;
    }

    public boolean isEnabled () {
        return myEnabledState;
    }

    public void setEnabled (boolean isEnabled) {
        myEnabledState = isEnabled;
    }
}

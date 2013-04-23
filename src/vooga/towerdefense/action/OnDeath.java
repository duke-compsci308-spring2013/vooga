package vooga.towerdefense.action;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameElements.GameElement;

/**
 * 
 * Defines how something acts on death
 * @author Matthew Roy
 *
 */
public class OnDeath extends Action {

    Attribute myHealth;
    List<Action> myActions;
    /**
     * @param initiator
     */
    public OnDeath (Attribute health) {
        super();
        myHealth = health;
        setEnabled(false);
    }

    public void update(double elapsedTime) {
        executeAction(elapsedTime);
        if (isEnabled()) {
            updateFollowupActions(elapsedTime);
        }
    }
    
    /**
     * Overrides from superclasses
     * @param elapseTime 
     */
    @Override
    public void executeAction (double elapseTime) {
        if (myHealth.getValue() <= 0)
            setEnabled(true);
    }
    
    public void execute() {
        for (Action a : myActions) {
            a.executeAction(2);
        }
    }

}

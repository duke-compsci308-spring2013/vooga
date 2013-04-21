package vooga.towerdefense.factories.actionfactories;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;

/**
 * Creates actions based on definitions of the ActionFactory
 * @author Matthew Roy
 *
 */
public class ActionFactory {
    
    public ActionFactory() {
        
    }
    
    public List<Action> createActions() {
        List<Action> myActions = new ArrayList<Action>();
        return myActions;
    }

}
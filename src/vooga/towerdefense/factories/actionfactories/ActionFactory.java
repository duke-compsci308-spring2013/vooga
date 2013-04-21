package vooga.towerdefense.factories.actionfactories;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;


/**
 * Creates actions based on definitions of the ActionFactory
 * NEEDS TO BE INITIALIZED BEFORE ANYTHING HAPPENS
 * 
 * @author Matthew Roy
 * 
 */
public abstract class ActionFactory {

    protected GameMap myMap;
    protected Graphics2D myPen;
    protected List<ActionFactory> myFollowUpActions;

    public ActionFactory () {
    }
    
    /**
     * Places in all of the objects that the factory could need to function
     * Cannot create actions until initialized
     */
    public void initialize (GameMap map, Graphics2D pen) {
        myMap = map;
        myPen = pen;
        myFollowUpActions = new ArrayList<ActionFactory>();
    }
    
    
    // TODO: Not sure if this is the best way to implement this. There needs to be a way to give in
    // a list of followup actions to the action
    public void addFollowUpActionsFactories (ActionFactory addToList) {
        myFollowUpActions.add(addToList);
    }

    
    /**
     * THIS IS HOW YOU MAKE A NEW INSTANTIATION OF ACTION
     * @param e
     * @return
     */
    public Action createAction(GameElement e) {
        Action myAction = buildAction(e);
        myAction.addFollowUpActions(createFollowUpActions(e));
        return myAction;
    }
    

    /**
     * Used to just create the single action
     * @param e
     * @return
     */
    protected abstract Action buildAction (GameElement e);
    
    /**
     * Used to create the following actions
     * @param e
     * @return
     */
    protected List<Action> createFollowUpActions(GameElement e) {
        List<Action> followingActions = new ArrayList<Action>();
        for (ActionFactory a : myFollowUpActions) {
            followingActions.add(a.createAction(e));
        }
        return followingActions;
    }

}

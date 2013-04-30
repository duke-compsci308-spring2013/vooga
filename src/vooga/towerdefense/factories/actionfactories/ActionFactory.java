package vooga.towerdefense.factories.actionfactories;

import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Player;


/**
 * Creates actions based on definitions of the ActionFactory
 * NEEDS TO BE INITIALIZED BEFORE ANYTHING HAPPENS
 * 
 * @author Matthew Roy
 * 
 */
public abstract class ActionFactory {

    private GameMap myMap;
    private Player myPlayer;
    private List<ActionFactory> myFollowUpActions;

    public ActionFactory () {
        myFollowUpActions = new ArrayList<ActionFactory>();
    }

    /**
     * Places in all of the objects that the factory could need to function
     * Cannot create actions until initialized
     */
    public void initialize (GameMap map, Player player) {
        myMap = map;
        myPlayer = player;
        for (ActionFactory a : myFollowUpActions) {
            a.initialize(map, player);
        }
    }

    public GameMap getMap () {
        return myMap;
    }

    public Player getPlayer () {
        return myPlayer;
    }

    public void addFollowUpActionsFactories (ActionFactory addToList) {
        myFollowUpActions.add(addToList);
    }

    public void addFollowUpActionsFactories (List<ActionFactory> addToList) {
        myFollowUpActions.addAll(addToList);
    }

    public List<ActionFactory> getFollowUpActions () {
        return myFollowUpActions;
    }

    /**
     * Create an action by calling buildAction on specific action factory.
     * 
     * @param e
     * @return
     */
    public Action createAction (GameElement e) {
        Action myAction = buildAction(e);
        myAction.addFollowUpActions(createFollowUpActions(e));
        return myAction;
    }

    /**
     * Creates a targeted action, which 
     * @param e
     * @param target
     * @return
     */
    public TargetedAction createTargetedAction (GameElement e, GameElement target) {
        Action myAction = buildTargetedAction(e, target);
        myAction.addFollowUpActions(createFollowUpActions(e, target));
        return (TargetedAction) myAction;
    }

    /**
     * Builds an action, overridden by action factory.
     * 
     * @param e
     * @return
     */
    protected abstract Action buildAction (GameElement e);

    /**
     * Builds a targeted action, overridden by action factory.
     * Returns buildAction by default.
     * 
     * @param e
     * @return
     */
    protected Action buildTargetedAction (GameElement e, GameElement target){
    	return buildAction(e);
    }
    
    /**
     * Used to create the following actions
     * 
     * @param e
     * @return
     */
    protected List<Action> createFollowUpActions (GameElement e) {
        List<Action> followingActions = new ArrayList<Action>();
        for (ActionFactory a : myFollowUpActions) {
            followingActions.add(a.createAction(e));
        }
        return followingActions;
    }

    /**
     * Used to create the following actions
     * 
     * @param e
     * @return
     */
    protected List<Action> createFollowUpActions (GameElement e, GameElement target) {
        List<Action> followingActions = new ArrayList<Action>();
        for (ActionFactory a : myFollowUpActions) {
            followingActions.add(a.createTargetedAction(e, target));
        }
        return followingActions;
    }

}

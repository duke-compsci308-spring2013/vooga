package vooga.towerdefense.action;

import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;


/**
 * 
 * Finds all game elements on map within radius of its source location and
 * sets its followup actions to target those elements
 * it then calls update on its followup actions
 * 
 * 
 * @author Matthew Roy
 * @author Xu Rui
 */
public class FindTargets extends Action {

    private Attribute myScanningRadius;
    private Location mySource;
    private GameMap myMap;

    public FindTargets (GameMap map, Location source, Attribute attackRadius) {
        super();
        myScanningRadius = attackRadius;
        mySource = source;
        myMap = map;
    }

    public void update (double elapsedTime) {
        if (isEnabled()) {
            executeAction(elapsedTime);
            updateFollowupActions(elapsedTime);
        }
    }

    @Override
    public void executeAction (double elapsedTime) {
        setTargets(myMap.getTargetsWithinRadius(mySource, myScanningRadius.getValue()));
    }

    public void updateFollowupActions (double elapsedTime) {
        for (Action a : getFollowUpActions()) {
            a.setTargets(getTargets());
            a.update(elapsedTime);
        }
    }
}

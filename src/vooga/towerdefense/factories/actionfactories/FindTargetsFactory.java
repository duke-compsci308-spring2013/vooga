package vooga.towerdefense.factories.actionfactories;

import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.FindTargets;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;

/**
 * Creates find target actions that give all of their followup actions 
 * the game elements inside their radius
 * @author Matthew Roy
 *
 */
public class FindTargetsFactory extends ActionFactory {
    
    private String myRadiusId;
    private List<ActionFactory> myFollowUpActions;
    
    /**
     * 
     * @param AttrRadiusToSearch is the id of the attribute used for radius
     */
    public FindTargetsFactory (String AttrRadiusToSearch) {
        myRadiusId = AttrRadiusToSearch;
    }

    /**
     * Creates a find targets action
     * @param e
     * @return 
     */
    @Override
    protected Action buildAction (GameElement e) {
        Location searchCenter = e.getCenter();
        Action locateTargets = new FindTargets(myMap, searchCenter, e.getAttributeManager().getAttribute(myRadiusId));
        for (ActionFactory a : myFollowUpActions) {
            locateTargets.addFollowUpAction(a.buildAction(e));
        }
        return locateTargets;
    }

}

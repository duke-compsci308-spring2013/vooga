package vooga.towerdefense.action;

import java.util.List;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.factories.ProjectileFactory;
import vooga.towerdefense.factories.examples.ExampleProjectileFactory;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Vector;


/**
 * 
 * @author Xu Rui
 * @author Matthew Roy
 * 
 */
public class FindTargets extends Action {

    private Attribute myScanningRadius;
    private List<GameElement> myTargets;
    private Location mySource;
    private GameMap myMap;

    public FindTargets (GameMap map, Location source, Attribute attackRadius) {
        myScanningRadius = attackRadius;
        mySource = source;
        myMap = map;
        myTargets = myMap.getTargetsWithinRadius(source, myScanningRadius.getValue());
        initAction();
    }

    public void update (double elapsedTime) {
        if (isEnabled()) {
            myTargets = myMap.getTargetsWithinRadius(mySource, myScanningRadius.getValue());
            executeAction(elapsedTime);
        }
    }

    @Override
    public void executeAction (double elapsedTime) {
        if (!myTargets.isEmpty()) {
            List<Action> actions = getFollowUpAction();
            for (Action a : actions) {
                a.setTargets(myTargets);
                a.executeAction(elapsedTime);
            }
        }

    }

}

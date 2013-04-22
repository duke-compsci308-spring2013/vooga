package vooga.towerdefense.action;

import java.util.List;

import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.model.GameMap;


/**
 * Single attack action launched by a GameElement.
 * 
 * @author XuRui
 * @author Matthew Roy
 * @author Zhen Gou
 * 
 */
public class AttackAction extends PeriodicAction {
    private GameElement myInitiator;
    private GameMap myMap;

    public AttackAction (GameMap map, GameElement initiator) {
        myMap = map;
        myInitiator = initiator;
        setCoolDown(initiator.getAttributeManager()
                .getAttribute(AttributeConstants.ATTACK_INTERVAL).getValue());
    }

    @Override
    public void executeAction (double elapsedTime) {
        updateTimer(elapsedTime);
        // check whether it's in cool down
        if (isReady()) {

            // get targets that we wanna shoot
            List<GameElement> targets =
                    myMap
                            .getTargetsWithinRadius(
                                                    myInitiator.getCenter(),
                                                    myInitiator
                                                            .getAttributeManager()
                                                            .getAttribute(AttributeConstants.ATTACK_RADIUS)
                                                            .getValue(),
                                                    (int) (myInitiator
                                                            .getAttributeManager()
                                                            .getAttribute(AttributeConstants.NUM_OF_TARGETS)
                                                            .getValue()));

            // shoot a projectile towards each target

            for (GameElement target : targets) {
                myMap.addGameElement(myInitiator.getAttributeManager().getProjectileFactory()
                        .createProjectile(myInitiator, target.getCenter()));

            }

            resetTimer();
        }

    }

}

package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.ShootProjectile;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;


/**
 * This is an action factory that builds a LaunchProjectile action.
 * 
 * @author XuRui
 * 
 */

public class ShootProjectileFactory extends ActionFactory {
    private String myNumProjectiles;
    private String myNumShots;
    private String myCooldown;
    private String myFactory;
    private int targetDetectionOn;

    /**
     * constructor
     * 
     * @param factory
     */
    public ShootProjectileFactory (
                                   @ActionAnnotation(name = "projectile", value = "name") String factory,
                                   @ActionAnnotation(name = "num_of_projectile", value = "attribute") String numUnits,
                                   @ActionAnnotation(name = "num_of_shots", value = "attribute") String numShots,
                                   @ActionAnnotation(name = "cooldown", value = "attribute") String cooldown,
                                   @ActionAnnotation(name = "targetDetection", value = "attribute") String targetDetection) {
        super();
        myNumProjectiles = numUnits;
        myNumShots = numShots;
        myCooldown = cooldown;
        myFactory = factory;
        targetDetectionOn = Integer.parseInt(targetDetection);

    }

    /**
     * Builds a LaunchProjectile action with GameElementFactory already added
     * into corresponding attribute manager.
     */
    @Override
    protected Action buildAction (GameElement e) {
        return new ShootProjectile(e.getAttributeManager().getAttribute(myCooldown),
                                   e.getAttributeManager().getAttribute(myNumProjectiles), e
                                           .getAttributeManager().getAttribute(myNumShots),
                                   e.getCenter(), e.getAttributeManager()
                                           .getGameElementFactory(myFactory), getMap(),
                                   targetDetectionOn == 1);
    }

}

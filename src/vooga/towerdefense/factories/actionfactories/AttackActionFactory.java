package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Player;

/**
 * A bundle of action factories to make implementing game elements easier
 * @author Matthew Roy
 *
 */
public class AttackActionFactory extends ActionFactory {
    
    
    private String myRadius;
    private String myAttackSpeed;
    private String myNumberOfTargets;
    private String myTargets;
    private String myProjectileType;
    
    private FindTargetsFactory myFinder;
    private FilterTargetsFactory myFilter;
    private PeriodicActionFactory mySpeed;
    private LaunchProjectileFactory myProjectiles;
    
    
    public AttackActionFactory(@ActionAnnotation(name = "attack radius", value = "attribute") String attackRadius,
                               @ActionAnnotation(name = "attack speed", value = "attribute") String attackSpeed,
                               @ActionAnnotation(name = "num targets", value = "attribute") String numTargets,
                               @ActionAnnotation(name = "target affiliation", value = "attribute") String targetAffiliation,
                               @ActionAnnotation(name = "projectile", value = "name") String projectileFactory) {
        super();
        myRadius = attackRadius;
        myAttackSpeed = attackSpeed;
        myNumberOfTargets = numTargets;
        myTargets = targetAffiliation;
        myProjectileType = projectileFactory;
    }
    
    public void initialize (GameMap map, Player player) {
        super.initialize(map, player);
        makeComboFactories();
        myFinder.initialize(map, player);
        myFilter.initialize(map, player);
        mySpeed.initialize(map, player);
        myProjectiles.initialize(map, player);
    }
    
    
    public void makeComboFactories() {
        myFinder = new FindTargetsFactory(myRadius);
        myFilter = new FilterTargetsFactory(myTargets, myNumberOfTargets);
        mySpeed = new PeriodicActionFactory(myAttackSpeed);
        myProjectiles = new LaunchProjectileFactory(myProjectileType);
    }

    /**
     * 
     * @param e
     * @return 
     */
    @Override
    protected Action buildAction (GameElement e) {
        Action attack = myFinder.buildAction(e);
        Action filter = myFilter.createAction(e);
        Action speed = mySpeed.createAction(e);
        Action projectile = myProjectiles.createAction(e);
        attack.addFollowUpAction(filter);
        filter.addFollowUpAction(speed);
        speed.addFollowUpAction(projectile);
        return attack;
    }

}

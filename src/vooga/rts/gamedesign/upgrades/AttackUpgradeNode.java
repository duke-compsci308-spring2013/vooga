package vooga.rts.gamedesign.upgrades;

import vooga.rts.game.RTSGame;
import vooga.rts.gamedesign.sprite.gamesprites.Projectile;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gamedesign.strategy.attackstrategy.CanAttack;
import vooga.rts.gamedesign.weapon.Weapon;
import vooga.rts.util.ReflectionHelper;

/**
 * This class represents the type of upgrade taken place on Attack Strategy. In
 * the case of Attack Strategy the only upgrade will be from Cannot Attack to
 * Can Attack.
 * 
 * @author Wenshun Liu
 *
 */
public class AttackUpgradeNode extends UpgradeNode {

    public AttackUpgradeNode (UpgradeTree upgradeTree,
                              String upgradeType,
                              int upgradeValue,
                              int costedResourceAmount) {
        super(upgradeTree, upgradeType, upgradeValue, costedResourceAmount);
    }

    /**
     * Applies the upgrade to an InteractiveEntity using reflection helper,
     * which locates the property to be upgraded and applies the upgrade.
     */
    @Override
    public void upgrade (InteractiveEntity requester) {
    	ReflectionHelper.setValue("myAttackStrategy", requester,
    			new CanAttack(requester.getWorldLocation(),
    					requester.getPlayerID()));
    	Weapon w = RTSGame.getFactory().getWeapon(getUpgradeValue());    	
    	if (w == null) {
    	    w =
                new Weapon(new Projectile(Projectile.DEFAULT_PIC,
                		requester.getWorldLocation(),
                        Projectile.DEFAULT_DIMENSION, requester.getPlayerID(),
                        Projectile.DEFAULT_DAMAGE,
                        Projectile.DEFAULT_HEALTH,800),Weapon.DEFAULT_RANGE,
                        requester.getWorldLocation(),
                        Weapon.DEFAULT_COOLDOWN_TIME);
    	}
    	else {
    	    w = w.copy();
    	}
        requester.addWeapon(w);
    }

}

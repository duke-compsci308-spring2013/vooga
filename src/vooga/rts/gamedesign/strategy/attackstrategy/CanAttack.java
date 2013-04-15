package vooga.rts.gamedesign.strategy.attackstrategy;

import java.util.ArrayList;
import java.util.List;

import vooga.rts.gamedesign.Weapon;
import vooga.rts.gamedesign.sprite.InteractiveEntity;
import vooga.rts.gamedesign.sprite.rtsprite.IAttackable;


/**
 * This class implements AttackStrategy and is used as an instance in
 * InteractiveEntity for objects that are able to attack. The attack method in
 * this class will specify how the interactive will attack.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu
 * 
 */
public class CanAttack implements AttackStrategy {
    private List<Weapon> myWeapons;
    private int myWeaponIndex;
    private boolean myCanAttack = true;

	public CanAttack() {
		 myWeapons = new ArrayList<Weapon>();
		 myWeaponIndex = 0;
	}

	/**
	 * Attacks the given IAttackable object by first judging whether the Weapon
	 * is in range for the attack action.
	 * @param enemy the IAttackable object being attacked.
	 * @param distance the distance between the CanAttack object and the enemy.
	 */
	public void attack(IAttackable enemy, double distance) {
		if(inWeaponRange((InteractiveEntity) enemy, distance)) {
            myWeapons.get(myWeaponIndex).fire((InteractiveEntity) enemy);
        }    
	}
	
	/**
	 * Determines if the IAttackable object is in the range of the currently
	 * activated Weapon.
	 * @param enemy the IAttackable object being attacked.
	 * @param distance the distance between the CanAttack object and the enemy.
	 * @return whether the IAttackable object is in the range of the currently
	 * activated Weapon.
	 */
    private boolean inWeaponRange(InteractiveEntity enemy, double distance) {
        //ellipse thing doesnt seem to be working very well. 
        if(!myWeapons.isEmpty() && distance < myWeapons.get(myWeaponIndex).getRange()){
            return true;
        }
        //buggy :( myWeapons.get(myWeaponIndex).inRange(enemy)
        return false;
    }
    
    /**
	 * Returns the list of Weapon stored in this CanAttack object.
	 * @return the list of Weapon stored
	 */
    public List<Weapon> getWeapons() {
        return myWeapons;
    }
    
    /**
	 * Returns the index of the Weapon that's currently been activated in the
	 * list of Weapons belonged to this CanAttack object.
	 * @return the index of the Weapon that's currently been activated
	 */
    public int getWeaponIndex() {
        return myWeaponIndex;
    }
    
    /**
     * Changes the currently activated Weapon by setting its index as
     * WeaponIndex.
     * @param weaponIndex
     */
    public void setWeaponIndex(int weaponIndex) {
        myWeaponIndex = weaponIndex;
    }

    /**
     * Determines if this CanAttack object currently has Weapon stored.
     * @return
     */
    public boolean hasWeapon(){
        return !myWeapons.isEmpty();
    }
    
    /**
	 * Adds a Weapon to the list of Weapons belonged to this AttackStrategy.
	 * @param weapon the new Weapon to be added into the list.
	 */
    public void addWeapons(Weapon weapon) {
        myWeapons.add(weapon);
    }

	/**
	 * Determines whether this CanAttack is able to attack.
	 * @return Whether this CanAttack is able to attack.
	 */
	public boolean getCanAttack() {
		return myCanAttack;
	}

	public Weapon getCurrentWeapon() {
		return myWeapons.get(myWeaponIndex);
	}
}

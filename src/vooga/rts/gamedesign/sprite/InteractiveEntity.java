package vooga.rts.gamedesign.sprite;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import vooga.rts.gamedesign.action.Action;
import vooga.rts.gamedesign.sprite.rtsprite.IAttackable;
import vooga.rts.gamedesign.sprite.rtsprite.Projectile;
import vooga.rts.gamedesign.strategy.attackstrategy.AttackStrategy;
import vooga.rts.gamedesign.strategy.attackstrategy.CannotAttack;
import vooga.rts.gamedesign.upgrades.UpgradeNode;
import vooga.rts.gamedesign.upgrades.UpgradeTree;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;

/**
 * This class is the extension of GameEntity. It represents shapes that are
 * able to upgrade (to either stat of its current properties or add new
 * properties) and attack others.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu
 *
 */
public abstract class InteractiveEntity extends GameEntity implements IAttackable{

	//Default speed 
	private static int DEFAULT_INTERACTIVEENTITY_SPEED = 150;
	
    private boolean isSelected;
    private UpgradeTree myUpgradeTree;
    private Sound mySound;
    private AttackStrategy myAttackStrategy;
    private int myArmor;
    private List<Action> myActions;

    /**
	 * Creates a new interactive entity.
	 * @param image is the image of the interactive entity
	 * @param center is the location of the interactive entity
	 * @param size is the dimension of the interactive entity
	 * @param sound is the sound the interactive entity makes
	 * @param teamID is the ID of the team that the interactive entity is on
	 * @param health is the health of the interactive entity
	 */
    public InteractiveEntity (Pixmap image, Location3D center, Dimension size, Sound sound, int playerID, int health) {
        super(image, center, size, playerID, health);
        //myMakers = new HashMap<String, Factory>(); //WHERE SHOULD THIS GO?
        mySound = sound;
        myAttackStrategy = new CannotAttack();
        myActions = new ArrayList<Action>();
        isSelected = false;

    }
    /*
     * Ze clone method
     */
    //TODO: Make abstract
    public InteractiveEntity copy(){
    	return null;
    }
	/**
	 * Returns the upgrade tree for the interactive entity.
	 * @return the upgrade tree for the interactive entity
	 */
	public UpgradeTree getUpgradeTree() {
		return myUpgradeTree;
	}
	
	public void setUpgradeTree(UpgradeTree upgradeTree, int playerID) {
		myUpgradeTree = upgradeTree;
	}
	public int getSpeed() {
		return DEFAULT_INTERACTIVEENTITY_SPEED;
	}
	/**
	 * This method specifies that the interactive entity is getting attacked
	 * so it calls the attack method of the interactive entity on itself.
	 * @param a is the interactive entity that is attacking this interactive
	 * entity
	 */
	public void getAttacked(InteractiveEntity a) {
		a.attack(this);
	}
	/**
	 * Returns the sound that the interactive entity makes.
	 * @return the sound of the interactive entity
	 */
	public Sound getSound() {
		return mySound;
	}

    
	/**
     * Sets the isSelected boolean to the passed in bool value. 
     */
    public void select(boolean bool) {
        isSelected = bool;
    }

    public List<Action> getActions() {
        return myActions;
    }
	/**
	 * This method specifies that the interactive entity is attacking an 
	 * IAttackable. It checks to see if the IAttackable is in its range, it 
	 * sets the state of the interactive entity to attacking, and then it
	 * attacks the IAttackable if the state of the interactive entity lets it
	 * attack. 
	 * @param a is the IAttackable that is being attacked.
	 */
	public void attack(IAttackable a) {
		double distance = Math.sqrt(Math.pow(getWorldLocation().getX() - ((InteractiveEntity) a).getWorldLocation().getX(), 2) + Math.pow(getWorldLocation().getY() - ((InteractiveEntity) a).getWorldLocation().getY(), 2)); 
		if(!this.isDead()) {
			//getEntityState().setAttackingState(AttackingState.ATTACKING);
			getEntityState().attack();
			//setVelocity(getVelocity().getAngle(), 0);
			//getGameState().setMovementState(MovementState.STATIONARY);
			if(getEntityState().canAttack()) {
			
				myAttackStrategy.attack(a, distance);
				
			}
		}    
	} 
	//TODO: THIS IS DUPLICATED CODE AS IN ATTACK STRATEGY!!! SHOULD DELETE IT!
	public boolean inRange(InteractiveEntity enemy) {
		//ellipse thing doesnt seem to be working very well. 
		double distance = Math.sqrt(Math.pow(getWorldLocation().getX() - enemy.getWorldLocation().getX(), 2) + Math.pow(this.getWorldLocation().getY() - enemy.getWorldLocation().getY(), 2)); 
		if(getAttackStrategy().getCanAttack() && !getAttackStrategy().getWeapons().isEmpty() && distance < getAttackStrategy().getWeapons().get(getAttackStrategy().getWeaponIndex()).getRange()){
			return true;
		}
		//buggy :( myWeapons.get(myWeaponIndex).inRange(enemy)
		return false;
	}

	/**
	 * Sets the attack strategy for an interactive. Can set the interactive
	 * to CanAttack or to CannotAttack and then can specify how it would
	 * attack.
	 * 
	 * @param newStrategy is the new attack strategy that the interactive
	 *        will have
	 */
	public void setAttackStrategy(AttackStrategy newStrategy){
		myAttackStrategy = newStrategy;
	}
	/**
	 * Returns the current attack strategy of the interactive
	 * 
	 * @return the current attack strategy
	 */
	public AttackStrategy getAttackStrategy () {
		return myAttackStrategy;
	}


	public int calculateDamage(int damage) {
		return damage * (1-(myArmor/(myArmor+100)));
	}

    /**
     * upgrades the interactive based on the selected upgrade
     * @param upgradeNode is the upgrade that the interactive will get
     * @throws NoSuchMethodException 
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws SecurityException 
     * @throws IllegalArgumentException 
     */
    public void upgrade (UpgradeNode upgradeNode) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException { 	
        //upgradeNode.apply(upgradeNode.getUpgradeTree().getUsers());
    }
    public UpgradeTree getTree(){
        return myUpgradeTree;
    }

    
    public Action findAction(String name) {
    	for (Action a: myActions) {
    		if (a.getName().equals(name)) {
    			return a;
    		}
    	}
    	return null;
    }

	@Override
	public void update(double elapsedTime){
		super.update(elapsedTime);
		if(myAttackStrategy.getCanAttack() && !getAttackStrategy().getWeapons().isEmpty()){
			myAttackStrategy.getWeapons().get(myAttackStrategy.getWeaponIndex()).update(elapsedTime);
		}
	}
	@Override
	public void paint(Graphics2D pen) {
		//pen.rotate(getVelocity().getAngle());
		super.paint(pen);
		if(myAttackStrategy.getCanAttack() && !getAttackStrategy().getWeapons().isEmpty()){
			for(Projectile p : myAttackStrategy.getWeapons().get(myAttackStrategy.getWeaponIndex()).getProjectiles()) {
				p.paint(pen);               
			}
		}
	}



}

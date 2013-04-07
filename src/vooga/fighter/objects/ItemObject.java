package vooga.fighter.objects;

import java.awt.Dimension;

import vooga.fighter.util.Location;
import vooga.fighter.util.Pixmap;

public class ItemObject extends GameObject implements Damageable,Perishable, Spawnable, Wieldable{
	private static final int DEFAULT_HEALTH=10; 
	private int myHealth=DEFAULT_HEALTH; 
	public ItemObject(Pixmap image, Location center, Dimension size) {
		super(image, center, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAttackPower(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unequip(CharacterObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void equip(CharacterObject o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spawn(Location l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean timedOut() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stopTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTime(int amount) {
		// TODO Auto-generated method stub
		
	}

	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return myHealth<=0;
	}

}

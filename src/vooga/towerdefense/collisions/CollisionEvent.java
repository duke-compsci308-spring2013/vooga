package vooga.towerdefense.collisions;

import vooga.towerdefense.gameElements.GameElement;

public abstract class CollisionEvent {
	
	public abstract boolean hasCollided(GameElement object1, GameElement object2);
	
	
	

}

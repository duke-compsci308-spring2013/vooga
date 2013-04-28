package vooga.towerdefense.action.waveactions;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;

/**
 * Spawns a wave of units on an interval.
 * @author JLongley
 *
 */
public class WaveAction extends Action {

	private int myUnitsRemaining;
	private int myCooldown;
	private GameElementFactory myFactory;
	private int myClock;
	private GameMap myMap;

	public WaveAction(int numUnits, int cooldown, GameElementFactory factory,
			GameMap gameMap) {
		super();
		myUnitsRemaining = numUnits;
		myCooldown = cooldown;
		myFactory = factory;
		myClock = 0;
		myMap = gameMap;
	}

	@Override
	public void executeAction(double elapsedTime) {
		if (myUnitsRemaining == 0) {
			setEnabled(false);
		} else if (myClock > myCooldown) {
			spawnUnit();
			myClock = 0;

		}
		myClock += elapsedTime;
		
		if(myUnitsRemaining <= 0) {
			setEnabled(false);
		}
	}

	private void spawnUnit() {
		GameElement unit = myFactory.createElement(myMap.getSpawnLocation());
		//make a unit an enemy
		Attribute affiliation = unit.getAttributeManager().getAttribute(AttributeConstantsEnum.AFFILIATION.getStatusCode());
		affiliation.modifyValue(AttributeConstantsEnum.Enemy.getValue());
		
		myMap.addGameElement(unit);
		--myUnitsRemaining;
	}

}

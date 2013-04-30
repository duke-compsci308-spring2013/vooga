package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;

/**
 * Performs followup actions based on a random chance
 * 
 * @author Matthew Roy
 * 
 */
public class RandomChance extends Action {

	private Attribute myChance;

	public RandomChance(Attribute randomChance) {
		super();
		myChance = randomChance;
	}

	public void update(double elapsedTime) {
		if (Math.random() <= myChance.getValue()) {
			super.update(elapsedTime);
		}
	}

	/**
	 * Does nothing, since random chance just executes followup action on a
	 * random chance
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void executeAction(double elapsedTime) {
	}

}

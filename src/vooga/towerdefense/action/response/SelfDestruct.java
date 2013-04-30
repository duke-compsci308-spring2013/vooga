package vooga.towerdefense.action.response;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;

/**
 * Sets a timer on game element and causes it to self-destruct after a certain time.
 * @author XuRui
 *
 */
public class SelfDestruct extends Action {

	private GameMap myMap;
	private GameElement myElement;
	private double myClock;
	private double myLifespan;

	public SelfDestruct(GameMap map, GameElement element,
			Attribute lifespan) {
		super();
		myMap = map;
		myElement = element;
		myLifespan = lifespan.getValue();
		myClock = 0;
	}

	@Override
	public void executeAction(double elapsedTime) {
		myClock += elapsedTime;
		if (myClock >= myLifespan) {
			myMap.removeGameElement(myElement);
		}
	}
}

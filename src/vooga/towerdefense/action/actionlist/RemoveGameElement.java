package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;

/**
 * Removes a game element from the game map. Can remove target game element or
 * itself.
 * 
 * @author Matthew Roy
 * 
 */
public class RemoveGameElement extends Action {

	private GameMap myMap;
	private GameElement myElement;

	/**
     * 
     */
	public RemoveGameElement(GameMap map, GameElement elementToRemove) {
		super();
		myMap = map;
		myElement = elementToRemove;
	}

	public void executeAction(double elapsedTime) {
		execute();
	}

	public void execute() {
		myMap.removeGameElement(myElement);
	}
}

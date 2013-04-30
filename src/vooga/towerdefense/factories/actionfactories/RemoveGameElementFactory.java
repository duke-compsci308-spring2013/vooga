package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.RemoveGameElement;
import vooga.towerdefense.gameelements.GameElement;

/**
 * This is an action factory that builds RemoveElement actions.
 * 
 * @author Zhen Gou
 * 
 */
public class RemoveGameElementFactory extends ActionFactory {

	public RemoveGameElementFactory() {
		super();
	}

	@Override
	public Action buildAction(GameElement e) {
		System.out.println("remove game element action built");
		return new RemoveGameElement(getMap());
	}

}

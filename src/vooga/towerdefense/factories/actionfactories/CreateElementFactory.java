package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.CreateElement;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;
/**
 * factory that creats a create element action
 * @author Zhen Gou
 *
 */
public class CreateElementFactory extends ActionFactory{
	private String myFactoryName;
	public CreateElementFactory(
			@ActionAnnotation(name = "projectile", value = "name") String factory) {
		super();
		myFactoryName = factory;
	}

	@Override
	protected Action buildAction(GameElement e) {
		return new CreateElement(getMap(), e, e
				.getAttributeManager().getGameElementFactory(myFactoryName));
	}
	

}

package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.actionlist.RemoveGameElement;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;
import vooga.towerdefense.model.Player;

/**
 * Factory that creates a combination of deathly attacks.
 * 
 * @author Matthew Roy
 * 
 */
public class DeathPackageFactory extends ActionFactory {

	ActionFactory myDeath;
	ActionFactory myPlayerValue;
	RemoveGameElementFactory myRemoveElement;

	/**
	 * Default unit death USES: Health Attribute Worth Attribute Remove from map
	 */
	public DeathPackageFactory() {
		super();
	}

	public void initialize(GameMap map, Player player) {
		super.initialize(map, player);
		makeComboFactories();
		myDeath.initialize(map, player);
		myPlayerValue.initialize(map, player);
		myRemoveElement.initialize(map, player);
	}

	public void makeComboFactories() {
		myDeath = new OnDeathFactory();
		myRemoveElement = new RemoveGameElementFactory();
		myPlayerValue = new ModifyPlayerAttributeFactory(
				AttributeConstantsEnum.MONEY.getStatusCode(),
				AttributeConstantsEnum.MONEY.getStatusCode());
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	@Override
	protected Action buildAction(GameElement e) {
		Action death = myDeath.createAction(e);
		death.addFollowUpAction(myPlayerValue.createAction(e));
		RemoveGameElement delete = (RemoveGameElement) myRemoveElement.createAction(e);
		delete.addTarget(e);
		death.addFollowUpAction(delete);
		return death;
	}

}

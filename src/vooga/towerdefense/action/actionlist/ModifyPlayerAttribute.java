package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.model.Player;

/**
 * Modifies the player attribute value by applying another attribute value to
 * it. E.g. modifies player health by attacker damage level.
 * 
 * @author Matthew Roy
 * 
 */
public class ModifyPlayerAttribute extends Action {

	private Attribute myAppliedAttribute;
	private String myTargetAttribute;
	private Player myPlayer;

	public ModifyPlayerAttribute(Player player, Attribute attributeToApply,
			String targetAttributeName) {
		super();
		myTargetAttribute = targetAttributeName;
		myAppliedAttribute = attributeToApply;
		myPlayer = player;
	}

	/**
	 * 
	 * @param elapsedTime
	 */
	@Override
	public void executeAction(double elapsedTime) {
		Attribute toChange = myPlayer.getAttributeManager().getAttribute(
				myTargetAttribute);
		if (toChange != null && myAppliedAttribute != null) {
			System.out.println("NOT NULL");
			toChange.modifyValue(myAppliedAttribute.getValue());
		}
	}

}

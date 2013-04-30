package vooga.towerdefense.action.actionlist;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstantsEnum;
import vooga.towerdefense.model.Player;

/**
 * Action for changing player's score/life/money or any other stat that Player
 * has in a game If the Player class was changed, this needs to be changed.
 * 
 * @author Zhen Gou
 * 
 */
public class PlayerStatAction extends Action {
	private Player myPlayer;
	private Attribute myScoreChangeReference;
	private Attribute myLifeChangeReference;
	private Attribute myMoneyChangeReference;

	/**
	 * 
	 * @param player
	 * @param scoreChange
	 * @param lifeChange
	 * @param moneyChange
	 */
	public PlayerStatAction(Player player, Attribute scoreChange,
			Attribute lifeChange, Attribute moneyChange) {
		myPlayer = player;
		myScoreChangeReference = scoreChange;
		myLifeChangeReference = lifeChange;
		myMoneyChangeReference = moneyChange;

	}

	@Override
	public void executeAction(double elapsedTime) {
		myPlayer.getAttributeManager()
				.getAttribute(AttributeConstantsEnum.SCORE.getStatusCode())
				.modifyValue(myScoreChangeReference.getValue());
		myPlayer.getAttributeManager()
				.getAttribute(AttributeConstantsEnum.HEALTH.getStatusCode())
				.modifyValue(myLifeChangeReference.getValue());
		myPlayer.getAttributeManager()
				.getAttribute(AttributeConstantsEnum.MONEY.getStatusCode())
				.modifyValue(myMoneyChangeReference.getValue());

	}

}

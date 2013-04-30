package vooga.towerdefense.factories.actionfactories;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.attack.FindTargets;
import vooga.towerdefense.factories.ActionAnnotation;
import vooga.towerdefense.gameelements.GameElement;
import util.Location;

/**
 * This is an action factory that creates find target actions that give all of
 * their follow up actions the game elements inside their radius
 * 
 * @author Matthew Roy
 * 
 */
public class FindTargetsFactory extends ActionFactory {

	private String myRadiusId;

	/**
	 * 
	 * @param AttrRadiusToSearch
	 *            is the id of the attribute used for radius
	 */
	public FindTargetsFactory(
			@ActionAnnotation(name = "attack radius", value = "attribute") String AttrRadiusToSearch) {
		super();
		myRadiusId = AttrRadiusToSearch;
	}

	/**
	 * Creates a find targets action with follow up actions added.
	 * 
	 * @param e
	 * @return
	 */
	@Override
	protected Action buildAction(GameElement e) {
		Location searchCenter = e.getCenter();
		Action locateTargets = new FindTargets(getMap(), searchCenter, e
				.getAttributeManager().getAttribute(myRadiusId));
		return locateTargets;
	}

}
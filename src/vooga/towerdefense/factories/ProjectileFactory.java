package vooga.towerdefense.factories;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.MoveToDestination;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.gameElements.GameElement;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;

/**
 * will need to be changed dramatically, now it's quick dirty for testing
 * 
 */

public class ProjectileFactory extends GameElementFactory {
	private static final AttributeConstants ATTRIBUTES_CONSTANTS = new AttributeConstants();
	private static final int DEFAULT_WIDTH = 10;
	private static final Pixmap DEFAULT_IMAGE = new Pixmap("fireball.gif");
	private static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH,
			DEFAULT_WIDTH);
	private static final List<Action> DEFAULT_ACTIONS = new ArrayList<Action>();
	private static final AttributeManager DEFAULT_ATTRIBUTE_MANAGER = new AttributeManager();

	public GameElement createProjectile(GameElement initiator,
			GameElement target) {

		return new GameElement(DEFAULT_IMAGE, initiator.getCenter(),
				DEFAULT_SIZE, DEFAULT_ATTRIBUTE_MANAGER, DEFAULT_ACTIONS);

	}

	public GameElement createProjectile(GameElement initiator,
			Location targetLocation) {
		GameElement projectile = new GameElement(DEFAULT_IMAGE,
				initiator.getCenter(), DEFAULT_SIZE, DEFAULT_ATTRIBUTE_MANAGER);
		projectile.addActions(DEFAULT_ACTIONS);

		List<Action> actions = new ArrayList<Action>();
		actions.add(new MoveToDestination(projectile.getCenter(),
				targetLocation, initiator.getAttributeManager().getAttribute(
						AttributeConstants.MOVE_SPEED)));
		projectile.addActions(actions);
		return projectile;
	}

}

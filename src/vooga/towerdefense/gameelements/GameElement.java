package vooga.towerdefense.gameelements;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.AttributeManager;
import util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Sprite;

/**
 * Underlying game element object that all types of sprites in the game are
 * created from Defined by its attributes and actions
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * @author Zhen Gou
 */
public class GameElement extends Sprite {

	private AttributeManager myAttributeManager;
	private List<Action> myActions;

	/**
	 * 
	 * @param image
	 * @param center
	 * @param size
	 * @param attributes
	 * @param actions
	 */
	public GameElement(Pixmap image, Location center, Dimension size,
			AttributeManager attributes) {
		super(image, center, size);
		myAttributeManager = attributes;
		myActions = new ArrayList<Action>();
	}

	/**
	 * Updates all attributes and actions
	 * 
	 * @param elapsedTime
	 */
	public void update(double elapsedTime) {

		for (Action a : myActions) {
			a.update(elapsedTime);
		}
		myAttributeManager.update();
	}

	@Override
	public void paint(Graphics2D pen) {
		super.paint(pen);
		
		myAttributeManager.paintHealth(pen, getCenter(), getSize());
	}

	public void addAction(Action a) {
		myActions.add(a);
	}

	public void addActions(List<Action> actions) {
		myActions.addAll(actions);
	}

	public AttributeManager getAttributeManager() {
		return myAttributeManager;
	}

	public List<Action> getActions() {
		return myActions;
	}

	/**
	 * Returns all target tracking actions, bridge for passing target
	 * information from actions in parent element to elements it spawns.
	 * 
	 * @return
	 */
	public List<TargetedAction> getTargetedActions() {
		List<TargetedAction> actions = new ArrayList<TargetedAction>();
		for (Action a : actions) {
			if (a.isTargetTracking()) {
				actions.add((TargetedAction) a);
			}
		}
		return actions;
	}

}

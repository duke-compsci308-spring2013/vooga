package vooga.towerdefense.gameelements;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import util.Location;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Sprite;


/**
 * Underlying game element object that all types of sprites in the game are
 * Defined by its attributes and actions
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * @author Zhen Gou
 */
public class GameElement extends Sprite {

    private AttributeManager myAttributeManager;
    private List<Action> myActions;

    /**
     * Creates a game element
     * 
     * @param image
     * @param center
     * @param size
     * @param attributes
     * @param actions
     */
    public GameElement (Pixmap image, Location center, Dimension size,
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
    public void update (double elapsedTime) {

        for (Action a : myActions) {
            a.update(elapsedTime);
        }
        myAttributeManager.update();
    }

    /**
     * Pains the game element, and paints its health bar if it has one
     */
    @Override
    public void paint (Graphics2D pen) {
        super.paint(pen);
        myAttributeManager.paintHealth(pen, getCenter(), getSize());
    }

    /**
     * Adds an action to this game element
     * 
     * @param a
     */
    public void addAction (Action a) {
        myActions.add(a);
    }

    /**
     * Add actions to this game element
     * 
     * @param actions
     */
    public void addActions (List<Action> actions) {
        myActions.addAll(actions);
    }

    /**
     * Returns the attribute manager of this game element
     * 
     * @return
     */
    public AttributeManager getAttributeManager () {
        return myAttributeManager;
    }

    /**
     * Returns the actions of this game element
     * 
     * @return
     */
    public List<Action> getActions () {
        return myActions;
    }

    /**
     * Returns all target tracking actions, bridge for passing target
     * information from actions in parent element to elements it spawns.
     * 
     * @return
     */
    public List<TargetedAction> getTargetedActions () {
        List<TargetedAction> actions = new ArrayList<TargetedAction>();
        for (Action a : myActions) {
            if (a.isTargetTracking()) {
                actions.add((TargetedAction) a);
            }
        }
        return actions;
    }
}

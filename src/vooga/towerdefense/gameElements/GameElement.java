package vooga.towerdefense.gameElements;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Sprite;


/**
 * Blank tower that holds its attributes and actions that define it
 * 
 * @author Matthew Roy
 * @author Xu Rui
 * @author Zhen Gou
 */
public class GameElement extends Sprite {

    private AttributeManager myAttributeManager;
    private List<Action> myActions;

    public GameElement (Pixmap image,
                        Location center,
                        Dimension size,
                        AttributeManager attributes,
                        List<Action> actions) {
        super(image, center, size);
        myAttributeManager = attributes;
        myActions = actions;
    }

    public GameElement (Pixmap image, Location center, Dimension size, List<Action> actions) {
        this(image, center, size, new AttributeManager(), actions);
    }

    public GameElement (Pixmap image, Location center, Dimension size, AttributeManager am) {
        this(image, center, size, am, new ArrayList<Action>());
    }

    public GameElement (Pixmap image, Location center, Dimension size) {
        this(image, center, size, new AttributeManager(), new ArrayList<Action>());
    }

    public void update (double elapsedTime) {

        for (Action a : myActions) {
            a.update(elapsedTime);
        }
    }

    public void addActions (List<Action> actions) {
        myActions.addAll(actions);
    }

    public AttributeManager getAttributeManager () {
        return myAttributeManager;
    }

    public List<Action> getActions () {
        return myActions;
    }

}

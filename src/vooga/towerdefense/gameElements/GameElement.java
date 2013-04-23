package vooga.towerdefense.gameElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import vooga.towerdefense.action.Action;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.attributes.AttributeConstants;
import vooga.towerdefense.attributes.AttributeManager;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.util.Sprite;


/**
 * Underlying game element object that all types of sprites in the game are created from
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
     * 
     * @param image
     * @param center
     * @param size
     * @param attributes
     * @param actions
     */
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

    @Override
    public void paint (Graphics2D pen) {
        super.paint(pen);

        // FIXME: Hardcoded healthbars 
        Attribute health = getAttributeManager().getAttribute(AttributeConstants.HEALTH);
        if (health != null) {
            pen.setColor(Color.red);
            pen.fillRect((int) this.getX(), (int) this.getY() -
                                            (int) this.getHeight() /
                                            2, (int) (this.getWidth() * (health
                    .getValue() / health.getOriginalValue())), (int) this.getHeight() / 10);
        }
    }

    public void addAction (Action a) {
        myActions.add(a);
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

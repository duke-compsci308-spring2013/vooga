package vooga.towerdefense.gameElements;

import java.awt.Graphics2D;
import vooga.towerdefense.attributes.AttributeConstants;


/**
 * this is a state manager that we pre-made for the designer, for fast prototyping.
 * to do more customized stuff, designer can always subclass StateManager to create his own
 * 
 * @author Zhen Gou
 * 
 */

public class BasicStateManager extends StateManager {
    private static final AttributeConstants myAttributeConstants = new AttributeConstants();
    private final State myNormalState;
    private final State myBuffedState;
    private State myCurrentState;

    public BasicStateManager (GameElement unit, State normal, State buffed) {
        super(unit);
        myNormalState = normal;
        myBuffedState = buffed;
        myCurrentState = myNormalState;

    }

    /**
     * core functions of state manager, decides state switch logic and paint
     */

    @Override
    public void updateAndPaint (Graphics2D pen) {
        myCurrentState = myNormalState;
        if (getGameElement().getAttributeManager().getAttribute(myAttributeConstants.ARMOR).isChanged()) {
            myCurrentState = myBuffedState;
        }

        paint(pen);

    }

    private void paint (Graphics2D pen) {
        myCurrentState.paint(pen, getGameElement());

    }

}

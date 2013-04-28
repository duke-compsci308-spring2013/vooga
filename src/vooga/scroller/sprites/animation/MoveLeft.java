package vooga.scroller.sprites.animation;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.Pixmap;

/**
 * Animation state that represents moving left. When the sprite stops, this animation
 * state will display the view of the sprite facing left.
 * @author Scott Valentine
 *
 */
public class MoveLeft extends AnimationState<Sprite> {

    private Pixmap myStand;

    /**
     * Creates a new MoveLeft animation state using the pictures
     * that represent moving left and standing left.
     * 
     * @param move is the image that represents moving left.
     * @param stand is the image that represents standing left.
     */
    public MoveLeft (Pixmap stand) {
        super(stand);
        myStand = stand;
    }

    @Override
    public boolean validAnimation (Sprite unit) {
        if(unit.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION).getMagnitude() > 0){            
            unit.getView().setDefaultView(myStand);
            return true;
        }
        return false;
    }
}



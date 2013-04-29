package vooga.scroller.sprites.animation.state_movement;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;

public class MoveUpState extends SpriteMovementState {

    public static final int STATE_ID = -4;

    
    private ISpriteView myStandView;

    public MoveUpState (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, Sprite.UP_DIRECTION, speed);
        myStandView = stand;
    }
    
    @Override
    public void deactivate() {
        super.deactivate();
        getUnit().setView(myStandView);
    }

}

package vooga.scroller.sprites.animation.state_movement;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;

public class MoveRightState extends SpriteMovementState {
    
    public static final int STATE_ID = -5;

    
    private ISpriteView myStandView;

    public MoveRightState (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, Sprite.RIGHT_DIRECTION, speed);
        myStandView = stand;
    }
    
    @Override
    public void deactivate() {
        super.deactivate();
        getUnit().setView(myStandView);
    }
}

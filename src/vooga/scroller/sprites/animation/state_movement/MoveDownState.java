package vooga.scroller.sprites.animation.state_movement;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;

public class MoveDownState extends SpriteMovementState {

    public static final int STATE_ID = -2;
    
    private ISpriteView myStandView;

    public MoveDownState (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, Sprite.DOWN_DIRECTION, speed);
        myStandView = stand;
    }
    
    @Override
    public void deactivate() {
        super.deactivate();
        getUnit().setView(myStandView);
    }

}

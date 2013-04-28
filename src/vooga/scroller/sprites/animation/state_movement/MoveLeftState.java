package vooga.scroller.sprites.animation.state_movement;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;

public class MoveLeftState extends SpriteMovementState{

    public static final int STATE_ID = -3;

    
    private ISpriteView myStandView;
    
    public MoveLeftState (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, Sprite.LEFT_DIRECTION, speed);
        myStandView = stand;
    }

    @Override
    public void deactivate() {
        super.deactivate();
        getUnit().setView(myStandView);
    }
    
}

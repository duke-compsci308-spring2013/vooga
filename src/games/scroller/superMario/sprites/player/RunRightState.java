package games.scroller.superMario.sprites.player;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.state_movement.MoveRightState;
import vooga.scroller.util.ISpriteView;


public class RunRightState extends MoveRightState {

    public static final int STATE_ID = -7;

    public RunRightState (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, stand, speed);
    }

    @Override
    public int getID () {
        return STATE_ID;
    }

}

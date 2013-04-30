package games.scroller.superMario.sprites.player.states;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.util.ISpriteView;


public class RunLeftState extends MoveLeftState {

    public static final int STATE_ID = -6;

    public RunLeftState (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, stand, speed);
    }

    @Override
    public int getID () {
        return STATE_ID;
    }
}

package games.scroller.mr_fish.sprites.player.states;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;


public class TiltDown extends Tilt {

    public static final int STATE_ID = 6;

    public TiltDown (Sprite unit, double rateOfRotation, double speed) {
        super(unit, rateOfRotation, Sprite.DOWN_DIRECTION, speed);
        // TODO Auto-generated constructor stub
    }


}

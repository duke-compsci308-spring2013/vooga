package games.scroller.mr_fish.sprites.player.states;

import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.state.SpriteState;


public class TiltUp extends Tilt {

    public static final int STATE_ID = 5;

    
    public TiltUp (Sprite unit, double rateOfRotation, double speed) {
        super(unit, -1*rateOfRotation, Sprite.UP_DIRECTION, speed);
        // TODO Auto-generated constructor stub
    }



}

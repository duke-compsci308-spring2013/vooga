package vooga.scroller.sprites.animation.movement;

import vooga.scroller.kirbyGame.spritesDefinitions.players.Kirby;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.ISpriteView;

public class MoveLeft extends SpriteMovement{

    public static final int STATE_ID = -3;

    
    private ISpriteView myStandView;
    
    public MoveLeft (Sprite sprite, ISpriteView move, ISpriteView stand, double speed) {
        super(sprite, move, Sprite.LEFT_DIRECTION, speed);
        myStandView = stand;
    }

    @Override
    public void deactivate() {
        super.deactivate();
        getUnit().setView(myStandView);
    }
    
    @Override
    public void activate() {
        super.activate();
        Kirby myKirby = (Kirby) getUnit();
        myKirby.setStateID(MoveLeft.STATE_ID);
    }
    
}

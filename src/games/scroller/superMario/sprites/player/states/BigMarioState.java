package games.scroller.superMario.sprites.player.states;

import games.scroller.superMario.sprites.SuperMarioLib;
import games.scroller.superMario.sprites.player.Mario;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.Animation;
import vooga.scroller.sprites.animation.state_movement.MoveDownState;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.sprites.animation.state_movement.MoveRightState;
import vooga.scroller.sprites.animation.state_movement.SpriteMovementState;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;

public class BigMarioState extends MarioSpriteState {

    private static final Pixmap BIG_MOVE_LEFT = SuperMarioLib.makePixmap("big_walk_left.gif");
    private static final Pixmap BIG_STAND_LEFT = SuperMarioLib.makePixmap("big_stand_left.png");
    private static final Pixmap BIG_MOVE_RIGHT = SuperMarioLib.makePixmap("big_walk_right.gif");

    private static final Pixmap BIG_MOVE_DOWN = SuperMarioLib.makePixmap("big_duck_right.png");
//    private static final Pixmap BIG_MOVE_RIGHT = SuperMarioLib.makePixmap("big_walk_right.gif");
    private static final double SPEED = 100;

    private static final ISpriteView BIG_STAND_RIGHT = SuperMarioLib.makePixmap("big_stand_right.png");

    public BigMarioState (Mario unit) {
        super(unit);
        
        Animation<Sprite> bigAnimation = new Animation<Sprite>();
        SpriteMovementState bigLeft = new MoveLeftState(unit, BIG_MOVE_LEFT,
                                                        BIG_STAND_LEFT, SPEED);
        SpriteMovementState bigRight = new MoveRightState(unit, BIG_MOVE_RIGHT,
                                                          BIG_STAND_RIGHT, SPEED);
        SpriteMovementState bigDown = new MoveDownState(unit, BIG_MOVE_DOWN,
                                                        BIG_STAND_RIGHT, SPEED);
        bigAnimation.addAnimationState(bigLeft);
        bigAnimation.addAnimationState(bigRight);
        bigAnimation.addAnimationState(bigDown);
        
        setAnimation(bigAnimation);
    }

}

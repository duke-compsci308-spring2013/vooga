package games.scroller.superMario.sprites.player.states;

import games.scroller.superMario.sprites.SuperMarioLib;
import games.scroller.superMario.sprites.player.Mario;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.Animation;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.sprites.animation.state_movement.MoveRightState;
import vooga.scroller.sprites.animation.state_movement.SpriteMovementState;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;

public class BaseMarioState extends MarioSpriteState {
    

    private static final Pixmap MOVE_LEFT = SuperMarioLib.makePixmap("mario_walk_left.gif"); 
    private static final Pixmap STAND_LEFT = SuperMarioLib.makePixmap("mario_stand_left.png");
    private static final Pixmap MOVE_RIGHT = SuperMarioLib.makePixmap("mario_walk_right.gif");
    private static final ISpriteView STAND_RIGHT = SuperMarioLib.makePixmap("mario_stand_right.png");
    private static final double SPEED = 100;

    public BaseMarioState (Mario unit) {
        super(unit);
        Animation<Sprite> baseAnimation = new Animation<Sprite>();
        
        SpriteMovementState baseLeft = new MoveLeftState(unit, MOVE_LEFT,
                                                        STAND_LEFT, SPEED);
        SpriteMovementState baseRight = new MoveRightState(unit, MOVE_RIGHT,
                                                          STAND_RIGHT, SPEED);
        baseAnimation.addAnimationState(baseLeft);
        baseAnimation.addAnimationState(baseRight);
        setAnimation(baseAnimation);
    }

}

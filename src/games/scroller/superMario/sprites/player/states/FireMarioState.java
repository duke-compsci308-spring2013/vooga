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

public class FireMarioState extends MarioSpriteState {
    

    private static final Pixmap FIRE_MOVE_LEFT = SuperMarioLib.makePixmap("fire_walk_left.gif");
    private static final Pixmap FIRE_STAND_LEFT = SuperMarioLib.makePixmap("fire_stand_left.png");
    private static final ISpriteView FIRE_STAND_RIGHT = SuperMarioLib.makePixmap("fire_stand_right.png");
    private static final Pixmap FIRE_MOVE_RIGHT = SuperMarioLib.makePixmap("fire_walk_right.gif");
    private static final double SPEED = 100;

    public FireMarioState (Mario unit) {
        super(unit);

        Animation<Sprite> fireAnimation = new Animation<Sprite>();
        
        SpriteMovementState fireLeft = new MoveLeftState(unit, FIRE_MOVE_LEFT,
                                                        FIRE_STAND_LEFT, SPEED);
        SpriteMovementState fireRight = new MoveRightState(unit, FIRE_MOVE_RIGHT,
                                                          FIRE_STAND_RIGHT, SPEED);
        
        fireAnimation.addAnimationState(fireLeft);
        fireAnimation.addAnimationState(fireRight);
        setAnimation(fireAnimation);
    }

}

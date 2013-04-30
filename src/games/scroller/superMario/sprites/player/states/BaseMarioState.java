package games.scroller.superMario.sprites.player.states;

import games.scroller.superMario.sprites.SuperMarioLib;
import games.scroller.superMario.sprites.player.Mario;
import games.scroller.superMario.sprites.player.RunRightState;
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
    private static final double SPEED = 200;
    private static final Pixmap RUN_LEFT = SuperMarioLib.makePixmap("mario_run_left.gif");
    private static final ISpriteView RUN_RIGHT = SuperMarioLib.makePixmap("mario_run_right.gif");
    private static final double RUN_SPEED = 300;

    public BaseMarioState (Mario unit) {
        super(unit);
        Animation<Sprite> baseAnimation = new Animation<Sprite>();
        
        SpriteMovementState baseLeft = new MoveLeftState(unit, MOVE_LEFT,
                                                        STAND_LEFT, SPEED);
        SpriteMovementState baseRight = new MoveRightState(unit, MOVE_RIGHT,
                                                          STAND_RIGHT, SPEED);
        SpriteMovementState baseRunLeft = new RunLeftState(unit, RUN_LEFT,
                                                         STAND_LEFT, RUN_SPEED);
         SpriteMovementState baseRunRight = new RunRightState(unit, RUN_RIGHT,
                                                           STAND_RIGHT, RUN_SPEED);
         
        baseAnimation.addAnimationState(baseLeft);
        baseAnimation.addAnimationState(baseRight);
        baseAnimation.addAnimationState(baseRunLeft);
        baseAnimation.addAnimationState(baseRunRight);
        setAnimation(baseAnimation);
    }

}

package vooga.scroller.marioGame.spritesDefinitions.players;

import java.awt.Dimension;
import util.Vector;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_management.IInputListener;
import vooga.scroller.marioGame.spritesDefinitions.MarioLib;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.sprites.animation.state_movement.MoveRightState;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.physics.Force;
import vooga.scroller.util.physics.Gravity;
import vooga.scroller.view.GameView;


@InputClassTarget
public class Mario extends Player implements IInputListener {

    private static final String CONTROLS_FILE_PATH =
            "vooga/scroller/marioGame/controls/MarioMapping";

    private static final int MAX_JUMPS = 1;
    private static final Pixmap DEFAULT_IMAGE = MarioLib.makePixmap("mario_stand_right.gif");
    // private static final int DEATH_PENALTY = 1000;

    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 100);

    // private static final double MOVE_MAGNITUDE = 10;

    private static final double MAX_SPEED = 300;

    private static final ISpriteView MOVE_LEFT_VIEW = MarioLib.makePixmap("mario_move_left.gif");

    private static final ISpriteView STAND_LEFT_VIEW = MarioLib.makePixmap("mario_stand_left.gif");

    private static final double SPEED = 100;

    private static final ISpriteView MOVE_RIGHT_VIEW = MarioLib.makePixmap("mario_move_right.gif");

    private static final ISpriteView STAND_RIGHT_VIEW = MarioLib.makePixmap("mario_stand_right.gif");

    private int myJumpCount;
    private Force myGravity;

    public Mario (Dimension size, GameView gameView, ScrollingManager sm) {
        super(DEFAULT_IMAGE, size, gameView, sm, new Integer(1), new Integer(1));
        // MarioLib.addLeftRightAnimationToPlayer(this, "mario.gif");
        myJumpCount = 0;
        myGravity = new Gravity(this);

        initializePossibleStates();

    }

    /**
     * Initialize all possible states, including movement for mario.
     */
    @Override
    protected void initializePossibleStates () {
        this.addPossibleState(MoveLeftState.STATE_ID, new MoveLeftState(this, MOVE_LEFT_VIEW,
                                                                        STAND_LEFT_VIEW, SPEED));
        this.addPossibleState(MoveRightState.STATE_ID, new MoveRightState(this, MOVE_RIGHT_VIEW,
                                                                          STAND_RIGHT_VIEW, SPEED));
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myGravity.apply();

        if (myJumpCount == MAX_JUMPS &&
            this.getVelocity().getComponentVector(Sprite.UP_DIRECTION).getMagnitude() < .5) {
            myJumpCount = 0;
        }

        super.update(elapsedTime, bounds);
        checkSpeed();
    }

    private void checkSpeed () {
        double speed = this.getVelocity().getMagnitude();
        if (speed > MAX_SPEED) {
            double angle = this.getVelocity().getDirection();
            this.setVelocity(angle, MAX_SPEED);
        }
    }

    @Override
    public void handleDeath (Level level) {

    }

    public Player getPlayer () {
        return this;
    }

    @Override
    public String getInputFilePath () {
        return CONTROLS_FILE_PATH;
    }

    @InputMethodTarget(name = "leftstart")
    public void walkLeft () {
        System.out.println("start");
        this.activateState(MoveLeftState.STATE_ID);
    }

    @InputMethodTarget(name = "leftend")
    public void stopLeft () {
        System.out.println("end");
        this.deactivateState(MoveLeftState.STATE_ID);
    }

    @InputMethodTarget(name = "rightstart")
    public void walkRight () {
        this.activateState(MoveRightState.STATE_ID);
    }

    @InputMethodTarget(name = "rightend")
    public void stopRight () {
        this.deactivateState(MoveRightState.STATE_ID);
    }

    @InputMethodTarget(name = "jump")
    public void jump () {
        if (this.getVelocity().getComponentVector(Sprite.UP_DIRECTION).getMagnitude() < .5 &&
            this.getVelocity().getComponentVector(Sprite.DOWN_DIRECTION).getMagnitude() < .5 &&
            myJumpCount < MAX_JUMPS) {
            addVector(JUMP_VELOCITY);
            myJumpCount += 1;
        }
    }

    @Override
    public void incrementScore (int increment) {
        // TODO Auto-generated method stub

    }

    @Override
    public Force[] setForces () {
        // TODO Auto-generated method stub
        return null;
    }
}

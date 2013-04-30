package games.scroller.superMario.sprites;

import games.scroller.superMario.MarioGravity;
import java.awt.Dimension;
import util.Vector;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_management.IInputListener;
import vooga.scroller.model.Model;
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
            // "games/scroller/superMario/controls/MarioMapping";

            "games/scroller/marioGame/controls/MarioMapping";

    private static final Pixmap DEFAULT_IMAGE = SuperMarioLib.makePixmap("mario_stand_right.png");
    // private static final int DEATH_PENALTY = 1000;

    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 100);

    // private static final double MOVE_MAGNITUDE = 10;

    private static final double MAX_SPEED = 600;

    private static final ISpriteView MOVE_LEFT = SuperMarioLib.makePixmap("mario_walk_left.gif");

    private static final ISpriteView STAND_LEFT = SuperMarioLib.makePixmap("mario_stand_left.png");

    private static final double SPEED = 100;

    private static final ISpriteView MOVE_RIGHT = SuperMarioLib.makePixmap("mario_walk_right.gif");

    private static final ISpriteView STAND_RIGHT = SuperMarioLib
            .makePixmap("mario_stand_right.png");

    private static final Dimension DEFAULT_SIZE = new Dimension(32, 32);

    private static final int DEFAULT_HEALTH = new Integer(1);

    private static final int DEFAULT_DAMAGE = new Integer(1);

    private Force myGravity;

    public Mario () {
        super(DEFAULT_IMAGE, DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);

        myGravity = new MarioGravity(this);
    }

    public Mario (Model m) {
        this();
        setModel(m);
    }

    public Mario (GameView gameView, ScrollingManager sm) {
        super(DEFAULT_IMAGE, DEFAULT_SIZE, gameView, sm, DEFAULT_HEALTH, DEFAULT_DAMAGE);
        // MarioLib.addLeftRightAnimationToPlayer(this, "mario.gif");

        myGravity = new MarioGravity(this);
        initializePossibleStates();

    }

    /**
     * Initialize all possible states, including movement for mario.
     */
    @Override
    protected void initializePossibleStates () {
        this.addPossibleState(MoveLeftState.STATE_ID, new MoveLeftState(this, MOVE_LEFT,
                                                                        STAND_LEFT, SPEED));
        this.addPossibleState(MoveRightState.STATE_ID, new MoveRightState(this, MOVE_RIGHT,
                                                                          STAND_RIGHT, SPEED));
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myGravity.apply();

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
            this.getVelocity().getComponentVector(Sprite.DOWN_DIRECTION).getMagnitude() < .5) {
            addVector(JUMP_VELOCITY);
        }
    }

    public void incrementScore (int increment) {
        // TODO Auto-generated method stub

    }
}

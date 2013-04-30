package games.scroller.stickmansam;

import games.scroller.stickmansam.StickmanSpriteLibrary.Bullet;
import java.awt.Dimension;
import util.Vector;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.level_editor.Level;
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

/**
 * Stickman player
 * @author David Winegar
 *
 */
@InputClassTarget
public class StickmanPlayer extends Player {

    private static final int GRAVITY_CONSTANT = -15;
    private static final Pixmap STICKMAN_VIEW = StickmanSpriteLibrary.makePixmap("stickman.png");
    private static final Dimension SIZE = new Dimension(32, 45);
    private static final int HEALTH = 100;
    private static final int DAMAGE = 20;
    private static final String INPUT_LOCATION = "games/scroller/stickmansam/keymap";
    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 149);
    private static final double MAX_JUMP_VELOCITY = 1;
    private static final int SPEED = 100;
    private static final int MAX_JUMPS = 1;
    private static final double MAX_SPEED = 300;
    private static final int LEFT = 180;
    private static final int RIGHT = 0;
    private static final int TIME_BETWEEN_BULLETS = 40;

    private static final ISpriteView MOVE_LEFT = StickmanSpriteLibrary
            .makePixmap("stickmanleft.png");
    private static final ISpriteView STAND_LEFT = StickmanSpriteLibrary
            .makePixmap("stickmanleft.png");
    private static final ISpriteView MOVE_RIGHT = StickmanSpriteLibrary.makePixmap("stickman.png");
    private static final ISpriteView STAND_RIGHT = StickmanSpriteLibrary.makePixmap("stickman.png");

    private int myJumpCount;
    private Force myGravity;
    private boolean myGravityEnabled = true;
    private int myDirection = RIGHT;
    private int myBulletTimer = 0;

    /**
     * Instantiate player
     * @param gameView
     * @param sm
     */
    public StickmanPlayer (GameView gameView,
                           ScrollingManager sm) {
        super(STICKMAN_VIEW, SIZE, gameView, sm, HEALTH, DAMAGE);

        myJumpCount = 0;
        myGravity = new Gravity(this, GRAVITY_CONSTANT);

        addPossibleState(MoveLeftState.STATE_ID, new MoveLeftState(this, MOVE_LEFT,
                                                                   STAND_LEFT, SPEED));
        addPossibleState(MoveRightState.STATE_ID, new MoveRightState(this, MOVE_RIGHT,
                                                                     STAND_RIGHT, SPEED));
    }

    @Override
    public String getInputFilePath () {
        return INPUT_LOCATION;
    }

    @Override
    public void handleDeath (Level level) {
        getLevel().getDoor().goToNextLevel();
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        if (myGravityEnabled) {
            myGravity.apply();
        }
        if (myBulletTimer > 0) {
            myBulletTimer--;
        }

        if (myJumpCount == MAX_JUMPS &&
            getVelocity().getComponentVector(Sprite.UP_DIRECTION).getMagnitude() < .5) {
            myJumpCount = 0;
        }

        super.update(elapsedTime, bounds);
        checkSpeed();
    }

    /**
     * Sets whether gravity is enabled not
     * 
     * @param gravity boolean
     */
    public void setGravity (boolean gravity) {
        myGravityEnabled = gravity;
    }

    private void checkSpeed () {
        double speed = getVelocity().getMagnitude();
        if (speed > MAX_SPEED) {
            double angle = getVelocity().getDirection();
            this.setVelocity(angle, MAX_SPEED);
        }
    }

    @InputMethodTarget(name = "leftstart")
    public void walkLeft () {
        myDirection = LEFT;
        activateState(MoveLeftState.STATE_ID);
    }

    @InputMethodTarget(name = "leftend")
    public void stopLeft () {
        deactivateState(MoveLeftState.STATE_ID);
    }

    @InputMethodTarget(name = "rightstart")
    public void walkRight () {
        myDirection = RIGHT;
        activateState(MoveRightState.STATE_ID);
    }

    @InputMethodTarget(name = "rightend")
    public void stopRight () {
        deactivateState(MoveRightState.STATE_ID);
    }

    @InputMethodTarget(name = "jump")
    public void jump () {
        if (getVelocity().getComponentVector(Sprite.UP_DIRECTION).getMagnitude() < MAX_JUMP_VELOCITY &&
            getVelocity().getComponentVector(Sprite.DOWN_DIRECTION).getMagnitude() < MAX_JUMP_VELOCITY) {
            addVector(JUMP_VELOCITY);
        }
    }

    @InputMethodTarget(name = "fire")
    public void fire () {
        if (myBulletTimer == 0) {
            getLevel().addSprite(new Bullet(myDirection));
            myBulletTimer = TIME_BETWEEN_BULLETS;
        }
    }

}

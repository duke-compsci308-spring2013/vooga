package games.scroller.stickmansam;

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
import games.scroller.stickmansam.StickmanSpriteLibrary.Bullet;

@InputClassTarget
public class StickmanPlayer extends Player {

    private static final Pixmap STICKMAN_VIEW = StickmanSpriteLibrary.makePixmap("stickman.png");
    private static final Dimension SIZE = new Dimension (32, 45);
    private static final int HEALTH = 100;
    private static final int DAMAGE = 20;    
    private static final String INPUT_LOCATION = "games/scroller/stickmansam/keymap";
    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 99);
    private static final double MAX_JUMP_VELOCITY = 0.5;
    private static final int SPEED = 100;
    private static final int MAX_JUMPS = 1;
    private static final double MAX_SPEED = 300;
    
    private static final ISpriteView MOVE_LEFT = StickmanSpriteLibrary.makePixmap("stickmanleft.png");
    private static final ISpriteView STAND_LEFT = StickmanSpriteLibrary.makePixmap("stickmanleft.png");
    private static final ISpriteView MOVE_RIGHT = StickmanSpriteLibrary.makePixmap("stickman.png");
    private static final ISpriteView STAND_RIGHT = StickmanSpriteLibrary.makePixmap("stickman.png");


    
    private int myJumpCount;
    private Force myGravity;

   
   public StickmanPlayer (GameView gameView,
                           ScrollingManager sm) {
        super(STICKMAN_VIEW, SIZE, gameView, sm, HEALTH, DAMAGE);
        
        myJumpCount = 0;
        myGravity = new Gravity(this);
        
        this.addPossibleState(MoveLeftState.STATE_ID, new MoveLeftState(this, MOVE_LEFT,
                                                                        STAND_LEFT, SPEED));
        this.addPossibleState(MoveRightState.STATE_ID, new MoveRightState(this, MOVE_RIGHT,
                                                                          STAND_RIGHT, SPEED));
    }

    @Override
    public String getInputFilePath () {
        return INPUT_LOCATION;
    }

    @Override
    public void handleDeath (Level level) {
        this.getLevel().getDoor().goToNextLevel();
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
    
    @InputMethodTarget(name = "leftstart")
    public void walkLeft () {
        this.activateState(MoveLeftState.STATE_ID);
    }

    @InputMethodTarget(name = "leftend")
    public void stopLeft () {
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
        if (this.getVelocity().getComponentVector(Sprite.UP_DIRECTION).getMagnitude() < MAX_JUMP_VELOCITY &&
            this.getVelocity().getComponentVector(Sprite.DOWN_DIRECTION).getMagnitude() < MAX_JUMP_VELOCITY) {
            addVector(JUMP_VELOCITY);
        }
    }
    
    @InputMethodTarget(name = "fire")
    public void fire () {
        getLevel().addSprite(new Bullet());
    }

}

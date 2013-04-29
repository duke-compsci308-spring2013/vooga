package games.scroller.stickmansam;

import java.awt.Dimension;
import util.Vector;
import util.input.InputMethodTarget;
import vooga.scroller.level_editor.Level;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.sprites.animation.state_movement.MoveRightState;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Pixmap;
import vooga.scroller.view.GameView;

public class StickmanPlayer extends Player {

    private static final Pixmap STICKMAN_VIEW = StickmanSpriteLibrary.makePixmap("e.png");
    private static final Dimension SIZE = new Dimension (50, 30);
    private static final int HEALTH = 100;
    private static final int DAMAGE = 1;    
    private static final String INPUT_LOCATION = "/games/scroller/stickmansam/keymap";
    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 100);
    private static final double MAX_JUMP_VELOCITY = 0.5;
    
   public StickmanPlayer (GameView gameView,
                           ScrollingManager sm) {
        super(STICKMAN_VIEW, SIZE, gameView, sm, HEALTH, DAMAGE);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getInputFilePath () {
        return INPUT_LOCATION;
    }

    @Override
    public void handleDeath (Level level) {
        // TODO Auto-generated method stub

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

    }

}

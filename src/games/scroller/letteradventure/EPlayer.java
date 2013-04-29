package games.scroller.letteradventure;

import games.scroller.letteradventure.SpriteLibrary.Projectile;
import java.awt.Dimension;
import util.Vector;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_management.IInputListener;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.state_movement.MoveLeftState;
import vooga.scroller.sprites.animation.state_movement.MoveRightState;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.statistics.Statistic;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.physics.Force;
import vooga.scroller.util.physics.Gravity;
import vooga.scroller.view.GameView;

/**
 * The main player in LetterAdventure.
 * 
 * @author Ellango, David Liu
 *
 */
@InputClassTarget
public class EPlayer extends Player implements IInputListener{

    
    private static final Pixmap E_VIEW = SpriteLibrary.makePixmap(SpriteLibrary.IMAGES_DIRECTORY , "e.png");
    private static final int HEALTH = 1;
    private static final int DAMAGE = 0;    
    private static final int SPEED = 300;
    private static final String INPUT_LOCATION = "games/scroller/letteradventure/LetterMapping";
    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 100);
    private static final double MAX_JUMP_VELOCITY = 0.5;
    private static final int INITIAL_SCORE = 10;
    private static final int SCORE_LOSS = 1;

    private Force myGravity;
    private Statistic myScore;
    
    public EPlayer(GameView gameView, ScrollingManager sm) {
        super(E_VIEW, SpriteLibrary.DEFAULT_SIZE, gameView, sm, HEALTH, DAMAGE);
        myGravity = new Gravity(this);
        intializeStates();
        myScore = new Score(INITIAL_SCORE);
        
    }
    
    /**
     * Initialize all possible states, including movement for mario.
     */
    private void intializeStates () {
        this.addPossibleState(MoveLeftState.STATE_ID, new MoveLeftState(this, E_VIEW,
                                                                        E_VIEW, SPEED));
        this.addPossibleState(MoveRightState.STATE_ID, new MoveRightState(this, E_VIEW,
                                                                          E_VIEW, SPEED));
    }
    
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myGravity.apply();
        super.update(elapsedTime, bounds);
    }

    @Override
    public String getInputFilePath () {
        return INPUT_LOCATION;
    }

    /**
     * Score is lost for every time player dies.
     */
    @Override
    public void handleDeath (Level level) {
        this.setCenter(level.getStartPoint().getX(), level.getStartPoint().getY());
        this.setHealth(HEALTH);
        myScore.removeValue(SCORE_LOSS);
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
        this.getLevel().addSprite(new Projectile());
    }
    
//    /**
//     * Returns the score of the player after the end of the game.
//     * @return
//     */
//    public int getScore() {
//        return myScore;
//    }

}

package vooga.scroller.kirbyGame.spritesDefinitions.players;


import java.awt.Dimension;
import util.Location;
import util.Vector;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.ConsumeState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.CutterAttackLeftState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.FloatLeftState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.FloatRightState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.InhaleLeftState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.InhaleRightState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.KirbyCutterWalkLeftState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.KirbyCutterWalkRightState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.WalkLeftFullState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.WalkRightFullState;

import vooga.scroller.level_editor.Level;
import vooga.scroller.level_management.IInputListener;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.movement.MoveLeft;
import vooga.scroller.sprites.animation.movement.MoveRight;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.physics.Force;
import vooga.scroller.util.physics.Gravity;
import vooga.scroller.view.GameView;

@InputClassTarget
public class Kirby extends Player implements IInputListener{


    private static final String CONTROLS_FILE_PATH = "vooga/scroller/kirbyGame/controls/KirbyMapping";


    private static final int MAX_JUMPS = 2;
    private static final Pixmap DEFAULT_IMAGE = KirbyLib.makePixmap("kw3.png");
    //    private static final int DEATH_PENALTY = 1000;


    private static final Vector JUMP_VELOCITY = new Vector(Sprite.UP_DIRECTION, 20);


    //private static final double MOVE_MAGNITUDE = 10;


    private static final double MAX_SPEED = 300;


    private static final ISpriteView MOVE_LEFT = KirbyLib.makePixmap("kirbyrunL.gif");
    private static final ISpriteView STAND_LEFT = KirbyLib.makePixmap("kw3l.png");
    private static final ISpriteView MOVE_RIGHT = KirbyLib.makePixmap("kirbyrunR.gif");
    private static final ISpriteView STAND_RIGHT = KirbyLib.makePixmap("kw3.png");


    private static final double SPEED = 100;

    private static String myCurrentState; 
    private static String LASER_ENEMY = "laserEnemy";
    private static String CUTTER_ENEMY = "cutterEnemy";
    private static String DEFAULT = "default";


    private int myJumpCount;
    private Gravity myGravity;
    private IEnemy consumedEnemy;


    public boolean isConsumedEnemy () {
        return (consumedEnemy != null);
    }

    public void setConsumedEnemy (IEnemy consumedEnemy) {
        this.consumedEnemy = consumedEnemy;
    }

    public Kirby (Location center, Dimension size, GameView gameView, ScrollingManager sm) {
        super(DEFAULT_IMAGE, center, size, gameView, sm, new Integer(1), new Integer (1));
        //MarioLib.addLeftRightAnimationToPlayer(this, "mario.gif");
        myJumpCount = 0;
        myGravity = new Gravity(this);
        consumedEnemy = null;

        intializeStates();
        myCurrentState = DEFAULT;

    }

    /**
     * Initialize all  possible states, including movement for mario.
     */
    private void intializeStates () {
        this.addPossibleState(MoveLeft.STATE_ID, new MoveLeft(this, MOVE_LEFT, STAND_LEFT, SPEED));
        this.addPossibleState(MoveRight.STATE_ID, new MoveRight(this, MOVE_RIGHT, STAND_RIGHT, SPEED));
        this.addPossibleState(FloatLeftState.STATE_ID, new FloatLeftState(this));
        this.addPossibleState(FloatRightState.STATE_ID, new FloatRightState(this));
        this.addPossibleState(InhaleLeftState.STATE_ID, new InhaleLeftState(this));
        this.addPossibleState(InhaleRightState.STATE_ID, new InhaleRightState(this));
        this.addPossibleState(WalkLeftFullState.STATE_ID, new WalkLeftFullState(this));
        this.addPossibleState(WalkRightFullState.STATE_ID, new WalkRightFullState(this));
        this.addPossibleState(ConsumeState.STATE_ID, new ConsumeState(this));
        this.addPossibleState(KirbyCutterWalkLeftState.STATE_ID, new KirbyCutterWalkLeftState(this));
        this.addPossibleState(KirbyCutterWalkRightState.STATE_ID, new KirbyCutterWalkRightState(this));
        this.addPossibleState(CutterAttackLeftState.STATE_ID, new CutterAttackLeftState(this));



    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myGravity.apply();

        if (myJumpCount == MAX_JUMPS && this.getVelocity().getComponentVector(Sprite.UP_DIRECTION).getMagnitude() < .5) {
            myJumpCount = 0;
        }


        super.update(elapsedTime, bounds);
        checkSpeed();

    }

    private void checkSpeed () {
        double speed = this.getVelocity().getMagnitude();       
        if(speed > MAX_SPEED){
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

    public Gravity getGravity() {
        return myGravity;
    }

    @Override
    public String getInputFilePath () {
        return CONTROLS_FILE_PATH;
    }

    @InputMethodTarget(name = "leftstart")
    public void walkLeft() {
        
        if (isConsumedEnemy()) {
            this.activateState(WalkLeftFullState.STATE_ID);
        }
        
        
        if (myCurrentState.equals(CUTTER_ENEMY)) {
            this.activateState(KirbyCutterWalkLeftState.STATE_ID);
        }
        
        else if(this.getCurrentStateID() == FloatRightState.STATE_ID) {
            this.deactivateState(getCurrentStateID());
            this.activateState(FloatLeftState.STATE_ID);
        } 
        else {
            this.activateState(MoveLeft.STATE_ID);
        }
    }

    @InputMethodTarget(name = "leftend")
    public void stopLeft() {        
        this.deactivateState(MoveLeft.STATE_ID);
        this.deactivateState(WalkLeftFullState.STATE_ID);
        this.deactivateState(KirbyCutterWalkLeftState.STATE_ID);

    }
    

    @InputMethodTarget(name = "rightstart")
    public void walkRight() {
        if (isConsumedEnemy()) {
            this.activateState(WalkRightFullState.STATE_ID);
        }
        
        if (myCurrentState.equals(CUTTER_ENEMY)) {
            this.activateState(KirbyCutterWalkRightState.STATE_ID);
        }
        
        else if (this.getCurrentStateID() == FloatLeftState.STATE_ID) {
            this.deactivateState(getCurrentStateID());
            this.activateState(FloatRightState.STATE_ID);
        }
        else {
            this.activateState(MoveRight.STATE_ID);
        }
    }

    @InputMethodTarget(name = "rightend")
    public void stopRight() {
        this.deactivateState(MoveRight.STATE_ID);
        this.deactivateState(WalkRightFullState.STATE_ID);
        this.deactivateState(KirbyCutterWalkRightState.STATE_ID);
    }

    @InputMethodTarget(name = "jumpstart")
    public void startJump() {
        addVector(JUMP_VELOCITY);
        this.activateState(FloatLeftState.STATE_ID);
    }

    @InputMethodTarget(name = "inhalestart")
    public void startInhale() {
                
        System.out.println("Current StateID: " + getCurrentStateID() + " MoveRight stateid: " + MoveRight.STATE_ID + " MoveLeft stateid: " + MoveLeft.STATE_ID);

        if (getCurrentStateID() == MoveLeft.STATE_ID || getCurrentStateID() == InhaleLeftState.STATE_ID) {
            this.activateState(InhaleLeftState.STATE_ID);
            return;
        }
        
        if (getCurrentStateID() == MoveRight.STATE_ID || getCurrentStateID() == InhaleRightState.STATE_ID) {
            this.activateState(InhaleRightState.STATE_ID);
            return;
        }
    }
    
    @InputMethodTarget(name = "inhalestop")
    public void stopInhale() {
        this.deactivateState(InhaleLeftState.STATE_ID);
        this.deactivateState(InhaleRightState.STATE_ID);
    }

    // @InputMethodTarget(name = "leftstart")
    public void startFloatLeft() {
        this.activateState(FloatLeftState.STATE_ID);
    }

    //@InputMethodTarget(name = "rightstart")
    public void startFloatRight() {
        this.activateState(FloatRightState.STATE_ID);

    }

    public void stopFloatLeft() {
        this.deactivateState(FloatLeftState.STATE_ID);
    }

    public void stopFloatRight() {
        this.deactivateState(FloatRightState.STATE_ID);
    }

    public void stopFloat() {
        this.deactivateState(FloatLeftState.STATE_ID);
        this.deactivateState(FloatRightState.STATE_ID);
    }
    
    @InputMethodTarget(name = "consumestart")
    public void startConsumeState() {
        if (isConsumedEnemy()) {
            this.activateState(ConsumeState.STATE_ID);
        }

    }
    
    @InputMethodTarget(name = "consumestop")
    public void stopConsumeState() {
        this.deactivateState(ConsumeState.STATE_ID);
        if (consumedEnemy instanceof KirbyLib.CutterEnemy) {
            this.activateState(KirbyCutterWalkRightState.STATE_ID);
            myCurrentState = CUTTER_ENEMY;
        }
        consumedEnemy = null; 

    }
        
    @InputMethodTarget(name = "attackstart")
    public void startAttackLeftState() {
        if (myCurrentState.equals(CUTTER_ENEMY)) {
            System.out.println("Getting into attack state. mycurrentstate: " + myCurrentState);
            this.activateState(CutterAttackLeftState.STATE_ID);
        }

    }
    
    @InputMethodTarget(name = "attackstop")
    public void stopAttackState() {
      //  this.deactiv

    }


    public void startFullState() {
        if (getCurrentStateID() == InhaleLeftState.STATE_ID) {
            this.activateState(WalkLeftFullState.STATE_ID);
        }
        else {
            this.activateState(WalkRightFullState.STATE_ID);
        }
    }

    public void incrementScore (int increment) {
        // TODO Auto-generated method stub
    }
}




package games.scroller.mr_fish.sprites.player;

import games.scroller.mr_fish.sprites.FishLib;
import games.scroller.mr_fish.sprites.FishLib.Fireball;
import games.scroller.mr_fish.sprites.items.Item;
import games.scroller.mr_fish.sprites.player.states.InventoryState;
import games.scroller.mr_fish.sprites.player.states.TiltDown;
import games.scroller.mr_fish.sprites.player.states.TiltUp;
import games.scroller.mr_fish.sprites.player.stats.Inventory;
import games.scroller.mr_fish.sprites.player.stats.Score;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import util.Location;
import util.ValueText;
import util.Vector;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.animation.movement.MoveDown;
import vooga.scroller.sprites.animation.movement.MoveLeft;
import vooga.scroller.sprites.animation.movement.MoveRight;
import vooga.scroller.sprites.animation.movement.MoveUp;
import vooga.scroller.sprites.state.DefaultSpriteState;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.statistics.Statistic;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;
import vooga.scroller.view.GameView;

@InputClassTarget
public class MrFish extends Player {

    private static final Pixmap MR_FISH_VIEW = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_move_right.gif");
    private static final Dimension MR_FISH_SIZE = new Dimension(32,32);
    public static final int MR_FISH_HEALTH = 20;
    private static final int MR_FISH_DAMAGE = 10;
    private static final int DEATH_PENALTY = 100;
    private static final String INPUT_LOCATION = "games/scroller/mr_fish/controls/MrFishMapping";
    private static final ISpriteView MOVE_LEFT = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_move_left.gif");
    private static final ISpriteView STAND_LEFT = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_stand_left.gif");
    private static final double SPEED = 75;
    private static final ISpriteView MOVE_RIGHT = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_move_right.gif");
    private static final ISpriteView STAND_RIGHT = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_stand_right.gif");
    private static final ISpriteView MOVE_UP = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_move_up.gif");
    private static final ISpriteView STAND_UP = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_stand_up.gif");
    private static final ISpriteView MOVE_DOWN = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_move_down.gif");
    private static final ISpriteView STAND_DOWN = FishLib.makePixmap(FishLib.IMAGE_LOCATION, "frog_stand_down.gif");
    private static final double RATE_ROTATION = 2.0;
    private static final Point2D SCORE_LOCATION = new Location(100, 30);
    private static final Color SCORE_COLOR = Color.BLACK;
    private static final String HEALTH_STRING = "HEALTH";
    private static final Color HEALTH_COLOR = Color.CYAN;
    private static final Point2D HEALTH_LOCATION = new Location(300, 30);
    private static final double FIRE_SPEED = 50;
    
    
    
    private Inventory myInventory;
    private Statistic myScore;
    private Statistic myMoney;
    
    private ValueText myScoreHeader;
    private ValueText myHealthHeader;
    
    
    public MrFish (Location center, GameView gameView, ScrollingManager sm) {
        super(MR_FISH_VIEW, center, MR_FISH_SIZE, gameView, sm, MR_FISH_HEALTH, MR_FISH_DAMAGE);
        
        myScore = new Score();
        myInventory = new Inventory(this);
        
        myScoreHeader = new ValueText(myScore.getName(), myScore.getAggregateValue());
        myHealthHeader = new ValueText(HEALTH_STRING, this.getHealth());

        initializePossibleStates();
    }

    private void initializePossibleStates () {
        // TODO left, right, up, down, super, invincible
        
        // Movement states
        this.addPossibleState(MoveLeft.STATE_ID, new MoveLeft(this, MOVE_LEFT, STAND_LEFT, SPEED));
        this.addPossibleState(MoveRight.STATE_ID, new MoveRight(this, MOVE_RIGHT, STAND_RIGHT, SPEED));
        this.addPossibleState(MoveUp.STATE_ID, new MoveUp(this, MOVE_UP, STAND_UP, SPEED));
        this.addPossibleState(MoveDown.STATE_ID, new MoveDown(this, MOVE_DOWN, STAND_DOWN, SPEED));

        this.addPossibleState(InventoryState.STATE_ID, new InventoryState(this, myInventory));
        
        //this.addPossibleState(TiltUp.STATE_ID, new TiltUp(this, RATE_ROTATION, SPEED));
        //this.addPossibleState(TiltDown.STATE_ID, new TiltDown(this, RATE_ROTATION, SPEED));
    }
    
    public void update (double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);
        myHealthHeader.resetValue();
        myHealthHeader.updateValue(this.getHealth());
        
    }

    @Override
    public void paint(Graphics2D pen){
        super.paint(pen);
        myScoreHeader.paint(pen, SCORE_LOCATION, SCORE_COLOR);    
        myHealthHeader.paint(pen, HEALTH_LOCATION, HEALTH_COLOR);
    }
    
    @Override
    public String getInputFilePath () {
        return INPUT_LOCATION;
    }

    @Override
    public void handleDeath (vooga.scroller.level_editor.Level level) {
        // lose all money
        myMoney.removeValue(myMoney.getAggregateValue());
        myScore.removeValue(DEATH_PENALTY);
        
        this.reset();
    }

    
    @InputMethodTarget(name = "leftstart")
    public void walkLeft() {
        this.activateState(MoveLeft.STATE_ID);
    }
    
    @InputMethodTarget(name = "leftend")
    public void stopLeft() {        
        this.deactivateState(MoveLeft.STATE_ID);
    }
    
    @InputMethodTarget(name = "upstart")
    public void walkUp() {
        this.activateState(MoveUp.STATE_ID);
        //this.activateState(TiltUp.STATE_ID);
    }
    
    @InputMethodTarget(name = "upend")
    public void stopUp() {
        this.deactivateState(MoveUp.STATE_ID);
        //this.deactivateState(TiltUp.STATE_ID);

    }
    
    @InputMethodTarget(name = "downstart")
    public void walkDown() {
        this.activateState(MoveDown.STATE_ID);
        //this.activateState(TiltDown.STATE_ID);

    }
    
    @InputMethodTarget(name = "downend")
    public void stopDown() {
        //this.deactivateState(TiltDown.STATE_ID);

        this.deactivateState(MoveDown.STATE_ID);
    }
    
    @InputMethodTarget(name = "rightstart")
    public void walkRight() {
        this.activateState(MoveRight.STATE_ID);
    }
    
    @InputMethodTarget(name = "rightend")
    public void stopRight() {
        this.deactivateState(MoveRight.STATE_ID);
    }
    
    @InputMethodTarget(name = "fire")
    public void fire(){
        
        GameCharacter fireball = new Fireball(this.getCenter());
        Vector velocity = new Vector(this.getVelocity());
        velocity.setMagnitude(FIRE_SPEED);
        fireball.setVelocity(velocity);
        
        
        this.getLevel().addSprite(fireball);
    }

    public void incrementScore (int value) {
        myScoreHeader.updateValue(value);
        myScore.addValue(value);
        
    }

    public void addItem (Item collectible) {
        myInventory.addItem(collectible);       
    }
    
    @InputMethodTarget(name = "menuon")
    public void showInventory(){
        this.activateState(InventoryState.STATE_ID);
        this.deactivateState(DefaultSpriteState.DEFAULT_ID);
    }
    
    @InputMethodTarget(name = "menuoff")
    public void hideInventory(){
        this.deactivateState(InventoryState.STATE_ID);
        this.activateState(DefaultSpriteState.DEFAULT_ID);

    }
    
    @InputMethodTarget(name = "menuright")
    public void selectRight(){
        myInventory.getNextItem();
        
    }
    @InputMethodTarget(name = "menuleft")
    public void selectLeft(){
        myInventory.getPreviousItem();
    }

    @InputMethodTarget(name = "selectitem")
    public void selectItem(){
        myInventory.getPreviousItem();
        myInventory.selectCurrent();
    }

    
}

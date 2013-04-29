package vooga.scroller.kirbyGame.spritesDefinitions;


import java.awt.Dimension;
import java.awt.geom.Point2D;
import util.Location;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.animation.Animation;
import vooga.scroller.sprites.animation.MovingSpriteAnimationFactory;
import vooga.scroller.sprites.interfaces.Locatable;
import vooga.scroller.sprites.movement.BackAndForth;
import vooga.scroller.sprites.movement.Movement;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;
import util.Vector;


/**
 * This class is a convenient way to gather all the classes and use reflection
 * to retrieve all classes. Not sure if this is the best way to implement this.
 * But it is definitely better than having to parse the package. -DF
 * The Classes are static to allow instantiation w/o an instance of MarioLib.
 * TODO - Decide Whether to keep this implementation or switch to ENUM
 * Moreover, it is an example of the sprite-specification file the game designer
 * will need to provide.
 */
public class KirbyLib extends EncapsulatedSpriteLibrary {
    private static final Dimension DEFAULT_SIZE = new Dimension(32, 32);
    private static final Location DEFAULT_LOC = new Location(32, 32);
    private static final int DEFAULT_HEALTH = 1;
    private static final int DEFAULT_DAMAGE = 0;


    public static class CutterEnemy extends GameCharacter implements IEnemy {
        private static final String DEFAULT_IMG = "cutterwalkL.gif";
        private int SPEED = 30;
        private int RADIUS = 45;
        private TrackPlayer movement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);

        public CutterEnemy () {
            this(DEFAULT_LOC);
        }

        public CutterEnemy (Location center) {
            super(makePixmap(DEFAULT_IMG), center, DEFAULT_SIZE, new Integer(1), new Integer(1));
        }

        public void update (double elapsedTime, Dimension bounds) {
            movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (vooga.scroller.level_editor.Level level) {
            // TODO Auto-generated method stub   
        }
        
        @Override
        public void addTarget(Locatable target){
            movement.setTarget(target);
        }
    }

    
    public static class LaserEnemy extends GameCharacter implements IEnemy {

        private static final String DEFAULT_IMG = "laserwalkL.gif";
        private int SPEED = 30;
        private int RADIUS = 45;
        private TrackPlayer movement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);


        public LaserEnemy () {
            this(DEFAULT_LOC);
        }

        public LaserEnemy (Location center) {
            super(makePixmap(DEFAULT_IMG), center, DEFAULT_SIZE, new Integer(1), new Integer(2));
        }

        public void update (double elapsedTime, Dimension bounds) {
//            movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (vooga.scroller.level_editor.Level level) {
            // TODO Auto-generated method stub   
        }

        @Override
        public void addTarget(Locatable target){
//            movement.setTarget(target);
        }
        
    }
    
    public static class Boss extends GameCharacter implements IEnemy {

        private static final String DEFAULT_IMG = "bossstationary.gif";
        private static final Dimension BOSS_SIZE = new Dimension(128, 80);
        private int SPEED = 30;
        private int RADIUS = 45;
        private TrackPlayer movement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);


        public Boss () {
            this(DEFAULT_LOC);
        }

        public Boss (Location center) {
            super(makePixmap(DEFAULT_IMG), center, BOSS_SIZE, new Integer(1), new Integer(2));
        }

        public void update (double elapsedTime, Dimension bounds) {
            movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (vooga.scroller.level_editor.Level level) {
        }
        
        // TODO :This is hacky
        @Override
        public void addTarget(Locatable target){
            movement.setTarget(target);
        }
        
    }


    public static class PlatformOne extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "laserplatform.png";

        public PlatformOne () {
            this(DEFAULT_LOC);
        }

        public PlatformOne (Location center) {
            this(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public PlatformOne (String img, Location center, Dimension size) {
            super(makePixmap(img), center, size);
        }
    }

    public static class PlatformTwo extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "cutterplatform.png";

        public PlatformTwo () {
            this(DEFAULT_LOC);
        }

        public PlatformTwo (Location center) {
            this(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public PlatformTwo (String img, Location center, Dimension size) {
            super(makePixmap(img), center, size);
        }
    }
    
    public static class PlatformThree extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "bossplatform.png";

        public PlatformThree () {
            this(DEFAULT_LOC);
        }

        public PlatformThree (Location center) {
            this(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public PlatformThree (String img, Location center, Dimension size) {
            super(makePixmap(img), center, size);
        }
    }
    
    
    public static class KirbyLaser extends GameCharacter implements IEnemy {

        //has no movement right now 
        private static final String DEFAULT_IMG = "kirbylaserproj.gif";

        private static final Dimension KIRBY_LASER_SIZE = new Dimension(64,8);
        private int SPEED = 30;


        public KirbyLaser () {
            this(DEFAULT_LOC);
        }

        public KirbyLaser (Location center) {
            super(makePixmap(DEFAULT_IMG), center, KIRBY_LASER_SIZE, new Integer(1), new Integer(2));
        }

        public void update (double elapsedTime, Dimension bounds) {
            //movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (vooga.scroller.level_editor.Level level) {
            // TODO Auto-generated method stub   
        }
    }
    
    public static class EnemyLaser extends GameCharacter implements IEnemy {

        //has no movement right now 
        private static final String DEFAULT_IMG = "laserprojectile.png";
        private static final Dimension LASER_SIZE = new Dimension(32,8);
        private int SPEED = 30;


        public EnemyLaser () {
            this(DEFAULT_LOC);
        }

        public EnemyLaser (Location center) {
            super(makePixmap(DEFAULT_IMG), center, LASER_SIZE, new Integer(1), new Integer(2));
        }

        public void update (double elapsedTime, Dimension bounds) {
            //movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (vooga.scroller.level_editor.Level level) {
            // TODO Auto-generated method stub   
        }
    }
    
    public static class FireBall extends GameCharacter implements IEnemy {

        //has no movement right now 
        private static final String DEFAULT_IMG = "bossprojectile.gif";
        private static final Dimension FIRE_SIZE = new Dimension(45,45);
        private int SPEED = 30;


        public FireBall () {
            this(DEFAULT_LOC);
        }

        public FireBall (Location center) {
            super(makePixmap(DEFAULT_IMG), center, FIRE_SIZE, new Integer(1), new Integer(2));
        }

        public void update (double elapsedTime, Dimension bounds) {
            //movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (vooga.scroller.level_editor.Level level) {
            // TODO Auto-generated method stub   
        }
    }

    public static class DoorPortal extends LevelPortal {

        private static final String PORTAL_IMG = "door.png";
        private static final Dimension PORTAL_SIZE = new Dimension(32, 64);

        @Override
        public ISpriteView initView () {
            return makePixmap(PORTAL_IMG);
        }

        @Override
        public Dimension initSize () {
            return PORTAL_SIZE;
        }
    }
    
    public static class StarPortal extends LevelPortal {

        private static final String PORTAL_IMG = "star.png";
        private static final Dimension PORTAL_SIZE = new Dimension(32, 32);

        @Override
        public ISpriteView initView () {
            return makePixmap(PORTAL_IMG);
        }

        @Override
        public Dimension initSize () {
            return PORTAL_SIZE;
        }
    }

    /**
     * TODO - how to enforce implementation of this?
     * 
     * @return
     */
    public static String getImagesDirectory () {
        return "/vooga/scroller/kirbyGame/images/";
    }

    public static Pixmap makePixmap (String fileName) {
        return makePixmap(getImagesDirectory(), fileName);
    }

    public static void addLeftRightAnimationToPlayer (Player player, String baseFileName) {
        MovingSpriteAnimationFactory msaf =
                new MovingSpriteAnimationFactory(getImagesDirectory(), baseFileName);
        Animation<Sprite> playerAnimation = msaf.generateAnimation(player);
        player.setView(playerAnimation);
    }
    


}

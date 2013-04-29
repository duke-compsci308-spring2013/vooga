package games.scroller.superMario.sprites;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.marioGame.spritesDefinitions.collisions.MarioVisitMethods;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.Locatable;
import vooga.scroller.sprites.movement.BackAndForth;
import vooga.scroller.sprites.movement.Movement;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;

public class SuperMarioLib extends EncapsulatedSpriteLibrary {
    private static final Dimension DEFAULT_SIZE = new Dimension(32, 32);
    private static final int DEFAULT_HEALTH = 1;
    private static final int DEFAULT_DAMAGE = 0;
    
    public static String getImagesDirectory () {
        return "/games/scroller/superMario/images/";
    }

    public static Pixmap makePixmap (String fileName) {
        return makePixmap(getImagesDirectory(), fileName);
    }
    
    public static class Platform extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "block.png";

        public Platform () {
            this(DEFAULT_IMG, DEFAULT_SIZE);
        }

        public Platform (String img, Dimension size) {
            super(makePixmap(img), size);
        }
    }
    

    public static class LevelTwoBlockOne extends Platform implements IPlatform {

        private static final String DEFAULT_IMG = "leveltwoblock1.png";
        private static final Dimension DEFAULT_SIZE = new Dimension(128, 96);

        public LevelTwoBlockOne () {
            super(DEFAULT_IMG, DEFAULT_SIZE);
        }

        public void print () {
        }
    }

    public static class LevelTwoBlockTwo extends Platform implements IPlatform {

        private static final String DEFAULT_IMG = "leveltwoblock2.png";
        private static final Dimension DEFAULT_SIZE = new Dimension(96, 32);

        public LevelTwoBlockTwo () {
            super(DEFAULT_IMG, DEFAULT_SIZE);
        }

        public void print () {
        }
    }

    public static class LevelTwoBlockThree extends Platform implements IPlatform {

        private static final String DEFAULT_IMG = "leveltwoblock3.png";
        private static final Dimension DEFAULT_SIZE = new Dimension(768, 192);

        public LevelTwoBlockThree () {
            super(DEFAULT_IMG, DEFAULT_SIZE);
        }

        public void print () {
        }
    }

    public static class ItemBlock extends Sprite implements IPlatform {

        private static final String BLOCK_IMG = "itemBlock.gif";

        public ItemBlock () {
            super(makePixmap(BLOCK_IMG), DEFAULT_SIZE);
        }

    }
    
    
    
    public static class Coin extends GameCharacter implements ICollectible {

        private static final String DEFAULT_IMG = "coin.png";
        private static final int DEFAULT_COIN_VALUE = 900;

        public Coin () {
            super(makePixmap(DEFAULT_IMG), DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
        }

        @Override
        public int getValue () {
            return DEFAULT_COIN_VALUE;
        }

        @Override
        public void handleDeath (Level level) {
            // killing this does not do anything
        }

    }
    
    public static class Fireflower extends GameCharacter implements ICollectible {

        private static final String DEFAULT_IMG = "fireflower.png";
        private static final int DEFAULT_COIN_VALUE = 900; //TODO

        public Fireflower () {
            super(makePixmap(DEFAULT_IMG), DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
        }

        @Override
        public int getValue () {
            return DEFAULT_COIN_VALUE;
        }

        @Override
        public void handleDeath (Level level) {
            // killing this does not do anything
        }

    }

    public static class Koopa extends GameCharacter implements IEnemy {
        private static final String DEFAULT_IMG = "koopa.png";
        private static final Dimension KOOPA_SIZE = new Dimension(32, 64);
        private int SPEED = 30;
        private int RADIUS = 45;
        private TrackPlayer movement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);

        public Koopa () {
            super(makePixmap(DEFAULT_IMG), KOOPA_SIZE, new Integer(1), new Integer(1));
        }

        public void update (double elapsedTime, Dimension bounds) {
            movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
        }

        // TODO :This is hacky
        @Override
        public void addTarget (Locatable target) {
            movement.setTarget(target);
        }

    }

    public static class Goomba extends GameCharacter implements IEnemy {

        private static final String DEFAULT_IMG = "goomba.gif";
        private static final Dimension GOOMBA_SIZE = new Dimension(32, 32);
        private int SPEED = 30;
        private Point2D START = new Point2D.Double(500.0, 100.0);
        private Point2D END = new Point2D.Double(200.0, 500.0);

        private Movement movement = new BackAndForth(this, START, END, SPEED);

        public Goomba () {
            super(makePixmap(DEFAULT_IMG), GOOMBA_SIZE, new Integer(1), new Integer(1));
        }

        public void update (double elapsedTime, Dimension bounds) {
            movement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
            
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

    @Override
    public VisitLibrary getVisitLibrary () {
        return new MarioVisitMethods(); //TODO - needs to implement legit ones
    }

}

package vooga.scroller.marioGame.spritesDefinitions;

import java.awt.Dimension;
import util.Location;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.sprites.interfaces.ICollectible;
import vooga.scroller.sprites.interfaces.IEnemy;
import vooga.scroller.sprites.interfaces.IPlatform;
import vooga.scroller.sprites.movement.LeftAndRight;
import vooga.scroller.sprites.movement.Movement;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.movement.UpAndDown;
import vooga.scroller.sprites.superclasses.NonStaticEntity;
import vooga.scroller.sprites.superclasses.StaticEntity;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.Sprite;
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
public class MarioLib extends EncapsulatedSpriteLibrary {
    private static final Dimension DEFAULT_SIZE = new Dimension(32, 32);
    private static final Location DEFAULT_LOC = new Location(32, 32);
    private static final int DEFAULT_HEALTH = 1;
    private static final int DEFAULT_DAMAGE = 0;

    public static class Coin extends StaticEntity implements ICollectible {

        private static final String DEFAULT_IMG = "coin.png";
        private static final int DEFAULT_COIN_VALUE = 900;

        public Coin () {
            this(DEFAULT_LOC);
        }

        public Coin (Location center) {
            super(makePixmap(DEFAULT_IMG), center, DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
        }

        @Override
        public int getValue () {
            return DEFAULT_COIN_VALUE;
        }
    }

    public static class Koopa extends NonStaticEntity implements IEnemy {
        private static final String DEFAULT_IMG = "koopa.png";
        private static final Dimension KOOPA_SIZE = new Dimension(32, 64);
        private Movement movement = new TrackPlayer(this);

        
        public Koopa () {
            this(DEFAULT_LOC);
        }

        public Koopa (Location center) {
            super(makePixmap(DEFAULT_IMG), center, KOOPA_SIZE, new Integer(1), new Integer(1));
        }

        public void update (double elapsedTime, Dimension bounds) {
            changeVelocity(getMovement(movement)); // want to make this call every
            super.update(elapsedTime, bounds);
        }

        @Override
        public Vector getMovement (Movement movement) {
            return movement.execute(450, 1000000, getPlayer());
        }
    }

    public static class Turtle extends NonStaticEntity implements IEnemy {

        private static final String DEFAULT_IMG = "turtle.gif";
        private Movement movement = new TrackPlayer(this);

        public Turtle () {
            this(DEFAULT_LOC);
        }

        public Turtle (Location center) {
            super(makePixmap(DEFAULT_IMG), center, DEFAULT_SIZE, new Integer(1), new Integer(2));
        }

        public void update (double elapsedTime, Dimension bounds) {
            changeVelocity(getMovement(movement)); 
            super.update(elapsedTime, bounds);
        }

        @Override
        public Vector getMovement (Movement movement) {
            return movement.execute(450, 1000000, getPlayer());
        }

    }

    public static class Platform extends StaticEntity implements IPlatform {

        private static final String DEFAULT_IMG = "block.png";

        public Platform () {
            this(DEFAULT_LOC);
        }

        public Platform (Location center) {
            this(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public Platform (String img, Location center, Dimension size) {
            super(makePixmap(img), center, size, DEFAULT_HEALTH, DEFAULT_DAMAGE);
        }
    }

    public static class Plant extends StaticEntity implements IEnemy {
        private static final String DEFAULT_IMG = "plant.png";

        public Plant () {
            this(DEFAULT_LOC);
        }

        public Plant (Location center) {
            super(makePixmap(DEFAULT_IMG), center, new Dimension(32, 32), DEFAULT_HEALTH, new Integer(2));
        }
    }

    public static class MovingPlatformOne extends NonStaticEntity implements IPlatform {

        private static final String DEFAULT_IMG = "platform.gif";
        private static final int DEFAULT_SPEED = 60;
        private static final Vector DEFAULT_VELOCITY = new Vector(Sprite.DOWN_DIRECTION,
                                                                  DEFAULT_SPEED);
        private Movement movement = new UpAndDown(this);


        public MovingPlatformOne () {
            this(DEFAULT_LOC);
        }

        public MovingPlatformOne (Location center) {
            super(makePixmap(DEFAULT_IMG), center, new Dimension(96, 32), DEFAULT_HEALTH, DEFAULT_DAMAGE);
            this.changeVelocity(DEFAULT_VELOCITY);
        }

        public void update (double elapsedTime, Dimension bounds) {
            changeVelocity(getMovement(movement)); // want to make this call
            super.update(elapsedTime, bounds);
        }

        @Override
        public Vector getMovement (Movement movement) {
            return movement.execute(100, 250, DEFAULT_SPEED);
        }

    }

    public static class MovingPlatformTwo extends NonStaticEntity implements IPlatform {

        private static final String DEFAULT_IMG = "platform.gif";
        private static final int DEFAULT_SPEED = 60;
        private static final Vector DEFAULT_VELOCITY = new Vector(Sprite.RIGHT_DIRECTION,
                                                                  DEFAULT_SPEED);
        private Movement movement = new LeftAndRight(this);


        public MovingPlatformTwo () {
            this(DEFAULT_LOC);
        }

        public MovingPlatformTwo (Location center) {
            super(makePixmap(DEFAULT_IMG), center, new Dimension(96, 32), DEFAULT_HEALTH, DEFAULT_DAMAGE);
            this.changeVelocity(DEFAULT_VELOCITY);
        }

        public void update (double elapsedTime, Dimension bounds) {
            changeVelocity(getMovement(movement));
            super.update(elapsedTime, bounds);
        }

        @Override
        public Vector getMovement (Movement movement) {
            return movement.execute(500, 1000, DEFAULT_SPEED);
        }

    }

    public static class LevelTwoBlockOne extends Platform implements IPlatform {

        private static final String DEFAULT_IMG = "leveltwoblock1.png";
        private static final Dimension DEFAULT_SIZE = new Dimension(128, 96);

        public LevelTwoBlockOne () {
            this(DEFAULT_LOC);
        }

        public LevelTwoBlockOne (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public void print () {
        }
    }

    public static class LevelTwoBlockTwo extends Platform implements IPlatform {

        private static final String DEFAULT_IMG = "leveltwoblock2.png";
        private static final Dimension DEFAULT_SIZE = new Dimension(96, 32);

        public LevelTwoBlockTwo () {
            this(DEFAULT_LOC);
        }

        public LevelTwoBlockTwo (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public void print () {
        }
    }

    public static class LevelTwoBlockThree extends Platform implements IPlatform {

        private static final String DEFAULT_IMG = "leveltwoblock3.png";
        private static final Dimension DEFAULT_SIZE = new Dimension(768, 192);

        public LevelTwoBlockThree () {
            this(DEFAULT_LOC);
        }

        public LevelTwoBlockThree (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        public void print () {
        }
    }
    
    /**
     * TODO - how to enforce implementation of this?
     * @return
     */
    public static String getImagesDirectory () {
        return "/vooga/scroller/marioGame/images/";
    }

    public static Pixmap makePixmap(String fileName) {
        return makePixmap(getImagesDirectory(), fileName);
    }

}

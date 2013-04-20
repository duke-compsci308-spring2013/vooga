package vooga.scroller.sprites.test_sprites;

import java.awt.Dimension;
import util.Location;
import vooga.scroller.level_editor.ISpriteLibrary;
import vooga.scroller.sprites.interfaces.ICollectible;
import vooga.scroller.sprites.interfaces.IEnemy;
import vooga.scroller.sprites.interfaces.IPlatform;
import vooga.scroller.sprites.movement.LeftAndRight;
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
public class MarioLib implements ISpriteLibrary {
    private static final Dimension DEFAULT_SIZE = new Dimension(32, 32);
    private static final Location DEFAULT_LOC = new Location(32, 32);

    public static class Coin extends StaticEntity implements ICollectible {

        private static final String DEFAULT_IMG = "coin.png";
        private static final int DEFAULT_VALUE = 900;
        private int myHealth = 1;
        
        public Coin () {
            this(DEFAULT_LOC);
        }

        public Coin (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, DEFAULT_SIZE);
        }

        public void print () {
            System.out.println("Coin");
        }

        /**
         * Gives a value of the coin
         * 
         * @return an integer that represents the 
         */
        public int getValue () {
            return DEFAULT_VALUE;
        }

        @Override
        public void takeHit (int hitValue) {
            myHealth -= hitValue;
        }
        
        public int getHealth() {
            return myHealth;
        }
    }
    
    public static class LilMario extends NonStaticEntity {
        
        private static final String DEFAULT_IMG = "lilMario.png";
        private static final Dimension MY_SIZE = new Dimension(32, 32);

        public LilMario () {
            this(DEFAULT_LOC);
            
        }
        
        public LilMario (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, MY_SIZE);
        }
    }
    
 public static class BigMario extends NonStaticEntity {
        
        private static final String DEFAULT_IMG = "bigMario.png";
        private static final Dimension MY_SIZE = new Dimension(32, 64);

        public BigMario () {
            this(DEFAULT_LOC);
            
        }
        
        public BigMario (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, MY_SIZE);
        }
    }

    public static class Koopa extends NonStaticEntity implements IEnemy {

        private static final String DEFAULT_IMG = "koopa.png";
        private static final Dimension MY_SIZE = new Dimension(32, 64);
        
        private int myHealth = 1;
        private int myDamage = 1;

        public Koopa () {
            this(DEFAULT_LOC);
        }

        public Koopa (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, MY_SIZE);
        }

        public void print () {
            System.out.println("Koopa");
        }

        public void update (double elapsedTime, Dimension bounds) {
            TrackPlayer movement = new TrackPlayer(this);
            changeVelocity(movement.execute(45, 100, getPlayer())); // want to make this call every
                                                                    // X seconds
            super.update(elapsedTime, bounds);
        }
        @Override
        public int getHealth() {
            return myHealth;
        }
        
        public void takeHit(int damage) {
            myHealth -= damage;
        }

        @Override
        public Sprite getEnemy () {
            return this;
        }

        @Override
        public int getHit () {
            return myDamage;
        }

    }

    public static class Turtle extends NonStaticEntity implements IEnemy {

        private static final String DEFAULT_IMG = "turtle.gif";
        private int myHealth = 3;
        private int myDamage = 2;

        public Turtle () {
            this(DEFAULT_LOC);
        } // used by level editor

        public Turtle (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, DEFAULT_SIZE);
        }

        public void takeHit (int damage) {
            myHealth -= damage;
        }

        public Sprite getEnemy () {
            return this;
        }

        public int getHit () {
            return myDamage;
        }

        public void update (double elapsedTime, Dimension bounds) {
            // changeVelocity(trackPlayer(70, 150)); //want to make this call every X seconds
            super.update(elapsedTime, bounds);
        }
    }

    public static class Platform extends StaticEntity implements IPlatform {

        private static final String DEFAULT_IMG = "block.png";

        public Platform () {
            this(DEFAULT_LOC);
        }

        public Platform (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, DEFAULT_SIZE);
        }
        public Platform (String img, Location center, Dimension size) {
            super(new Pixmap(img), center, size);
        }

        public void print () {
            System.out.println("Platform");
        }

        @Override
        public Platform getPlatform () {
            return this;
        }
    }
    
    public static class Plant extends StaticEntity implements IEnemy {
        private static final String DEFAULT_IMG = "plant.png";
        private int myHealth = 2;
        private int myDamage = 1;
        
        public Plant () {
            this(DEFAULT_LOC);
        }

        public Plant (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, new Dimension(32, 32));
        }
        
        public void takeHit(int damage) {
            myHealth -= damage;
        }

        @Override
        public Sprite getEnemy () {
            return this;
        }

        @Override
        public int getHit () {
            return myDamage;
        }

    }

    /**
     * Represents a moving platform that moves in the up/down direction
     * 
     * @author Jay Wang
     */
    public static class MovingPlatformOne extends NonStaticEntity implements IPlatform {

        private static final String DEFAULT_IMG = "platform.gif";
        private static final int DEFAULT_SPEED = 60;
        private static final Vector DEFAULT_VELOCITY = new Vector(Sprite.DOWN_DIRECTION,
                                                                  DEFAULT_SPEED);

        public MovingPlatformOne () {
            this(DEFAULT_LOC);
        }

        public MovingPlatformOne (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, new Dimension(96, 32));
            this.changeVelocity(DEFAULT_VELOCITY);
        }

        public void update (double elapsedTime, Dimension bounds) {
            UpAndDown movement = new UpAndDown(this);
            changeVelocity(movement.execute(100, 250, DEFAULT_SPEED)); // want to make this call
                                                                       // every X seconds
            super.update(elapsedTime, bounds);
        }

        @Override
        public MovingPlatformOne getPlatform () {
            return this;
        }
    }

    /**
     * Represents a moving platform that moves in the left/right direction
     * 
     * @author Jay Wang
     */
    public static class MovingPlatformTwo extends NonStaticEntity implements IPlatform {

        private static final String DEFAULT_IMG = "platform.gif";
        private static final int DEFAULT_SPEED = 60;
        private static final Vector DEFAULT_VELOCITY = new Vector(Sprite.RIGHT_DIRECTION,
                                                                  DEFAULT_SPEED);

        public MovingPlatformTwo () {
            this(DEFAULT_LOC);
        }

        public MovingPlatformTwo (Location center) {
            super(new Pixmap(DEFAULT_IMG), center, new Dimension(96, 32));
            this.changeVelocity(DEFAULT_VELOCITY);
        }

        public void update (double elapsedTime, Dimension bounds) {
            LeftAndRight movement = new LeftAndRight(this);
            changeVelocity(movement.execute(500, 1000, DEFAULT_SPEED));
            super.update(elapsedTime, bounds);
        }
        
        @Override
        public MovingPlatformTwo getPlatform () {
            return this;
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

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Sprite>[] getSpritesClasses () {
        return (Class<? extends Sprite>[]) this.getClass().getClasses();
    }

}

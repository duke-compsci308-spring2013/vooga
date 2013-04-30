package games.scroller.stickmansam;

import java.awt.Dimension;
import util.Vector;
import vooga.scroller.extra_resources.movements.TimedMovement;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.movement.Movement;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.physics.Force;
import vooga.scroller.util.physics.Gravity;

/**
 * Library for Stickman Sam sprites
 * @author David Winegar
 *
 */
public class StickmanSpriteLibrary extends EncapsulatedSpriteLibrary {
    private static final Dimension ENEMY_SIZE = new Dimension(32, 45);
    private static final Dimension BLOCK_SIZE = new Dimension(32, 32);
    private static final String PLATFORM_IMAGE = "platform.png";
    private static final String PLATFORM_IMAGE_2 = "platform2.png";
    private static final String IMAGES = "/games/scroller/stickmansam/images/";

    /**
     * platform (1 slot)
     * @author David Winegar
     *
     */
    public static class Platform extends Sprite implements IPlatform {
        /**
         * Create platform
         */
        public Platform () {
            super(makePixmap(PLATFORM_IMAGE), BLOCK_SIZE);
        }
    }

    /**
     * Big platform (4x4 slots)
     * @author David Winegar
     *
     */
    public static class BigPlatform extends Sprite implements IPlatform {
        private static final Dimension BIG_BLOCK_SIZE = new Dimension(128, 128);

        /**
         * Create big platform
         */
        public BigPlatform () {
            super(makePixmap(PLATFORM_IMAGE_2), BIG_BLOCK_SIZE);
        }
    }

    /**
     * Enemy stick zombie that injures player.
     * @author David Winegar
     *
     */
    public static class StickZombie extends GameCharacter implements IEnemy {
        private static final String ZOMBIE_IMAGE = "zombie.png";
        private static final int HEALTH = 60;
        private static final int DAMAGE = 2;
        private Force myGravity;
        private static final int SPEED = 5;
        private static final int TIME = 1;
        private static final int ANGLE = 180;
        private Movement myMovement = new TimedMovement(this, TIME, ANGLE, SPEED);
        /**
         * Create zombie
         */
        public StickZombie () {
            super(makePixmap(ZOMBIE_IMAGE), ENEMY_SIZE, HEALTH, DAMAGE);
            myGravity = new Gravity(this);
        }

        @Override
        public void handleDeath (Level level) {
            level.removeSprite(this);
        }

        @Override
        public void update (double elapsedTime, Dimension bounds) {
            myGravity.apply();
            super.update(elapsedTime, bounds);
        }

    }

    /**
     * Bullet that player shoots
     * @author David Winegar
     *
     */
    public static class Bullet extends GameCharacter {
        private static final int VELOCITY = 500;
        private static final String BULLET_IMAGE = "bullet.png";
        private static final Dimension BULLET_SIZE = new Dimension(15, 10);
        private static final int BULLET_DAMAGE = 20;

        /**
         * Create bullet with given direction
         * @param direction
         */
        public Bullet (int direction) {
            super(makePixmap(BULLET_IMAGE), BULLET_SIZE, 1, BULLET_DAMAGE);
            this.setVelocity(new Vector(direction, VELOCITY));
        }

        @Override
        public void handleDeath (Level level) {
            level.removeSprite(this);
        }

    }

    /**
     * door to advance to next level
     * @author David Winegar
     *
     */
    public static class Door extends LevelPortal {
        private static final String EMPTY_IMAGE = "empty.png";

        @Override
        public ISpriteView initView () {
            return makePixmap(EMPTY_IMAGE);
        }

        @Override
        public Dimension initSize () {
            return BLOCK_SIZE;
        }

    }

    /**
     * Helper method to create Pixmaps from filepaths.
     * 
     * @author Letter Adventure team
     * @param fileName
     * @return
     */
    public static Pixmap makePixmap (String fileName) {
        return makePixmap(IMAGES, fileName);
    }
}

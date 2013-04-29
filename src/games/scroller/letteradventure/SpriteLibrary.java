package games.scroller.letteradventure;

import java.awt.Dimension;
import vooga.scroller.extra_resources.movements.TimedMovement;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.movement.Movement;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;

/**
 * All the sprites present in the LetterAdventure with the exception of
 * the main player.
 * @author Ellango, David Liu
 *
 */
public class SpriteLibrary extends EncapsulatedSpriteLibrary {
    public static final Dimension DEFAULT_SIZE = new Dimension(42, 42);
    public static final String IMAGES_DIRECTORY = "/src/games/scroller/letteradventure/images/";

    /**
     * This is the platform that all the characters walk along.
     * 
     */
    public static class Platform extends Sprite implements IPlatform {
        public static final String PLATFORM_IMAGE = "platform.png";

        public Platform (ISpriteView image, Dimension size) {
            super(makePixmap(PLATFORM_IMAGE), DEFAULT_SIZE);
        }

    }

    /**
     * GEnemies are small enemies that the player has to avoid.
     * 
     * They are G's.
     * 
     */
    public static class GEnemy extends GameCharacter implements IEnemy {
        private static final String G_IMG = "gimage.png";
        private static final int SPEED = 20;
        private static final int HEALTH = 1;
        private static final int TIME = 1;
        private static final int ANGLE = 0;
        private Movement myMovement = new TimedMovement(this, TIME, ANGLE, SPEED);

        public GEnemy () {
            super(makePixmap(G_IMG), DEFAULT_SIZE, HEALTH, 0);
        }

        @Override
        public void update (double elapsedTime, Dimension bounds) {
            myMovement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (Level level) {
            // not sure what to put here.
        }

    }

    /**
     * GBoss is the boss character that the player must defeat in the boss
     * level.
     * 
     * Also is a G, but a bigger G.
     */
    public static class GBoss extends GameCharacter implements IEnemy {
        private static final String BOSS_IMG = "gboss.png";
        private static final int SPEED = 5;
        private static final int RADIUS = 300;
        private static final int HEALTH = 3;
        private Movement myMovement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);

        public GBoss () {
            super(makePixmap(BOSS_IMG), DEFAULT_SIZE, HEALTH, 0);
        }

        @Override
        public void update (double elapsedTime, Dimension bounds) {
            myMovement.execute();
            super.update(elapsedTime, bounds);
        }

        @Override
        public void handleDeath (Level level) {
            // end game
            // would like to return score from EPlayer to the arcade...
        }
    }

    /**
     * 
     * Projectiles do damage to enemies.  
     * 
     * Is a D.
     *
     */
    public static class Projectile extends GameCharacter {

        private static final String PROJECTILE_IMG = "d.png";
        private static final Dimension SIZE = new Dimension(20, 20);

        private static final int HEALTH = 1;
        private static final int DAMAGE = 3;
        private static final int MAX_TIME = 50;

        private int myTime;
        
        public Projectile () {
            super(makePixmap(PROJECTILE_IMG), SIZE, HEALTH, DAMAGE);
        }

        @Override
        public void update (double elapsedTime, Dimension bounds) {
            super.update(elapsedTime, bounds);
            myTime += 1;
            if (myTime >= MAX_TIME) {
                this.setHealth(0);
            }
        }

        @Override
        public void handleDeath (Level level) {

        }

    }

    /**
     * Helper method to create Pixmaps from filepaths.
     * 
     * @param fileName
     * @return
     */
    public static Pixmap makePixmap (String fileName) {
        return makePixmap(IMAGES_DIRECTORY, fileName);
    }

}

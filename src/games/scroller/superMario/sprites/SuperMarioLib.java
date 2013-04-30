package games.scroller.superMario.sprites;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.Locatable;
import vooga.scroller.sprites.movement.BackAndForth;
import vooga.scroller.sprites.movement.Movement;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;


public class SuperMarioLib extends EncapsulatedSpriteLibrary {

    private static final String IMAGE_DIR = "/games/scroller/superMario/images/";

    private static final int DEFAULT_SIZE = 32;
    private static final int DEFAULT_HEALTH = 1;
    private static final int DEFAULT_DAMAGE = 0;
    private static final int DEFAULT_ITEM_VALUE = 1000;
    private static final int DEFAULT_COIN_VALUE = 100;

    public static String getImagesDirectory () {
        return IMAGE_DIR;
    }

    public static Pixmap makePixmap (String fileName) {
        return makePixmap(getImagesDirectory(), fileName);
    }

    public static class WoodBlock extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "platform.png";

        public WoodBlock () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1));
        }

    }

    public static class WoodPole extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodPole.png";

        public WoodPole () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1));
        }
    }

    public static class WoodLeft extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodPlatformLeft.png";

        public WoodLeft () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1));
        }
    }

    public static class WoodMid extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodPlatformMid.png";

        public WoodMid () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1));
        }
    }

    public static class WoodRight extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodPlatformRight.png";

        public WoodRight () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1));
        }
    }

    public static class WoodBigLeft extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodBigLeft.png";

        public WoodBigLeft () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 2));
        }
    }

    public static class WoodBigMid extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodBigMid.png";

        public WoodBigMid () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 2));
        }
    }

    public static class WoodBigRight extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "woodBigRight.png";

        public WoodBigRight () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 2));
        }
    }

    public static class Block extends Sprite implements IPlatform {

        private static final String DEFAULT_IMG = "block.png";

        public Block () {
            this(DEFAULT_IMG, makeSize(1, 1));
        }

        public Block (String img, Dimension size) {
            super(makePixmap(img), size);
        }
    }

    public static class Blocks3by1 extends Block implements IPlatform {

        private static final String DEFAULT_IMG = "blocks3by1.png";

        public Blocks3by1 () {
            super(DEFAULT_IMG, makeSize(3, 1));
        }

        public void print () {
        }
    }

    public static class Blocks4by3 extends Block implements IPlatform {

        private static final String DEFAULT_IMG = "blocks4by3.png";

        public Blocks4by3 () {
            super(DEFAULT_IMG, makeSize(4, 3));
        }

        public void print () {
        }
    }

    public static class Blocks24by6 extends Block implements IPlatform {

        private static final String DEFAULT_IMG = "blocks24by6.png";

        public Blocks24by6 () {
            super(DEFAULT_IMG, makeSize(24, 6));
        }

        public void print () {
        }
    }

    public static class ItemBlock extends Sprite implements IPlatform {

        private static final String BLOCK_IMG = "itemBlock.gif";

        public ItemBlock () {
            super(makePixmap(BLOCK_IMG), makeSize(1, 1));
        }

    }

    public static class Coin extends GameCharacter implements ICollectible {

        private static final String DEFAULT_IMG = "coin.gif";

        public Coin () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1), DEFAULT_HEALTH, DEFAULT_DAMAGE);
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

    public static class Fireflower extends GameCharacter implements IPowerUp {

        private static final String DEFAULT_IMG = "item_flower.png";

        public Fireflower () {
            super(makePixmap(DEFAULT_IMG), makeSize(1, 1), DEFAULT_HEALTH, DEFAULT_DAMAGE, true);
        }

        @Override
        public void update (double elapsedTime, Dimension bounds) {
            super.update(elapsedTime, bounds);
        }

        @Override
        public int getValue () {
            return DEFAULT_ITEM_VALUE;
        }

        @Override
        public void handleDeath (Level level) {
            // killing this does not do anything
        }

        @Override
        public int getStateID () {
            // TODO Auto-generated method stub
            return FireState.STATE_ID;
        }

    }

    public static class Koopa extends GameCharacter implements IEnemy {
        private static final String DEFAULT_IMG = "koopa_default_image.png";
        private static final Dimension KOOPA_SIZE = new Dimension(32, 64);
        private int SPEED = 30;
        private int RADIUS = 45;
        private TrackPlayer movement;

        public Koopa () {
            super(makePixmap(DEFAULT_IMG), KOOPA_SIZE, new Integer(1), new Integer(1));
            movement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);
        }

        public void update (double elapsedTime, Dimension bounds) {
            super.update(elapsedTime, bounds);
            movement.execute();
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
            super(makePixmap(DEFAULT_IMG), GOOMBA_SIZE, DEFAULT_HEALTH, new Integer(1));
            movement = new BackAndForth(this, START, END, SPEED);
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

    private static Dimension makeSize (int w, int h) {
        return new Dimension(w * DEFAULT_SIZE, h * DEFAULT_SIZE);
    }
}

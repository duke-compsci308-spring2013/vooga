package games.scroller.stickmansam;

import java.awt.Dimension;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.Pixmap;

public class StickmanSpriteLibrary extends EncapsulatedSpriteLibrary {
    private static final Dimension ENEMY_SIZE = new Dimension(32, 45);
    private static final String PLATFORM_IMAGE = "platform.png";
    private static final String IMAGES = "/games/scroller/stickmansam/images/";

    public static class Platform extends Sprite implements IPlatform {
        private static final Dimension BLOCK_SIZE = new Dimension (32, 32);
        
        public Platform () {
            super(makePixmap(PLATFORM_IMAGE), BLOCK_SIZE);
        }
    }
    
    public static class BigPlatform extends Sprite implements IPlatform {
        private static final Dimension BLOCK_SIZE = new Dimension (128, 128);
        
        public BigPlatform () {
            super(makePixmap(PLATFORM_IMAGE), BLOCK_SIZE);
        }
    }
    
    public static class StickZombie extends GameCharacter implements IEnemy {
        private static final String ZOMBIE_IMAGE = "zombie.png";
        private static final int HEALTH = 60;

        public StickZombie () {
            super(makePixmap(ZOMBIE_IMAGE), ENEMY_SIZE, HEALTH, 0);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleDeath (Level level) {
            //level.removeSprite(this);
        }

    }
    
    public static class StickSpider extends GameCharacter implements IEnemy {
        private static final String SPIDER_IMAGE = "smallspider.png";
        private static final int HEALTH = 60;

        public StickSpider () {
            super(makePixmap(SPIDER_IMAGE), ENEMY_SIZE, HEALTH, 0);
            // TODO
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public static class BigStickSpider extends GameCharacter implements IEnemy {
        private static final String BIG_SPIDER_IMAGE = "bigspider.png";
        private static final int HEALTH = 300;
        private static final Dimension BIG_SPIDER_SIZE = new Dimension (110, 90);

        public BigStickSpider () {
            super(makePixmap(BIG_SPIDER_IMAGE), BIG_SPIDER_SIZE, HEALTH, 0);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public static class Bullet extends GameCharacter {
        private static final String BULLET_IMAGE = "bullet.png";
        private static final Dimension BULLET_SIZE = new Dimension (15, 10);
        private static final int BULLET_DAMAGE = 20;

        public Bullet () {
            super(makePixmap(BULLET_IMAGE), BULLET_SIZE, 1, BULLET_DAMAGE);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
            
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

package games.scroller.stickmansam;

import java.awt.Dimension;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;

public class StickmanSpriteLibrary extends EncapsulatedSpriteLibrary {
    public static final Dimension DEFAULT_SIZE = new Dimension(45, 45);
    private static final String IMAGES = "/src/games/scroller/stickmansam/images/";

    public static class BottomPlatform extends Sprite implements IPlatform {
        public static final String PLATFORM_IMAGE = "platform.png";

        public BottomPlatform (ISpriteView image, Dimension size) {
            super(makePixmap(PLATFORM_IMAGE), DEFAULT_SIZE);
        }
    }
    
    public static class StickZombie extends GameCharacter implements IEnemy {
        public static final String ZOMBIE_IMAGE = "platform.png";

        public StickZombie (ISpriteView image, Dimension size, int health, int damage) {
            super(image, size, health, damage);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleDeath (Level level) {
            level.removeSprite(this);
        }

    }
    
    public static class StickSpider extends GameCharacter implements IEnemy {
        public static final String SPIDER_IMAGE = "platform.png";

        public StickSpider (ISpriteView image, Dimension size, int health, int damage) {
            super(image, size, health, damage);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public static class BigStickSpider extends GameCharacter implements IEnemy {
        public static final String BIG_SPIDER_IMAGE = "platform.png";

        public BigStickSpider (ISpriteView image, Dimension size, int health, int damage) {
            super(image, size, health, damage);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void handleDeath (Level level) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public static class Bullet extends GameCharacter {
        public static final String BULLET_IMAGE = "platform.png";

        public Bullet (ISpriteView image, Dimension size, int health, int damage) {
            super(image, size, health, damage);
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

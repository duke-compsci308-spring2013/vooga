package games.scroller.mr_fish.sprites;

import games.scroller.mr_fish.sprites.player.MrFish;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import util.Location;
import vooga.scroller.extra_resources.inventory.Item;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.library.EncapsulatedSpriteLibrary;
import vooga.scroller.level_management.LevelPortal;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.Locatable;
import vooga.scroller.sprites.movement.TrackPlayer;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;

public class FishLib extends EncapsulatedSpriteLibrary {

    public static final String IMAGE_LOCATION = "/games/scroller/mr_fish/images/";
    public static final Location DEFAULT_CENTER = new Location(0,0);
    private static Random myRandom = new Random();
    
    
    public static class Krill extends Item implements ICollectible {

        private static final Pixmap DEFAULT_IMG = makePixmap(IMAGE_LOCATION,"super_krill.gif");
        private static final Dimension DEFAULT_SIZE = new Dimension(32, 64);
        private static final int HEALTH_GAIN = 5;
        private static final int VALUE = 5;
   
        public Krill(){
            this(DEFAULT_CENTER);
        }
        
        public Krill (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
        }

        @Override
        public int getValue () {
            return VALUE;
        }

        @Override
        public void useItem (MrFish p) {
            p.setHealth(p.getHealth() + HEALTH_GAIN);          
        }  
    }
    
    public static class TreasureChest extends Item implements ICollectible {
        private static final Pixmap DEFAULT_IMG = makePixmap(IMAGE_LOCATION,"treasure_chest.gif");
        private static final Dimension DEFAULT_SIZE = new Dimension(64, 32);
        
        private List<Item> myItems;
        
        public TreasureChest(){
            this(DEFAULT_CENTER);

        }
        
        
        
        private void addItems () {
            
            for(int i = 0; i < 50; ++i){
                //myItems.add(new TreasureChest());
                myItems.add(new Krill());
                myItems.add(new HushPuppies());
            }
            
            myItems.add(new JamesCameron());
            
        }



        public TreasureChest (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
            myItems = new ArrayList<Item>();
            addItems();
            myRandom = new Random();
        }


        @Override
        public int getValue () {
            // TODO Auto-generated method stub
            return 0;
        }


        @Override
        public void useItem (MrFish p) {
            p.addItem(getRandomItem());
            
        }

        private Item getRandomItem () {
            int index = myRandom.nextInt(myItems.size());
            return myItems.get(index);
        }
        
    }
    
    public static class HushPuppies extends Item implements ICollectible {
        
        private static final Pixmap DEFAULT_IMG = makePixmap(IMAGE_LOCATION,"hush_puppies.png");
        private static final Dimension DEFAULT_SIZE = new Dimension(64, 32);
        
        private static final int HEALTH_MULTIPLIER = 10;
        
        public HushPuppies(){
            this(DEFAULT_CENTER);
        }
        
        public HushPuppies (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
            // TODO Auto-generated constructor stub
        }

        @Override
        public int getValue () {
            // TODO Auto-generated method stub
            return 10;
        }
        @Override
        public void useItem (MrFish p) {
            double multiplier = Math.pow(myRandom.nextGaussian()-.5, 2);
            p.takeHit((int)(HEALTH_MULTIPLIER*multiplier));
            }

    }
    
    public static class JamesCameron extends Item implements ICollectible {
        private static final Pixmap DEFAULT_IMG = makePixmap(IMAGE_LOCATION,"james_cameron.png");
        private static final Dimension DEFAULT_SIZE = new Dimension(64, 84);
        private static final int SCORE = 1000;
        
        public JamesCameron(){
            this(DEFAULT_CENTER);
        }
        
        public JamesCameron (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE);
            // TODO Auto-generated constructor stub
        }

        @Override
        public int getValue () {
            // TODO Auto-generated method stub
            return 0;
        }
        @Override
        public void useItem (MrFish p) {
            p.incrementScore(SCORE);
            
        }

    }
    
    public static class Shark extends GameCharacter implements IEnemy {
        private static final Pixmap DEFAULT_IMG = makePixmap(IMAGE_LOCATION,"shark_left.gif");
        private static final Dimension DEFAULT_SIZE = new Dimension(64, 32);
        private static final int DEFAULT_HEALTH = 10;
        private static final int DEFAULT_DAMAGE = 7;
        private static final int SPEED = 30;
        private static final int RADIUS = 300;
        
        private TrackPlayer movement = new TrackPlayer(this, getLocatable(), SPEED, RADIUS);

        
        public Shark(){
            this(DEFAULT_CENTER);
        }
        
        public Shark (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
            initStates();
        }

        private void initStates () {
            // left, right
            
        }

        public void update (double elapsedTime, Dimension bounds) {
            movement.execute();
            super.update(elapsedTime, bounds);
        }

        
        
        @Override
        public void handleDeath (Level level) {
            level.addSprite(new TreasureChest(this.getCenter()));            
        }
        
        @Override
        public void addTarget(Locatable target){
            super.addTarget(target);
            movement.setTarget(target);
        }
        
    }
    
    public static class Baracuda extends GameCharacter implements IEnemy {
        private static final Pixmap DEFAULT_IMG = makePixmap(IMAGE_LOCATION,"baracuda_left.gif");
        private static final Dimension DEFAULT_SIZE = new Dimension(64, 32);
        private static final int DEFAULT_HEALTH = 5;
        private static final int DEFAULT_DAMAGE = 5;
        
        public Baracuda(){
            this(DEFAULT_CENTER);
        }
        
        
        public Baracuda (Location center) {
            super(DEFAULT_IMG, center, DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
            initStates();
        }

        private void initStates () {
            // TODO left, right states
            
        }

        @Override
        public void handleDeath (Level level) {
            level.addSprite(new HushPuppies(this.getCenter()));
            
        }
        
    }
    
    public static class Coral1 extends Sprite implements IPlatform{
        private static final Pixmap IMG1 = makePixmap(IMAGE_LOCATION,"coral1.gif");
        private static final Pixmap IMG2 = makePixmap(IMAGE_LOCATION,"coral2.png");
        private static final Pixmap IMG3 = makePixmap(IMAGE_LOCATION,"coral3.png");
        private static final Pixmap IMG4 = makePixmap(IMAGE_LOCATION,"coral4.gif");
        private static final Pixmap IMG5 = makePixmap(IMAGE_LOCATION,"coral5.png");

        private static final Random RANDOM = new Random();
        
        
        private static final Pixmap[] DEFAULT_IMAGES = {IMG1, IMG2, IMG3, IMG4, IMG5};
        
        private static final Dimension DEFAULT_SIZE = new Dimension(32, 320);
        
        public Coral1(){
            this(DEFAULT_CENTER);
        }
        
        public Coral1 (Location center) {
            super(DEFAULT_IMAGES[RANDOM.nextInt(DEFAULT_IMAGES.length)], center, DEFAULT_SIZE);
        }
        
    }
    
    
    public static class Coral2 extends Sprite implements IPlatform{
        private static final Pixmap IMG1 = makePixmap(IMAGE_LOCATION,"coral1.gif");
        private static final Pixmap IMG2 = makePixmap(IMAGE_LOCATION,"coral2.png");
        private static final Pixmap IMG3 = makePixmap(IMAGE_LOCATION,"coral3.png");
        private static final Pixmap IMG4 = makePixmap(IMAGE_LOCATION,"coral4.gif");
        private static final Pixmap IMG5 = makePixmap(IMAGE_LOCATION,"coral5.png");

        private static final Random RANDOM = new Random();
        
        
        private static final Pixmap[] DEFAULT_IMAGES = {IMG1, IMG2, IMG3, IMG4, IMG5};
        
        private static final Dimension DEFAULT_SIZE = new Dimension(320, 32);
        
        public Coral2(){
            this(DEFAULT_CENTER);
        }
        
        public Coral2 (Location center) {
            super(DEFAULT_IMAGES[RANDOM.nextInt(DEFAULT_IMAGES.length)], center, DEFAULT_SIZE);
        }
        
    }

    public static class StarPortal extends LevelPortal {
        private static final Pixmap DEFAULT_IMGAGE = makePixmap(IMAGE_LOCATION,"porthole.gif");
        private static final Dimension DEFAULT_SIZE = new Dimension(64, 64);

        
        
        @Override
        public ISpriteView initView () {
            // TODO Auto-generated method stub
            return DEFAULT_IMGAGE;
        }

        @Override
        public Dimension initSize () {
            // TODO Auto-generated method stub
            return DEFAULT_SIZE;
        }
        
    }
    
    public static class Fireball extends GameCharacter {

        private static final Pixmap DEFAULT_IMGAGE = makePixmap(IMAGE_LOCATION,"fireball.png");
        private static final Dimension DEFAULT_SIZE = new Dimension(15, 15);
        
        private static final int DEFAULT_HEALTH = 1;
        private static final int DEFAULT_DAMAGE = 3;
        private static final int MAX_TIME = 50;
        
        
        private int myTime;
        
        public Fireball (Location center) {
            super(DEFAULT_IMGAGE, center, DEFAULT_SIZE, DEFAULT_HEALTH, DEFAULT_DAMAGE);
            myTime = 0;
        }

        @Override
        public void update (double elapsedTime, Dimension bounds) {
            super.update(elapsedTime, bounds);
            myTime += 1;
            if(myTime >= MAX_TIME){
                this.setHealth(0);
            }
        }
        
        @Override
        public void handleDeath (Level level) {
            
            
        }
        
    }

    
}
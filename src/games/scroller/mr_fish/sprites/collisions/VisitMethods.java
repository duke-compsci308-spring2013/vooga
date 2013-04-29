package games.scroller.mr_fish.sprites.collisions;

import games.scroller.mr_fish.sprites.FishLib.Baracuda;
import games.scroller.mr_fish.sprites.FishLib.Fireball;
import games.scroller.mr_fish.sprites.FishLib.Shark;
import games.scroller.mr_fish.sprites.player.MrFish;
import util.Vector;
import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.inventory.Item;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;

/**
 * This is where you want to place your visit methods. To keep this class as 
 * clean as possible, I don't actually handle the collision logic here. I handle 
 * that logic in a Game specific Collisions class. You can, of course, handle all 
 * collisions in these visit() methods if you prefer. 
 * 
 * @author Jay Wang
 *
 */
public class VisitMethods extends VisitLibrary{

    private CollisionDirection direction = new CollisionDirection();


    public void visit (GameCharacter player, IPlatform platform) {
        Direction collisionType = direction.collisionDirection(player, platform);
        if (collisionType == null) return;

        switch (collisionType) {
            case TOP:
                player.setCenter(player.getX(), platform.getTop() - (player.getHeight() / 2));
                Vector v = player.getVelocity().getComponentVector((double) Sprite.DOWN_DIRECTION);
                v.negate();
                player.addVector(v);

                break;
            case BOTTOM:
                player.setCenter(player.getX(), platform.getBottom() + (player.getHeight() / 2));

                Vector up = player.getVelocity().getComponentVector(Sprite.UP_DIRECTION);
                up.negate();
                player.addVector(up);

                break;
            case LEFT:
                player.setCenter(platform.getLeft() - (player.getWidth() / 2), player.getY());
                Vector l = player.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
                l.negate();
                player.addVector(l);

                break;
            case RIGHT:
                player.setCenter(platform.getRight() + (player.getWidth() / 2), player.getY());

                Vector r = player.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
                r.negate();
                player.addVector(r);

                break;
            default:
                break;
        }
    }
    
    public void visit (MrFish fish, Item collectible) {
        fish.addItem(collectible);
        collectible.setHealth(0);
    }
        
    public void visit (MrFish fish, Shark enemy) {
            fish.takeHit(enemy.getHit());
            fish.setCenter(enemy.getCenter().x + enemy.getWidth(), enemy.getCenter().y + enemy.getHeight());    
    }
    
    public void visit (MrFish fish, Baracuda enemy) {
        fish.takeHit(enemy.getHit());
        fish.setCenter(enemy.getCenter().x + enemy.getWidth(), enemy.getCenter().y + enemy.getHeight());    
}
    
    public void visit (Player player, IDoor levelPortal) {
        levelPortal.goToNextLevel();
    }
    
    public void visit (Fireball fire, IEnemy enemy){
        enemy.takeHit(fire.getHit());
        fire.setHealth(0);
    }
    

}

package games.scroller.letteradventure;

import games.scroller.letteradventure.SpriteLibrary.Projectile;
import util.Vector;
import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;

/**
 * These are all the interactions between sprites in LetterAdventure.
 * 
 * @author Ellango, David Liu
 *
 */
public class VisitMethods extends VisitLibrary {

    /**
     * when a player interacts with a platform, it moves on it.
     * 
     * @param player
     * @param platform
     */
    public void visit (Player player, IPlatform platform) {
        Direction collisionType =  new CollisionDirection().collisionDirection(player, platform);
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
    
    /**
     * Player takes damage when colliding with an enemy.
     * 
     * @param player
     * @param enemy
     */
    public void visit (Player player, IEnemy enemy) {
        player.takeHit(enemy.getHit());
    }
        
    /**
     * Enemy takes damage when gets hit by projectile
     * 
     * @param enemy
     * @param projectile
     */
    public void visit (IEnemy enemy, Projectile projectile) {
        enemy.takeHit(projectile.getHit());
    }
    
    /**
     * When player reaches portal, goes to next level.
     * @param player
     * @param levelPortal
     */
    public void visit (Player player, IDoor levelPortal) {
        levelPortal.goToNextLevel();
    }

}

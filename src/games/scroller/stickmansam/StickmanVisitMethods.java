package games.scroller.stickmansam;

import games.scroller.stickmansam.StickmanSpriteLibrary.Bullet;
import games.scroller.stickmansam.StickmanSpriteLibrary.StickZombie;
import util.Vector;
import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;


/**
 * Visit methods for the Stickman Sam game.
 * 
 * @author David Winegar
 * 
 */
public class StickmanVisitMethods extends VisitLibrary {

    /**
     * Visits the player with a platform, deals with collisions.
     * 
     * @param player player
     * @param platform platform
     */
    public void visit (StickmanPlayer player, IPlatform platform) {
        Direction collisionType = new CollisionDirection().collisionDirection(player, platform);
        player.setGravity(true);
        if (collisionType == null) return;

        switch (collisionType) {
            case TOP:
                player.setCenter(player.getX(), platform.getTop() - (player.getHeight() / 2));
                Vector v = player.getVelocity().getComponentVector(Sprite.DOWN_DIRECTION);
                v.negate();
                player.addVector(v);

                break;
            case BOTTOM:
                player.setCenter(player.getX(), platform.getBottom() + (player.getHeight() / 2));

                // set gravity off when standing on solid ground
                player.setGravity(false);

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
     * Deals with collisions between enemy and player, player cannot move into enemy and player
     * takes damage.
     * 
     * @param player player
     * @param enemy enemy
     */
    public void visit (StickmanPlayer player, IEnemy enemy) {
        player.takeHit(enemy.getHit());

        Direction collisionType = new CollisionDirection().collisionDirection(player, enemy);
        if (collisionType == null) return;

        switch (collisionType) {
            case LEFT:
                player.setCenter(enemy.getLeft() - (player.getWidth() / 2), player.getY());
                Vector l = player.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
                l.negate();
                player.addVector(l);

                break;
            case RIGHT:
                player.setCenter(enemy.getRight() + (player.getWidth() / 2), player.getY());

                Vector r = player.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
                r.negate();
                player.addVector(r);

                break;
            default:
                break;
        }
    }

    /**
     * When bullet hits element, the element takes damage and the bullet is destroyed.
     * 
     * @param element element
     * @param bullet bullet
     */
    public void visit (GameCharacter element, Bullet bullet) {
        element.takeHit(bullet.getHit());
        bullet.takeHit(2);
    }

    /**
     * Go to next level on hitting portal
     * 
     * @param player player
     * @param levelPortal levelportal
     */
    public void visit (Player player, IDoor levelPortal) {
        levelPortal.goToNextLevel();
    }

    /**
     * don't allow enemies to fall through platforms.
     * 
     * @param enemy enemy
     * @param platform platform
     */
    public void visit (StickZombie enemy, IPlatform platform) {
        Direction collisionType = new CollisionDirection().collisionDirection(enemy, platform);
        if (collisionType == null) return;

        switch (collisionType) {
            case TOP:
                enemy.setCenter(enemy.getX(), platform.getTop() - (enemy.getHeight() / 2));
                Vector v = enemy.getVelocity().getComponentVector(Sprite.DOWN_DIRECTION);
                v.negate();
                enemy.addVector(v);

                break;
            case BOTTOM:
                enemy.setCenter(enemy.getX(), platform.getBottom() + (enemy.getHeight() / 2));

                Vector up = enemy.getVelocity().getComponentVector(Sprite.UP_DIRECTION);
                up.negate();
                enemy.addVector(up);

                break;
            case LEFT:
                enemy.setCenter(platform.getLeft() - (enemy.getWidth() / 2), enemy.getY());
                Vector l = enemy.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
                l.negate();
                enemy.addVector(l);

                break;
            case RIGHT:
                enemy.setCenter(platform.getRight() + (enemy.getWidth() / 2), enemy.getY());

                Vector r = enemy.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
                r.negate();
                enemy.addVector(r);

                break;
            default:
                break;
        }
    }
}

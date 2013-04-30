package games.scroller.stickmansam;

import games.scroller.stickmansam.StickmanSpriteLibrary.Bullet;
import util.Vector;
import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;

public class StickmanVisitMethods extends VisitLibrary {

    
    public void visit (StickmanPlayer player, IPlatform platform) {
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

                player.
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
    
    public void visit (Player player, IEnemy enemy) {
        player.takeHit(enemy.getHit());
    }
    
    public void visit (IEnemy enemy, Bullet bullet) {
        enemy.takeHit(bullet.getHit());
    }
    
    public void visit (Player player, IDoor levelPortal) {
        levelPortal.goToNextLevel();
    }
    
    public void visit (IEnemy enemy, IPlatform platform) {
        Direction collisionType =  new CollisionDirection().collisionDirection(enemy, platform);
        if (collisionType == null) return;

        switch (collisionType) {
            case TOP:
                enemy.setCenter(enemy.getX(), platform.getTop() - (enemy.getHeight() / 2));
                Vector v = enemy.getVelocity().getComponentVector((double) Sprite.DOWN_DIRECTION);
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

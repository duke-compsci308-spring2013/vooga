package vooga.scroller.extra_resources.sprite_interfaces;

import util.Vector;
import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.Direction;


/**
 * This class handles standard player collisions and is used by default 
 * in encapsulated libraries when a designer doesn't specify explicit 
 * visit methods and uses the standard interfaces provided.
 * 
 * @author Jay Wang, Dagbedji Fagnisse
 */
public class StandardPlayerCollisions extends VisitLibrary {

    private CollisionDirection direction = new CollisionDirection();

    public void visit (Player player, IDoor levelPortal) {
        levelPortal.goToNextLevel();
    }

    public void visit (Player player, IEnemy enemy) {
        if (direction.collisionDirection(player, enemy).equals(Direction.TOP)) {
            enemy.takeHit(player.getHit());
        }
        else {
            player.takeHit(enemy.getHit());
        }
    }

    public void visit (Player player, ICollectible collectible) {
        player.incrementScore(collectible.getValue());
        collectible.takeHit(player.getHit());
    }

    public void visit (Player player, IPlatform platform) {

        Direction collisionType = direction.collisionDirection(player, platform);

        if (collisionType == null) return;

        switch (collisionType) {
            case TOP:
                player.setCenter(player.getX(), platform.getTop() - (player.getHeight() / 2));
                Vector v = player.getVelocity().getComponentVector((double) Sprite.DOWN_DIRECTION);
                v.negate();
                player.addVector(v);

//                Vector right = player.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
//                Vector left = player.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
//
//                right.negate();
//                right.scale(FRICTION);
//                left.negate();
//                left.scale(FRICTION);
//                player.addVector(right);
//                player.addVector(left);
//
//                Vector sLeft = platform.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
//                sLeft.scale(FRICTION);
//                Vector sRight = platform.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
//                sRight.scale(FRICTION);
//
//                player.addVector(sRight);
//                player.addVector(sLeft);

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

}

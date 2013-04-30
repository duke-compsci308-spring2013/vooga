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

    public void visit (Sprite sp, IPlatform platform) {

        Direction collisionType = direction.collisionDirection(sp, platform);

        if (collisionType == null) return;

        switch (collisionType) {
            case TOP:
                sp.setCenter(sp.getX(), platform.getTop() - (sp.getHeight() / 2));
                Vector v = sp.getVelocity().getComponentVector((double) Sprite.DOWN_DIRECTION);
                v.negate();
                sp.addVector(v);

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
                sp.setCenter(sp.getX(), platform.getBottom() + (sp.getHeight() / 2));

                Vector up = sp.getVelocity().getComponentVector(Sprite.UP_DIRECTION);
                up.negate();
                sp.addVector(up);

                break;
            case LEFT:
                sp.setCenter(platform.getLeft() - (sp.getWidth() / 2), sp.getY());
                Vector l = sp.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
                l.negate();
                sp.addVector(l);

                break;
            case RIGHT:
                sp.setCenter(platform.getRight() + (sp.getWidth() / 2), sp.getY());

                Vector r = sp.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
                r.negate();
                sp.addVector(r);

                break;
            default:
                break;
        }
    }

}

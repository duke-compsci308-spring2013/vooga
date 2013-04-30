package games.scroller.superMario.sprites;

import games.scroller.superMario.sprites.player.Mario;
import util.Vector;
import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.util.Direction;

public class SuperMarioCollisions {
    
    private CollisionDirection direction = new CollisionDirection();

    public void marioAndCollectible (Mario mario, ICollectible collectible) {
        mario.incrementScore(collectible.getValue());
        collectible.takeHit(mario.getHit());
    }

    public void marioAndEnemy (Mario mario, IEnemy enemy) {
        if (direction.collisionDirection(mario, enemy).equals(Direction.TOP)) {
            enemy.takeHit(mario.getHit());
        }
        else {
            mario.takeHit(enemy.getHit());
        }
        
    }

    public void marioAndLevelPortal (IDoor levelPortal) {
        levelPortal.goToNextLevel();
        
    }

    public void marioAndPowerUp (Mario mario, IPowerUp p) {
        mario.changeState(p.getStateID());
    }

    public void marioAndPlatform (Sprite sprite, IPlatform platform) {
        Direction collisionType = direction.collisionDirection(sprite, platform);
    
        if (collisionType == null) return;
    
        switch (collisionType) {
            case TOP:
                sprite.setCenter(sprite.getX(), platform.getTop() - (sprite.getHeight() / 2));
                Vector v = sprite.getVelocity().getComponentVector((double) Sprite.DOWN_DIRECTION);
                v.negate();
                sprite.addVector(v);
                break;
                
            case BOTTOM:
                sprite.setCenter(sprite.getX(), platform.getBottom() + (sprite.getHeight() / 2));
    
                Vector up = sprite.getVelocity().getComponentVector(Sprite.UP_DIRECTION);
                up.negate();
                sprite.addVector(up);
                break;
                
            case LEFT:
                sprite.setCenter(platform.getLeft() - (sprite.getWidth() / 2), sprite.getY());
                Vector l = sprite.getVelocity().getComponentVector(Sprite.LEFT_DIRECTION);
                l.negate();
                sprite.addVector(l);
                break;
                
            case RIGHT:
                sprite.setCenter(platform.getRight() + (sprite.getWidth() / 2), sprite.getY());
    
                Vector r = sprite.getVelocity().getComponentVector(Sprite.RIGHT_DIRECTION);
                r.negate();
                sprite.addVector(r);
                break;
                
            default:
                break;
        }
    }

}

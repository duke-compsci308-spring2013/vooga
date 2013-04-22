package vooga.scroller.marioGame.spritesDefinitions;


import vooga.scroller.collision_manager.CollisionDirection;
import vooga.scroller.marioGame.spritesDefinitions.players.MarioCollisions;
import vooga.scroller.sprites.interfaces.ICollectible;
import vooga.scroller.sprites.interfaces.IEnemy;
import vooga.scroller.sprites.interfaces.ILevelPortal;
import vooga.scroller.sprites.interfaces.IPlatform;
import vooga.scroller.sprites.interfaces.IPlayer;
import vooga.scroller.util.Direction;

public class VisitMethods {

    /**
     * Begin the long slew of visit() methods... 
     * Thinking about ways to refactor/repackage this.
     * 
     */
       
    private CollisionDirection direction = new CollisionDirection();
    private MarioCollisions collisions = new MarioCollisions();

    public void visit (IPlayer player, IPlatform platform) {
        collisions.marioAndPlatformCollision(player, platform);
    }
    
    public void visit (IPlayer player, ICollectible coin) {
        player.incrementScore(coin.getValue());
        coin.takeHit(player.getHit());
    }
        
    public void visit (IPlayer player, IEnemy enemy) {
        if (direction.collisionDirection(player, enemy).equals(Direction.TOP)) {
            enemy.takeHit(player.getHit());
        }
        else {
            player.takeHit(enemy.getHit());
        }        
    }
    
    public void visit (IPlayer player, ILevelPortal levelPortal) {
        levelPortal.goToNextLevel(player.getPlayer());
    }
}

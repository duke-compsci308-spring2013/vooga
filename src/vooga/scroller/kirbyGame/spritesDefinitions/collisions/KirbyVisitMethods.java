package vooga.scroller.kirbyGame.spritesDefinitions.collisions;

import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib.CutterEnemy;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib.LaserEnemy;
import vooga.scroller.kirbyGame.spritesDefinitions.players.Kirby;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.InhaleLeftState;
import vooga.scroller.kirbyGame.spritesDefinitions.players.states.InhaleRightState;
import vooga.scroller.marioGame.spritesDefinitions.players.Mario;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;

/**
 * This is where you want to place your visit methods. To keep this class as 
 * clean as possible, I don't actually handle the collision logic here. I handle 
 * that logic in a Game specific Collisions class. You can, of course, handle all 
 * collisions in these visit() methods if you prefer. 
 * <br>
 * <br>
 * Note that your game's VisitMethods needs to extend VisitLibrary. This is because 
 * the Collision Manager is going to exepect a VisitLibrary object in its constructor. 
 * 
 * @author Jay Wang
 *
 */
public class KirbyVisitMethods extends VisitLibrary {

       
    private KirbyCollisions collisions = new KirbyCollisions();

    //FILL VISIT METHODS
    
    public void visit (Kirby kirby, IPlatform platform) {
        collisions.kirbyAndPlatformCollision(kirby, platform);
    }
    
    
    public void visit (Kirby kirby, CutterEnemy cutterEnemy) {
        if (kirby.getCurrentStateID() == InhaleLeftState.STATE_ID || kirby.getCurrentStateID() == InhaleRightState.STATE_ID) {
            //kirby consumes enemy
            cutterEnemy.takeHit(kirby.getHit());
            kirby.setConsumedEnemy(cutterEnemy);
            kirby.startFullState();
            
        }
        
        else {
            //enemy hurts kirby 
        }
    }
    
}

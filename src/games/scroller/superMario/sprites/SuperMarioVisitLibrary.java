package games.scroller.superMario.sprites;

import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.extra_resources.sprite_interfaces.IEnemy;
import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;


public class SuperMarioVisitLibrary extends VisitLibrary {

    private SuperMarioCollisions myCollisions = new SuperMarioCollisions();

    public void visit (Sprite sprite, IPlatform platform) {
        myCollisions.spriteAndPlatform(sprite, platform);
    }

    public void visit (Mario mario, ICollectible collectible) {
        myCollisions.marioAndCollectible(mario, collectible);
    }

    public void visit (Mario mario, IPowerUp p) {
        myCollisions.marioAndPowerUp(mario, p);
        myCollisions.marioAndCollectible(mario, p);
    }

    public void visit (Mario mario, IEnemy enemy) {
        myCollisions.marioAndEnemy(mario, enemy);
    }

    public void visit (Mario mario, IDoor levelPortal) {
        myCollisions.marioAndLevelPortal(levelPortal);
    }
    
    public void visit (Mario mario, IItemBlock block) {
        myCollisions.spriteAndPlatform(mario, block);
        myCollisions.marioAndItemBlock(mario,block);
    }
}

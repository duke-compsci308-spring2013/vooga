package games.scroller.superMario.sprites;

import vooga.scroller.extra_resources.sprite_interfaces.IPlatform;
import vooga.scroller.sprites.Sprite;


public interface IItemBlock extends IPlatform {

    public Sprite createSprite (int myState);

    public int getHits ();

    public void handleHit (int state);

}

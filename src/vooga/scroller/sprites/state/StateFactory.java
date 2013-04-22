package vooga.scroller.sprites.state;

import java.util.HashMap;
import java.util.Map;
import vooga.scroller.marioGame.spritesDefinitions.players.InvisibleState;
import vooga.scroller.util.Sprite;

public class StateFactory {

    private Sprite mySprite;
    
    public StateFactory(Sprite sprite) {
        mySprite = sprite;
    }

    public Map<Integer, State> createStateMap () {
        // TODO: read in states from files and use reflection to create
        
        Map<Integer, State> states = new HashMap<Integer, State>();
        
        InvisibleState invisibleMario= new InvisibleState(mySprite);
        
        states.put(1, invisibleMario);
       
        return states;
    }
    
    
    
}

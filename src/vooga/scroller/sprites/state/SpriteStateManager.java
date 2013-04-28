package vooga.scroller.sprites.state;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import vooga.scroller.sprites.Sprite;


/**
 * Limitations: can't do the same state multiple times.
 * 
 * @author Scott Valentine
 *
 */
public class SpriteStateManager {

    
    //private static final SpriteState DEFAULT_STATE = new DefaultSpriteState();

    
    @SuppressWarnings("rawtypes")
    private Map<Integer, SpriteState> myStates;
    @SuppressWarnings("rawtypes")
    private Queue<SpriteState> myActiveStates;
     
    
    
    /**
     * Initializes a state manager with the default state.
     * 
     * @param sprite is the sprite for which this state manager controls the states.
     */
    @SuppressWarnings("rawtypes")
    public SpriteStateManager(Sprite sprite) {
        myActiveStates = new PriorityQueue<SpriteState>();
        myStates = new HashMap<Integer, SpriteState>();
        SpriteState<Sprite> defaultState = new DefaultSpriteState(sprite);
        myStates.put(DefaultSpriteState.DEFAULT_ID, defaultState);
        myActiveStates.add(defaultState);
    }
    
    
    /**
     * Update the active states in this manager.
     * 
     * @param elapsedTime
     * @param bounds
     */
    @SuppressWarnings("rawtypes")
    public void update (double elapsedTime, Dimension bounds) {
        Iterator<SpriteState> it = myActiveStates.iterator();
        
        while(it.hasNext()){
            it.next().update(elapsedTime, bounds);
        }

//        for(SpriteState spState: myActiveStates){
//            spState.update(mySprite, elapsedTime, bounds);
//        }
    }

    /**
     * Paint the states active in this manager.
     * 
     * @param pen
     */
    public void paint (Graphics2D pen) {
        // only paint the first state (painting multiple states does not work).
       
        @SuppressWarnings("rawtypes")
        SpriteState sp = myActiveStates.peek();
        sp.paint(pen, 0);
    }
    
    /**
     * Activates the specified state.
     * @param stateID
     */
    public void activateState(int stateID){
        @SuppressWarnings("rawtypes")
        SpriteState state = myStates.get(stateID);
        if(!myActiveStates.contains(state) && state != null){
            state.activate();
            myActiveStates.add(state);
        }
    }

    /**
     * Deactivates the specified state. If stateID is not valid, remove no state.
     * @param stateID
     */
    @SuppressWarnings("rawtypes")
    public void deactivateState(int stateID) {
        SpriteState state = myStates.get(stateID);
        if(state != null && myActiveStates.contains(state)){
            state.deactivate();
            myActiveStates.remove(myStates.get(stateID));
        }
    }
    
    /**
     * Adds a new possible state to this manager.
     * @param stateID
     * @param state
     */
    @SuppressWarnings("rawtypes")
    public void addState(int stateID, SpriteState state){
        myStates.put(stateID, state);
    }
    
    /**
     * Removes a possible state fromt this manager.
     * 
     * @param stateID
     */
    public void removeState(int stateID){
        myStates.remove(stateID);
    }
}

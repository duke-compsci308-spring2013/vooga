package vooga.scroller.level_management;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import util.Location;
import util.input.Input;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.View;
import vooga.scroller.example.LevelFactory;
import vooga.scroller.level_editor.Level;

/**
 * Manages the flow and order of levels in gameplay.
 * 
 * @author Scott Valentine
 *
 */
public class LevelManager {

    private static final String DEFAULT_INPUT_CONTROLS = "vooga/scroller/resources/controls/SplashMapping";
    
    private Input myInput;
    private Level myInitialLevel;
    private Level myCurrentLevel;
    private View myView;
    
        
    /**
     * Creates a new level manager based on the view used by individual levels.
     * @param view to be used in constructing individual levels.
     */
    public LevelManager(ScrollingManager sm, View view) {   
        myView = view;
        LevelFactory lf = new LevelFactory(this, sm, view);
        myInitialLevel = lf.generateLevels();        
        //myCurrentLevel = myLevels.get(DEFAULT_START_LEVEL_ID); 
        myInput = new Input(DEFAULT_INPUT_CONTROLS, view);
        setCurrentLevel(myInitialLevel);
    }
    
    /**
     * Gives the current level.
     * 
     * @return The current level
     */
    public Level getCurrentLevel() {
        return myCurrentLevel;
    }
    
    /**
     * Sets the current level to the specified ID.
     * 
     * @param id of the level to become the current level.
     */
    public void setCurrentLevel(Level level) {
        if(myCurrentLevel != null){
            myCurrentLevel.removeInputListeners(myInput);
            Player p = myCurrentLevel.getPlayer();
            myCurrentLevel = level;
            myCurrentLevel.addPlayer(p);
        }
        else{
            myCurrentLevel = level;
        }
        myCurrentLevel.addInputListeners(myInput);
    }
    
    /**
     * Map a door to a starting point. Bind door to this level manager.
     */
    public void put(IDoor door, Level nextLevel) {
        door.setNextLevel(nextLevel);
        door.setManager(this);
    }
    
    public void updateLevel(double elapsedTime, Dimension bounds) {        
        myCurrentLevel.update(elapsedTime, bounds, myView);
    }
}


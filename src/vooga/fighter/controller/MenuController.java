package vooga.fighter.controller;

import util.Location;
import util.input.Input;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.ControllerDelegate;
import vooga.fighter.controller.GameInfo;
import vooga.fighter.controller.LevelController;
import vooga.fighter.model.*;
import vooga.fighter.util.Paintable;
import vooga.fighter.view.Canvas;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;


/**
 * 
 * @author Jerry Li and Jack Matteucci
 * 
 */
public class MenuController extends Controller {

    private static final String INPUT_PATHWAY = "PATHWAY";
    private GameInfo myGameInfo;

    public MenuController (String name, Canvas frame) {
        super(name, frame);
    }
	
    public MenuController(String name, Canvas frame, ControllerDelegate manager, 
    		GameInfo gameinfo) {
    	super(name, frame, manager, gameinfo);
    }
    
    public void loadMode() {
        List<Integer> characterNames = myGameInfo.getCharacters();
        int mapID = myGameInfo.getMapName();
        Mode temp = new LevelMode(this, characterNames, mapID);
        setMode(temp);
    }


    /**
     * Checks special occurences of game state.
     */
    public void notifyEndCondition () {
       
    }
    /**
     * Checks special occurences of game state.
     */
    public void notifyEndCondition(String string) {
        
    }


    @Override
    public Controller getController (ControllerDelegate delegate, GameInfo gameinfo) {
        return new MenuController(super.getName(), super.getView(),
                                   delegate, gameinfo);
    }

    @Override
    protected Input makeInput () {
        return new Input(INPUT_PATHWAY, super.getView());
    }

    

}

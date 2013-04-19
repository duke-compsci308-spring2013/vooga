package vooga.fighter.controller;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import util.Location;
import util.input.*;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.Mode;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.Mode;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.util.Paintable;
import vooga.fighter.view.Canvas;


/**
 * 
 * @author Jerry Li
 * 
 * @Modified by Jack Matteucci
 * 
 */

public abstract class LevelController extends Controller {
    private static final String INPUT_PATHWAY = "vooga.fighter.config.leveldefault";
    private List<CharacterObject> myInputObjects;

    public LevelController (String name, Canvas frame) {
        super(name, frame);
    }

    public LevelController(String name, Canvas frame, ControllerDelegate manager, 
                GameInfo gameinfo) {
        super(name, frame, manager, gameinfo);
        setInput(manager.getInput());
        getInput().replaceMappingResourcePath(INPUT_PATHWAY);
        getInput().addListenerTo(this);
        DisplayLoopInfo gameLoopInfo = new GameLoopInfo((LevelMode) getMode());
        setLoopInfo(gameLoopInfo);
    }

    
    public void loadMode() {
        List<String> characterNames = getGameInfo().getCharacters();
        String mapID = getGameInfo().getMapName();
        LevelMode temp = new LevelMode(this, characterNames, mapID);
        setMode((Mode) temp);
        myInputObjects = temp.getMyCharacterObjects();
    }
    
    protected List<CharacterObject> getInputObjects(){
    	return myInputObjects;
    }
   
   
    public void removeListener(){
    	super.removeListener();
    	getInput().removeListener(this);
    }
}
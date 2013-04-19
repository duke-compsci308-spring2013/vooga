package vooga.fighter.controller;

import util.Location;
import util.input.AlertObject;
import util.input.Input;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import util.input.PositionObject;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.ControllerDelegate;
import vooga.fighter.controller.GameInfo;
import vooga.fighter.controller.OneVOneController;
import vooga.fighter.model.*;
import vooga.fighter.model.objects.MouseClickObject;
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

public abstract class MenuController extends Controller {

    private static final String INPUT_PATHWAY = "vooga.fighter.config.menudefault";

    public MenuController (String name, Canvas frame) {
        super(name, frame);
    }
	
    public MenuController(String name, Canvas frame, ControllerDelegate manager, 
    		GameInfo gameinfo) {
    	super(name, frame, manager, gameinfo);
        setInput(manager.getInput());
        getInput().replaceMappingResourcePath(INPUT_PATHWAY);
        getInput().addListenerTo(this);
    	DisplayLoopInfo LoopInfo =  new DisplayLoopInfo(super.getMode());
    	setLoopInfo(LoopInfo);
    }
    
    public void loadMode() {
        Mode mode = new MenuMode(this, super.getName());
        super.setMode(mode);
    }
    
    public MenuMode getMode(){
    	return (MenuMode) super.getMode();
    }
    
    public void removeListener(){
    	super.removeListener();
    	getInput().removeListener(this);
    }
    

}

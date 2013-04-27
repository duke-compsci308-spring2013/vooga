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
 * controller for selecting game mode (1v1, 2v2)
 * 
 * @author Jerry Li 
 * @author Jack Matteucci
 * 
 */



public class ModeSelectMenuController extends MenuController {

    private static final String FILE_NAME = "config.ModeCharacterNumbers";
    private String myFileName;
    private ResourceBundle myResources;

    /**
     * Initial constructor
     */
    public ModeSelectMenuController () {
        super();

    }

    /**
     * COncrete constructor
     * @param name      controller name 
     * @param frame     canvas
     * @param manager   controllermanager
     * @param gameinfo  gameinfo
     * @param pathway   filepath
     */
    public ModeSelectMenuController(String name, Canvas frame, ControllerDelegate manager, 
                                    GameInfo gameinfo, String pathway) {
        super(name, frame, manager, gameinfo, pathway);
        myFileName = getHardFilePath() + FILE_NAME;
        myResources = ResourceBundle.getBundle(myFileName);
    }

    /**
     * Return concrete controller
     */
    public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String pathway) {
        Controller controller = new ModeSelectMenuController(name, frame, manager, gameinfo, pathway);
        return controller;
    }
    /**
     * Checks this controller's end conditions
     */
    public void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getGameInfo().setGameMode(choice);
        getGameInfo().setNumCharacters(Integer.parseInt(myResources.getString(choice)));    		
        getManager().notifyEndCondition(getMode().getMenusNext(choice));

    }

    /**
     * removes input listener
     */
    public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * check modecondition
     */
    public void checkConditions(){
        for(ModeCondition condition: getConditions())
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
    }



}

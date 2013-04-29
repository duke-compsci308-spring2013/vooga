package games.fighter.JerryJackExample.controller.levels;

import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.view.Canvas;

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
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String pathway) {
        Controller controller = new ModeSelectMenuController(name, frame, manager, gameinfo, pathway);
        return controller;
    }
    /**
     * Checks this controller's end conditions
     */
    @Override
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
    @Override
	public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * check modecondition
     */
    @Override
	public void checkConditions(){
        for(ModeCondition condition: getConditions())
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
    }



}

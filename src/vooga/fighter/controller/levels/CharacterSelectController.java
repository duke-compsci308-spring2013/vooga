package vooga.fighter.controller.levels;

import util.input.Input;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.view.Canvas;



/**
 * A Controller that handles choosing the character
 * @author Jerry Li
 * @author Jack Matteucci
 */


public class CharacterSelectController extends MenuController {

    private int myCharLimit;
    private int myCharIndex;
    private Input myInput;

    /**
     * Initial Constructor
     */
    public CharacterSelectController () {
        super();
    }

    /**
     * Concrete Constructor, two constructors used for reflection purposes
     * @param name      Name of controller
     * @param frame     Canvas
     * @param manager   ControllerManager
     * @param gameinfo  GameInfo
     */
    public CharacterSelectController(String name, Canvas frame, ControllerDelegate manager, 
                                     GameInfo gameinfo, String filepath) {
        super(name, frame, manager, gameinfo, filepath);
        myCharLimit = getGameInfo().getNumCharacters();
        myCharIndex = 0;
    }

    /**
     * returns concrete controller
     */
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String filepath) {
        Controller controller = new CharacterSelectController(name, frame, manager, gameinfo, filepath);
        return controller;
    }

    /**
     * Notifies the ControllerDelegate of a condition
     */
    @Override
	public void notifyEndCondition(String choice) {
        getGameInfo().addCharacters(choice);
        getMode().resetChoice();
        myCharIndex ++;
        if(myCharIndex >= myCharLimit){
            removeListener();
            getManager().notifyEndCondition(getMode().getMenusNext(choice));
        }
    } 

    /**
     * Removes input listener
     */
    @Override
	public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * Checks conditions
     */
    @Override
	public void checkConditions(){
        for(ModeCondition condition: getConditions())
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
    }

}


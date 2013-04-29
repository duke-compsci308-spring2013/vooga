package games.fighter.JerryJackExample.controller.levels;

import games.fighter.JerryJackExample.AdvancedGameInfo;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.controller.levels.CharacterSelectController;
import vooga.fighter.controller.levels.MenuController;
import vooga.fighter.view.Canvas;



/**
 * A Controller that handles choosing the character
 * @author Jerry Li
 * @author Jack Matteucci
 */


public class NumberCharacterSelect extends MenuController {

    /**
     * Initial constructor
     */
    public NumberCharacterSelect() {
        super();
    }

    /**
     * Concrete constructor, called when level is switched to by controllermanager
     * @param name      name of controller
     * @param frame     canvas
     * @param manager   ControllerManager
     * @param gameinfo  GameInfo
     */
    public NumberCharacterSelect(String name, Canvas frame, ControllerDelegate manager, 
                               GameInfo gameinfo, String pathway) {
        super(name, frame, manager, gameinfo, pathway);
    }

    /**
     * Returns concrete controller, used when level is switched to by controllermanager
     */
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String pathway) {
        return new NumberCharacterSelect(name, frame, manager, gameinfo, pathway);
    }

    /**
     * Notifies the delegate when controller ends
     */
    @Override
	public void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getGameInfo().setNumCharacters(Integer.parseInt(choice));
        getManager().notifyEndCondition(getMode().getMenusNext(choice));
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

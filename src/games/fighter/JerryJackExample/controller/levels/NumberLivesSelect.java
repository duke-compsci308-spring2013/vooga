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


public class NumberLivesSelect extends MenuController {

    /**
     * Initial constructor
     */
    public NumberLivesSelect() {
        super();
    }

    /**
     * Concrete constructor, called when level is switched to by controllermanager
     * @param name      name of controller
     * @param frame     canvas
     * @param manager   ControllerManager
     * @param gameinfo  GameInfo
     */
    public NumberLivesSelect(String name, Canvas frame, ControllerDelegate manager, 
                               GameInfo gameinfo, String pathway) {
        super(name, frame, manager, gameinfo, pathway);
    }

    /**
     * Returns concrete controller, used when level is switched to by controllermanager
     */
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String pathway) {
        return new NumberLivesSelect(name, frame, manager, gameinfo, pathway);
    }

    /**
     * Notifies the delegate when controller ends
     */
    @Override
	public void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getAdvancedGameInfo().setMaxLives(Integer.parseInt(choice));
        getManager().notifyEndCondition(getMode().getMenusNext(choice));
    }

    /**
     * Removes input
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
    
    public AdvancedGameInfo getAdvancedGameInfo(){
    	return (AdvancedGameInfo) super.getGameInfo();
    }

}

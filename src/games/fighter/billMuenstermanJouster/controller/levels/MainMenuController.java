package games.fighter.billMuenstermanJouster.controller.levels;

import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.view.Canvas;
import java.util.ResourceBundle;


/**
 * 
 * Details a MainMenuController class, where start game etc. selections are
 * @author Jack Matteucci
 * @author Jerry Li
 * 
 * This is a great class to reference when trying to understand how to extend the
 * menu controller hierarchy
 * 
 */
public class MainMenuController extends MenuController {

    private ResourceBundle myResources;

    /**
     * Initial constructor. Called by ControllerFactory initially
     * using reflection. 
     */
    public MainMenuController () {
        super();
    }
    /**
     * Concrete constructor, used when controller is switched to 
     * @param name      name of controller
     * @param frame     canvas
     * @param manager   ControllerManager
     * @param gameinfo  GameInfo
     */
    public MainMenuController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                              String pathway) {
        super(name, frame, manager, gameinfo, pathway);

        getGameInfo().reset();
        setInput(manager.getInput());
        getInput().addListenerTo(this);
    }

    /**
     * Returns concrete controller, used when controller is switched to
     */
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, 
                                    GameInfo gameinfo, String pathway) {
        return new MainMenuController(name, frame, manager, gameinfo, pathway);
    }


    /**
     * Checks this controller's end conditions and notifies the 
     * delegate
     */
    @Override
    public void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getManager().notifyEndCondition(getMode().getMenusNext(choice));
    }

    /**
     * Removes the input listener
     */
    @Override
	public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * Checks condition
     */
    @Override
	public void checkConditions(){
        for(ModeCondition condition: getConditions()){
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
        }
    }

}

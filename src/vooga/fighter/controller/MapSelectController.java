package vooga.fighter.controller;

import vooga.fighter.controller.Controller;
import vooga.fighter.controller.ControllerDelegate;
import vooga.fighter.controller.GameInfo;
import vooga.fighter.view.Canvas;



/**
 * Details a map select controller, where you can select maps
 * 
 * 
 * @author Jack Matteucci 
 * @author Jerry Li
 */


public class MapSelectController extends MenuController {

    /**
     * Initial constructor
     */
    public MapSelectController () {
        super();
    }

    /**
     * Concrete constructor, called when level is switched to by controllermanager
     * @param name      name of controller
     * @param frame     canvas
     * @param manager   ControllerManager
     * @param gameinfo  GameInfo
     */
    public MapSelectController(String name, Canvas frame, ControllerDelegate manager, 
                               GameInfo gameinfo, String pathway) {
        super(name, frame, manager, gameinfo, pathway);
    }

    /**
     * Returns concrete controller, used when level is switched to by controllermanager
     */
    public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String pathway) {
        return new MapSelectController(name, frame, manager, gameinfo, pathway);
    }

    /**
     * Notifies the delegate when controller ends
     */
    public void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getGameInfo().setMapName(choice);
        getManager().notifyEndCondition(getMode().getMenusNext(choice));
    }

    /**
     * Removes input
     */
    public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * Checks conditions
     */
    public void checkConditions(){
        for(ModeCondition condition: getConditions())
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
    }

}


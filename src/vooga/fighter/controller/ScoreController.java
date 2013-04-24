package vooga.fighter.controller;

import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import util.input.PositionObject;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.ControllerDelegate;
import vooga.fighter.controller.GameInfo;
import vooga.fighter.model.objects.MouseClickObject;
import vooga.fighter.view.Canvas;



/**
 * Details a controller for displaying scores and goes back to menu
 * 
 * Extends a menucontroller, because has menu objects. Only addition 
 * functionality wise is displaying info
 * 
 * 
 * @author Jerry Li
 * 
 */

@InputClassTarget
public class ScoreController extends MenuController {


    /**
     * Initial constructor
     */
    public ScoreController () {
        super();
    }

    /**
     * Concrete constructor;
     * @param name      name of controller
     * @param frame     canvas
     * @param manager   controllermanager
     * @param gameinfo  GameInfo
     * @param filePath  file path
     */
    public ScoreController(String name, Canvas frame, ControllerDelegate manager,
                           GameInfo gameinfo, String filePath) {
        super(name, frame, manager, gameinfo, filePath);
        ScoreInfo scoreInfo = new ScoreInfo(filePath);
        scoreInfo.setWinners(gameinfo.getWinners());
    }

    /**
     * Returns concrete constructor
     */
    public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String filepath) {
        Controller controller = new ScoreController(name, frame, manager, gameinfo, filepath);
        return controller;
    }

    /**
     * Checks this controller's end conditions
     */
    public void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getGameInfo().getCharacters().clear();
        getManager().notifyEndCondition(getMode().getMenusNext(choice));
    }


    /**
     * Creates mouse object when clicked
     */
    @InputMethodTarget(name = "continue")
    public void mouseclick(PositionObject pos)  {
        super.getMode().addObject(new MouseClickObject(pos.getPoint2D(), getHardFilePath()));
    }

    /**
     * Remove input
     */
    public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * check modeconditions
     */
    public void checkConditions(){
        for(ModeCondition condition: getConditions()){
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
        }
    }
}

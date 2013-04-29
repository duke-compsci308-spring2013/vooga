package games.fighter.davidalan.controller.levels;

import util.input.InputClassTarget;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.displayinformation.ScoreInfo;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.view.Canvas;
import vooga.fighter.view.ScoreScreenLayout;



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
        this.getView().setLayout(new ScoreScreenLayout());
        scoreInfo.setWinners(getGameInfo().getWinners());

    }

    /**
     * Returns concrete constructor
     */
    @Override
	public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
                                    String filepath) {
        Controller controller = new ScoreController(name, frame, manager, gameinfo, filepath);
        return controller;
    }

    /**
     * Checks this controller's end conditions
     */
    @Override
	public void notifyEndCondition(String choice) {
    	removeListener();
    	getMode().resetChoice();
    	getGameInfo().getCharacters().clear();
    	getManager().notifyEndCondition(getMode().getMenusNext(choice));
        }

    /**
     * Remove input
     */
    @Override
	public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }

    /**
     * check modeconditions
     */
    @Override
	public void checkConditions(){
        for(ModeCondition condition: getConditions()){
            if(condition.checkCondition(getMode())) notifyEndCondition(getMode().peekChoice());
        }
    }
}

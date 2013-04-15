package vooga.fighter.controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import vooga.fighter.game.LevelMode;
import vooga.fighter.game.Mode;
import vooga.fighter.input.Input;
import vooga.fighter.input.InputClassTarget;
import vooga.fighter.view.Canvas;


/**
 * 
 * @author Jerry Li
 * 
 * @Modified by Jack Matteucci
 * 
 */
@InputClassTarget
public class LevelController extends Controller {
    private static final String INPUT_PATHWAY = "PATHWAY";
    private GameInfo myGameInfo;

    public LevelController (String name, Canvas frame) {
        super(name, frame);
    }
	
    public LevelController(String name, Canvas frame, ControllerDelegate manager, 
    		GameInfo gameinfo) {
    	super(name, frame, manager, gameinfo);
    }
    
	

    
    public void loadMode() {
        List<Integer> characterNames = myGameInfo.getCharacters();
        int mapID = myGameInfo.getMapName();
        Mode temp = new LevelMode(this, characterNames, mapID);
        setMode(temp);
    }


    /**
     * Checks special occurences of game state.
     */
    public void notifyEndCondition() {
        
    }


    @Override
    public Controller getController (ControllerDelegate delegate, GameInfo gameinfo) {
        return new LevelController(super.getName(), super.getView(),
                                   delegate, gameinfo);
    }

    @Override
    protected Input makeInput () {
        return new Input(INPUT_PATHWAY, super.getView());
    }

}
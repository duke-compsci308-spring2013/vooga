package vooga.fighter.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import util.Location;
import util.input.AlertObject;
import util.input.Input;
import util.input.InputMethodTarget;
import vooga.fighter.model.LevelMode;
import vooga.fighter.model.Mode;
import vooga.fighter.util.Paintable;
import vooga.fighter.view.Canvas;

public class ScoreController extends Controller {
    private static final String INPUT_PATHWAY = "vooga.fighter.input.Game1Mapping_en_US";
    
    private Mode myMode;
    private Canvas myCanvas;

    public ScoreController (String name, Canvas frame) {
        super(name, frame);
        System.out.println("score controller started");
    }

    public ScoreController (String name, Canvas frame, ControllerDelegate manager,
                            GameInfo gameinfo) {
        super(name, frame, manager, gameinfo);
        loadMode();
        GameLoopInfo gameLoopInfo = new GameLoopInfo((LevelMode) super.getMode());
        setGameLoopInfo(gameLoopInfo);
        frame.setViewDataSource(gameLoopInfo);
        System.out.println("score controller finished");
    }
    
    
    public void loadMode() {
        List<Integer> characterNames = myGameInfo.getCharacters();
        int mapID = myGameInfo.getMapName();
        Mode temp = new LevelMode(this, characterNames, mapID);
        setMode(temp);
    }
    
    /**
     * Exits program.
     */
    public void exit () {
        System.exit(0);
    }

   
    @Override
    public Controller getController (ControllerDelegate delegate, GameInfo gameinfo) {
        return new ScoreController(super.getName(), super.getView(),
                                   delegate, gameinfo);
    }

    @Override
    protected Input makeInput () {
        Input input = new Input(INPUT_PATHWAY, super.getView());
        input.addListenerTo(this);
        return input;
    }
    
    @InputMethodTarget(name = "Exit_Game")
    public void playerOneJumpInput (AlertObject alObj)  {
        System.exit(0);
    }
    
    @InputMethodTarget(name = "Restart")
    public void playerOneLeftInput (AlertObject alObj) {
        System.out.println("restarting");
        notifyEndCondition();
        
    }

    @Override
    public void notifyEndCondition () {
        System.out.println("score controller notify end is working");
        myGameInfo.setCharacter(0, 1);
        myGameInfo.setCharacter(1, 2);
        myGameInfo.setMapName(1);
        myManager.notifyEndCondition("Test");
        
    }

	@Override
	public void notifyEndCondition(String endCondition) {
		// TODO Auto-generated method stub
		
	}
   

}

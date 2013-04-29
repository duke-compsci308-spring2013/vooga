package games.fighter.JerryJackExample.controller.levels;



import java.util.List;
import java.util.ResourceBundle;
import util.Vector;
import util.input.*;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.GameManager;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.controller.levels.LevelController;
import vooga.fighter.controller.levels.OneVOneController;
import vooga.fighter.forces.Force;
import vooga.fighter.forces.ForceFactory;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.util.CollisionDetector;
import vooga.fighter.view.Canvas;
import vooga.fighter.view.FourPlayerMatchGameLayout;


/**
 * A 1v1 fighting game instance
 * 
 * @author Jerry Li
 * @author by Jack Matteucci
 * 
 * Great class to reference when subclasing level controller 
 */
@InputClassTarget
public class OneVOne extends OneVOneController {
	    private static final String INPUT_PATHWAY = "config.leveldefault";
	    private static final String SCORESCREEN = "ScoreScreen";
	    private String myInputPathway;
	    private String myScorePathway;
	    private List<Force> myForces;
	    private ResourceBundle myResources;
	    private CollisionDetector myCollisionDetector;
	    private int myPlayerOneTicker;
	    private int myPlayerTwoTicker;

	    /**
	     * Initial constructor
	     */
	    public OneVOne() {
	        super();
	    }   

	    /**
	     * Concrete constructor
	     * @param name      name of controller
	     * @param frame     frame
	     * @param manager   ControllerManager
	     * @param gameinfo  GameInfo
	     * @param filepath  FilePath
	     */
	    public OneVOne(String name, Canvas frame, ControllerDelegate manager, 
	    		GameInfo gameinfo, String filepath) {
	    	super(name, frame, manager, gameinfo, filepath);
	    	myCollisionDetector = new CollisionDetector();
	    }

	    /**
	     * Return concrete controller
	     */
	    @Override
		public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
	                                    String filepath) {
	        Controller controller = new OneVOne(name, frame, manager, gameinfo, filepath);
	        myPlayerOneTicker = 0;
	        myPlayerTwoTicker = 0;
	        return controller;
	    }

	    /**
	     * Details movement inputs
	     * @param alObj
	     */
	    @InputMethodTarget(name = "player1_jump")
	    public void playerOneJumpInput (AlertObject alObj)  {
	    	for(GameObject object : getMode().getMyObjects()){
	    		if(object instanceof EnvironmentObject && 
	    				myCollisionDetector.hitTop(object.getCurrentState().getCurrentRectangle(),
	    						getInputObjects().get(0).getCurrentState().getCurrentRectangle() )){
	    						getInputObjects().get(0).jump();
	    						break;
	    		}
	    	}
	    }
	    @Override
	    @InputMethodTarget(name = "player2_jump")
	    public void playerTwoJumpInput (AlertObject alObj)  {
	    	for(GameObject object : getMode().getMyObjects()){
	    		if(object instanceof EnvironmentObject && 
	    				myCollisionDetector.hitTop(object.getCurrentState().getCurrentRectangle(),
	    						getInputObjects().get(1).getCurrentState().getCurrentRectangle() )){
	    						getInputObjects().get(1).jump();
	    						break;
	    		}
	    	}
	    }
	    @Override
	    @InputMethodTarget(name = "player1_attack")
	    public void playerOneAttackInput (AlertObject alObj)  {
	    		if(myPlayerOneTicker>10){
	    			AttackObject attack = getInputObjects().get(0).attack("weakPunch");
	    			getMode().addObject(attack);
	    			myPlayerOneTicker=0;
	    		}
	    }
	    @Override
	    @InputMethodTarget(name = "player2_attack")
	    public void playerTwoAttackInput (AlertObject alObj)  {
    		if(myPlayerTwoTicker>10){
    			AttackObject attack = getInputObjects().get(1).attack("weakPunch");
    			getMode().addObject(attack);
    			myPlayerTwoTicker=0;
    		}
	    }
	    
	    @Override
	    protected void developerUpdate(){
	    	myPlayerOneTicker++;
	    	myPlayerTwoTicker++;
	    }

	    /**
	     * Removes listener
	     */
	    @Override
		public void removeListener(){
	        super.removeListener();
	        getInput().removeListener(this);
	    }


}
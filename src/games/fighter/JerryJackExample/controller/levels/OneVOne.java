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
	    private static final String INPUT_PATHWAY = "config.threedefault";
	    private String myInputPathway;
	    private String myScorePathway;
	    private List<Force> myForces;
	    private ResourceBundle myResources;
	    private CollisionDetector myCollisionDetector;
	    private int myPlayerOneTicker;
	    private int myPlayerTwoTicker;
	    private int myPlayerThreeTicker;
	    private boolean myThreeplayer;

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
	        myPlayerOneTicker = 0;
	        myPlayerTwoTicker = 0;
	        myPlayerThreeTicker = 0;
	        if(getGameInfo().getNumCharacters()>2) getInput().replaceMappingResourcePath(
	        		getHardFilePath() + INPUT_PATHWAY );
	    }

	    /**
	     * Return concrete controller
	     */
	    @Override
		public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
	                                    String filepath) {
	        Controller controller = new OneVOne(name, frame, manager, gameinfo, filepath);
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
	    @InputMethodTarget(name = "player1_punch")
	    public void playerOneWeakPunchInput (AlertObject alObj)  {
	    		if(myPlayerOneTicker>10){
	    			AttackObject attack = getInputObjects().get(0).attack("weakPunch");
	    			getMode().addObject(attack);
	    			myPlayerOneTicker=0;
	    		}
	    }
	    @Override
	    @InputMethodTarget(name = "player2_punch")
	    public void playerTwoAttacInput (AlertObject alObj)  {
    		if(myPlayerTwoTicker>10){
    			AttackObject attack = getInputObjects().get(1).attack("weakPunch");
    			getMode().addObject(attack);
    			myPlayerTwoTicker=0;
    		}
	    }
	    
	    @InputMethodTarget(name = "player1_kick")
	    public void playerOneKicknput(AlertObject alObj) {
	    	if(myPlayerOneTicker>10){
	        AttackObject newAttack = getInputObjects().get(0).attack("kick");
	        getMode().addObject(newAttack);
	        myPlayerTwoTicker=0;
	    	}
	    }
	    
	    @InputMethodTarget(name = "player2_kick")
	    public void playerTwoKickInput(AlertObject alObj) {
	    	if(myPlayerTwoTicker>10){
	        AttackObject newAttack = getInputObjects().get(1).attack("kick");
	        getMode().addObject(newAttack);
	        myPlayerTwoTicker=0;
	    	}
	    }
	    
	    @InputMethodTarget(name = "player3_jump")
	    public void playerThreeJumpInput (AlertObject alObj)  {
	    	for(GameObject object : getMode().getMyObjects()){
	    		if(object instanceof EnvironmentObject && 
	    				myCollisionDetector.hitTop(object.getCurrentState().getCurrentRectangle(),
	    						getInputObjects().get(2).getCurrentState().getCurrentRectangle() )){
	    						getInputObjects().get(2).jump();
	    						break;
	    		}
	    	}
	    }
	    
	    @InputMethodTarget(name = "player3_punch")
	    public void playerThreeAttackInput (AlertObject alObj)  {
	    		if(myPlayerThreeTicker>10){
	    			AttackObject attack = getInputObjects().get(2).attack("weakPunch");
	    			getMode().addObject(attack);
	    			myPlayerThreeTicker=0;
	    		}
	    }
	    
	    @InputMethodTarget(name = "player3_kick")
	    public void playerThreeKicknput(AlertObject alObj) {
	    	if(myPlayerThreeTicker>10){
	        AttackObject newAttack = getInputObjects().get(2).attack("kick");
	        getMode().addObject(newAttack);
	        myPlayerThreeTicker=0;
	    	}
	    }
	    
	    @InputMethodTarget(name = "player3_left")
	    public void playerThreeLeftInput (AlertObject alObj) {
	        getInputObjects().get(2).move(180);

	    }

	    @InputMethodTarget(name = "player3_right")
	    public void playerThreeRightInput(AlertObject alObj) {
	        getInputObjects().get(2).move(0);

	    }
	    
	    @Override
	    protected void developerUpdate(){
	    	myPlayerOneTicker++;
	    	myPlayerTwoTicker++;
	    	myPlayerThreeTicker++;
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
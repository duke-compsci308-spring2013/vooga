package games.fighter.MattJimmyExample.controller.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import vooga.fighter.controller.Controller;
import vooga.fighter.controller.displayinformation.GameLoopInfo;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.MapObject;

import vooga.fighter.model.utils.UpdatableLocation;
import vooga.fighter.util.CollisionManager;
import vooga.fighter.view.Canvas;


/**
 * 
 * @author Jerry Li
 * 
 * @author Jack Matteucci
 * 
 * This is the basic controller used for levels.  It is attached to the LevelMode, so
 * one may see how the LevelMode is intantiated within this hierarchy if he/she may
 * need to extend controller.  Most extensions should just  subclass this class, as 
 * it provides the functionality to change winning conditions/ special conditions/
 * inputs.
 * 
 */

public abstract class LevelController extends Controller {
    private static final String INPUT_PATHWAY = "config.leveldefault";
    private static final String SCORE_PATHWAY = "config.score";
    private static final String SCORE = "ScoreScreen";
    private List<CharacterObject> myInputObjects;
    private List<ModeCondition> myWinConditions;
    private List<ModeCondition> myUniqueConditions;
    String myInputPathway;
    String myScorePathway;
    private ResourceBundle myResource;

    public LevelController () {
        super();
    }

    public LevelController(String name, Canvas frame, ControllerDelegate manager, 
                           GameInfo gameinfo, String filePath) {
        super(name, frame, manager, gameinfo, filePath);
        setInput(manager.getInput());
        myInputPathway = getHardFilePath() + INPUT_PATHWAY;
        myScorePathway = getHardFilePath() + SCORE_PATHWAY;
        myResource = ResourceBundle.getBundle(myScorePathway);
        getInput().replaceMappingResourcePath(myInputPathway);
        getInput().addListenerTo(this);
        GameLoopInfo gameLoopInfo = new GameLoopInfo(getMode());
        setLoopInfo(gameLoopInfo);
        myWinConditions =new ArrayList<ModeCondition>();
        myUniqueConditions =new ArrayList<ModeCondition>();
        setupConditions();
        gameinfo.setGameLoopInfo(gameLoopInfo);
    }

    /**
     * gets proper mode and sets it via hierarchy to this controller's mode
     */
    @Override
	public void loadMode() {
        LevelMode temp = new LevelMode(new CollisionManager());
        super.setMode(temp);
        myInputObjects = temp.getCharacterObjects();
    }
    /**
     * Initializes all Mode objects, called by setMode in Contoller
     */
    @Override
	public void initializeMode(){
        loadMap(getGameInfo().getMapName());
        loadCharacters(getGameInfo().getCharacters(), getMode().getMap().getStartPositions());
        loadHealth();
    }

    /**
     * returns the current objects that inputs directly act upon
     */
    protected List<CharacterObject> getInputObjects(){
        return myInputObjects;
    }
    /**
     * returns the Current Level Mode
     */
    @Override
	public LevelMode getMode(){
        return (LevelMode) super.getMode();
    }
    /**
     * returns this controller
     */
    @Override
	public Controller getController() {
        return this;
    }
    /**
     * removes listener from this and super class
     */
    @Override
	public void removeListener(){
        super.removeListener();
        getInput().removeListener(this);
    }
    /**
     * Adds a condition to the winning Conditions variable
     */
    protected void addWinCondition(ModeCondition condition){
        myWinConditions.add(condition);
    }
    /**
     * adds a unique Condition to the Unique Condition variable, these can really 
     * be any type condition, and will be handled if game developer chooses to subclass
     * and add handling this in the checkConditions
     */
    protected void addUniqueCondition(ModeCondition condition){
        myUniqueConditions.add(condition);
    }
    /**
     * Returns the current Win Conditions
     */
    protected List<ModeCondition> getWinConditions(){
        return myWinConditions;
    }

    /**
     * returns the current Unique Conditions, which will only be used if this class is 
     * subclassed
     */
    protected List<ModeCondition> getUniqueConditions(){
        return myUniqueConditions;
    }

    /**
     * Sets up the Winning Conditions.  While there is a default set, this method is to
     * be overridden by the developer, as it is called in the Level Controller constructor;
     */
    public void setupConditions(){
        addWinCondition(wincondition);
    }

    /**
     * Method that is called in the Controller loop, meant to cycle through the all 
     * sets of conditions that could possible apply and execute appropriately 
     * depending on the condition
     */
    @Override
	public void checkConditions(){
        for(ModeCondition condition : getWinConditions()){
            if(condition.checkCondition(getMode())) getManager().notifyEndCondition(myResource.getString(SCORE));
        }
    }

    /**
     * Anonymous Class that is fed into the winConditions via setupConditions
     */
    ModeCondition wincondition = new ModeCondition() {
        
    	@Override
		public boolean checkCondition(Mode mode) {    	   
    		LevelMode levelmode = (LevelMode) mode;
    		boolean change = false;
    		    for (int i = 0; i < levelmode.getCharacterObjects().size(); i++) {
				if(!levelmode.getCharacterObjects().get(i).hasHealthRemaining()) {
					change = true;
					for(int j = 0; j < levelmode.getCharacterObjects().size(); j++){
						if(j!=i) getGameInfo().addWinners(j);
						getGameInfo().addScore(levelmode.getCharacterObjects().get(j).getHealth().getHealth());
						getGameInfo().addTotalScore(j, getGameInfo().getScore(j));
					}
					break;
				}
			}
    		    return change;
		}
    };

    /**
     * This method is empty.  It is meant to be overriden by the GameDeveloper, 
     * as it is called in the update loop, so that they can insert anything they
     * would like in the update loop
     */
    @Override
	protected void developerUpdate(){
    }


    /**
     * Loads the health of the characters
     */
    protected void loadHealth() {
        for (int i = 0; i < getMode().getCharacterObjects().size(); i++) {
            getMode().getHealthStats().add(getMode().getCharacterObjects().get(i).getHealth());
        }
    }

    /**
     * Loads the environment objects for a map using the ObjectLoader.
     */
    protected void loadMap(String mapName) {
        getMode().setMap(new MapObject(mapName, getHardFilePath()));
        List<EnvironmentObject> mapObjects = getMode().getMap().getEnviroObjects();
        for (EnvironmentObject object : mapObjects) {
            getMode().addObject(object);
        }
    }

    /**
     * Loads the character objects for the selected characters using the ObjectLoader.
     */
    protected void loadCharacters(List<String> characterNames, List<UpdatableLocation> startingPos) {
        for (int i = 0; i < characterNames.size(); i++) {
            String charName = characterNames.get(i);
            UpdatableLocation start = startingPos.get(i);
            CharacterObject newCharacter = new CharacterObject(charName, start, getHardFilePath());
            getMode().addObject(newCharacter);
            getMode().addCharacter(newCharacter);
        }
    }

}
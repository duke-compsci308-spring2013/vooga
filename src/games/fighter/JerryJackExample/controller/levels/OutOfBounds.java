package games.fighter.JerryJackExample.controller.levels;

import java.util.List;
import java.util.ResourceBundle;

import util.input.AlertObject;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.GameManager;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.controller.levels.OneVOneController;
import vooga.fighter.forces.Force;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.util.CollisionDetector;
import vooga.fighter.view.Canvas;

		@InputClassTarget
		public class OutOfBounds extends OneVOne {
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
			    public OutOfBounds() {
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
			    public OutOfBounds(String name, Canvas frame, ControllerDelegate manager, 
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
			     * Removes listener
			     */
			    @Override
				public void removeListener(){
			        super.removeListener();
			        getInput().removeListener(this);
			    }
			    @Override
				public void checkConditions(){
			            if(winCondition.checkCondition(getMode())) getManager().notifyEndCondition(SCORESCREEN);
			    }
			    
			    /**
			     * Anonymous Class that is fed into the winConditions via setupConditions
			     */
			    ModeCondition winCondition = new ModeCondition() {
					public boolean checkCondition(Mode mode) {
			    		LevelMode levelmode = (LevelMode) mode;
			    		boolean change = false;
			    		    for (int i = 0; i < levelmode.getCharacterObjects().size(); i++) {
							if(levelmode.getCharacterObjects().get(i).getLocation().getY()>GameManager.SIZE.getHeight()+100) {
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

	}

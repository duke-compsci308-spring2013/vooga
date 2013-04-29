package games.fighter.JerryJackExample.controller.levels;

import games.fighter.JerryJackExample.AdvancedGameInfo;

import java.util.ArrayList;
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
		public class Stock extends OneVOne {
			    private static final String STOCK_PATHWAY = "config.stock";
			    private static final String SCORESCREEN = "ScoreScreen";
			    private static final int DEFAULT_HEALTH = 100;
			    private String myStockPathway;
			    private List<Integer> myCharacterLives;

			    /**
			     * Initial constructor
			     */
			    public Stock() {
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
			    public Stock(String name, Canvas frame, ControllerDelegate manager, 
			    		GameInfo gameinfo, String filepath) {
			    	super(name, frame, manager, gameinfo, filepath);
			    	myCharacterLives = new ArrayList<Integer>();
			    	for(int i = 0; i< getGameInfo().getNumCharacters(); i ++){
			    		myCharacterLives.add(getAdvancedGameInfo().getMaxLives());
			    	}
			    }

			    /**
			     * Return concrete controller
			     */
			    @Override
				public Controller getController(String name, Canvas frame, ControllerDelegate manager, GameInfo gameinfo,
			                                    String filepath) {
			        Controller controller = new Stock(name, frame, manager, gameinfo, filepath);
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
			    		    	if(myCharacterLives.get(i) == 0){
			    		    		change = true;
			    		    		for(int j = 0; j < levelmode.getCharacterObjects().size(); j++){
									if(j!=i) getGameInfo().addWinners(j);
									getGameInfo().addScore(levelmode.getCharacterObjects().get(j).getHealth().getHealth());
									getGameInfo().addTotalScore(j, getGameInfo().getScore(j));
								}
								break;
			    		    	}
			    		    if(!levelmode.getCharacterObjects().get(i).getHealth().hasHealthRemaining()){ 
			    		    	myCharacterLives.set(i,myCharacterLives.get(i) -1);
			    		    	levelmode.getCharacterObjects().get(i).getHealth().setHealth(DEFAULT_HEALTH);
			    		    }		    		    	
			    		    }
			    		    return change;
			    }
			    };
			    
			    public AdvancedGameInfo getAdvancedGameInfo(){
			    	return (AdvancedGameInfo) super.getGameInfo();
			    }

	}

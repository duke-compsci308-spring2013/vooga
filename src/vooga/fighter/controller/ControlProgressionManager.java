package vooga.fighter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ControlProgressionManager {
        
        //for testing purposes
	private static final String MAINMENU = "MainMenu";
	private static final String MODESELECT = "ModeSelectMenu";
	private static final String CHARACTERSELECT = "CharacterSelectMenu";
	private static final String MAPSELECT = "MapSelectMenu";
	private static final String SCORECONTROLLER = "GameOver";
	private static final String TOURNEY = "Tourney";
	private static final String TEST = "test";
	
	private static final String NEXT = "Next";
	private static final String BACK = "Back";
	private GameInfo myGameInfo;
	private List<String> myControllerList;
	
	public ControlProgressionManager(GameInfo gameinfo) {
		myGameInfo = gameinfo;
		myControllerList = new ArrayList<String>();
		updateProgression(myControllerList, myGameInfo);
	}
	
	public String getNextController(String currentController, String Condition){
		updateProgression(myControllerList, myGameInfo);
		for(int i=1; i<myControllerList.size(); i++ ){
			if(currentController.equals(myControllerList.get(i))){
				if(checkTourney(currentController, myGameInfo) && Condition.equals(NEXT)) 
					return selectTourneyLevel(myGameInfo);
				else if(Condition.equals(NEXT)){
					return myControllerList.get(i+1);
				}
				else if(Condition.equals(BACK)) {
				    return myControllerList.get(i -1);
				}
			}
		}
		return MAINMENU; //Can't go wrong with MainMenu!
	}
	
	public String getFirstController(){
		return MAINMENU;
	}
	
	private void updateProgression(List<String> list, GameInfo gameinfo){
		list.clear();
		list.add(MAINMENU);
		list.add(MAINMENU);
		list.add(MODESELECT);
		list.add(CHARACTERSELECT);
		list.add(MAPSELECT);
		if(gameinfo.getGameMode() != null){
		list.add(gameinfo.getGameMode());
		list.add(SCORECONTROLLER);
		list.add(MAINMENU);
		}
	}
	
	private boolean checkTourney(String currentController, GameInfo info){
		return (currentController.equals(SCORECONTROLLER)&&TOURNEY.equals(info.getGameMode()));
	}

	
	private String selectTourneyLevel(GameInfo info){
		List<String> mapsplayed = info.getMapsPlayed();
		if(mapsplayed.size() == info.getMapCount()) return MAINMENU;
		boolean mapselected = false;
		while(!mapselected){
			int randomlevel = (int)(Math.random()*info.getMapCount()); 
			String map = info.getMapNames().get(randomlevel);
			if(!mapsplayed.contains(map)){
				info.setMapName(map);
				info.getMapsPlayed().add(map);
				return myGameInfo.getGameMode();
			}
		}
		return MAINMENU; //Can't go wrong with MainMenu!

	}
}

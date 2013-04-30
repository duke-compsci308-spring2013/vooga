package games.fighter.JerryJackExample;

import java.util.List;

import vooga.fighter.controller.gameinformation.GameInfo;

public class AdvancedGameInfo extends GameInfo {
	
	private int myMaxLives;

	public AdvancedGameInfo(List<String> mapNames) {
		super(mapNames);
		myMaxLives = 1;
	}

	public AdvancedGameInfo(List<String> mapNames, List<String> characters,
			String map) {
		super(mapNames, characters, map);
		myMaxLives = 1;
	}
	
	public void setMaxLives(int lives){
		myMaxLives = lives;
	}
	
	public int getMaxLives(){
		return myMaxLives;
	}

}

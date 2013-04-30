package games.fighter.tvarnerExample;

import arcade.games.ArcadeInteraction;
import vooga.fighter.controller.GameManager; 
import vooga.fighter.controller.GameManagerRunAlone;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class MegaFighter extends GameManagerRunAlone {

	
	
	
	public MegaFighter() {
		//super(arcade);
		// TODO Auto-generated constructor stub
	}

	private final String FILE_PATH = "games.fighter.tvarnerExample.";
	
	
	@Override
	protected void setup(){
		        setFilePathway(FILE_PATH);
		        setCanvas(new Canvas(SIZE));
		        setInfo(new GameInfo(new MapLoader(FILE_PATH).getMapNames()));
	}

}
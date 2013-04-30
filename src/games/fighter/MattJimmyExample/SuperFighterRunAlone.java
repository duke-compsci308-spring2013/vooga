package games.fighter.MattJimmyExample;

import vooga.fighter.controller.GameManagerRunAlone; 
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class SuperFighterRunAlone extends GameManagerRunAlone {

	private final String FILE_PATH = "games.fighter.MattJimmyExample.";
	
	public SuperFighterRunAlone() {
	}
	
	@Override
	protected void setup(){
		        setFilePathway(FILE_PATH);
		        setCanvas(new Canvas(SIZE));
		        setInfo(new GameInfo(new MapLoader(FILE_PATH).getMapNames()));
	}

}

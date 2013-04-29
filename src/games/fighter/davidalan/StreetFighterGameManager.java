package games.fighter.davidalan;

import vooga.fighter.controller.*;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class StreetFighterGameManager extends GameManagerRunAlone {
	
	private static final String PATHWAY = "games.fighter.davidalan.";
	
	public StreetFighterGameManager () {
		super();
	}
	
    /**
    * The one method NEEDED to be overwritten by game developer
    */
    protected void setup(){
        setFilePathway(PATHWAY);
        setCanvas(new Canvas(SIZE));
        setInfo(new GameInfo(new MapLoader(PATHWAY).getMapNames()));
    }
}
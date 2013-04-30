package games.fighter.davidalan;

import arcade.games.ArcadeInteraction;
import vooga.fighter.controller.*;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class StreetFighterGameManager extends GameManager {
	
	private static final String PATHWAY = "games.fighter.davidalan.";
	
	public StreetFighterGameManager (ArcadeInteraction arcade) {
		super(arcade);
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
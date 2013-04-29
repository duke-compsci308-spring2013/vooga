package games.fighter.billMuenstermanJouster.config;

import arcade.games.ArcadeInteraction;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;
import games.fighter.billMuenstermanJouster.controller.GameManager;
import games.fighter.billMuenstermanJouster.controller.GameManagerRunAlone;

public class JousterGameManager extends GameManager {
	
	private static final String PATHWAY = "games.fighter.billMuenstermanJouster.";
	
	public JousterGameManager (ArcadeInteraction arcade) {
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

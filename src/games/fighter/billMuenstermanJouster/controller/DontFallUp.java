package games.fighter.billMuenstermanJouster.controller;

import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;
import arcade.games.ArcadeInteraction;
import games.fighter.billMuenstermanJouster.JousterGameManager;

public class DontFallUp extends JousterGameManager {
	
	private static final String myPath = new String("C:Users/Bill/workspace/vooga/src/games/fighter/billMuenstermanJouster/JousterGameManager.java");

	public DontFallUp(ArcadeInteraction arcade) {
		super(arcade);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setup() {
		setFilePathway(myPath);
		setCanvas(new Canvas(SIZE));
		setInfo(new GameInfo(new MapLoader(myPath).getMapNames()));
	}	

}

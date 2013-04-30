package games.rts.francescogame;

import vooga.rts.game.RTSGame;

import arcade.games.ArcadeInteraction;

/**
 * Main class to create our sample game
 * It only requires generated xml files to work
 * @author Francesco Agosti
 *
 */
public class Main extends RTSGame {

	public Main (ArcadeInteraction arcade) {
		super(arcade, "/games/rts/francescogame/resources/Factory.xml");    
		setMap("/games/rts/francescogame/resources/LevelEditor2.xml");
	}

	public static void main (String[] args) {
		Main gameMain = new Main(null);
		gameMain.run();
	}
}

package games.rts.anAdventure;

import vooga.rts.game.RTSGame;

import arcade.games.ArcadeInteraction;

/**
 * Main class to create our sample game
 * It only requires generated xml files to work
 * @author Henrique Moraes & Sherry Liu
 *
 */
public class Main extends RTSGame {

	public Main (ArcadeInteraction arcade) {
		super(arcade, "/games/rts/anAdventure/resources/Factory.xml");    
		setMap("/games/rts/anAdventure/resources/resources.xml");
	}

	public static void main (String[] args) {
		Main gameMain = new Main(null);
		gameMain.run();
	}
}

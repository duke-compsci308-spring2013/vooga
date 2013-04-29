package games.rts.myRTS;

import vooga.rts.game.RTSGame;

import arcade.games.ArcadeInteraction;

/**
 * main class
 * @author Eunsu (Joe) Ryu - jesryu
 */
public class Main extends RTSGame {
	private static final String FACTORY_LOCATION = "/games/rts/myRTS/Factory.xml";
	private static final String RESOURCE_LOCATION = "/games/rts/myRTS/resources.xml";

	/**
	 * Arcade will construct this class.
	 * @param arcade, arcade object
	 */
	public Main (ArcadeInteraction arcade) {
		super(arcade, FACTORY_LOCATION);    
		setMap(RESOURCE_LOCATION);
	}

	/**
	 * Testing main method. Will be hooked up to the arcade
	 */
	public static void main (String[] args) {
		new Main(null).run();
	}
}

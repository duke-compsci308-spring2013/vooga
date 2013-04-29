package games.rts.zombieCraft;

import arcade.games.ArcadeInteraction;
import vooga.rts.game.RTSGame;

/**
 * 
 * @author Yang , Ziqiang
 *
 */
public class GameStart extends RTSGame {

	public GameStart(ArcadeInteraction arcade) {
		super(arcade, "/games/rts/zombieCraft/factory/Factory.xml");
		setMap("/games/rts/zombieCraft/map/teer.xml");
		
	}

	public static void main(String[] args) {
		GameStart myGame = new GameStart(null);
		myGame.run();
	}
}

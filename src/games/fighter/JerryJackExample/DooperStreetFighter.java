package games.fighter.JerryJackExample;

import java.awt.Dimension;

import arcade.games.ArcadeInteraction;
import arcade.games.UserGameData;
import vooga.fighter.controller.GameManager;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class DooperStreetFighter extends GameManager {
	
    private final String FILE_PATH = "games.fighter.JerryJackExample.";
    
    private static final Dimension CANVAS_SIZE = new Dimension (1200, 900);
	/**
	 * Our terrific game
	 */
	public DooperStreetFighter(ArcadeInteraction arcade) {
		super(arcade);
	}
	
	/**
	 * Need to override setup in order to change file path.  Also
	 * we wanted a bigger Canvas :)
	 */
    @Override
    protected void setup(){
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(CANVAS_SIZE));
        setInfo(new AdvancedGameInfo(new MapLoader(FILE_PATH).getMapNames()));
    }
	/**
	 * Needed to add in one more stat to keep track of
	 */
    @Override
    public UserGameData generateNewProfile () {
    	UserGameData data = new UserGameData();
    	data.setScore(getGameInfo().getLastScore());
        return data;
    }

}

package games.fighter.JerryJackExample;

import java.awt.Dimension;

import arcade.games.ArcadeInteraction;
import arcade.games.UserGameData;
import vooga.fighter.controller.GameManager;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class SuperFighter extends GameManager {
	
    private final String FILE_PATH = "games.fighter.JerryJackExample.";
    
    private static final Dimension CANVAS_SIZE = new Dimension (1200, 900);

	public SuperFighter(ArcadeInteraction arcade) {
		super(arcade);
	}
	
    @Override
    protected void setup(){
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(CANVAS_SIZE));
        setInfo(new AdvancedGameInfo(new MapLoader(FILE_PATH).getMapNames()));
    }
   
    @Override
    public UserGameData generateNewProfile () {
    	UserGameData data = new UserGameData();
    	data.setScore(getGameInfo().getLastScore());
        return data;
    }

}

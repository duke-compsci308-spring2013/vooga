package games.fighter.MattJimmyExample;

import arcade.games.ArcadeInteraction;
import vooga.fighter.controller.GameManager;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class FighterExamplePW extends GameManager {

    private final String FILE_PATH = "games.fighter.MattJimmyExample.";
    
    public FighterExamplePW(ArcadeInteraction arcade) {
        super(arcade);
        setup();
    }
    
     /**
     * Overrides superclass setup() method to use the correct file path.
     */
    @Override
    protected void setup() {
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(SIZE));
        setInfo(new GameInfo(new MapLoader(FILE_PATH).getMapNames()));
    }
    
}

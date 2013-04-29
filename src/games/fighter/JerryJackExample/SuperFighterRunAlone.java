package games.fighter.JerryJackExample;

import java.awt.Dimension;
import vooga.fighter.controller.GameManagerRunAlone;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class SuperFighterRunAlone extends GameManagerRunAlone {

    private final String FILE_PATH = "games.fighter.JerryJackExample.";
    
    private static final Dimension CANVAS_SIZE = new Dimension (1200, 900);

    public SuperFighterRunAlone() {
    }

    @Override
    protected void setup(){
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(CANVAS_SIZE));
        setInfo(new AdvancedGameInfo(new MapLoader(FILE_PATH).getMapNames()));
    }

}

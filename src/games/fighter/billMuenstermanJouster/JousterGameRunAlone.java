package games.fighter.billMuenstermanJouster;


import java.awt.Dimension;
import vooga.fighter.controller.GameManagerRunAlone;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;


public class JousterGameRunAlone extends GameManagerRunAlone {


    private final String FILE_PATH = "games.fighter.billMuenstermanJouster.";
    
    private static final Dimension CANVAS_SIZE = new Dimension (1200, 900);


    public JousterGameRunAlone() {
    }


    @Override
    protected void setup(){
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(CANVAS_SIZE));
        setInfo(new GameInfo(new MapLoader(FILE_PATH).getMapNames()));
    }


}


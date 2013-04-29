package games.fighter.fightman;

import java.awt.Dimension;

import vooga.fighter.controller.GameManagerRunAlone;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;

public class FightManRunAlone extends GameManagerRunAlone {
    private static final String FILE_PATH = "games.fighter.fightman.";
    private static final Dimension CANVAS_SIZE = new Dimension (800, 600);
    
    private static int myScore = 0;
    
    @Override
    protected void setup(){
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(CANVAS_SIZE));
        setInfo(new GameInfo(new MapLoader(FILE_PATH).getMapNames()));
    }
    
    public static int getScore () {
        return myScore;
    }
    
    public static void setScore (int score) {
        myScore = score;
    }

}

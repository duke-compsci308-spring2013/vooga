package games.fighter.fightman;

import games.fighter.fightman.controller.levels.FightManMenu;

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
        myScore = 0;
        FightManMenu.p1Score = 0;
        FightManMenu.p2Score = 0;
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(CANVAS_SIZE));
        setInfo(new GameInfo(new MapLoader(FILE_PATH).getMapNames()));
        getGameInfo().addCharacters("Red");
        getGameInfo().addCharacters("Blue");
    }
    
    public static int getScore () {
        return myScore;
    }
    
    public static void setScore (int score) {
        myScore = score;
    }

}

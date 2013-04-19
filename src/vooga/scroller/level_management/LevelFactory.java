package vooga.scroller.level_management;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import util.Location;
import vooga.scroller.level_editor.LEGrid;
import vooga.scroller.level_editor.Level;
import vooga.scroller.level_editor.LevelParser;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.test_sprites.MarioLib;
import vooga.scroller.util.Pixmap;
import vooga.scroller.view.View;


/**
 * Instantiates all of the levels for gameplay.
 * 
 * @author Scott Valentine, Dagbedji Fagnisse
 * 
 */
public class LevelFactory {

    private LevelManager myLevelManager;
    private LevelParser myLevelReader;
    private ScrollingManager mySM;
    private View myView;

    public LevelFactory (LevelManager lm, ScrollingManager sm, View view) {
        myLevelManager = lm;
        myLevelReader = new LevelParser();
        mySM = sm;
        myView = view;
    }

    private Level buildLevel (int id, LEGrid grid) {
        Level result = new Level(id, mySM, myView, grid);
        return result;
    }

    /**
     * Generates levels to be displayed by the view and played by the model.
     * 
     * @param view is the view used for level information.
     * @return a the first of the List of all levels that will be played in the game.
     */
    public Level generateLevels () {
        List<Level> levels = new ArrayList<Level>();
        SplashPage splash = new SplashPage(new Pixmap("MARIO SPLASH.png"), 0, myView, mySM);
        splash.addDoor(new LevelPortal());

        // TODO: this will ideally read in levels from file and create instances of each level
        // This works for demo
        Level level1 = buildLevel(1, loadGridFromFile("01.level"));
        hardCodeCompleteL1(level1);
        Level level2 = new Level(2, mySM, myView);
        hardcodeLevel2(level2);
        Level level3 = buildLevel(3, loadGridFromFile("02.level"));
        hardCodeCompleteL2(level2);
        levels.add(splash);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(splash);

        linkLevels(levels);
        return splash;
    }

    private void linkLevels (List<Level> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            myLevelManager.put(levels.get(i).getDoor(), levels.get(i + 1));
        }
    }

    private void hardcodeLevel2 (Level level2) {
        for (int i = 0; i < 20; ++i) {
            level2.addSprite(new MarioLib.Platform(new Location(50 * i, 160)));
        }
    }

    private void hardCodeCompleteL2 (Level level2) {
        Location level2Start = new Location(100, 140);
        LevelPortal level2End = new LevelPortal(new Location(1000, 140));
        level2.addStartPoint(level2Start);
        level2.addSprite(level2End);
        level2.setBackground(new ImageIcon(getClass().getResource(
                "/vooga/scroller/images/backgrounds/forestbackground.jpg"))
.getImage());
    }

    private void hardCodeCompleteL1 (Level level1) {
        level1.setBackground(new ImageIcon(getClass().getResource(
                                           "/vooga/scroller/images/backgrounds/background.png"))
                .getImage());
    }

    private LEGrid loadGridFromFile (String filename) {
        // TODO: Factor this out. make editable.
        String pre = "src/vooga/scroller/level_management/";
        File f = (new File(pre + filename)).getAbsoluteFile();
        LEGrid result = myLevelReader.makeGridFromFile(f);
        return result;
    }

    private Level hardcodeLevel1 (View view, ScrollingManager sm, int id) {
        Level myCurrLevel = new Level(id, sm, view);
        myCurrLevel.addSprite(new MarioLib.Coin(
                                                new Location(view.getWidth() - 400, view
                                                        .getHeight() - 250)
                ));

        myCurrLevel.addSprite(new MarioLib.Coin(
                                                new Location(view.getWidth() - 400, view
                                                        .getHeight() - 350)
                ));

        myCurrLevel.addSprite(new MarioLib.Koopa(
                                                 new Location(view.getWidth() - 300, view
                                                         .getHeight() - 275)
                ));

        myCurrLevel.addSprite(new MarioLib.Koopa(
                                                 new Location(view.getWidth() - 200, view
                                                         .getHeight() - 350)
                ));

        myCurrLevel.addSprite(new MarioLib.Platform(
                                                    new Location(500, 600)
                ));
        myCurrLevel.addSprite(new MarioLib.Platform(
                                                    new Location(700, 600)
                ));

        // myCurrLevel.addSprite(new MarioLib.Platform(
        // new Location(900, 500)
        // ));
        myCurrLevel.addSprite(new MarioLib.Platform(
                                                    new Location(1100, 500)
                ));

        myCurrLevel.addSprite(new MarioLib.Platform(
                                                    new Location(1300, 400)
                ));
        myCurrLevel.addSprite(new MarioLib.Platform(
                                                    new Location(1500, 400)
                ));

        myCurrLevel.addSprite(new MarioLib.Plant(
                                                 new Location(500, 400)
                ));

        myCurrLevel.addSprite(new MarioLib.MovingPlatformTwo(
                                                             new Location(900, 500)
                ));
        myCurrLevel
                .setBackground(new ImageIcon(getClass()
                        .getResource("/vooga/scroller/images/backgrounds/background.png"))
                        .getImage());

        myCurrLevel.addSprite(new MarioLib.LevelTwoBlockOne(
                                                            new Location(64, 252)
                ));

        for (int i = 0; i < 32; ++i) {
            myCurrLevel.addSprite(new MarioLib.Coin(new Location(i * 50, 75)));
        }

        myCurrLevel.addSprite(new MarioLib.LevelTwoBlockTwo(
                                                            new Location(272, 220)
                ));
        myCurrLevel.addSprite(new MarioLib.LevelTwoBlockTwo(
                                                            new Location(464, 220)
                ));
        myCurrLevel.addSprite(new MarioLib.LevelTwoBlockTwo(
                                                            new Location(656, 220)
                ));

        myCurrLevel.addSprite(new MarioLib.LevelTwoBlockThree(
                                                              new Location(1216, 204)
                ));
        return myCurrLevel;
    }

}

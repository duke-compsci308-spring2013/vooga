package vooga.fighter.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JFrame;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;
import vooga.fighter.controller.ControllerManager;
import vooga.fighter.controller.gameinformation.GameInfo;

import arcade.games.UserGameData;


/**
 * 
 * @author Jack Matteucci
 * 
 *  * NOTE:  THIS CODE IS JUST FOR TESTING PURPOSES!
 * It is duplicated but only because we didn't 
 * want to have to include arcade when testing...
 */

public class GameManagerRunAlone{
    public static final Dimension SIZE = new Dimension(800, 600);

    public static final String TITLE = "Fighter!";
    public static final int THREE_TOP_HIGH_SCORES = 3;
    private static final String FILE_PATH = "vooga.fighter.";
    private Canvas myCanvas;
    private ControllerManager myControllerManager;
    private GameInfo myGameInfo;
    private String myHardFilePathway;

    public GameManagerRunAlone () {
        setup();
        ControllerFactory factory = new ControllerFactory(myCanvas, myHardFilePathway);
        ControlProgressionManager progressionmanager = makeProgression(factory.getMap());
        myControllerManager =
                new ControllerManager(myCanvas, myGameInfo, factory, progressionmanager, myHardFilePathway);
        JFrame frame = makeFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myCanvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
    * Runs the entire engine
    */
    public void run() {
        myControllerManager.run();
    }

    /**
    * Allows one to subclass and use own progression manager, which handles switching
    * levels
    */
    protected ControlProgressionManager makeProgression(Map<String, Controller> map){
        return new ControlProgressionManager(map);
    }

    /**
    * Sets the File Pathway
    */
    protected void setFilePathway (String pathway) {
        myHardFilePathway = pathway;
    }

    protected GameInfo getGameInfo () {
        return myGameInfo;
    }
    /**
    * Allows subclassing of gameInfo to includ new scores/functionality
    */
    protected void setInfo (GameInfo info) {
        myGameInfo = info;
    }
    /**
    * Allows subclassing of canvas to include new view functionality
    */
    protected void setCanvas (Canvas canvas) {
        myCanvas = canvas;
    }
    /**
    * Allows for new frame Title
    */
    protected JFrame makeFrame () {
        return new JFrame(TITLE);
    }
    /**
    * The one method NEEDED to be overwritten by game developer
    */
    protected void setup(){
        setFilePathway(FILE_PATH);
        setCanvas(new Canvas(SIZE));
        setInfo(new GameInfo(new MapLoader(myHardFilePathway).getMapNames()));
    }

}

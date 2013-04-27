package vooga.fighter.controller;

import java.awt.BorderLayout; 
import java.awt.Dimension;
import javax.swing.JFrame;

import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;
import vooga.fighter.model.loaders.MapLoader;
import vooga.fighter.view.Canvas;
import vooga.fighter.controller.ControllerManager;
import vooga.fighter.controller.GameInfo;
import util.input.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jack Matteucci
 *
 */

public class GameManager extends Game{
public static final Dimension SIZE = new Dimension(800, 600);

    public static final String TITLE = "Fighter!";
    public static final int THREE_TOP_HIGH_SCORES = 3;
    private Canvas myCanvas;
    private ControllerManager myControllerManager;
    private GameInfo myGameInfo;
    private String myHardFilePathway;

    public GameManager(ArcadeInteraction arcade) {
    	super(arcade);
    	setFilePathway();
        setCanvas();
        setInfo();
        ControllerFactory factory = makeFactory(myCanvas,myHardFilePathway);
        ControlProgressionManager progressionmanager = makeProgression(factory.getMap());
        myControllerManager = makeManager(myCanvas, myGameInfo, factory, progressionmanager,myHardFilePathway);
        JFrame frame = makeFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myCanvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void run (){
        myControllerManager.run();
    }

    private ControllerFactory makeFactory(Canvas canvas, String pathway){
        return new ControllerFactory(canvas, pathway);
    }

    private ControllerManager makeManager(Canvas canvas, GameInfo info, ControllerFactory factory,
                                            ControlProgressionManager progression, String myFilePathway){
        return new ControllerManager(canvas, info, factory, progression, myFilePathway);
    }

    protected ControlProgressionManager makeProgression(Map map){
        return new ControlProgressionManager(map);
    }


    public UserGameData generateNewProfile(){
        return myGameInfo;
    }

    protected void setFilePathway(){
        myHardFilePathway = "vooga.fighter.";
    }
	 
	 
	 protected GameInfo getGameInfo(){
		 return myGameInfo;
	 }

	@Override
	public GameData generateNewGameProfile() {
		return null;
	}
	
	protected void setInfo(){
		myGameInfo = new GameInfo(new MapLoader(myHardFilePathway).getMapNames());
	}
	
	protected void setCanvas(){
		myCanvas = new Canvas(SIZE);
	}
	
	protected JFrame makeFrame(){
		return new JFrame(TITLE);
	}


}

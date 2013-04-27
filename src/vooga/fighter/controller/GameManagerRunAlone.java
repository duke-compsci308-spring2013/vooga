package vooga.fighter.controller;

import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import arcade.games.ArcadeInteraction;
import arcade.games.Game;
import arcade.games.GameData;
import arcade.games.UserGameData;
import util.Pixmap;
import vooga.fighter.model.loaders.MapLoader;
import util.Text;
import vooga.fighter.view.Canvas;
import vooga.fighter.controller.ControllerManager;
import vooga.fighter.controller.GameInfo;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 
 * @author Jack Matteucci
 *
 */

public class GameManagerRunAlone{
    	public static final Dimension SIZE = new Dimension(800, 600);
    	    public static final String TITLE = "Fighter!";
    	    public static final int THREE_TOP_HIGH_SCORES = 3;
    	    private Canvas myCanvas;
    	    private ControllerManager myControllerManager;
    	    private GameInfo myGameInfo;
    	    private String myHardFilePathway;

    	    public GameManagerRunAlone() {
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

    	    protected ControllerFactory makeFactory(Canvas canvas, String pathway){
    	        return new ControllerFactory(canvas, pathway);
    	    }

    	    protected ControllerManager makeManager(Canvas canvas, GameInfo info, ControllerFactory factory,
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

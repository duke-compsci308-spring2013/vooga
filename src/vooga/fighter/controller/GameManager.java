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
import arcade.games.UserGameData;
import util.Pixmap;
import vooga.fighter.util.Text;
import vooga.fighter.view.Canvas;
import vooga.fighter.controller.ControllerManager;
import vooga.fighter.controller.GameInfo;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;



public class GameManager { //extends Game{
public static final Dimension SIZE = new Dimension(800, 600);
    public static final String TITLE = "Fighter!";
    private Canvas myCanvas;
    private ControllerManager myControllerManager;
    private GameInfo myGameInfo;

    public GameManager() {
        myCanvas = new Canvas(SIZE); 
        List<Integer> myTwoCharacters = new ArrayList<Integer>();
        myTwoCharacters.add(1);
        myTwoCharacters.add(2);
        myGameInfo = new GameInfo(myTwoCharacters, 1);
        myControllerManager = new ControllerManager(myCanvas, myGameInfo);
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        frame.getContentPane().add(myCanvas, BorderLayout.CENTER);
        // display them
        frame.pack();
        frame.setVisible(true);
	}
    
//    public GameManager(ArcadeInteraction arcade){
//    	this();
//    }
    
    public GameManager(String runindividually){
        this();
    	//DisplayMode dm = new DisplayMode(SIZE.width,SIZE.height, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        // container that will work with user's OS
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components
        frame.getContentPane().add(myCanvas, BorderLayout.CENTER);
        // display them
        frame.pack();
        frame.setVisible(true);
    }
    
    
	 public void run (){
		 myControllerManager.run();
	    }
	 

	 public UserGameData generateNewProfile(){
		return myGameInfo;
	 }

}

package vooga.fighter.controller;



import java.awt.Dimension;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import vooga.fighter.view.Canvas;
/**
 * 
 * @author Jack Matteucci
 *
 */

public class ControllerManager implements ControllerDelegate{
	private static final String MAINMENU = "MainMenu";
	private static final String TEST = "Test";
	private Map<String, Controller> myControllerMap;
	private Controller myCurrentController;
	private Canvas myCanvas;
	private GameInfo myGameInfo;
	
	public ControllerManager(Canvas frame, GameInfo gameinfo) {
		myCanvas = frame;
		myControllerMap = new ControllerFactory(frame).getMap();
		myGameInfo = gameinfo;
		myCurrentController = myControllerMap.get(TEST).getController(this, myGameInfo);
	}
	
	public void run(){
		myCurrentController.start();
	}

	public void switchController(String NextController) {
	        System.out.println("switching controllers");
		myCurrentController.stop();
		myCurrentController = myControllerMap.get(NextController);
		System.out.println("now the controller is: " + myCurrentController.getName() );
		//myCurrentController.displaySplash();
		myCurrentController = myCurrentController.getController(this, myGameInfo);
		myCurrentController.start();	
	}      

	@Override
	public void notifyEndCondition(String string) {
		switchController(string);
		
	}

}

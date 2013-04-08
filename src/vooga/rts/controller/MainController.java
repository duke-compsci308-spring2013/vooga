package vooga.rts.controller;

import java.awt.Graphics2D;
import vooga.rts.gui.Window;

public class MainController extends AbstractController {

	
	public GameController myGameController;
	public LoadingController myLoadingController;
	public MenuController myMenuController;
	
	private Window myWindow; 

	
    public MainController () {
    	myWindow = new Window();    	
        myGameController = new GameController();
        myLoadingController = new LoadingController();
        myMenuController = new MenuController();
        
        myWindow.setFullscreen(true);
    }
    
    @Override
    public void receiveUserInput () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update (double elapsedTime) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void paint (Graphics2D pen) {
        // TODO Auto-generated method stub
        
    }
    
}

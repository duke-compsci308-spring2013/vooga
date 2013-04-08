package vooga.rts.controller;

import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import vooga.rts.Game;
import vooga.rts.gui.Window;

public class MainController extends AbstractController {

	
	
	private GameController myGameController;
	private LoadingController myLoadingController;
	private MenuController myMenuController;
	
	private Window myWindow; 
	
	private MainState myState;	
	
	private Timer myTimer;

	public MainController () {
    	myState = MainState.Starting;
    	
    	myWindow = new Window();    	
        myGameController = new GameController();
        myLoadingController = new LoadingController();
        myMenuController = new MenuController();
        
        myState = MainState.Loading;
        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(
        		new TimerTask() {					
					@Override
					public void run() {
						update(this.scheduledExecutionTime());
						render();
					}
				}, 0, Game.TIME_PER_FRAME());
        myWindow.setFullscreen(true);
    }
    
    @Override
    public void receiveUserInput () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update (double elapsedTime) {
        switch (myState) {
			case Game:
				myGameController.update(elapsedTime);
				break;
			case Loading:
				break;
			case Menu:
				break;
			case Splash:
				break;
			case Starting:
				break;
			default:
				break;        
        }
    }
    
    public void render() {
    	Graphics2D graphics = myWindow.getCanvas().getGraphics();
    	paint(graphics);
    	myWindow.getCanvas().render();
    }

    @Override
    public void paint (Graphics2D pen) {
        
        
    }
    
    public MainState getState() {
		return myState;
	}

	public void setState(MainState state) {
		this.myState = state;
	}
    
}

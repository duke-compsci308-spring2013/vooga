package vooga.scroller.level_management.splash_page;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import util.Location;
import util.input.Input;
import util.input.InputClassTarget;
import util.input.InputMethodTarget;
import vooga.scroller.level_management.IInputListener;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.util.IGameComponent;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.view.GameView;

@InputClassTarget
public abstract class SplashPage implements IInputListener, IGameComponent{

    // TODO: this string is the part of every Splash page and thus needs to be removed
    public static final String CONTROLS_FILE_PATH = "vooga/scroller/marioGame/controls/SplashMapping";

    // TODO: this class needs to be extendible and more general so developers can defined
    // their own controls for the splash page.
    
    private IDoor myDoor;
    private ISpriteView myBackground;
    private int myID;
    private GameView myGameView;
    private Player myPlayer;
    //private Mouse myMouse;
    private List<Sprite> mySprites;
    
    // buttons
    //private Button myButton;
    
    public SplashPage (ISpriteView backgroundImage, int splashID, GameView gameView, ScrollingManager sm) {
        myBackground = backgroundImage;
        myID = splashID;
        myGameView = gameView;
        mySprites = getSprites();
    }

    /**
     * Sets the next level to go to given ID.
     * 
     * @param id of the next level to go.
     */
    public void addDoor(IDoor door) {
        myDoor = door;
    }
    
   
    @Override
    public void update(double elapsedTime, Dimension bounds, GameView gameView) {
        // Just leave the background image.
        
    }


    @Override
    public void paint (Graphics2D pen) {
        myBackground.paint(pen, new Location((double)myGameView.getWidth()/2, 
                                             (double)myGameView.getHeight()/2), 
                                             myGameView.getSize());
        for(Sprite sp: mySprites) {
            sp.paint(pen);
        }
    }

    public abstract List<Sprite> getSprites();
        
    
    @InputMethodTarget(name = "exit")
    public void exit() {
        System.exit(-1);
    }
    

    
//    @InputMethodTarget(name = "mouse")
//    public void updateMouse(PositionObject posObj) {
//        myMouse.setCenter(posObj.getPoint2D().getX(), posObj.getPoint2D().getY());
//    }
//    
//    @InputMethodTarget(name = "click")
//    public void mouseClick(PositionObject posObj) {
//        if(myButton.intersects(posObj.getPoint2D())){
//            nextLevel();
//        }
//    }
    
    @Override
	public abstract void addInputListeners (Input input);

    @Override
	public abstract void removeInputListeners(Input input);
    
    @Override
	public abstract String getInputFilePath (); 
        //return myControlLocation;
    
    
    @Override
	public IDoor getDoor() {
        return myDoor;
    }

    @Override
    public Player getPlayer () {
        return myPlayer;
    }

    @Override
    public void addPlayer (Player p) {
        myPlayer = p;
    }


    @Override
    public double getRightBoundary () {
        return myGameView.getWidth();
    }

    @Override
    public Dimension getLevelBounds () {
        return myGameView.getSize();
    }

    @Override
    public double getLeftBoundary () {
        return 0;
    }

    @Override
    public double getUpperBoundary () {
        return 0;
    }

    @Override
    public double getLowerBoundary () {       
        return myGameView.getHeight();

    }

    @Override
    public Image getBackground () {
        return myBackground.getImage();
    }
}

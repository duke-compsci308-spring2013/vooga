package vooga.scroller.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import util.input.Input;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;
import vooga.scroller.sprites.superclasses.Player;
import vooga.scroller.view.GameView;

public interface IGameComponent {

    
    public void update(double elapsedTime, Dimension bounds, GameView view);
    
   
    public void addInputListeners (Input myInput);
    
    public void removeInputListeners (Input myInput);
    
    public void paint (Graphics2D pen);
    
    public void addSprite(Sprite sprite);
    
    //public String getInputFilePath ();

    public IDoor getDoor ();

    public Player getPlayer ();
    
    public void addPlayer(Player p) ;

    public double getRightBoundary ();

    public Dimension getLevelBounds ();

    public double getLeftBoundary ();

    public double getUpperBoundary ();

    public double getLowerBoundary ();

    public Image getBackground ();
    
}
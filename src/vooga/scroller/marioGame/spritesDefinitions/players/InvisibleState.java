package vooga.scroller.marioGame.spritesDefinitions.players;

import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.scroller.sprites.state.State;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.Pixmap;
import vooga.scroller.util.Sprite;




public class InvisibleState implements State{

    private static final Pixmap DEFAULT_IMAGE = new Pixmap("invisible.gif");
    private static final double INVISIBLE_TIME = 5;
    private Sprite mySprite;
    private ISpriteView myDefaultView;
    private double myTime;
    //private 
    
    public InvisibleState(Sprite sprite) {
        mySprite = sprite;
        myTime = 0.0;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        myTime += elapsedTime;
        if(myTime > INVISIBLE_TIME) {
            this.deactivate();
        }
        
    }

    @Override
    public void activate () {
        if(!mySprite.getView().equals(DEFAULT_IMAGE)){
            myDefaultView = mySprite.getView();
        }
        mySprite.setView(DEFAULT_IMAGE);
    }

    @Override
    public void deactivate () {
        mySprite.setView(myDefaultView);   
        myTime = 0.0;
    }

    @Override
    public void paint (Graphics2D pen) {
        DEFAULT_IMAGE.paint(pen, mySprite.getCenter(), new Dimension((int)mySprite.getWidth(),(int) mySprite.getHeight()));
    }


}

package vooga.rts.gui.menus.gamesubmenus;

import vooga.rts.util.SDimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gui.Window;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Location;


public abstract class SubMenu extends Observable implements Observer {

    protected InteractiveEntity mySelectedEntity;
    private BufferedImage myBGImage;
    private SDimension mySize;
    private Location myPos;
    private boolean isFocused;

    public SubMenu (String image, SDimension size, Location pos) {
        myBGImage =
                ResourceManager.getInstance().<BufferedImage> getFile(image,
                                                                      BufferedImage.class);
        mySize = size;
        myPos = pos;
    }

    public void setSelectedEntity (InteractiveEntity i) {
        mySelectedEntity = i;
    }

    public void paint (Graphics2D pen) {
        if (myBGImage != null) {
            pen.drawImage(myBGImage, (int) myPos.getX(), (int) myPos.getY(), (int) mySize.getWidth(), (int) mySize.getHeight(), null);
        }
    }

    public boolean checkWithinBounds (int x, int y) {
        return (x > myPos.x && y > myPos.y && x < (myPos.x + mySize.width) && y < (myPos.y + mySize.height));
    }

    public boolean checkWithinBounds (Location l) {
        return checkWithinBounds((int) l.getX(), (int) l.getY());
    }

    public abstract void processClick (int x, int y);

    public abstract void processHover (int x, int y);

    public  void update (double elapsedTime) {};

    public void setFocused (boolean b) {
        isFocused = b;
    }
    
    public void processClick () {
        // TODO Auto-generated method stub
        
    }

}

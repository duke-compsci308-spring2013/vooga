package vooga.rts.gui.buttons;

import vooga.rts.util.SDimension;
import java.awt.image.BufferedImage;
import vooga.rts.gui.Button;
import vooga.rts.util.Location;

public class ImageButton extends Button {

    public ImageButton (String image, SDimension size, Location pos) {
        super(image, size, pos);
    }
    
    public ImageButton (BufferedImage image, SDimension size, Location pos) {
        super(null, size, pos);
        setImage(image);
    }

    @Override
    public void update (double elapsedTime) {
        setChanged();
        notifyObservers();
    }

    @Override
    public void processClick () {
        update(0);
    }

    @Override
    public void processHover () {
        // TODO Auto-generated method stub
    }
}

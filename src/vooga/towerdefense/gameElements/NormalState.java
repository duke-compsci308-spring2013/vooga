package vooga.towerdefense.gameElements;

import java.awt.Graphics2D;


public class NormalState implements State {
    public void paint (Graphics2D pen, GameElement object) {
        // draw normally
        pen.drawImage(object.getPixmap().getImage(), (int) object.getX(), (int) object.getY(),
                      (int) object.getWidth(), (int) object.getHeight(), null);
    }

    @Override
    public void setSate () {
        // do nothing
    }

}

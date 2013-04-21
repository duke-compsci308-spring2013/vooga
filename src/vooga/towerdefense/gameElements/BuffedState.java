package vooga.towerdefense.gameElements;

import java.awt.Graphics2D;


public class BuffedState implements State {
    private final double INCREASE_RATIO = 1.5;

    public void paint (Graphics2D pen, GameElement object) {
        // draw amplified image
        pen.drawImage(object.getPixmap().getImage(), (int) object.getX(),
                      (int) object.getY(),
                      (int) (object.getWidth() * INCREASE_RATIO),
                      (int) (object.getHeight() * INCREASE_RATIO), null);
    }

    @Override
    public void setSate () {
        // do nothing
    }

}

package vooga.towerdefense.view.introscreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import vooga.towerdefense.view.TDView;


public abstract class SelectScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int XCOORD = 0;
    private static final int YCOORD = 0;
    private Color myBackgroundColor = Color.WHITE;
    private TDView myView;

    public SelectScreen (Dimension size, TDView view) {
        setPreferredSize(size);
        myView = view;
        setVisible(true);
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(myBackgroundColor);
        pen.fillRect(XCOORD, YCOORD, getSize().width, getSize().height);

    }

    public TDView getView () {
        return myView;
    }

    public abstract void displayImages (Graphics2D pen);

    public abstract void checkPositionClicked (Point point);

}

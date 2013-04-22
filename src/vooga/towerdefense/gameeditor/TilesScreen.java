package vooga.towerdefense.gameeditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


public class TilesScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private MouseAdapter myMouseListener;
    private List<Pixmap> myPixmaps;

    public TilesScreen (Dimension size, List<Pixmap> pixmaps) {
        setSize(size);
        setPreferredSize(size);
        setVisible(true);
        makeListener();
        myPixmaps = new ArrayList<Pixmap>();
        myPixmaps = pixmaps;
        addMouseListener(myMouseListener);
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponents(pen);
        paintImages((Graphics2D) pen);
    }

    private void paintImages (Graphics2D pen) {
        int xC = 30;
        int yC = 30;
        for (int k = 0; k < myPixmaps.size(); k++) {
            myPixmaps.get(k).paint(pen, new Location(k * 50 + xC, yC), new Dimension(50, 50));
            xC += 10;
        }
    }

    private void makeListener () {
        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {

            }
        };
    }
}

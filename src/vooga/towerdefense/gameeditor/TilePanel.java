package vooga.towerdefense.gameeditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


public class TilePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private MouseAdapter myMouseListener;
    private List<Pixmap> myPixmaps;
    private Map<Pixmap, Rectangle> myBounds;
    private MapEditorScreen myEditor;

    public TilePanel (Dimension size, List<Pixmap> pixmaps, MapEditorScreen editor) {
        setSize(size);
        setPreferredSize(size);
        setVisible(true);
        makeListener();
        myEditor = editor;
        myPixmaps = new ArrayList<Pixmap>();
        myPixmaps = pixmaps;
        myBounds = new HashMap<Pixmap, Rectangle>();
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
            myBounds.put(myPixmaps.get(k), new Rectangle((k * 50 + xC) - 25, yC - 25, 50, 50));
            xC += 10;
        }
    }

    private void makeListener () {
        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                handlePixmapClicked(e.getPoint());
            }
        };
    }

    protected void handlePixmapClicked (Point point) {
        for (Pixmap pixmap : myPixmaps) {
            if (myBounds.get(pixmap).contains(point)) {
                myEditor.makeTileInstances(pixmap.getFileName());
            }
        }
    }
}

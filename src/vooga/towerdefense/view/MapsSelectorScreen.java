package vooga.towerdefense.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import vooga.towerdefense.util.Location;
import vooga.towerdefense.util.Pixmap;


/**
 * This screen enables the player to select the type of map that they would
 * like to play in.
 * 
 * @author Leonard K. Ng'eno
 * 
 */
public class MapsSelectorScreen extends JPanel {
    // private static final String RESOURCE = "/vooga/towerdefense/images/maps/";
    private static final long serialVersionUID = 1L;
    private static final Dimension SIZE = new Dimension(200, 200);
    private MouseAdapter myMouseListener;
    private Pixmap myMap1;
    private Pixmap myMap2;
    private Pixmap myMap3;
    private Pixmap myMap4;
    private Map<Pixmap, Rectangle> myMapImages;
    private JButton myNextScreen;
    private static final int XCOORD = 0;
    private static final int YCOORD = 0;
    private Color myBackgroundColor = Color.WHITE;
    private TDView myView;
    private boolean myMapSelected = false;

    public MapsSelectorScreen (Dimension size, TDView view) {
        myView = view;
        setPreferredSize(size);
        myMapImages = new HashMap<Pixmap, Rectangle>();
        setInputListener();
        initMapImages();
        addMouseListener(myMouseListener);
        add(makeNextScreenButton());

    }

    private Component makeNextScreenButton () {
        myNextScreen = new JButton("NEXT");
        myNextScreen.setLocation(800, 800);
        myNextScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (myMapSelected == true) {
                    myView.showModesScreen();
                }
            }
        });
        return myNextScreen;
    }

    private void initMapImages () {
        myMap1 = new Pixmap("map1.gif");
        myMap2 = new Pixmap("map2.gif");
        myMap3 = new Pixmap("map3.gif");
        myMap4 = new Pixmap("map4.gif");

        myMapImages.put(myMap1, new Rectangle(new Point(100, 50), SIZE));
        myMapImages.put(myMap2, new Rectangle(new Point(450, 50), SIZE));
        myMapImages.put(myMap3, new Rectangle(new Point(100, 350), SIZE));
        myMapImages.put(myMap4, new Rectangle(new Point(450, 350), SIZE));

    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(myBackgroundColor);
        pen.fillRect(XCOORD, YCOORD, getSize().width, getSize().height);
        displayMaps((Graphics2D) pen);
    }

    private void setInputListener () {
        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                System.out.println("clicked: " + e.getPoint());
                checkPositionClicked(e.getPoint());
            }
        };
    }

    private void checkPositionClicked (Point point) {
        for (Map.Entry<Pixmap, Rectangle> entry : myMapImages.entrySet()) {
            if (entry.getValue().contains(point)) {
                System.out.println("Pixmap name: " + entry.getKey().getFileName());
                myMapSelected = true;
            }
        }
    }

    private void displayMaps (Graphics2D pen) {
        for (Map.Entry<Pixmap, Rectangle> entry : myMapImages.entrySet()) {
            entry.getKey().paint(pen,
                                 new Location(entry.getValue().getCenterX(), entry.getValue()
                                         .getCenterY()), SIZE);
        }
    }

    private void sendMapImageClicked (String imageName) {
        // TODO send the name of the type of map the user selected to the GameModel/Controller?
    }
}

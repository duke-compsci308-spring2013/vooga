package vooga.towerdefense.view.introscreens;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import util.Location;
import vooga.towerdefense.controller.Controller;
import vooga.towerdefense.util.Pixmap;
import vooga.towerdefense.view.TDView;


/**
 * 
 * This screen enables the player to select the type of map that they would
 * like to use in their game.
 * 
 * @author Leonard K. Ng'eno
 * 
 */
public class MapsSelectorScreen extends SelectScreen {
    private static final long serialVersionUID = 1L;
    private static final Dimension SIZE = new Dimension(200, 200);
    private MouseAdapter myMouseListener;
    private Map<Pixmap, Rectangle> myMapImages;
    private JButton myNextScreenButton;
    private boolean myMapSelected;
    private Controller myController;
    private Pixmap mySelectedMap;
    private TDView myView;

    public MapsSelectorScreen (Dimension size, TDView view, Controller controller) {
        super(size, view);
        myController = controller;
        myView = view;
        myMapSelected = false;
        setInputListener();
        myMapImages = new HashMap<Pixmap, Rectangle>();
        initMapImages();
        addMouseListener(myMouseListener);
        add(makeNextScreenButton(), BorderLayout.SOUTH);
    }

    private Component makeNextScreenButton () {
        myNextScreenButton = new JButton("NEXT");
        myNextScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (myMapSelected == true) {
                    try {
                        myView.dismissMapSelector();
                        myController.setMap(mySelectedMap);
                    }
                    catch (IllegalArgumentException e1) {
                        e1.printStackTrace();
                    }
                    catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    catch (InstantiationException e1) {
                        e1.printStackTrace();
                    }
                    catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                    catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        return myNextScreenButton;
    }

    private void initMapImages () {
        Set<Pixmap> mapPixmaps = myController.getMapImages();
        int x = 50;
        int y = 50;
        for (Pixmap p : mapPixmaps) {
            myMapImages.put(p, new Rectangle(new Point(x, y), SIZE));
            x += 100;
            if (x >= getSize().width) {
                x = 0;
                y += 200;
            }
        }
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        displayImages((Graphics2D) pen);
        myNextScreenButton.setVisible(true);
    }

    private void setInputListener () {
        myMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                checkPositionClicked(e.getPoint());
            }
        };
    }

    /**
     * Identify the map that has been selected
     */
    @Override
    public void checkPositionClicked (Point point) {
        for (Map.Entry<Pixmap, Rectangle> entry : myMapImages.entrySet()) {
            if (entry.getValue().contains(point)) {
                myMapSelected = true;
                mySelectedMap = entry.getKey();
                highlightSelectedImage(entry.getValue());
            }
        }
    }

    /**
     * Draw a highlighting rectangle on the selected map
     * @param rect      the rectangle to surround the selected map
     */
    private void highlightSelectedImage (Rectangle rect) {
        double thickness = 10;
        Graphics2D pen = (Graphics2D)getGraphics();
        pen.setStroke(new BasicStroke((float) thickness));
        pen.setColor(Color.GREEN);
        pen.drawRect(rect.x, rect.y, rect.width, rect.height);
        
    }

    /**
     * Draw the map representations on the screen
     */
    @Override
    public void displayImages (Graphics2D pen) {
        for (Map.Entry<Pixmap, Rectangle> entry : myMapImages.entrySet()) {
            entry.getKey().paint(pen,
                                 new Location(entry.getValue().getCenterX(), entry.getValue()
                                         .getCenterY()), SIZE);
        }
    }

}

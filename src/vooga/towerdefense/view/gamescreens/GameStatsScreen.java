package vooga.towerdefense.view.gamescreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import vooga.towerdefense.controller.Controller;


/**
 * Heads Up Display of the game statistics
 * 
 * @author Leonard K. Ng'eno
 * 
 */

public class GameStatsScreen extends InformationScreen {

    private static final long serialVersionUID = 1L;
    private static final int XCOORD = 0;
    private static final int YCOORD = 0;
    private Color myBackgroundColor = Color.WHITE;

    public GameStatsScreen (Dimension size, Controller controller) {
    	super(size, controller);
        setPreferredSize(size);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(myBackgroundColor);
        pen.fillRect(XCOORD, YCOORD, getSize().width, getSize().height);
    }
}

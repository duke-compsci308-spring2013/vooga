package vooga.towerdefense.view.gamescreens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import vooga.towerdefense.controller.Controller;


/**
 * Allows the game developer to create multiple screens in
 * one panel of the BorderLayout.
 * 
 * @author Angelica Schwartz
 */
public class MultipleScreenPanel extends JPanel {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * constructor.
     * @param Dimension
     */
    public MultipleScreenPanel (Dimension size, Controller controller) {
        setPreferredSize(size);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    /**
     * adds a screen to this multiple screen panel.
     * @param screen
     * @param location
     */
    public void addScreen (JPanel screen, String location) {
        add(screen, location);
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
    }
}

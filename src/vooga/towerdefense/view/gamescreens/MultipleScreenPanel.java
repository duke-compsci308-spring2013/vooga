package vooga.towerdefense.view.gamescreens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JPanel;


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
     * map of screens to their respective locations in
     * the BorderLayout.
     */
    private Map<JPanel, String> myScreens;

    /**
     * constructor.
     * 
     * @param screens
     */
    public MultipleScreenPanel (Dimension size, Map<JPanel, String> screens) {
        setPreferredSize(size);
        setLayout(new BorderLayout());
        myScreens = screens;
        makePanel();
        setVisible(true);
    }

    /**
     * helper method to put the screens in their correct
     * locations.
     */
    private void makePanel () {
        for (JPanel screen : myScreens.keySet()) {
            add(screen, myScreens.get(screen));
        }
    }

}

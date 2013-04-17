package vooga.towerdefense.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * This class makes a screen that can display any information in it.
 * Examples include information about game stats, units, towers, etc.
 * 
 * @author Angelica Schwartz
 */
public class InformationScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final String INFO_LOCATION = BorderLayout.CENTER;
    private int myWidth;
    private int myHeight;
    private JTextArea myTextArea;
    private String myTitle;

    /**
     * Constructor.
     * 
     * @param title
     * @param size
     */
    public InformationScreen (String title, Dimension size) {
        setPreferredSize(size);
        setLayout(new BorderLayout());
        myTitle = title + "\n";
        myWidth = size.width;
        myHeight = size.height;
        add(makeInfoTextArea(), INFO_LOCATION);
        setVisible(true);
    }

    /**
     * displays the information contained in the string parameter.
     * 
     * @param information is the string to display
     */
    public void displayInformation (String information) {
        myTextArea.setText(myTitle + information);
        repaint();
    }

    /**
     * clears the text from the information screen.
     */
    public void clearScreen () {
        displayInformation("");
    }

    /**
     * gets the text area for this screen.
     * 
     * @return the text area
     */
    public JTextArea getTextArea () {
        return myTextArea;
    }

    /**
     * paints the InfoScreen.
     * 
     * @param pen
     */
    @Override
    public void paint (Graphics pen) {
        super.paintComponent(pen);
        myTextArea.paint(pen);
    }

    /**
     * creates the text area.
     * 
     * @return the blank JTextArea
     */
    private JComponent makeInfoTextArea () {
        myTextArea = new JTextArea();
        myTextArea.setSize(myWidth, myHeight);
        myTextArea.setEditable(false);
        return myTextArea;
    }
}

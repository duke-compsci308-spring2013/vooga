package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Super class that makes common behavior for each of
 * the editor screens.
 * 
 * @author Angelica Schwartz
 * 
 */
public abstract class GameEditorScreen extends JPanel {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String ADD_BUTTON_TEXT = "Add to game";
    private static final String FINISH_BUTTON_TEXT = "Next section";
    private String myNextScreenName = "vooga.towerdefense.gameeditor.";
    private String myTitle = " MAKING SCREEN";
    private MouseAdapter myMouseAdapter;
    private JButton myAddButton;
    private JButton myFinishButton;
    private Dimension mySize;
    private GameEditorController myController;

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     * @param title
     * @param nextScreenName
     */
    public GameEditorScreen (Dimension size,
                             GameEditorController controller,
                             String title,
                             String nextScreenName) {
        mySize = size;
        setPreferredSize(mySize);
        myController = controller;
        myTitle = title + myTitle;
        addTitle();
        myNextScreenName += nextScreenName;
        myMouseAdapter = makeMouseAdapter();
        addMouseListener(myMouseAdapter);
        addButtons();
    }

    /**
     * displays this screen.
     */
    public void display () {
        setVisible(true);
        repaint();
    }

    /**
     * clears the screen.
     */
    public void clearScreen () {
        // does nothing for game editor screen,
        // but can be overriden by subclass screens.
    }

    /**
     * gets the associated controller.
     * 
     * @return the GameEditorController
     */
    public GameEditorController getController () {
        return myController;
    }

    /**
     * gets the associated mouse listener.
     * 
     * @return the MouseAdapter
     */
    public MouseAdapter getMouseAdapter () {
        return myMouseAdapter;
    }

    /**
     * helper method to make common buttons.
     */
    private void addButtons () {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        myAddButton = new JButton(ADD_BUTTON_TEXT);
        myAddButton.addMouseListener(myMouseAdapter);
        myAddButton.setVisible(true);
        myFinishButton = new JButton(FINISH_BUTTON_TEXT);
        myFinishButton.addMouseListener(myMouseAdapter);
        myFinishButton.setVisible(true);
        buttonPanel.add(myAddButton, BorderLayout.NORTH);
        buttonPanel.add(myFinishButton, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
    }

    /**
     * helper method to make the title label.
     */
    private void addTitle () {
        JLabel titleLabel = new JLabel(myTitle);
        add(titleLabel);
    }

    /**
     * makes the mouse adapters for the screen.
     * 
     * @return the mouse adapter created
     */
    public MouseAdapter makeMouseAdapter () {
        MouseAdapter myMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (e.getSource().equals(myAddButton)) {
                    addElementToGame();
                    clearScreen();
                    JOptionPane.showMessageDialog(null, "Element Added to Your Game");
                }
                else if (e.getSource().equals(myFinishButton)) {
                    try {
                        setVisible(false);
                        myController.displayNextScreen(myNextScreenName);
                    }
                    catch (SecurityException e1) {
                        e1.printStackTrace();
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
                    catch (NoSuchMethodException e1) {
                        e1.printStackTrace();
                    }
                    catch (InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                }
                addAdditionalMouseBehavior(e);
            }
        };
        return myMouseAdapter;
    }

    /**
     * adds the specific element to the XML file.
     * will be implemented by the sub-classes
     */
    public abstract void addElementToGame ();

    /**
     * adds any additional mouse behavior.
     * will be implemented by the sub-classes
     */
    public abstract void addAdditionalMouseBehavior (MouseEvent e);
}

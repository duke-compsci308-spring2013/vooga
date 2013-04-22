package vooga.towerdefense.gameeditor;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * ViewEditorScreen is responsible for helping
 * the game developer make the view.
 * 
 * @author Angelica Schwartz
 */
public class ViewEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "MapEditorScreen";
    /**
     * package name for the available game screens.
     */
    private static final String SCREEN_PACKAGE_PATH = "vooga.towerdefense.view.gamescreens";
    /**
     * title constant.
     */
    private static final String TITLE_NAME = "VIEW ";

    /**
     * north constant.
     */
    private static final String NORTH_NAME = "NORTH";
    /**
     * south constant.
     */
    private static final String SOUTH_NAME = "SOUTH";
    /**
     * east constant.
     */
    private static final String EAST_NAME = "EAST";
    /**
     * west constant.
     */
    private static final String WEST_NAME = "WEST";
    /**
     * center constant.
     */
    private static final String CENTER_NAME = "CENTER";
    /**
     * width of the text field boxes.
     */
    private static final int TEXT_FIELD_WIDTH = 20;
    /**
     * north drop down box.
     */
    private JComboBox myNorthPanel;
    /**
     * south drop down box.
     */
    private JComboBox mySouthPanel;
    /**
     * center drop down box.
     */
    private JComboBox myCenterPanel;
    /**
     * east drop down box.
     */
    private JComboBox myEastPanel;
    /**
     * west drop down box.
     */
    private JComboBox myWestPanel;
    /**
     * north size field.
     */
    private JTextField myNorthSize;
    /**
     * south size field.
     */
    private JTextField mySouthSize;
    /**
     * center size field.
     */
    private JTextField myCenterSize;
    /**
     * east size field.
     */
    private JTextField myEastSize;
    /**
     * west size field.
     */
    private JTextField myWestSize;
    
    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public ViewEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        try {
            makeScreen();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void makeScreen() throws IOException, ClassNotFoundException {
        myNorthPanel = new JComboBox();
        mySouthPanel = new JComboBox();
        myCenterPanel = new JComboBox();
        myEastPanel = new JComboBox();
        myWestPanel = new JComboBox();
        myNorthSize = new JTextField(TEXT_FIELD_WIDTH);
        mySouthSize = new JTextField(TEXT_FIELD_WIDTH);
        myCenterSize = new JTextField(TEXT_FIELD_WIDTH);
        myEastSize = new JTextField(TEXT_FIELD_WIDTH);
        myWestSize = new JTextField(TEXT_FIELD_WIDTH);
        populateDropDoxBoxes();
        add(new JLabel(NORTH_NAME));
        add(myNorthPanel);
        add(myNorthSize);
        add(new JLabel(CENTER_NAME));
        add(myCenterPanel);
        add(myCenterSize);
        add(new JLabel(SOUTH_NAME));
        add(mySouthPanel);
        add(mySouthSize);
        add(new JLabel(EAST_NAME));
        add(myEastPanel);
        add(myEastSize);
        add(new JLabel(WEST_NAME));
        add(myWestPanel);
        add(myWestSize);
    }
    
    private void populateDropDoxBoxes() throws IOException, ClassNotFoundException {
        List<String> screens = getController().getClassNamesInPackage(SCREEN_PACKAGE_PATH);
        for (String s : screens) {
            myNorthPanel.addItem(s);
            mySouthPanel.addItem(s);
            myCenterPanel.addItem(s);
            myEastPanel.addItem(s);
            myWestPanel.addItem(s);
        }
    }

    /**
     * adds this level to the game.
     */
    @Override
    public void addElementToGame () {
        // TODO Auto-generated method stub
        // TODO: get rid of this magic number
        getController().setMapSize(new Dimension(500, 600));
        getController().addViewToGame();
    }

    /**
     * adds additional mouse behavior specific
     * to the ViewEditorScreen.
     * 
     * @param e is the MouseEvent
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        // TODO Auto-generated method stub

    }
}

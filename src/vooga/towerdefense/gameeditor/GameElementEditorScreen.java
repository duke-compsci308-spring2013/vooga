package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * GameElementEditorScreen is the super class for helping
 * the game developer make game elements.
 * 
 * @author Angelica Schwartz
 */
public abstract class GameElementEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * specifies user directory.
     */
    private static final String USER_DIR = "user.dir";
    /**
     * constant for text area width.
     */
    private static final int TEXT_AREA_WIDTH = 10;
    /**
     * constant for text area height.
     */
    private static final int TEXT_AREA_HEIGHT = 25;
    /**
     * class path for the attribute constants interface.
     */
    private static final String ATTRIBUTES_CLASS_PATH =
            "vooga.towerdefense.attributes.AttributeConstants";
    /**
     * package path for actions package.
     */
    private static final String ACTION_PACKAGE_PATH = "vooga.towerdefense.action";
    /**
     * constant for the attribute selector button.
     */
    private static final String ATTRIBUTES_ADD_BUTTON_TEXT = "Add attribute";
    /**
     * constant for the action selector button.
     */
    private static final String ACTION_ADD_BUTTON_TEXT = "Add action";
    /**
     * constant for the action delete button.
     */
    private static final String ACTION_DELETE_BUTTON_TEXT = "Clear selected action";
    /**
     * constant for the attributes delete button.
     */
    private static final String ATTRIBUTE_DELETE_BUTTON_TEXT = "Clear selected attribute";
    /**
     * constant for the action section title.
     */
    private static final String ACTION_TITLE = "Actions";
    /**
     * constant for the attribute section title.
     */
    private static final String ATTRIBUTE_TITLE = "Attributes";
    /**
     * constant for the image selector button.
     */
    private static final String IMAGE_SELECTOR_KEYWORD = "Select Image From File";
    /**
     * used to choose file in the directory.
     */
    private JFileChooser myFileChooser;
    /**
     * box to enter the name of the tower.
     */
    private JTextField myNameBox;
    /**
     * box to enter the image of the tower.
     */
    private JTextField myImageBox;
    /**
     * button to get the image of the tower from the file system.
     */
    private JButton myImageSelector;
    /**
     * drop down menu with the attributes available.
     */
    private JComboBox myAttributesBox;
    /**
     * button to add attributes to this tower.
     */
    private JButton myAddAttributeButton;
    /**
     * button to delete attributes from this tower.
     */
    private JButton myDeleteAttributeButton;
    /**
     * area where attributes the user has selected are displayed.
     */
    private JTextArea myAttributesSelected;
    /**
     * area where the game developer can enter the
     * attributes value.
     */
    private JTextField myAttributeValue;
    /**
     * drop down menu with the actions available.
     */
    private JComboBox myActionsBox;
    /**
     * button to add actions to this tower.
     */
    private JButton myAddActionButton;
    /**
     * button to delete actions from this tower.
     */
    private JButton myDeleteActionButton;
    /**
     * area where actions the user has selected are displayed.
     */
    private JTextArea myActionsSelected;

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public GameElementEditorScreen (Dimension size,
                                    GameEditorController controller,
                                    String title,
                                    String nextScreen) {
        super(size, controller, title, nextScreen);
        myFileChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        makeScreen();
    }

    /**
     * helper method to create all the parts of
     * the TowerEditorScreen.
     */
    private void makeScreen () {
        addCharacteristicsPanel();
        try {
            JPanel attributes = makeAttributesSection();
            JPanel actions = makeActionsSection();
            JPanel bottomScreen = new JPanel(new BorderLayout());
            bottomScreen.add(attributes, BorderLayout.NORTH);
            bottomScreen.add(actions, BorderLayout.SOUTH);
            add(bottomScreen, BorderLayout.SOUTH);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * clears all fields in the TowerEditorScreen.
     */
    @Override
    public void clearScreen () {
        myNameBox.setText("");
        myImageBox.setText("");
        myAttributesSelected.setText("");
        myActionsSelected.setText("");
    }

    /**
     * adds this element to the game.
     */
    @Override
    public abstract void addElementToGame ();

    /**
     * helper method to make the text boxes for
     * name and image + image button.
     */
    private void addCharacteristicsPanel () {
        JPanel characteristicsPanel = new JPanel();
        myNameBox = new JTextField(TEXT_AREA_WIDTH);
        myImageBox = new JTextField(TEXT_AREA_WIDTH);
        myImageSelector = new JButton(IMAGE_SELECTOR_KEYWORD);
        myImageSelector.addMouseListener(getMouseAdapter());
        characteristicsPanel.add(new JLabel("Name: "));
        characteristicsPanel.add(myNameBox);
        characteristicsPanel.add(new JLabel("Image: "));
        characteristicsPanel.add(myImageBox);
        characteristicsPanel.add(myImageSelector);
        add(characteristicsPanel, BorderLayout.NORTH);
    }

    /**
     * helper method to make the actions section of this screen.
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private JPanel makeActionsSection () throws ClassNotFoundException, IOException {
        JPanel actionSection = new JPanel(new BorderLayout());
        JPanel westSide = new JPanel(new BorderLayout());
        westSide.add(new JLabel(ACTION_TITLE), BorderLayout.NORTH);
        myActionsBox = new JComboBox();
        List<String> actions = getController().getClassNamesInPackage(ACTION_PACKAGE_PATH);
        for (String a : actions) {
            myActionsBox.addItem(a);
        }
        westSide.add(myActionsBox, BorderLayout.CENTER);
        actionSection.add(westSide, BorderLayout.WEST);
        myActionsSelected = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        actionSection.add(new JScrollPane(myActionsSelected), BorderLayout.CENTER);
        myAddActionButton = new JButton(ACTION_ADD_BUTTON_TEXT);
        myAddActionButton.addMouseListener(getMouseAdapter());
        JPanel optionsSubPanel = new JPanel(new BorderLayout());
        optionsSubPanel.add(myAddActionButton, BorderLayout.NORTH);
        myDeleteActionButton = new JButton(ACTION_DELETE_BUTTON_TEXT);
        myDeleteActionButton.addMouseListener(getMouseAdapter());
        optionsSubPanel.add(myDeleteActionButton, BorderLayout.SOUTH);
        JPanel eastSide = new JPanel(new BorderLayout());
        eastSide.add(optionsSubPanel, BorderLayout.NORTH);
        actionSection.add(eastSide, BorderLayout.EAST);
        return actionSection;
    }

    /**
     * helper method to make the attributes drop down box,
     * button, and text field.
     * 
     * @throws ClassNotFoundException
     */
    private JPanel makeAttributesSection () throws ClassNotFoundException {
        JPanel attributesSection = new JPanel(new BorderLayout());
        JPanel optionsSubPanel1 = new JPanel(new BorderLayout());
        optionsSubPanel1.add(new JLabel(ATTRIBUTE_TITLE), BorderLayout.NORTH);
        myAttributesBox = new JComboBox();
        List<String> attributes = getController().getFieldsInClass(ATTRIBUTES_CLASS_PATH);
        for (String a : attributes) {
            myAttributesBox.addItem(a);
        }
        optionsSubPanel1.add(myAttributesBox, BorderLayout.CENTER);
        myAttributeValue = new JTextField();
        optionsSubPanel1.add(myAttributeValue, BorderLayout.SOUTH);
        attributesSection.add(optionsSubPanel1, BorderLayout.WEST);
        myAttributesSelected = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        attributesSection.add(new JScrollPane(myAttributesSelected), BorderLayout.CENTER);
        JPanel optionsSubPanel2 = new JPanel(new BorderLayout());
        myAddAttributeButton = new JButton(ATTRIBUTES_ADD_BUTTON_TEXT);
        myAddAttributeButton.addMouseListener(getMouseAdapter());
        optionsSubPanel2.add(myAddAttributeButton, BorderLayout.NORTH);
        myDeleteAttributeButton = new JButton(ATTRIBUTE_DELETE_BUTTON_TEXT);
        myDeleteAttributeButton.addMouseListener(getMouseAdapter());
        optionsSubPanel2.add(myDeleteAttributeButton, BorderLayout.SOUTH);
        JPanel eastSide = new JPanel(new BorderLayout());
        eastSide.add(optionsSubPanel2, BorderLayout.NORTH);
        attributesSection.add(eastSide, BorderLayout.EAST);
        return attributesSection;
    }

    /**
     * adds additional mouse behavior specific to this screen.
     * 
     * @return mouse adapter
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        if (e.getSource().equals(myImageSelector)) {
            int response = myFileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = myFileChooser.getSelectedFile();
                String path = file.getPath().replace("%20", " ");
                myImageBox.setText(path);
            }
        }
        else if (e.getSource().equals(myAddAttributeButton)) {
            myAttributesSelected.setText(myAttributesSelected.getText() + "\n"
                                         + myAttributesBox.getSelectedItem().toString()
                                         + " = " + myAttributeValue.getText());
        }
        else if (e.getSource().equals(myAddActionButton)) {
            myActionsSelected.setText(myActionsSelected.getText() + "\n"
                                      + myActionsBox.getSelectedItem().toString());
        }
        else if (e.getSource().equals(myDeleteAttributeButton)) {
            myAttributesSelected.replaceSelection("");
        }
        else if (e.getSource().equals(myDeleteActionButton)) {
            myActionsSelected.replaceSelection("");
        }
    }
}

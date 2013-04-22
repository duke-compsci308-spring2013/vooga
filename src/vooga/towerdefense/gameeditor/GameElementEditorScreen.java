package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.sun.xml.internal.ws.util.StringUtils;


/**
 * GameElementEditorScreen is the super class for helping
 * the game developer make game elements.
 * 
 * @author Angelica Schwartz
 */
public class GameElementEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * specifies user directory.
     */
    private static final String USER_DIR = "user.dir";
    /**
     * title of this screen.
     */
    private static final String TITLE = "GAME ELEMENT ";
    /**
     * the next screen is the wave editor screen.
     */
    private static final String NEXT_SCREEN_NAME = "WaveEditorScreen";;
    /**
     * constant for text area width.
     */
    private static final int TEXT_AREA_WIDTH = 10;
    /**
     * constant for text area height.
     */
    private static final int TEXT_AREA_HEIGHT = 25;
    /**
     * constant for the attribute selector button.
     */
    private static final String ATTRIBUTES_ADD_BUTTON_TEXT = "Add attribute";
    /**
     * constant for the action selector button.
     */
    private static final String ACTION_ADD_BUTTON_TEXT = "Add action";
    /**
     * constant for follow up action button. 
     */
    private static final String FOLLOW_UP_ACTION_TEXT = "Add follow up action";
    /**
     * constant for the action delete button.
     */
    private static final String ACTION_DELETE_BUTTON_TEXT = "Clear selected action";
    /**
     * constant for the attributes delete button.
     */
    private static final String ATTRIBUTE_DELETE_BUTTON_TEXT = "Clear selected attribute";
    /**
     * option for new attribute.
     */
    private static final String NEW_TEXT = "NEW";
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
     * keyword for units.
     */
    private static final String UNIT_TYPE = "Unit";
    /**
     * keyword for towers.
     */
    private static final String TOWER_TYPE = "Tower";
    /**
     * keyword for projectiles.
     */
    private static final String PROJECTILE_TYPE = "Projectile";
    /**
     * text on button to add a new type.
     */
    private static final String ADD_NEW_TYPE_TEXT = "Add new type";
    /**
     * used to choose file in the directory.
     */
    private JFileChooser myFileChooser;
    /**
     * box to enter the type of the game element.
     */
    private JComboBox myTypeBox;
    /**
     * button add a new type of game element.
     */
    private JButton myAddTypeButton;
    /**
     * box to enter the name of the game element.
     */
    private JTextField myNameBox;
    /**
     * box to enter the image of the game element.
     */
    private JTextField myImageBox;
    /**
     * button to get the image of the game element from the file system.
     */
    private JButton myImageSelector;
    /**
     * drop down menu with the attributes available.
     */
    private JComboBox myAttributesBox;
    /**
     * button to add attributes to this game element.
     */
    private JButton myAddAttributeButton;
    /**
     * button to delete attributes from this game element.
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
     * button to add actions to this game element.
     */
    private JButton myAddActionButton;
    /**
     * button to add follow up actions to the selected action.
     */
    private JButton myAddFollowUpActionButton;
    /**
     * button to delete actions from this game element.
     */
    private JButton myDeleteActionButton;
    /**
     * area where actions the user has selected are displayed.
     */
    private JTextArea myActionsSelected;
    /**
     * arraylist with the default available types.
     */
    private List<String> myAvailableTypes;

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public GameElementEditorScreen (Dimension size,
                                    GameEditorController controller) {
        super(size, controller, TITLE, NEXT_SCREEN_NAME);
        myFileChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        myAvailableTypes = new ArrayList<String>();
        myAvailableTypes.add(UNIT_TYPE);
        myAvailableTypes.add(TOWER_TYPE);
        myAvailableTypes.add(PROJECTILE_TYPE);
        makeScreen();
    }

    /**
     * helper method to create all the parts of
     * the game elementEditorScreen.
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
     * gets the name of this game element.
     * @return a string of the name
     */
    public String getName() {
        return myNameBox.getText();
    }
    
    /**
     * gets the image path for this game element.
     * @return a string of the image path
     */
    public String getImagePath() {
        return myImageBox.getText();
    }
    
    /**
     * gets the attribute map of name to value.
     * @return a map of string to string
     */
    public Map<String, String> getAttributes() {
        Map<String, String> attributeMap = new HashMap<String, String>();
        String fullAttributes = myAttributesSelected.getText();
        while (!fullAttributes.equals("")) {
            int index = fullAttributes.indexOf("\n");
            String attribute = fullAttributes.substring(0, index);
            String[] nameAndValue = attribute.split(" ");
            attributeMap.put(nameAndValue[0], nameAndValue[1]);
            fullAttributes = fullAttributes.substring(index+1);
        }
        return attributeMap;
    }
    
    /**
     * gets the action map of name to value.
     * @return a map of string to string
     */
    //TODO: get rid of null values
    public Map<String, String> getActions() {
        Map<String, String> actionMap = new HashMap<String, String>();
        String fullActions = myActionsSelected.getText();
        while (!fullActions.equals("")) {
            int index = fullActions.indexOf("\n");
            String action = fullActions.substring(0, index);
            String[] nameAndValue = action.split(" ");
            actionMap.put(nameAndValue[0], "value");
            fullActions = fullActions.substring(index+1);
        }
        return actionMap;
    }
    
    /**
     * clears all fields in the game elementEditorScreen.
     */
    @Override
    public void clearScreen () {
        myNameBox.setText("");
        myImageBox.setText("");
        myAttributesSelected.setText("");
        myActionsSelected.setText("");
    }

    /**
     * helper method to make the text boxes for
     * name and image + image button.
     */
    private void addCharacteristicsPanel () {
        JPanel characteristicsPanel = new JPanel();
        myTypeBox = new JComboBox();
        for (String type : myAvailableTypes) {
            myTypeBox.addItem(type);
        }
        JPanel typePanel = new JPanel(new BorderLayout());
        typePanel.add(new JLabel("Type: "), BorderLayout.NORTH);
        myAddTypeButton = new JButton(ADD_NEW_TYPE_TEXT);
        myAddTypeButton.addMouseListener(getMouseAdapter());
        typePanel.add(myTypeBox, BorderLayout.CENTER);
        typePanel.add(myAddTypeButton, BorderLayout.SOUTH);
        add(typePanel);
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
        List<String> actions = getController().getAvailableActions();
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
        myAddFollowUpActionButton = new JButton(FOLLOW_UP_ACTION_TEXT);
        myAddFollowUpActionButton.addMouseListener(getMouseAdapter());
        optionsSubPanel.add(myAddFollowUpActionButton, BorderLayout.CENTER);
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
        List<String> attributes = getController().getAttributes();
        attributes.add(NEW_TEXT);
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
        if (e.getSource().equals(myAddTypeButton)) {
            String newType = JOptionPane.showInputDialog("Enter your new type");
            myTypeBox.addItem(newType);
            myTypeBox.setSelectedItem(newType);
        }
        else if (e.getSource().equals(myImageSelector)) {
            int response = myFileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = myFileChooser.getSelectedFile();
                String path = file.getPath().replace("%20", " ");
                myImageBox.setText(path);
            }
        }
        else if (e.getSource().equals(myAddAttributeButton)) {
            if (myAttributesBox.getSelectedItem().equals(NEW_TEXT)) {
                String name = JOptionPane.showInputDialog("Enter the name of your new attribute");
                String value = JOptionPane.showInputDialog("Enter the value for " + name);
                myAttributesSelected.setText(myAttributesSelected.getText()
                   + name + " " + value + "\n");
            }
            else {
                myAttributesSelected.setText(myAttributesSelected.getText()
                    + myAttributesBox.getSelectedItem().toString()
                    + " " + myAttributeValue.getText() + "\n");
            }
        }
        else if (e.getSource().equals(myAddActionButton)) {
            myActionsSelected.setText(myActionsSelected.getText() + addAction());
        }
        else if (e.getSource().equals(myAddFollowUpActionButton)) {
            String parentAction = myActionsSelected.getSelectedText();
            String childAction = addAction();
            for (char c : parentAction.toCharArray()) {
                if (c == '\t') {
                    childAction = "\t" + childAction;
                }
            }
            childAction = "\t" + childAction;
            myActionsSelected.setText(myActionsSelected.getText().substring(0, myActionsSelected.getText().indexOf(parentAction))
                                      + parentAction + "\n" + childAction
                                      + myActionsSelected.getText().substring(myActionsSelected.getText().indexOf(parentAction) 
                                                                              + parentAction.length()+1, myActionsSelected.getText().length()));
            
        }
        else if (e.getSource().equals(myDeleteAttributeButton)) {
            myAttributesSelected.replaceSelection("");
        }
        else if (e.getSource().equals(myDeleteActionButton)) {
            myActionsSelected.replaceSelection("");
        }
    }
    
    /**
     * returns the action as a string.
     * @return
     */
    private String addAction() {
        List<String> valuesToPromptFor;
        try {
            valuesToPromptFor = getController().getParametersForAction(myActionsBox.getSelectedItem().toString());
            String display = myActionsBox.getSelectedItem().toString();
            for (String value: valuesToPromptFor) {
                display += " " + JOptionPane.showInputDialog("Enter a " + value + " for this action");
            }
            return display + "\n";
        }
        catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * adds a game element to the game.
     */
    public void addElementToGame () {
        getController().addGameElementToGame(myTypeBox.getSelectedItem().toString(), myNameBox.getText(),
                                             myImageBox.getText(),
                                             makeMap(myAttributesSelected.getText()),
                                             myActionsSelected.getText());
    }
    
    /**
     * helper method to make a map from the string from a text area.
     * @param s is a string from a text area.
     */
    private Map<String, String> makeMap(String s) {
        Map<String, String> map = new HashMap<String, String>();
        String[] subParts = s.split("\n");
        for (String subPart : subParts) {
            while (!subPart.equals("")) {
                String[] nameAndValue = subPart.split(" ");
                map.put(nameAndValue[0], nameAndValue[1]);
            }
        }
        return map;
    }
}
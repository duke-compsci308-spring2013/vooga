package vooga.towerdefense.gameeditor.gamemaker.editorscreens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import vooga.towerdefense.gameeditor.controller.GameEditorController;
import vooga.towerdefense.gameeditor.gamemaker.editorscreens.subeditorscreens.ActionSection;


/**
 * LevelEditorScreen is responsible for helping
 * the game developer make levels.
 * 
 * @author Angelica Schwartz
 */
public class LevelEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * title constant.
     */
    private static final String TITLE_NAME = "LEVEL ";
    /**
     * next screen constant.
     */
    private static final String NEXT_SCREEN_NAME = "FinishScreen";
    /**
     * package for the wave action factories.
     */
    private static final String WAVE_ACTION_PACKAGE_PATH = "vooga.towerdefense.factories.waveactionfactories";
    /**
     * package path for the rules.
     */
    private static final String RULES_PACKAGE_PATH = "vooga.towerdefense.factories.rulefactories";
    /**
     * constant string for adding a new rule.
     */
    private static final String ADD_RULE_TEXT = "Add rule";
    /**
     * constant string for deleting a new rule.
     */
    private static final String DELETE_RULE_TEXT = "Delete selected rule";
    /**
     * constant for width of the JTextArea.
     */
    private static final int TEXT_AREA_WIDTH = 20;
    /**
     * constant for height of the JTextArea.
     */
    private static final int TEXT_AREA_HEIGHT = 50;
    /**
     * shows the rules that the game developer has already selected.
     */
    private JTextArea mySelectedRules;
    /**
     * button for adding new rules.
     */
    private JButton myAddRuleButton;
    /**
     * button for deleting rules.
     */
    private JButton myDeleteRuleButton;
    /**
     * the box that displays the available rules.
     */
    private JComboBox myRules;
    /**
     * the box that displays the available units.
     */
    private JComboBox myAvailableUnits;
    /**
     * section to display actions.
     */
    private ActionSection myActionSection;

    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    public LevelEditorScreen (Dimension size, GameEditorController controller) throws ClassNotFoundException, IOException {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        myAvailableUnits = new JComboBox();
        myActionSection = new ActionSection("Wave Actions", WAVE_ACTION_PACKAGE_PATH, getController().getAvailableActions(WAVE_ACTION_PACKAGE_PATH), getMouseAdapter());
        populateRules();
        populateUnits();
        makeScreen();
    }
    
    /**
     * overrides the clear screen method to
     *          clear all parts of the LevelEditorScreen.
     */
    @Override
    public void clearScreen() {
        super.clearScreen();
        myActionSection.clear();
        mySelectedRules.setText("");
    }
    
    /**
     * helper method for rules section.
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    private void populateRules() throws IOException, ClassNotFoundException {
        myRules = new JComboBox();
        List<String> rules = getController().getClassNamesInPackage(RULES_PACKAGE_PATH);
        for (String rule: rules) {
            rule = rule.substring(0, rule.length() - "Factory".length());
            myRules.addItem(rule);
        }
    }
    
    /**
     * helper method to add the buttons to this screen.
     */
    public void makeScreen() {
        add(myActionSection, BorderLayout.CENTER);
        mySelectedRules = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        add(new JScrollPane(mySelectedRules), BorderLayout.CENTER);
        myAddRuleButton = new JButton(ADD_RULE_TEXT);
        myDeleteRuleButton = new JButton(DELETE_RULE_TEXT);
        myAddRuleButton.addMouseListener(getMouseAdapter());
        myDeleteRuleButton.addMouseListener(getMouseAdapter());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(myRules, BorderLayout.NORTH);
        buttonPanel.add(myAddRuleButton, BorderLayout.CENTER);
        buttonPanel.add(myDeleteRuleButton, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.WEST);
    }
    
    /**
     * helper method to get the units created.
     */
    private void populateUnits() {
        List<String> units = getController().getUnits();
        for (String unit : units) {
            myAvailableUnits.addItem(unit);
        }
    }

    /**
     * adds this level to the game.
     */
    public void addElementToGame () {
        getController().addLevelToGame(getName(), mySelectedRules.getText(),
                                             myActionSection.getActions());
    }
    
    @Override
    public void addAdditionalMouseBehavior(MouseEvent e) {
        if (e.getSource().equals(myAddRuleButton)) {
            mySelectedRules.setText(mySelectedRules.getText()
                                    + (String)myRules.getSelectedItem());
            try {
                List<String> parameters = getController().getParametersForAction(RULES_PACKAGE_PATH + "."
                                                            + myRules.getSelectedItem());
                for (String parameter : parameters) {
                    String p = JOptionPane.showInputDialog("Enter a " + parameter + " for this rule");
                    mySelectedRules.setText(mySelectedRules.getText() + " " + p);
                }
                mySelectedRules.setText(mySelectedRules.getText() + "\n");
            }
            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource().equals(myDeleteRuleButton)) {
            mySelectedRules.replaceSelection("");
        }
        myActionSection.doAdditionalMouseBehavior(e);
    }
}

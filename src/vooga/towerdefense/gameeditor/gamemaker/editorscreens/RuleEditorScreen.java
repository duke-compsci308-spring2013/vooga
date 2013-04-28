package vooga.towerdefense.gameeditor.gamemaker.editorscreens;

import java.awt.BorderLayout;
import java.awt.Dimension;
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


/**
 * RuleEditorScreen is responsible for helping
 *      the game developer add rules to their game.
 *
 * @author Angelica Schwartz
 */
public class RuleEditorScreen extends GameEditorScreen {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    /**
     * title for this screen.
     */
    private static final String TITLE = "RULE ";
    /**
     * next screen in the game editor.
     */
    private static final String NEXT_SCREEN_NAME = "LevelEditorScreen";
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
     * drop down box that will display the rule choices.
     */
    private JComboBox myRules;
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
     * constructor.
     * @param size
     * @param controller
     */
    public RuleEditorScreen (Dimension size,
                             GameEditorController controller) {
        super(size, controller, TITLE, NEXT_SCREEN_NAME);
        mySelectedRules = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        add(new JScrollPane(mySelectedRules), BorderLayout.CENTER);
        try {
            addRuleChoices();
            addButtons();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * helper method to add the rules to the drop down box.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void addRuleChoices() throws IOException, ClassNotFoundException {
        myRules = new JComboBox();
        List<String> rules = getController().getClassNamesInPackage(RULES_PACKAGE_PATH);
        for (String rule: rules) {
            rule = rule.substring(0, rule.length() - "Factory".length());
            myRules.addItem(rule);
        }
        add(myRules, BorderLayout.WEST);
    }
    
    /**
     * helper method to add the buttons to this screen.
     */
    private void addButtons() {
        myAddRuleButton = new JButton(ADD_RULE_TEXT);
        myDeleteRuleButton = new JButton(DELETE_RULE_TEXT);
        myAddRuleButton.addMouseListener(getMouseAdapter());
        myDeleteRuleButton.addMouseListener(getMouseAdapter());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(myAddRuleButton, BorderLayout.NORTH);
        buttonPanel.add(myDeleteRuleButton, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.WEST);
    }

    @Override
    public void addElementToGame () {
        // TODO Auto-generated method stub
        getController().addRulesToGame(mySelectedRules.getText());
    }

    /**
     * adds additional mouse behavior for the rules editor screen.
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
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
        
    }
    

}

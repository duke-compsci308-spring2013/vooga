package vooga.towerdefense.gameeditor.gamemaker.editorscreens.subeditorscreens;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import vooga.towerdefense.factories.ActionAnnotation;

/**
 * Panel that holds the action editor.
 *
 * @author Angelica Schwartz
 */
public class ActionSection extends SubEditorSection {
    
    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String ACTION_ADD_BUTTON_TEXT = "Add Action";
    private static final String FOLLOW_UP_ACTION_TEXT = "Add follow up action";
    private static final String ACTION_DELETE_BUTTON_TEXT = "Delete Action";
    private static final String FACTORY_INDICATOR_STRING = "Factory";
    private JComboBox myActionsBox;
    private JTextArea myActionsSelected;
    private JButton myAddActionButton;
    private JButton myAddFollowUpActionButton;
    private JButton myDeleteActionButton;
    private String myActionPath;

    /**
     * constructor.
     * @param title
     * @param path
     * @param actions
     * @param mouseAdapter
     */
    public ActionSection(String title, String path,
                         List<String> actions,
                         MouseAdapter mouseAdapter) {
        super(title);
        setPath(path);
        makeActionsSection(actions, mouseAdapter);
    }
    
    /**
     * makes this action section.
     * @param actions
     * @param mouseAdapter
     */
    public void makeActionsSection(List<String> actions,
                                   MouseAdapter mouseAdapter) {
        JPanel westSide = new JPanel(new BorderLayout());
        westSide.add(new JLabel(getTitle()), BorderLayout.NORTH);
        myActionsBox = new JComboBox();
        for (String a : actions) {
            myActionsBox.addItem(a);
        }
        westSide.add(myActionsBox, BorderLayout.CENTER);
        add(westSide, BorderLayout.WEST);
        myActionsSelected = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        add(new JScrollPane(myActionsSelected), BorderLayout.CENTER);
        myAddActionButton = new JButton(ACTION_ADD_BUTTON_TEXT);
        myAddActionButton.addMouseListener(mouseAdapter);
        JPanel optionsSubPanel = new JPanel(new BorderLayout());
        optionsSubPanel.add(myAddActionButton, BorderLayout.NORTH);
        myAddFollowUpActionButton = new JButton(FOLLOW_UP_ACTION_TEXT);
        myAddFollowUpActionButton.addMouseListener(mouseAdapter);
        optionsSubPanel.add(myAddFollowUpActionButton, BorderLayout.CENTER);
        myDeleteActionButton = new JButton(ACTION_DELETE_BUTTON_TEXT);
        myDeleteActionButton.addMouseListener(mouseAdapter);
        optionsSubPanel.add(myDeleteActionButton, BorderLayout.SOUTH);
        JPanel eastSide = new JPanel(new BorderLayout());
        eastSide.add(optionsSubPanel, BorderLayout.NORTH);
        add(eastSide, BorderLayout.EAST);
    }
    
    /**
     * gets the actions from this section.
     * @return
     */
    public String getActions() {
        return myActionsSelected.getText();
    }
    
    /**
     * sets the action path for this section.
     * @param actionPath
     */
    public void setPath(String actionPath) {
        myActionPath = actionPath;
    }
    
    /**
     * clears this section.
     */
    @Override
    public void clear() {
        myActionsSelected.setText("");
    }

    /**
     * handles additional mouse behavior for this action
     *          section.
     */
    @Override
    public void doAdditionalMouseBehavior (MouseEvent e) {
        if (e.getSource().equals(myAddActionButton)) {
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
            myActionsSelected.setText(myActionsSelected.getText().substring(0,
                                      myActionsSelected.getText().indexOf(parentAction))
                                      + parentAction + "\n" + childAction
                                      + myActionsSelected.getText().substring(myActionsSelected.getText().indexOf(parentAction)
                                      + parentAction.length()+1, myActionsSelected.getText().length()));
            
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
        try {
            Class c = Class.forName(myActionPath + "." + myActionsBox.getSelectedItem().toString()
                                    + FACTORY_INDICATOR_STRING);
            Constructor[] constructors = c.getConstructors();
            Annotation[][] annotations = constructors[0].getParameterAnnotations();
            String display = myActionsBox.getSelectedItem().toString();
            for (Annotation[] annotation : annotations) {
                for (Annotation a: annotation) {
                    display += " " + JOptionPane.showInputDialog("Enter the " + ((ActionAnnotation)a).name()
                                                             + " (" + ((ActionAnnotation)a).value() + ")"
                                                             + " for this action");
                }
            }
            return display + "\n";
        }
        catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}

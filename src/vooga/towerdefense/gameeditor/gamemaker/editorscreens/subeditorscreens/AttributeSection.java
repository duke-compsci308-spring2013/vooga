package vooga.towerdefense.gameeditor.gamemaker.editorscreens.subeditorscreens;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import vooga.towerdefense.attributes.AttributeConstantsEnum;

/**
 * Panel that holds the attribute editor screen.
 */
public class AttributeSection extends SubEditorSection {

    /**
     * default serialized id.
     */
    private static final long serialVersionUID = 1L;
    private static final String NEW_TEXT = "New";
    private static final String ATTRIBUTE_ADD_BUTTON_TEXT = "Add";
    private static final String ATTRIBUTE_DELETE_BUTTON_TEXT = "Delete";
    private JComboBox myAttributesBox;
    private JTextField myAttributeValue;
    private JTextArea myAttributesSelected;
    private JButton myAddAttributeButton;
    private JButton myDeleteAttributeButton;

    /**
     * constructor.
     * @param title
     * @param attributes
     * @param mouseAdapter
     */
    public AttributeSection(String title, List<String> attributes, MouseAdapter mouseAdapter) {
        super(title);
        try {
            makeAttributesSection(attributes, mouseAdapter);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * helper method to make the attributes drop down box,
     * button, and text field.
     * 
     * @throws ClassNotFoundException
     */
    private void makeAttributesSection (List<String> attributes, MouseAdapter mouseAdapter) throws ClassNotFoundException {
        JPanel optionsSubPanel1 = new JPanel(new BorderLayout());
        optionsSubPanel1.add(new JLabel(getTitle()), BorderLayout.NORTH);
        myAttributesBox = new JComboBox();
        attributes.add(NEW_TEXT);
        for (String a : attributes) {
            myAttributesBox.addItem(a);
        }
        optionsSubPanel1.add(myAttributesBox, BorderLayout.CENTER);
        myAttributeValue = new JTextField();
        optionsSubPanel1.add(myAttributeValue, BorderLayout.SOUTH);
        add(optionsSubPanel1, BorderLayout.WEST);
        myAttributesSelected = new JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        add(new JScrollPane(myAttributesSelected), BorderLayout.CENTER);
        JPanel optionsSubPanel2 = new JPanel(new BorderLayout());
        myAddAttributeButton = new JButton(ATTRIBUTE_ADD_BUTTON_TEXT);
        myAddAttributeButton.addMouseListener(mouseAdapter);
        optionsSubPanel2.add(myAddAttributeButton, BorderLayout.NORTH);
        myDeleteAttributeButton = new JButton(ATTRIBUTE_DELETE_BUTTON_TEXT);
        myDeleteAttributeButton.addMouseListener(mouseAdapter);
        optionsSubPanel2.add(myDeleteAttributeButton, BorderLayout.SOUTH);
        JPanel eastSide = new JPanel(new BorderLayout());
        eastSide.add(optionsSubPanel2, BorderLayout.NORTH);
        add(eastSide, BorderLayout.EAST);
    }
    
    /**
     * clears this section.
     */
    @Override
    public void clear() {
        myAttributesSelected.setText("");        
    }
    
    /**
     * handles mouse behavior for this AttributeSection.
     */
    public void doAdditionalMouseBehavior(MouseEvent e) {
        if (e.getSource().equals(myAddAttributeButton)) {
            if (myAttributesBox.getSelectedItem().equals(NEW_TEXT)) {
                String name = JOptionPane.showInputDialog("Enter the name of your new attribute");
                String value = JOptionPane.showInputDialog("Enter the value for " + name);
                myAttributesSelected.setText(myAttributesSelected.getText()
                   + name + " " + value + "\n");
            }
            else {
                AttributeConstantsEnum enumType
                    = AttributeConstantsEnum.valueOf
                    (myAttributesBox.getSelectedItem().toString());
                myAttributesSelected.setText(myAttributesSelected.getText()
                    + enumType.getStatusCode()
                    + " " + myAttributeValue.getText() + "\n");
            }
        }
        else if (e.getSource().equals(myDeleteAttributeButton)) {
            myAttributesSelected.replaceSelection("");
        }
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
            if (nameAndValue.length != 1) {
                attributeMap.put(nameAndValue[0], nameAndValue[1]);
            }
            else {
                attributeMap.put(nameAndValue[0], "");
            }
            fullAttributes = fullAttributes.substring(index+1);
        }
        return attributeMap;
    }
    
    /**
     * helper method to make a map from the string from a text area.
     * @param s is a string from a text area.
     */
    public Map<String, String> makeMap(String s) {
        Map<String, String> map = new HashMap<String, String>();
        String[] subParts = s.split("\n");
        for (String subPart : subParts) {
            if (!subPart.equals("")) {
                String[] nameAndValue = subPart.split(" ");
                map.put(nameAndValue[0], nameAndValue[1]);
            }
        }
        return map;
    }

}

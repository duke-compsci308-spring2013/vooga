package vooga.towerdefense.gameeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * ViewEditorScreen is responsible for helping
 * the game developer make the view.
 * 
 * @author Leonard K. Ng'eno
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
    private static final String NEXT_SCREEN_NAME = "mapeditor.MapEditorScreen";
    /**
     * package name for the available game screens.
     */
    private static final String SCREEN_PACKAGE_PATH = "vooga.towerdefense.view.gamescreens";
    /**
     * the name of the class the represents multiple panels.
     */
    private static final String MULTIPLE_PANEL_NAME = "MultipleScreenPanel";
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
    private JComboBox myMultiPanel;
    private JTextField myMultiPanelSize;
    private ActionListener myActionListener;
    private List<JComboBox> myJComboPanels;
    private JPanel mySouthernPanelScreen;
    private JComboBox mySouthernPanelPosition;
    private JButton myMultiButtonADD;
    private JButton myMultiButtonDONE;
    private Map<String, List<String>> myMultiScreenMap;
    private String myKey;
    private List<String> myScreens;
    private JLabel myScreenLabel, mySizeLabel;
    
    /**
     * Constructor.
     * 
     * @param size
     * @param controller
     */
    public ViewEditorScreen (Dimension size, GameEditorController controller) {
        super(size, controller, TITLE_NAME, NEXT_SCREEN_NAME);
        myJComboPanels = new ArrayList<JComboBox>();
        myMultiScreenMap = new HashMap<String, List<String>>();
        myScreens = new ArrayList<String>();
        makeActionListener();
        try {
            add(makeScreen(), BorderLayout.CENTER);
            add(makeSouthPanel(), BorderLayout.SOUTH);
            attachListeners();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Note: you must contain a mapscreen\nand shopscreen in your view");
    }
    
    private JComponent makeSouthPanel () {
        mySouthernPanelScreen = new JPanel();
        mySouthernPanelScreen.setPreferredSize(new Dimension(800, 100));
        myScreenLabel = new JLabel("SCREEN");
        mySouthernPanelScreen.add(myScreenLabel);
        mySouthernPanelScreen.add(myMultiPanel);
        mySizeLabel = new JLabel("SIZE");
        mySouthernPanelScreen.add(mySizeLabel);
        mySouthernPanelScreen.add(myMultiPanelSize);
        mySouthernPanelScreen.add(mySouthernPanelPosition);
        myMultiButtonADD = new JButton("ADD");
        myMultiButtonDONE = new JButton("DONE");
        myMultiButtonADD.addActionListener(myActionListener);
        myMultiButtonDONE.addActionListener(myActionListener);
        mySouthernPanelScreen.add(myMultiButtonADD);
        mySouthernPanelScreen.add(myMultiButtonDONE);
        this.add(mySouthernPanelScreen, BorderLayout.SOUTH);
        mySouthernPanelScreen.setVisible(false);

        return mySouthernPanelScreen;
    }

    private void makeActionListener () {
        myActionListener = new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                if (e.getSource().equals(myMultiButtonADD)){
                    addPanelToMultiplePanels();
                }
                else if (e.getSource().equals(myMultiButtonDONE)){
                    doneAddingMultiplePanels();
                }
                checkItemSelected(e);
            }
            
        };
    }

    private void doneAddingMultiplePanels () {
        myMultiScreenMap.put(myKey, myScreens);
        mySouthernPanelScreen.setVisible(false);
        mySouthernPanelPosition.setVisible(false);
        myMultiPanel.setVisible(false);
        myMultiPanelSize.setVisible(false);
        myMultiButtonADD.setVisible(false);
        myMultiButtonDONE.setVisible(false);
        myScreenLabel.setVisible(false);
        mySizeLabel.setVisible(false);
    }

    private void addPanelToMultiplePanels () {
        String position = (String)mySouthernPanelPosition.getSelectedItem();
        String screen = (String)myMultiPanel.getSelectedItem();
        String size = myMultiPanelSize.getText();
       size = size.replaceFirst(",", "");
        myScreens.add(position + " " + screen + " " + size);
        myMultiPanelSize.setText("");
    }

    private void checkItemSelected (ActionEvent e) {
        for (JComboBox s: myJComboPanels) {
            if (s.getSelectedItem().equals(MULTIPLE_PANEL_NAME)){
                mySouthernPanelScreen.setVisible(true);
                myKey = s.getName();
                s.resetKeyboardActions();
            }
        }
    }
    
    private JComponent makeScreen() throws IOException, ClassNotFoundException {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 400));
        
        myNorthPanel = new JComboBox();
        myNorthPanel.setName(NORTH_NAME);
        mySouthPanel = new JComboBox();
        mySouthPanel.setName(SOUTH_NAME);
        myCenterPanel = new JComboBox();
        myCenterPanel.setName(CENTER_NAME);
        myEastPanel = new JComboBox();
        myEastPanel.setName(EAST_NAME);
        myWestPanel = new JComboBox();
        myWestPanel.setName(WEST_NAME);
        myMultiPanel = new JComboBox();
        mySouthernPanelPosition = new JComboBox();
        myNorthSize = new JTextField(TEXT_FIELD_WIDTH);
        mySouthSize = new JTextField(TEXT_FIELD_WIDTH);
        myCenterSize = new JTextField(TEXT_FIELD_WIDTH);
        myEastSize = new JTextField(TEXT_FIELD_WIDTH);
        myWestSize = new JTextField(TEXT_FIELD_WIDTH);
        myMultiPanelSize = new JTextField(TEXT_FIELD_WIDTH);
        
        populateDropDoxBoxes();
        alignCenterScreenComponents(panel);
        
        return panel;
    }
    
    private void alignCenterScreenComponents(JPanel panel) {
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(800, 50));
        north.add(new JLabel(NORTH_NAME));
        north.add(myNorthPanel);
        north.add(myNorthSize);
        panel.add(north);
        
        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(800, 50));
        center.add(new JLabel(CENTER_NAME));
        center.add(myCenterPanel);
        center.add(myCenterSize);
        panel.add(center);
        
        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(800, 50));
        south.add(new JLabel(SOUTH_NAME));
        south.add(mySouthPanel);
        south.add(mySouthSize);
        panel.add(south);
        
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(800, 50));
        east.add(new JLabel(EAST_NAME));
        east.add(myEastPanel);
        east.add(myEastSize);
        panel.add(east);
        
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(800, 50));
        west.add(new JLabel(WEST_NAME), BorderLayout.WEST);
        west.add(myWestPanel, BorderLayout.WEST);
        west.add(myWestSize, BorderLayout.WEST);
        panel.add(west);
    }
    
    private void attachListeners () {
        myJComboPanels.add(myCenterPanel);
        myJComboPanels.add(myEastPanel);
        myJComboPanels.add(myNorthPanel);
        myJComboPanels.add(mySouthPanel);
        myJComboPanels.add(myWestPanel);
        
        for (JComboBox jc : myJComboPanels) {
            jc.addActionListener(myActionListener);
        }
    }
    
    private void populateDropDoxBoxes() throws IOException, ClassNotFoundException {
        List<String> screens = getController().getClassNamesInPackage(SCREEN_PACKAGE_PATH);
        screens.add("");
        for (String s : screens) {
            myNorthPanel.addItem(s);
            mySouthPanel.addItem(s);
            myCenterPanel.addItem(s);
            myEastPanel.addItem(s);
            myWestPanel.addItem(s);
            myMultiPanel.addItem(s);
        }
        myNorthPanel.setSelectedItem("");
        mySouthPanel.setSelectedItem("");
        myCenterPanel.setSelectedItem("");
        myEastPanel.setSelectedItem("");
        myWestPanel.setSelectedItem("");
        
        mySouthernPanelPosition.addItem(NORTH_NAME);
        mySouthernPanelPosition.addItem(CENTER_NAME);
        mySouthernPanelPosition.addItem(SOUTH_NAME);
        mySouthernPanelPosition.addItem(EAST_NAME);
        mySouthernPanelPosition.addItem(WEST_NAME);
    }

    /**
     * adds this view to the game.
     */
    @Override
    public void addElementToGame () {
        getController().setMapSize(getMapDimension());
        List<String> viewInfo = makeViewStrings();
        String dimension = getTopLevelContainerDimension();
        getController().addViewToGame(dimension, viewInfo, myMultiScreenMap);
    }
    
    
    /**
     * makes a map of the screen name to the location.
     */
    private List<String> makeViewStrings() {
        List<String> viewInfo = new ArrayList<String>();
        String s = (String)myNorthPanel.getSelectedItem() + " " + myNorthSize.getText() + " " + "NORTH";
        viewInfo.add(s);
        s = (String)mySouthPanel.getSelectedItem() + " " + mySouthSize.getText() + " " + "SOUTH";
        viewInfo.add(s);
        s = (String)myCenterPanel.getSelectedItem() + " " + myCenterSize.getText() + " " + "CENTER";
        viewInfo.add(s);
        s = (String)myEastPanel.getSelectedItem() + " " + myEastSize.getText() + " " + "EAST";
        viewInfo.add(s);
        s = (String)myWestPanel.getSelectedItem() + " " + myWestSize.getText() + " " + "WEST";
        viewInfo.add(s);
        return viewInfo;
    }
    
    /**
     * helper method to get the map dimension from the string.
     * @return dimension of the map screen
     */
    private Dimension getMapDimension() {
        String dimension = "";
        if (myNorthPanel.getSelectedItem().equals("MapScreen")) {
            dimension += myNorthSize.getText();
        }
        else if (mySouthPanel.getSelectedItem().equals("MapScreen")) {
            dimension += mySouthSize.getText();
        }
        else if (myCenterPanel.getSelectedItem().equals("MapScreen")) {
            dimension += myCenterSize.getText();
        }
        else if (myEastPanel.getSelectedItem().equals("MapScreen")) {
            dimension += myEastSize.getText();
        }
        else {
            dimension += myWestSize.getText();
        }
        String[] dim = dimension.split(", ");
        return (new Dimension(Integer.parseInt(dim[0]), Integer.parseInt(dim[1])));
    }

    /**
     * adds additional mouse behavior specific
     * to the ViewEditorScreen.
     * 
     * @param e is the MouseEvent
     */
    @Override
    public void addAdditionalMouseBehavior (MouseEvent e) {
        List<String> screens = new ArrayList<String>();
        screens.add((String)myNorthPanel.getSelectedItem());
        screens.add((String)mySouthPanel.getSelectedItem());
        screens.add((String)myCenterPanel.getSelectedItem());
        screens.add((String)myEastPanel.getSelectedItem());
        screens.add((String)myWestPanel.getSelectedItem());
        if (screens.contains(MULTIPLE_PANEL_NAME)) {
           
        }
    }
}

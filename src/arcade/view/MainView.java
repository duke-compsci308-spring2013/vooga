
package arcade.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import arcade.games.GameInfo;
import arcade.model.Model;
import arcade.view.panels.ButtonPanel;
import arcade.view.panels.GameCenterPanel;
import arcade.view.panels.SocialCenterPanel;
import arcade.view.panels.StorePanel;

@SuppressWarnings("serial")

public class MainView extends JFrame {
	private static String TITLE = "The Awesome Vooga Center";
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 620;
	private static final String GAMECENTER = "Game Center";
	private static final String SOCIALCENTER = "Social Center";
	private static final String STORE = "Game Store";
	private static final String[] centers = { GAMECENTER, SOCIALCENTER, STORE };
	private JPanel myButtonPanel, myViewPanel;
	private JPanel[] myViewPanelList;
	private JPanel myContentPanel;
	private Model myModel;
	private ResourceBundle myResources;




	/**
	 * 
	 * @param model
	 * @param rb
	 */
	public MainView (Model model, ResourceBundle rb) {
		myModel = model;
		myResources = rb;
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		myContentPanel = (JPanel) getContentPane();
		myContentPanel.setBackground( Color.WHITE );
		myContentPanel.setLayout(null);

		// Panels
		myButtonPanel = new ButtonPanel(this);
		myButtonPanel.setBounds(0, 0, 140, WINDOW_HEIGHT);
		myContentPanel.add(myButtonPanel);

		createViewPanel();
		// Buttons

		
		setVisible(true);
	}

	/**
	 * 
	 */
	private void createViewPanel () {
		myViewPanelList = new JPanel[3];
		myViewPanelList[0] = new GameCenterPanel(this, myModel);
		myViewPanelList[1] = new SocialCenterPanel();
		myViewPanelList[2] = new StorePanel(myModel);

		myViewPanel = new JPanel(new CardLayout());
		myViewPanel.setBounds(150, 0, 650, 600);
		myViewPanel.add(myViewPanelList[0], GAMECENTER);
		myViewPanel.add(myViewPanelList[1], SOCIALCENTER);
		myViewPanel.add(myViewPanelList[2], STORE);
		initializeViewPanel();
		myContentPanel.add(myViewPanel);
	}

	/**
	 * 
	 */
	private void initializeViewPanel () {
		changeViewPanel(0);
	}

	/**
	 * 
	 * @param index
	 */
	public void changeViewPanel (int index) {
		// Second trial
		CardLayout temp = (CardLayout) (myViewPanel.getLayout());
		temp.show(myViewPanel, centers[index]);
	}

	public Collection<GameInfo> getGameList () {
		return myModel.getGameList();
	}
	
	public ResourceBundle getResources() {
	    return myResources;
	}

}


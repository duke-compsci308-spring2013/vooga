
package arcade.view.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import arcade.view.MainView;


public class ButtonPanel extends JPanel {
    /**
     * Constants.
     */
    private static final long serialVersionUID = 7307619822247104115L;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 150;
    private static final int ORIGIN = 0;
    private static final int GAMECENTER_INDEX = 0;
    private static final int SOCIALCENTER_INDEX = 1;
    private static final int STORE_INDEX = 2;

    /**
         * 
         */
    private MainView myUpperLevelPanel;

    /**
     * Constructor
     */
    public ButtonPanel (MainView upperLevel) {
        myUpperLevelPanel = upperLevel;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.lightGray);
        // Buttons
        String localDirectory = System.getProperty("user.dir");
        ImageIcon gameCenterIcon = new ImageIcon(localDirectory+"/src/arcade/resources/images/GameCenterIcon.jpg");
        ImageIcon socialCenterIcon = new ImageIcon(localDirectory+"/src/arcade/resources/images/SocialCenterIcon.jpg");
        ImageIcon storeIcon = new ImageIcon(localDirectory+"/src/arcade/resources/images/StoreIcon.jpg");
        
        JButton gameCenterButton = new JButton(gameCenterIcon);
        JButton socialCenterButton = new JButton(socialCenterIcon);
        JButton storeButton = new JButton(storeIcon);
        
        gameCenterButton.setBounds(ORIGIN, ORIGIN, BUTTON_WIDTH+20, BUTTON_HEIGHT);
        socialCenterButton.setBounds(ORIGIN, WINDOW_HEIGHT / 2 - BUTTON_HEIGHT / 2, BUTTON_WIDTH+20,
                                     BUTTON_HEIGHT);
        storeButton.setBounds(ORIGIN, WINDOW_HEIGHT - BUTTON_HEIGHT, BUTTON_WIDTH+20, BUTTON_HEIGHT);
        
        JLabel gameCenterLabel = new JLabel("<html><b><font size = 5><font face = champion>Game Center</font></font></b></html>");
        JLabel socialCenterLabel = new JLabel("<html><b><font size = 5><font face = champion>Social Center</font></font></b></html>");
        JLabel storeLabel = new JLabel("<html><b><font size = 5><font face = champion>Game Store</font></font></b></html>");
        
        
        add(gameCenterButton);
        add(gameCenterLabel);
        add(socialCenterButton);
        add(socialCenterLabel);
        add(storeButton);
        add(storeLabel);

        // Set-up Action Listener
        gameCenterButton.addActionListener(actionListenerConstructor(GAMECENTER_INDEX));
        socialCenterButton.addActionListener(actionListenerConstructor(SOCIALCENTER_INDEX));
        storeButton.addActionListener(actionListenerConstructor(STORE_INDEX));
    }

    private ActionListener actionListenerConstructor (final int index) {
        ActionListener output = new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                myUpperLevelPanel.changeViewPanel(index);
            }
        };
        return output;
    }
}


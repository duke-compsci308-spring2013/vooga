package vooga.rts.networking.client.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import vooga.rts.networking.communications.Message;
import vooga.rts.networking.communications.clientmessages.HostDescriptionMessage;
import vooga.rts.networking.logger.NetworkLogger;


public class ServerBrowser extends JFrame {
    
    private static final long serialVersionUID = -4494998676210936451L;
    private JTable myServerListTable;
    private String[] imageFileNames = { "Scroll.png", "Scroll1.jpg",
                                        "Scroll2.jpg", "Scroll3.jpg","Scroll.png", "Scroll1.jpg",
                                        "Scroll2.jpg", "Scroll3.jpg"};
    private JToolBar myBar = new JToolBar();
    private Map<JButton, JPanel> myCardMap;
    
    private JPanel myCards = new JPanel();
    private Queue<Message> myMessageQueue;
    
    private int index = 0;
    
    
    /**
     * Create the panel.
     */
    public ServerBrowser () {

        this.setPreferredSize(new Dimension(800,600));

        myCardMap = new HashMap<JButton, JPanel>();
        myMessageQueue = new LinkedList<Message>();
        createJoinPanel();
        
        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }
    
    protected JComponent setToolBar(){
        myBar.add(Box.createGlue());
        myBar.add(Box.createGlue());
        myBar.setOpaque(false);
        myBar.setFloatable(false);
        myBar.setRollover(true);
        JScrollPane scroll = new JScrollPane(myBar);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scroll;
    }
    
    public void removeConnection(String hostName){
        CardLayout card = (CardLayout) myCards.getLayout();
        for (Component c : myCards.getComponents()){
            
            JPanel u = (JPanel) c;
            System.out.println(u.getName());
            if (u.getName().equals(hostName)){
                System.out.println("removing "+hostName);
                card.removeLayoutComponent(u);
            }
        }    
    }
    
    public void addConnection(Message m) {
        HostDescriptionMessage host = (HostDescriptionMessage) m;
        ImageIcon icon;
        icon = ImageHelper.getImageIcon(host.getImagePath());
        ThumbnailAction thumbAction = null;      
        ImageIcon thumbnailIcon = new ImageIcon(ImageHelper.getScaledImage(icon.getImage(),80));
        thumbAction = new ThumbnailAction("panel "+index, thumbnailIcon);
        
        JButton thumbButton = new JButton(thumbAction);
        thumbButton.setActionCommand(thumbAction.getAction());
        JPanel card = DescriptionCardFactory.getInstance().createCard("../../resources/" + imageFileNames[index]);
        myCards.add("panel "+index,card);
        myCardMap.put(thumbButton, card);

        myBar.add(thumbButton, myBar.getComponentCount() - 1); 
        index++;
    }
    
    private class ThumbnailAction extends AbstractAction{
        
        //private Icon
        private String myAction;
         
        /**
         * @param Icon - The thumbnail to show in the button.
         * @param String - The descriptioon of the icon.
         */
        public ThumbnailAction(String action, Icon thumb){
            myAction = action;
            putValue(LARGE_ICON_KEY, thumb);
        }
        
        public String getAction(){
            return myAction;
        }
         
        /**
         * Shows the full image in the main area and sets the application title.
         */
        public void actionPerformed(ActionEvent e) {
            CardLayout layout = (CardLayout) myCards.getLayout();
            System.out.println("Action "+e.getActionCommand());
            layout.show(myCards, e.getActionCommand());
           
        }
    }

    private void createJoinPanel () {
        BackgroundPanel joinPanel = new BackgroundPanel();
        JPanel workingPanel = new JPanel();
        joinPanel.add(workingPanel);
        workingPanel.setLayout(new BorderLayout(10,10));
        
        workingPanel.add(setToolBar(),BorderLayout.SOUTH);

        // TODO get the name of the game and display it here
        myCards.setLayout(new CardLayout());
        myCards.setOpaque(false);
        workingPanel.add(myCards,BorderLayout.CENTER);
        workingPanel.setOpaque(false);
        getContentPane().add(joinPanel);
    }

}

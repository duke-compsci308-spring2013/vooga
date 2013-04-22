package arcade.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import arcade.view.modes.ArcadeMode;

/**
 * 
 * The panel where the buttons are placed to switch between Arcade Modes.
 * 
 * @author David Liu, Ellango Jothimurugesan, Henrique Moraes
 *
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
    private static final String IMAGES_DIRECTORY = System.getProperty("user.dir") + "/src/arcade/resources/images/";
    private static final String GAME_CENTER_PICTURE_NAME = "GameCenterIcon.jpg";
    private static final String SOCIAL_CENTER_PICTURE_NAME = "SocialCenterIcon.jpg";
    private static final String GAME_STORE_PICTURE_NAME = "StoreIcon.jpg";
    private static final int BUTTON_SIZE = 140;

    private MainView myMainView;
    private ResourceBundle myResources;

    /**
     * Creates a ButtonPanel with the mainView that it can manipulate by changing
     * which mode is showing, and a ResourceBundle
     * 
     * @param mainView
     * @param resources
     */
    public ButtonPanel (MainView mainView, ResourceBundle resources) {
        myMainView = mainView;
        myResources = resources;
        setBackground(Color.lightGray);
        add(createButtons());
    }
    
    /**
     * Creates the buttons in the panel.
     */
    private Component createButtons() {
        JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
        
        toolbar.add(makeButton(GAME_CENTER_PICTURE_NAME, ArcadeMode.GAMECENTER));
        toolbar.add(makeButtonLabel(myResources.getString(TextKeywords.GAME_CENTER)));
        
        toolbar.add(makeButton(SOCIAL_CENTER_PICTURE_NAME, ArcadeMode.SOCIALCENTER));
        toolbar.add(makeButtonLabel(myResources.getString(TextKeywords.SOCIAL_CENTER)));
        
        toolbar.add(makeButton(GAME_STORE_PICTURE_NAME, ArcadeMode.STORE));
        toolbar.add(makeButtonLabel(myResources.getString(TextKeywords.GAME_STORE)));
        
        toolbar.setOpaque(false);
        toolbar.setFloatable(false);
        return toolbar;
    }
    
    /**
     * Makes a single button with the filename of the image to place on the button,
     * and the ArcadeMode that it changes the MainView to.
     * 
     * @param imageFilename
     * @param mode
     * @return
     */
    private JButton makeButton(String imageFilename, final ArcadeMode mode) {
        JButton button = new JButton(createScaledIcon(imageFilename, BUTTON_SIZE));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent arg0) {
                myMainView.changeViewPanel(mode);
            }
        });
        button.setOpaque(false);
        return button;
    }
    
    /**
     * Creates a label with the specified text, formatted with a champion font
     * and bolded.
     * 
     * @param text
     * @return
     */
    private JLabel makeButtonLabel(String text) {
        return new JLabel("<html><b><font size = 5><font face = champion>" + text);
    }
    
    /**
     * A small utility method used to create an image icon from the provided
     * filepath, scaled to the desired size.
     * 
     * @param imageFilename
     * @param size
     * @return
     */
    private ImageIcon createScaledIcon(String imageFilename, int size){
        Image image = new ImageIcon(IMAGES_DIRECTORY + imageFilename).getImage();
        BufferedImage buffer = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, size, size, null);
        g2.dispose();
        return new ImageIcon(buffer);
    }
}


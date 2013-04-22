package arcade.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import arcade.games.GameInfo;
import arcade.model.Model;

/**
 * A SnapShot is a small clickable panel that represents a Game in the GameCenter
 * and Store.  Clicking a SnapShot opens up a more detailed view.
 * 
 * @author David Liu, Ellango Jothimurugesan
 * 
 */
@SuppressWarnings("serial")
public class SnapShot extends JPanel {

    public static final int THUMBNAIL_SIZE = 190;
    private GameInfo myGameInfo;
    private ResourceBundle myResources;
    private Model myModel;

    public SnapShot (GameInfo info, ResourceBundle resources, Model model) {
        myModel = model;
        myGameInfo = info;
        myResources = resources;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createThumbnail());
        add(createTitle());
        add(createRating());
        
        this.addMouseListener(createListener());
    }

    /**
     * Creates the thumbnail to be displayed in the SnapShot
     * @return
     */
    private Component createThumbnail () {
        ImageIcon icon = myGameInfo.getThumbnail();
        ImageIcon scaledIcon = createScaledIcon(icon, THUMBNAIL_SIZE);
        return new JLabel(scaledIcon);
    }
    
    /**
     * Creates the title to be displayed. The title is the game's name.
     * @return
     */
    private Component createTitle() {
        return new JLabel("<html><b><font size = 4>" + myGameInfo.getName());
    }
    
    /**
     * Creates the label for the rating of the game.
     * @return
     */
    private Component createRating() {
        return new JLabel(myGameInfo.getRating() + "");
    }
    
    /**
     * Creates a mouse listener so that upon clicking a SnapShot, the 
     * DetailView is launched.
     * 
     * @return
     */
    private MouseListener createListener () {
        return new MouseListener() {
            @Override
            public void mouseClicked (MouseEvent arg0) {
                if (arg0.getClickCount() == 2) {
                    new DetailView(myGameInfo, myResources, myModel);
                }
            }

            // these actions don't trigger any events.
            @Override
            public void mouseReleased (MouseEvent arg0) {}
            @Override
            public void mousePressed (MouseEvent arg0) {}
            @Override
            public void mouseExited (MouseEvent arg0) {}
            @Override
            public void mouseEntered (MouseEvent arg0) {}

        };
    }

    /**
     * TODO: REMOVE THE DUPLICATED CODE FROM HERE AND ButtonPanel
     * @param icon
     * @param size
     * @return
     */
    private ImageIcon createScaledIcon(ImageIcon icon, int size){
        Image image = icon.getImage();
        BufferedImage buffer = new BufferedImage(size,size,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, size, size, null);
        g2.dispose();
        return new ImageIcon(buffer);
    }
}

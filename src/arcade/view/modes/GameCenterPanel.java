package arcade.view.modes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import arcade.games.GameInfo;
import arcade.model.Model;
import arcade.view.SnapShot;

/**
 * 
 * @author David Liu, Ellango Jothimurugesan
 *
 */
@SuppressWarnings("serial")
public class GameCenterPanel extends JPanel {

    private static final int SNAPSHOTS_PER_ROW = 3;
    private Model myModel;
    private ResourceBundle myResources;

    /**
     * Constructor
     */
    public GameCenterPanel (Model model, ResourceBundle resources) {
        myModel = model;
        myResources = resources;
        
        setBackground(Color.WHITE);
        JPanel allSnapShots = new JPanel();
        allSnapShots.setBackground(Color.WHITE);
        allSnapShots.setLayout(new BoxLayout(allSnapShots, BoxLayout.Y_AXIS));
        createSnapShots(allSnapShots);
        add(allSnapShots);
        //add(new JScrollPane(allSnapShots));
    }

    /**
     * Create the snapshots for the games
     * Fixed so that it tiles dynamically as games are added instead of repeating instances of games
     * @author Josh Waldman
     */
    private void createSnapShots (JPanel panel) {
        int counter = 0;
        JPanel row = createNewRow();
        for (GameInfo info : myModel.getGameList()) {
            SnapShot snapshot = new SnapShot(info, myResources, myModel);
            if (counter % SNAPSHOTS_PER_ROW == 0) {
                row = createNewRow();
                panel.add(row);
            }
            row.add(snapshot);
            counter++;
        }
        int numToAdd = SNAPSHOTS_PER_ROW - (counter % SNAPSHOTS_PER_ROW);
        for (int i = 0; i < numToAdd; i++){
            row.add(createFiller());
        }
    }
    
    private JPanel createNewRow() {
        JPanel row = new JPanel();
        row.setBackground(Color.WHITE);
        return row;
    }
    
    private JPanel createFiller() {
        JPanel filler = new JPanel();
        filler.setBackground(Color.WHITE);
        filler.setPreferredSize(new Dimension(SnapShot.THUMBNAIL_SIZE, SnapShot.THUMBNAIL_SIZE));
        return filler;
    }
}

package vooga.towerdefense.view.gamescreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Map;
import javax.swing.JPanel;
import vooga.towerdefense.util.ValueText;


/**
 * Heads Up Display of the game statistics
 * 
 * @author Leonard K. Ng'eno
 * 
 */

public class GameStatsScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final String CURRENCY_LABEL = "Currency";
    private static final String HEALTH_LABEL = "Health";
    private static final String SCORE_LABEL = "Score";
    private static final Color LABEL_COLOR = Color.GREEN;
    private static final int LABELS_X_OFFSET = 50;
    private static final int MONEY_LABEL_Y_OFFSET = 10;
    private static final int LIVES_LABEL_Y_OFFSET = 30;
    private static final int SCORES_LABEL_Y_OFFSET = 50;
    private static final Point LIVES_POINT = new Point(LABELS_X_OFFSET, LIVES_LABEL_Y_OFFSET);
    private static final Point MONEY_POINT = new Point(LABELS_X_OFFSET, MONEY_LABEL_Y_OFFSET);
    private static final Point SCORES_POINT = new Point(LABELS_X_OFFSET, SCORES_LABEL_Y_OFFSET);
    private static final int XCOORD = 0;
    private static final int YCOORD = 0;
    private int myStartingMoney = 0;
    private int myStartingLives = 5;
    private int myStartingScore = 0;
    private Color myBackgroundColor = Color.WHITE;
    private ValueText myCurrency;
    private ValueText myHealth;
    private ValueText myScore;

    public GameStatsScreen (Dimension size) {
        setPreferredSize(size);
        setFocusable(true);

        initStatistics();

        setVisible(true);
    }

    @Override
    public void paintComponent (Graphics pen) {
        super.paintComponent(pen);
        pen.setColor(myBackgroundColor);
        pen.fillRect(XCOORD, YCOORD, getSize().width, getSize().height);
        paintStats((Graphics2D) pen);
    }

    /**
     * Update information on screen
     * @param map       contains health, scores, currency and their values
     */
    public void updatePlayerStatistics (Map<String, Integer> map) {
       updateCurrency(map.get(CURRENCY_LABEL)); 
       updateLives(map.get(HEALTH_LABEL));
       updateScores(map.get(SCORE_LABEL));
       repaint();
    }
    
    private void updateCurrency(int value) {
        myCurrency.updateValue(+value);
    }
    
    private void updateLives (int value) {
        myHealth.updateValue(+value);
    }
    
    private void updateScores(int value) {
        myScore.updateValue(+value);
    }
    
    // TODO load these from a file or somewhere
    private void initStatistics () {
        myCurrency = new ValueText(CURRENCY_LABEL, myStartingMoney);
        myHealth = new ValueText(HEALTH_LABEL, myStartingLives);
        myScore = new ValueText(SCORE_LABEL, myStartingScore);
    }

    private void paintStats (Graphics2D pen) {
        myCurrency.paint(pen, MONEY_POINT, LABEL_COLOR);
        myHealth.paint(pen, LIVES_POINT, LABEL_COLOR);
        myScore.paint(pen, SCORES_POINT, LABEL_COLOR);
    }
}

package vooga.scroller.statistics;

import java.awt.Color;
import java.awt.geom.Point2D;
import util.Location;

public class PlayerScore implements Statistic {

    private static final String DEFAULT_STRING = "SCORE";
    public static final Point2D DEFAULT_SCORE_LOCATION = new Location(50,20);
    public static final Color DEFAULT_SCORE_COLOR = Color.black;
    private int myScore;

    @Override
    public void addValue (int val) {
        myScore += val;
        
    }

    @Override
    public void removeValue (int val) {
        myScore -= val;
    }

    @Override
    public int getAggregateValue () {
        return myScore;
    }

    @Override
    public String getName () {
        return DEFAULT_STRING;
    }
    



}

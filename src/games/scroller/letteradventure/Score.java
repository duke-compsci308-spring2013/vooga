package games.scroller.letteradventure;

import vooga.scroller.statistics.Statistic;

/**
 * This class represents the user's score in LetterAdventure
 * @author Ellango, David Liu
 *
 */
public class Score implements Statistic {

    private static final String NAME = "SCORE";
    private int myScore;

    public Score (int initialScore) {
        myScore = initialScore;
    }

    @Override
    public void addValue (int val) {
        myScore += val;
    }

    @Override
    public void removeValue (int val) {
        if (val > 0) {
            myScore -= val;
        }
    }

    @Override
    public int getAggregateValue () {

        return myScore;
    }

    @Override
    public String getName () {
        return NAME;
    }

}

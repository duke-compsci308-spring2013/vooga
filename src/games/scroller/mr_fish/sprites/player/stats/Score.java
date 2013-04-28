package games.scroller.mr_fish.sprites.player.stats;

import vooga.scroller.statistics.Statistic;

public class Score implements Statistic {

    private static final String NAME = "MR. FISH SCORE";
    private int myScore;
    public Score(){
        myScore = 0;
    }
    
    @Override
    public void addValue (int val) {
        myScore +=val;
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
        return NAME;
    }

}

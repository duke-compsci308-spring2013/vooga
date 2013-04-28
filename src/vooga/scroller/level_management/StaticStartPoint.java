package vooga.scroller.level_management;

import util.Location;
import vooga.scroller.level_editor.Level;

/**
 * Acts as a start point for IDoors to go to.
 * 
 * This simply acts as a data struct with a Level and a Location that specify the next starting point.
 * 
 * @author Scott Valentine
 *
 */
public class StaticStartPoint implements StartPoint {

    private Level myLevel;
    private Location myLocation;
    
    public StaticStartPoint (Level level, Location location) {
        myLevel = level;
        myLocation = location;
    }

    @Override
    public int getLevelId () {
        return myLevel.getID();
    }

    @Override
    public Location getStartLocation () {       
        return myLocation;
    }

    @Override
    public Level getLevel () {
        return myLevel;
    }

}

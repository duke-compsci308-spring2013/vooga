package vooga.scroller.level_management;

import util.Location;
import vooga.scroller.level_editor.Level;

public interface StartPoint {

    public int getLevelId();
    
    public Level getLevel();
    
    public Location getStartLocation();
}

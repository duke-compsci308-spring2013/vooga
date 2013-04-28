package vooga.rts.ai;

import java.awt.Dimension;
import vooga.rts.map.*;
import util.Location;

public class AIMain {

    /**
     * @param args
     */
    public static void main (String[] args) {
        GameMap map = new GameMap(new Dimension(0,0), new Dimension(0,0));
        PathingHelper help = new PathingHelper(map);
        help.constructPath(map.get(0,0), map.get(2,1));
        Location next = new Location(0,0);
        while(help.size() > 0) {
            next = help.getNext(next);
            System.out.println(next.x + next.y + "\n");
        }
    }

}

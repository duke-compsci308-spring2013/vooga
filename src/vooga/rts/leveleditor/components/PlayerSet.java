package vooga.rts.leveleditor.components;

import java.util.HashMap;
import java.util.Map;
import vooga.rts.util.Location;

public class PlayerSet {
    
    private int myPlayerUpperLimit;
    private int myTeamUpperLimit;
    private int myPlayerNumber;
    
    private Map<Integer,Location> myPlayers;
    
    
    public PlayerSet(int playerLimit, int teamLimit) {
        myPlayerUpperLimit = playerLimit;
        myTeamUpperLimit = teamLimit;
        myPlayerNumber = 0;
        
        myPlayers = new HashMap<Integer,Location>();
    }
    
    public void addPlayer(Location loc) {
        myPlayers.put(myPlayerNumber + 1, loc);
    }
    
    public void addPlayer(int x, int y) {
        addPlayer(new Location(x,y));
    }
    
    public void removePlayer(Location startLocation, Location endLocation) {
        int startX = (int)startLocation.getX();
        int startY = (int)startLocation.getY();
        
        int endX = (int) endLocation.getX();
        int endY = (int) endLocation.getY();
        removePlayer(startX,startY,endX,endY);      
    }
    
    public void removePlayer(int startX, int startY, int endX, int endY) {
       
       for(Integer i : myPlayers.keySet()) {
            Location loc = myPlayers.get(i);
            int x = (int)loc.getX();
            int y = (int)loc.getY();
            if( ((x>=startX && x<=endX)||(x<=startX && x>= endX))&&((y>=startY && y<=endY)||(y<=startY && y>= endY))) {
                myPlayers.remove(i);
            }
       }
    }

    public void clearPlayers() {
        myPlayers.clear();
    }

    public int getMyPlayerUpperLimit() {
        return myPlayerUpperLimit;
    }

    public int getMyTeamUpperLimit() {
        return myTeamUpperLimit; 
    }
    
    public int getMyPlayerNumber() {
        return myPlayerNumber;
    }
    
    public Map<Integer,Location> getAllPlayers() {
        return myPlayers;
    }
}

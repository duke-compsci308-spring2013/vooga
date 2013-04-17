package vooga.rts.networking.client;

import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import vooga.rts.networking.server.GameContainer;

public class MapsAccessor {
    private static final String DEFAULT_RESOURCE_PACKAGE = "vooga.rts.networking.resources.";
    private ResourceBundle myMapResources;
    private Map<String, GameMap> myMaps;
    
    public MapsAccessor() {
        myMapResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "maps");
        for(String map : myMapResources.keySet()) {
            String values = myMapResources.getString(map);
            String [] vals = values.split(", "); 
            String nameOfMap = vals[0];
            int maxNumOfPlayers = Integer.parseInt(vals[1]);//TODO: could cause error if not an int, write metho to abstract this with try catch
            int playersPerTeam = Integer.parseInt(vals[2]);//same situation
           
            myMaps.put(nameOfMap, new GameMap(nameOfMap, getPathOfMap(nameOfMap), playersPerTeam, maxNumOfPlayers));
        }
    }
    
    
    private String getPathOfMap(String localName) {
        return "map directory path" + localName;//TODO: establish default maps directory location in reference to user.dir
    }
}

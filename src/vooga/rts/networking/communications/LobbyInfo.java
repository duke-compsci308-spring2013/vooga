package vooga.rts.networking.communications;

import java.io.Serializable;

public class LobbyInfo implements Serializable {

    private static final long serialVersionUID = 8433220026468566119L;
    private String myServerName;
    private int myMaxPlayers;
    private int myCurrentPlayers;
    private String myMapName;
    
    LobbyInfo (String serverName, int maxPlayers, int currentPlayers, String mapName) {
        myServerName = serverName;
        myMaxPlayers = maxPlayers;
        myCurrentPlayers = currentPlayers;
        myMapName = mapName;
    }
    
    public String getServerName () {
        return myServerName;
    }
    
    public int getMaxPlayers () {
        return myMaxPlayers;
    }
    
    public int getCurrentPlayers () {
        return myCurrentPlayers;
    }
    
    public String getMapName () {
        return myMapName;
    }
    
}

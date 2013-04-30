package vooga.rts.networking.communications.infoobjects;

/**
 * Basic lobby information used for sending across the network to the server browser. Lightweight so
 * we can send many of these very quickly. Contains information on the lobby name, maximum number of
 * players, current lobby id, and map name.
 * 
 * Implements the LobbyInfo interface:
 * 
 * @see vooga.rts.networking.communications.infoobjects.LobbyInfo
 * 
 * @author David Winegar
 * @author Sean Wareham
 * 
 */
public class SmallLobbyInfo implements LobbyInfo {

    private static final long serialVersionUID = -1941237597305628081L;
    private String myLobbyName;
    private int myMaxPlayers;
    private String myMapName;
    private int myPlayersCount = 0;
    private int myLobbyID;

    /**
     * Creates the lobbyInfo.
     * 
     * @param lobbyName name of lobby
     * @param mapName name of map
     * @param maxPlayers maximum players
     * @param lobbyId id
     */
    public SmallLobbyInfo (String lobbyName, String mapName, int maxPlayers, int lobbyId) {
        myLobbyName = lobbyName;
        myMaxPlayers = maxPlayers;
        myMapName = mapName;
        myLobbyID = lobbyId;
    }

    /**
     * Constructor that creates a new LobbyInfo with a different ID.
     * 
     * @param lobbyInfo info
     * @param newID new ID
     */
    public SmallLobbyInfo (SmallLobbyInfo lobbyInfo, int newID) {
        this(lobbyInfo.getLobbyName(), lobbyInfo.getMapName(), lobbyInfo.getMaxPlayers(), newID);
    }

    @Override
    public String getLobbyName () {
        return myLobbyName;
    }

    @Override
    public int getMaxPlayers () {
        return myMaxPlayers;
    }

    @Override
    public String getMapName () {
        return myMapName;
    }

    @Override
    public boolean isLobbyFull () {
        return myPlayersCount == myMaxPlayers;
    }

    @Override
    public void removePlayer (int id) {
        myPlayersCount--;
    }

    @Override
    public void removePlayer (PlayerInfo info) {
        removePlayer(info.getId());
    }

    @Override
    public void addPlayer (PlayerInfo info) {
        myPlayersCount++;
    }

    @Override
    public int getId () {
        return myLobbyID;
    }

    @Override
    public int getNumberOfPlayers () {
        return myPlayersCount;
    }

    @Override
    public boolean canStartGame () {
        return isLobbyFull();
    }

}

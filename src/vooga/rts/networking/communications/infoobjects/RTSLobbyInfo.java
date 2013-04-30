package vooga.rts.networking.communications.infoobjects;

/**
 * Provides RTS specific information on the lobby.
 * 
 * Extends ExpandedLobbyInfo:
 * 
 * @see vooga.rts.networking.communications.infoobjects.ExpandedLobbyInfo
 * 
 *      Implements the LobbyInfo interface:
 * @see vooga.rts.networking.communications.infoobjects.LobbyInfo
 * 
 * @author David Winegar
 * 
 */
public class RTSLobbyInfo extends ExpandedLobbyInfo {

    private static final long serialVersionUID = -5404268196520326107L;

    /**
     * Creates the RTSLobbyInfo with passed in information.
     * 
     * @param lobbyName name of lobby
     * @param mapName name of map
     * @param maxPlayers max players
     * @param id id
     */
    public RTSLobbyInfo (String lobbyName, String mapName, int maxPlayers, int id) {
        super(lobbyName, mapName, maxPlayers, id);
    }

    /**
     * Copies the existing lobbyInfo parameters to make the new lobbyInfo.
     * 
     * @param lobbyInfo info to copy
     */
    public RTSLobbyInfo (LobbyInfo lobbyInfo) {
        super(lobbyInfo);
    }

    /**
     * Copies the existing lobbyInfo parameters to make the new lobbyInfo, except it changes the ID.
     * 
     * @param lobbyInfo info to copy
     * @param newID new ID to give
     */
    public RTSLobbyInfo (LobbyInfo lobbyInfo, int newID) {
        super(lobbyInfo, newID);
    }

    @Override
    public boolean canStartGame () {
        if (!super.canStartGame()) {
            return false;
        }
        if (getMaxPlayers() == 1) {
            return true;
        }
        PlayerInfo[] players = getPlayerArray();

        RTSPlayerInfo player = (RTSPlayerInfo) players[0];
        int team1 = player.getTeam();
        for (int i = 1; i < players.length; i++) {
            player = (RTSPlayerInfo) players[i];
            if (player.getTeam() != team1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the maximum number of teams.
     * 
     * @return maximum number of teams
     */
    public int getMaxTeams () {
        return getMaxPlayers();
    }

}

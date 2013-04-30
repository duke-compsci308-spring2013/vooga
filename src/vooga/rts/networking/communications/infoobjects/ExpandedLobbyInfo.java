package vooga.rts.networking.communications.infoobjects;

import java.util.Arrays;


/**
 * Expanded lobby information used by the lobby model and view. Passed back and forth between the
 * server and client.
 * 
 * Implements the PlayerInfo interface:
 * 
 * @see vooga.rts.networking.communications.infoobjects.PlayerInfo
 * 
 * @author Sean Wareham
 * @author David Winegar
 * @author Henrique Moraes
 * 
 */
public class ExpandedLobbyInfo extends SmallLobbyInfo {

    private static final long serialVersionUID = 8433220026468566119L;
    private PlayerInfo[] myPlayers;
    private int myNextSlot = 0;

    /**
     * Creates the expanded lobby info.
     * 
     * @param lobbyName name of lobby
     * @param mapName name of map
     * @param maxPlayers max players
     * @param id id
     */
    public ExpandedLobbyInfo (String lobbyName,
                              String mapName,
                              int maxPlayers,
                              int id) {
        super(lobbyName, mapName, maxPlayers, id);
        myPlayers = new SmallPlayerInfo[maxPlayers];
    }

    /**
     * Copies the existing lobbyInfo parameters to make the new lobbyInfo.
     * 
     * @param lobbyInfo info to copy
     */
    public ExpandedLobbyInfo (LobbyInfo lobbyInfo) {
        this(lobbyInfo.getLobbyName(), lobbyInfo.getMapName(), lobbyInfo.getMaxPlayers(), lobbyInfo
                .getId());
    }

    /**
     * Copies the existing lobbyInfo parameters to make the new lobbyInfo, except it changes the ID.
     * 
     * @param lobbyInfo info to copy
     * @param newID new ID to give
     */
    public ExpandedLobbyInfo (LobbyInfo lobbyInfo, int newID) {
        this(lobbyInfo.getLobbyName(), lobbyInfo.getMapName(), lobbyInfo.getMaxPlayers(), newID);
    }

    @Override
    public void addPlayer (PlayerInfo player) {
        if (myNextSlot != getMaxPlayers()) {
            super.addPlayer(player);
            myPlayers[myNextSlot] = player;
            for (int i = myNextSlot + 1; i < myPlayers.length; i++) {
                if (myPlayers[i] == null) {
                    myNextSlot = i;
                    return;
                }
            }
            myNextSlot = getMaxPlayers();
        }
    }

    @Override
    public void removePlayer (int playerID) {
        for (int i = 0; i < myPlayers.length; i++) {
            if (myPlayers[i] != null && myPlayers[i].getId() == playerID) {
                myPlayers[i] = null;
                if (myNextSlot > i) {
                    myNextSlot = i;
                }
            }
        }
    }

    /**
     * Swaps out the player with a newer version.
     * 
     * @param player to change
     */
    public void changePlayer (PlayerInfo player) {
        for (int i = 0; i < myPlayers.length; i++) {
            if (myPlayers[i] != null && myPlayers[i].equals(player)) {
                myPlayers[i] = player;
            }
        }
    }

    /**
     * Gets the player in the given position
     * 
     * @param position of player
     * @return player
     */
    public PlayerInfo getPlayerAtPosition (int position) {
        return myPlayers[position];
    }

    /**
     * Returns a copy of the current players.
     * 
     * @return copy of player array
     */
    public PlayerInfo[] getPlayers () {
        return Arrays.copyOf(myPlayers, myPlayers.length);
    }

    /**
     * Utility function to give the actual array, and not a copy, to subclasses of
     * ExpandedLobbyInfo.
     * 
     * @return original players array
     */
    protected PlayerInfo[] getPlayerArray () {
        return myPlayers;
    }

    /**
     * Gets a PlayerInfo with the given id, as long as the PlayerInfo is contained in the array.
     * Returns null if player is not found.
     * 
     * @param id of player
     * @return player
     */
    public PlayerInfo getPlayer (int id) {
        for (PlayerInfo myPlayer : myPlayers) {
            if (myPlayer.getId() == id) {
                return myPlayer;
            }
        }
        return null;
    }

}

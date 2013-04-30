package vooga.rts.networking.communications.infoobjects;

import java.io.Serializable;


/**
 * Represents information about the lobby to the game, the server, and the client/server browser.
 * Intended to be send over the network. Can be implemented to give specific behavior for different
 * types of games.
 * 
 * The superclass of all LobbyInfos is the SmallLobbyInfo class:
 * 
 * @see vooga.rts.networking.communications.infoobjects.SmallLobbyInfo
 * 
 *      This interface implements the Serializable interface for serialization to a stream:
 * @see java.io.Serializable
 * 
 * @author David Winegar
 * 
 */
public interface LobbyInfo extends Serializable {

    /**
     * Returns the name of the lobby.
     * 
     * @return lobby name
     */
    String getLobbyName ();

    /**
     * Returns the maximum number of players this lobby can hold.
     * 
     * @return max players
     */
    int getMaxPlayers ();

    /**
     * Returns name of the Map of the current lobby.
     * 
     * @return map name
     */
    String getMapName ();

    /**
     * Returns whether if Lobby is full.
     * 
     * @return true if lobby is full
     */
    boolean isLobbyFull ();

    /**
     * Removes the PlayerInfo with the ID passed in. If the PlayerInfo is not found, no player is
     * removed.
     * 
     * @param id of player to remove
     */
    void removePlayer (int id);

    /**
     * Removes the PlayerInfo passed in. If the PlayerInfo is not found, no player is removed.
     * 
     * @param player to remove
     */
    void removePlayer (PlayerInfo player);

    /**
     * Adds the PlayerInfo passed in. If there is no space, the player is not added.
     * 
     * @param player to add
     */
    void addPlayer (PlayerInfo player);

    /**
     * Returns the ID of this LobbyInfo
     * 
     * @return id number
     */
    int getId ();

    /**
     * Gets the current number of players.
     * 
     * @return number of players
     */
    int getNumberOfPlayers ();

    /**
     * Returns if the game is startable. This is intended to be overriden in subclasses.
     * 
     * @return true if game can be started
     */
    public boolean canStartGame ();
}

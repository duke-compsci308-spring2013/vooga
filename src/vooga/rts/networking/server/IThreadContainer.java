package vooga.rts.networking.server;

import vooga.rts.networking.communications.LobbyInfo;

/**
 * Provides an interface between ClientInfoMessages coming in and the various thread container
 * classses.
 * 
 * @author David Winegar
 * 
 */
public interface IThreadContainer {

    /**
     * Removes the connection.
     * 
     * @param thread to remove
     */
    public void removeConnection (ConnectionThread thread);

    /**
     * Joins the specified GameContainer.
     * 
     * @param thread that is joining
     * @param gameName name of game to join
     */
    public void joinGameContainer (ConnectionThread thread, String gameName);

    /**
     * Joins the specified lobby.
     * 
     * @param thread that is joining
     * @param lobbyNumber number of lobby
     */
    public void joinLobby (ConnectionThread thread, int lobbyNumber);

    /**
     * Leaves the current lobby.
     * 
     * @param thread that is leaving
     */
    public void leaveLobby (ConnectionThread thread);

    /**
     * Starts a gameserver if the thread is in a lobby.
     * 
     * @param thread thread that starts it
     */
    public void startGameServer (ConnectionThread thread);

    /**
     * Requests information about a set number of lobbies
     * 
     * @param thread sends lobby info to this thread
     */
    public void requestLobbies (ConnectionThread thread);

    /**
     * Starts a new lobby
     * @param thread that starts new lobby
     * @param myLobbyInfo info about the lobby
     */
    public void startLobby (ConnectionThread thread, LobbyInfo myLobbyInfo);

}

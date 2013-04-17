package vooga.rts.networking.server;

/**
 * Provides an interface between ClientInfoMessages coming in and the various thread container classses.
 * @author David Winegar
 *
 */
public interface IThreadContainer {

    public void removeConnection (ConnectionThread thread);

    public void joinGame (ConnectionThread thread, String gameName);

    public void joinLobby (ConnectionThread thread, String lobbyName);

    public void leaveLobby (ConnectionThread thread);

    public void startGameServer (ConnectionThread thread);

    public void requestLobbies (ConnectionThread thread, int startNumber, int endNumber);

}

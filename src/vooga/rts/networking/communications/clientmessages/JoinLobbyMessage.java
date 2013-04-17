package vooga.rts.networking.communications.clientmessages;

import vooga.rts.networking.server.ConnectionThread;
import vooga.rts.networking.server.IThreadContainer;

/**
 * Requests information about a specific server.
 * @author David Winegar
 *
 */
public class JoinLobbyMessage extends ClientInfoMessage {

    private static final long serialVersionUID = -874567492715054864L;
    String myLobbyName;
    
    public JoinLobbyMessage (String serverName) {
        myLobbyName = serverName;
    }
    
    @Override
    public void execute (ConnectionThread thread, IThreadContainer server) {
        server.joinLobby(thread, myLobbyName);
    }

}

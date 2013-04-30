package vooga.rts.networking.communications.clientmessages;

import vooga.rts.networking.communications.AbstractLobbyInfoMessage;
import vooga.rts.networking.communications.infoobjects.ExpandedLobbyInfo;
import vooga.rts.networking.communications.infoobjects.SmallLobbyInfo;
import vooga.rts.networking.server.ConnectionThread;
import vooga.rts.networking.server.IThreadContainer;


/**
 * Message for updating the lobby information.
 * 
 * @author srwareham
 * 
 */
public class UpdateLobbyInfoMessage extends AbstractLobbyInfoMessage implements ClientInfoMessage {
    /**
     * 
     */
    private static final long serialVersionUID = 397434129045885123L;

    /**
     * Create the UpdateLobbyInfoMessage with the requisite LobbyInfo.
     * 
     * @param lobbyInfo 
     */
    public UpdateLobbyInfoMessage (SmallLobbyInfo lobbyInfo) {
        super(lobbyInfo);
    }

    @Override
    public void affectServer (ConnectionThread thread, IThreadContainer server) {
        server.updateLobbyInfo(thread, (ExpandedLobbyInfo) super.getLobbyInfo());
    }

}

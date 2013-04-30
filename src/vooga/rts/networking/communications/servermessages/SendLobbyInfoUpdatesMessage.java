package vooga.rts.networking.communications.servermessages;

import vooga.rts.networking.client.IClientModel;
import vooga.rts.networking.communications.AbstractLobbyInfoMessage;
import vooga.rts.networking.communications.infoobjects.ExpandedLobbyInfo;


/**
 * Sends ExpandedLobbyInfo to the client.
 * 
 * @author David Winegar
 * 
 */
public class SendLobbyInfoUpdatesMessage extends AbstractLobbyInfoMessage implements
        ServerInfoMessage {

    private static final long serialVersionUID = 6729477946727355957L;

    /**
     * Instantiates the messsage.
     * 
     * @param info to send
     */
    public SendLobbyInfoUpdatesMessage (ExpandedLobbyInfo info) {
        super(info);
    }

    @Override
    public void affectClient (IClientModel model) {
        model.updateLobby((ExpandedLobbyInfo) getLobbyInfo());
    }

}

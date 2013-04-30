package vooga.rts.networking.communications;

import vooga.rts.networking.communications.infoobjects.SmallLobbyInfo;

/**
 * Abstract class for sending LobbyInfo and ExpandedLobbyInfo.
 * Is extended by any server or client message that contains reference to the LobbyInfo.
 * 
 * @author David Winegar
 * @author srwareham
 * 
 */
public abstract class AbstractLobbyInfoMessage extends Message {

    private static final long serialVersionUID = 2163042663466305077L;
    private SmallLobbyInfo myLobbyInfo;

    /**
     * Takes in expandedLobbyInfo.
     * 
     * @param lobbyInfo info
     */
    public AbstractLobbyInfoMessage (SmallLobbyInfo lobbyInfo) {
        myLobbyInfo = lobbyInfo;
    }

    /**
     * Allows subclasses to get lobbyInfo.
     */
    protected SmallLobbyInfo getLobbyInfo () {
        return myLobbyInfo;
    }

}

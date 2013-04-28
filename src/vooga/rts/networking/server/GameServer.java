package vooga.rts.networking.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import vooga.rts.networking.NetworkBundle;
import vooga.rts.networking.communications.GameMessage;
import vooga.rts.networking.communications.Message;
import vooga.rts.networking.communications.servermessages.AlertClientMessage;
import vooga.rts.networking.communications.servermessages.CloseConnectionMessage;


/**
 * Server that represents one instance of a in-play game. It receives game-specific information
 * from a game client and relays such info to all others connected.
 * System level messages are not sent out to all clients.
 * 
 * @author Henrique Moraes
 * @author Sean Wareham
 * @author David Winegar
 * 
 */
public class GameServer extends Room {

    /**
     * Instantiates the gameServer with all the connections passed in by a lobby.
     * 
     * @param id id of the gameserver
     * @param container gamecontainer of the gameserver
     * @param lobby lobby to get connections from
     * @param logger logger to use
     */
    public GameServer (int id, GameContainer container, Lobby lobby, Logger logger) {
        super(id, container, lobby, logger);
        getLogger().log(Level.INFO,
                        NetworkBundle.getString("ServerStarted"));
    }

    @Override
    public void receiveMessageFromClient (Message message, ConnectionThread thread) {
        super.receiveMessageFromClient(message, thread);
        if (message instanceof GameMessage) {
            sendMessageToAllConnections(message);
        }
    }

    @Override
    public void removeConnection (ConnectionThread thread) {
        super.removeConnection(thread);
        sendMessageToAllConnections(new AlertClientMessage(
                                                           NetworkBundle
                                                                   .getString("LostConnection"),
                                                           NetworkBundle
                                                                   .getString("LostConnectionMessage")));
        sendMessageToAllConnections(new CloseConnectionMessage());
        removeAllConnections();
        getGameContainer().removeRoom(this);
    }
}

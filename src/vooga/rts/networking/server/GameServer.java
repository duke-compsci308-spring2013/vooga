package vooga.rts.networking.server;

import vooga.rts.networking.communications.Message;


/**
 * Server that represents one instance of a in-play game.. It receives information from game clients
 * and AI and
 * sends pushes changes to all other clients
 * 
 * @author Henrique Moraes
 * @author Sean Wareham
 * @author David Winegar
 * 
 */
public class GameServer extends Room {

    public GameServer (int id, GameContainer container, Lobby lobby) {
        super(id, container, lobby);
    }

    @Override
    public void receiveMessageFromClient (Message message, ConnectionThread thread) {
        sendMessageToAllConnections(message);
    }

}

package vooga.rts.networking.server;

import java.util.HashMap;
import java.util.Map;
import vooga.rts.networking.communications.Message;
import vooga.rts.networking.communications.clientmessages.ClientInfoMessage;


/**
 * Default class that provides default behavior for IThreadContainer and provides other behaviors
 * for thread containers.
 * 
 * @author David Winegar
 * 
 */
public abstract class AbstractThreadContainer implements IThreadContainer, IMessageReceiver {
    
    private Map<Integer, ConnectionThread> myConnectionThreads =
            new HashMap<Integer, ConnectionThread>();
    
    public AbstractThreadContainer () {   
    }
    
    public AbstractThreadContainer (AbstractThreadContainer container) {
        myConnectionThreads = new HashMap<Integer, ConnectionThread>(container.myConnectionThreads);
    }

    @Override
    public void joinGame (ConnectionThread thread, String gameName) {
    }

    @Override
    public void joinLobby (ConnectionThread thread, String lobbyName) {
    }

    @Override
    public void leaveLobby (ConnectionThread thread) {
    }

    @Override
    public void startGameServer (ConnectionThread thread) {
    }

    @Override
    public void requestLobbies (ConnectionThread thread, int startNumber, int endNumber) {
    }

    @Override
    public void removeConnection (ConnectionThread thread) {
        myConnectionThreads.remove(thread.getID());
    }

    /**
     * Default behavior: executing a system message only.
     */
    @Override
    public void receiveMessageFromClient (Message message, ConnectionThread thread) {
        if (message instanceof ClientInfoMessage) {
            ClientInfoMessage systemMessage = (ClientInfoMessage) message;
            systemMessage.execute(thread, this);
        }
    }

    protected void addConnection (ConnectionThread thread) {
        myConnectionThreads.put(thread.getID(), thread);
        thread.switchMessageServer(this);
    }

    protected void sendMessageToAllConnections (Message message) {
        for (ConnectionThread thread : myConnectionThreads.values()) {
            thread.sendMessage(message);
        }
    }

    protected void sendMessageToClient (ConnectionThread thread, Message message) {
        thread.sendMessage(message);
    }

    protected boolean haveNoConnections () {
        return myConnectionThreads.isEmpty();
    }
    
    protected void removeAllConnections () {
        myConnectionThreads.clear();
    }

}

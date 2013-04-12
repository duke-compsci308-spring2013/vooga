package vooga.rts.networking.server;

import java.util.ArrayList;
import java.util.List;
import vooga.rts.networking.communications.Message;
import vooga.rts.networking.communications.SystemMessage;
import vooga.rts.networking.factory.Command;
import vooga.rts.networking.factory.Factory;


/**
 * Object responsible for creating an instance of a game, and establishing
 * connections between clients and the game.
 * 
 * @author srwareham
 * @author David Winegar
 * 
 */
public class MatchmakerServer extends Thread implements IMessageReceiver {
    private List<ConnectionThread> myConnectionThreads = new ArrayList<ConnectionThread>();
    private List<ConnectionThread> myPotentialConnections = new ArrayList<ConnectionThread>();
    private List<GameServer> myGameServers = new ArrayList<GameServer>();
    private List<GameContainer> myGameContainers = new ArrayList<GameContainer>();
    private int myGameServerID = 0;
    private ConnectionServer myConnectionServer = new ConnectionServer(this);
    private Factory myFactory = new Factory();

    @Override
    public void run () {
        myConnectionServer.start();
    }

    protected void addConnection (ConnectionThread thread) {
        myConnectionThreads.add(thread);
        myPotentialConnections.add(thread);
        // TODO only for example client, remove later
        if (myPotentialConnections.size() > 1) {
            initializeGame();
        }
    }

    private void initializeGame () {
        GameServer gameServer = new GameServer(myGameServerID++);
        myGameServers.add(gameServer);
        for (ConnectionThread ct : myPotentialConnections) {
            gameServer.addClient(ct);
        }
        myPotentialConnections.clear();
        gameServer.start();
        myGameServerID++;
    }

    @Override
    public void sendMessage (Message message, ConnectionThread thread) {
        SystemMessage systemMessage = (SystemMessage) message;
        Command command = myFactory.getCommand(systemMessage.getMessage());
        command.execute(thread, this);
    }

}

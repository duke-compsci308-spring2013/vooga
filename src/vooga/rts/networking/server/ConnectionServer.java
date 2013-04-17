package vooga.rts.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * A pure connection server that listens to connections, creates them, and sends them to the
 * MatchmakerServer.
 * 
 * @author David Winegar
 * 
 */
public class ConnectionServer extends Thread {

    private static final int PORT = 55308;
    private int myConnectionID = 0;
    private MatchmakerServer myMatchServer;
    private boolean myServerAcceptingConnections = false;

    public ConnectionServer (MatchmakerServer matchServer) {
        myMatchServer = matchServer;
    }

    @Override
    public void run () {
        myServerAcceptingConnections = true;
        ServerSocket serverSocket = null;    

        while (myServerAcceptingConnections) {
            try {
                // DEBUGGING - can only use ports once on localhost, so use this to test multiple
                // connections
                // TODO remove after example
                serverSocket = new ServerSocket(PORT + myConnectionID);
                Socket socket = serverSocket.accept();
                ConnectionThread thread =
                        new ConnectionThread(socket, myMatchServer, myConnectionID);
                myConnectionID++;
                thread.start();
                myMatchServer.addConnection(thread);
                // TODO remove after example
                serverSocket.close();
            }
            catch (IOException e) {
                // TODO log file
                e.printStackTrace();
            }
        }

        if (serverSocket != null) {
            try {
                serverSocket.close();
            }
            catch (IOException e) {
                // TODO log file
                e.printStackTrace();
            }
        }
    }

}

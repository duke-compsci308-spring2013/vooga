package vooga.rts.networking.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import vooga.rts.networking.communications.Message;
import vooga.rts.networking.communications.clientmessages.InitialConnectionMessage;


/**
 * Represents a server-side connection between the client and the server.
 * 
 * @author David Winegar
 * @author Henrique Morales
 * 
 */
public class ConnectionThread extends Thread {
    private Socket mySocket;
    private ObjectInputStream myInput;
    private ObjectOutputStream myOutput;
    private int myID;
    private String myUserName;
    private String myGameName;
    private IMessageReceiver myMessageServer;
    private boolean myConnectionActive = false;

    /**
     * Represents a thread that communicates to a client
     * 
     * @param socket socket used for establishing the connection
     */
    ConnectionThread (Socket socket, IMessageReceiver server, int ID) {
        mySocket = socket;
        myMessageServer = server;

        try {
            myInput = new ObjectInputStream(mySocket.getInputStream());
            myOutput = new ObjectOutputStream(mySocket.getOutputStream());
        }
        catch (IOException e) {
            // TODO add logger
            e.printStackTrace();

        }
    }

    public void switchMessageServer (IMessageReceiver server) {
        myMessageServer = server;
    }

    /**
     * Keeps listening for messages and adds to the server's message queue
     */
    @Override
    public void run () {
        myConnectionActive = true;
        try {
            Object obj;
            
            // Checks to see if first class passed is initial connection mesage
            // TODO repeated/bad code
            if ((obj = myInput.readObject()) != null && obj instanceof InitialConnectionMessage) {
                Message message = (Message) obj;
                myMessageServer.receiveMessageFromClient(message, this);
            } else {
                myConnectionActive = false;
                myMessageServer.removeConnection(this);
                return;
            }
        while (myConnectionActive) {
                if ((obj = myInput.readObject()) != null && obj instanceof Message) {
                    Message message = (Message) obj;
                    myMessageServer.receiveMessageFromClient(message, this);
                }
            }  
        }
        catch (IOException e) {
            // TODO add logger
            e.printStackTrace();
            close();
        }
        catch (ClassNotFoundException e) {
            // TODO add logger
            e.printStackTrace();
            close();
        }
    }

    /**
     * Closes streams and socket of this thread
     * TODO catch exceptions
     */
    public void close () {
        myConnectionActive = false;
        try {
            if (myOutput != null) {
                myOutput.close();
            }
        }
        catch (Exception e) {
        }
        try {
            if (myInput != null) {
                myInput.close();
            }
        }
        catch (Exception e) {
        }
        ;
        try {
            if (mySocket != null) {
                mySocket.close();
            }
        }
        catch (Exception e) {
        }
    }

    /**
     * Broadcasts a Message object to the client
     * 
     * @param m Message object to be sent
     */
    public void sendMessage (Message m) {
        if (!mySocket.isConnected()) {
            close();
        }
        try {
            myOutput.writeObject(m);
        }
        catch (IOException e) {
            // TODO add logger
            e.printStackTrace();

        }
    }

    public int getID () {
        return myID;
    }
    
    public String getUserName () {
        return myUserName;
    }
    
    public String getGameName () {
        return myGameName;
    }
    
    /**
     * Sets the user name - can only be set once.
     * @param userName name to set
     */
    public void setUserName (String userName) {
        if (myUserName == null) {
            myUserName = userName;
        }
    }
    
    /**
     * Sets the game name - can only be set once.
     * @param gameName name to set
     */
    public void setGameName (String gameName) {
        if (myGameName == null) {
            myGameName = gameName;
        }
    }

}

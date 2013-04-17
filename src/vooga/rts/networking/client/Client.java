package vooga.rts.networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import vooga.rts.networking.communications.Message;


/**
 * Provides client-side methods for connecting to the multiplayer server.
 * In this case the client is the Arcade and the Game.
 * 
 * 
 * @author srwareham
 * @author David Winegar
 * 
 */
public class Client extends Thread implements IClient {
    private static final int PORT = 55308;
    private static final String HOST = "localhost";
    private ObjectInputStream myInput;
    private ObjectOutputStream myOutput;
    private Socket mySocket;
    private String myHost = HOST;
    private int myPort = PORT;
    private IMessageReceiver myReceiver;
    private boolean myRunning = false;
    
    public Client (IMessageReceiver receiver) {
        myReceiver = receiver;
    }

    /**
     * Creates the sockets and streams for this client
     */
    public void run () {
        try {
            mySocket = new Socket(myHost, myPort);
            myInput = new ObjectInputStream(mySocket.getInputStream());
            myOutput = new ObjectOutputStream(mySocket.getOutputStream());
        }
        catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        myRunning = true;
        while(myRunning){
            try {
                Object object;
                if ((object = myInput.readObject()) != null && object instanceof Message) { 
                    myReceiver.getMessage((Message) object); 
                }
            }
            catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                myRunning = false;
            }
        }
    }

    @Override
    public void sendData (Message message) {
        try {
            myOutput.writeObject(message);
        }
        catch (IOException e) {
            System.out.println("Exception writing to server: " + e);
        }
    }

    @Override
    public void setMessageReceiver (IMessageReceiver messageReceiver) {
        myReceiver = messageReceiver;
    }
}

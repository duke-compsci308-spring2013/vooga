package vooga.rts.networking.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.logger.LoggerManager;
import vooga.rts.networking.NetworkBundle;
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
	private static final String HOST = "login.cs.duke.edu";
	private ObjectInputStream myInput;
	private ObjectOutputStream myOutput;
	private Socket mySocket;
	private String myHost = HOST;
	private int myPort = PORT;
	private IMessageReceiver myReceiver;
	private boolean myRunning = false;
	private Logger myLogger;

	/**
	 * Instantiates the client and starts the connection.
	 * @param receiver to send messages to
	 */
	public Client (IMessageReceiver receiver) {
		myReceiver = receiver;
		LoggerManager log = new LoggerManager();
		myLogger = log.getLogger();
		try {
			mySocket = new Socket(myHost, myPort);
			myOutput = new ObjectOutputStream(mySocket.getOutputStream());
			myInput = new ObjectInputStream(mySocket.getInputStream());
		}
		catch (UnknownHostException e) {
			myLogger.log(Level.WARNING,
					NetworkBundle.getString("InitialConnectionFailed"));
		}
		catch (IOException e) {
			myLogger.log(Level.WARNING,
					NetworkBundle.getString("InitialConnectionFailed"));
		}
		start();
	}

	/**
	 * Creates the sockets and streams for this client
	 */
	public void run () {
		myRunning = true;
		while (myRunning) {
			try {
				Object object = myInput.readObject();
				if (object instanceof Message) {
					myReceiver.getMessage((Message) object);
				}
			}
			catch (ClassNotFoundException e) {
				myLogger.log(Level.WARNING,
						NetworkBundle.getString("ConnectionFailedClassEx"));
			}
			catch (IOException e) {
				myLogger.log(Level.WARNING,
						NetworkBundle.getString("ConnectionFailedIO"));
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
			myLogger.log(Level.WARNING,
					NetworkBundle.getString("ExceptionServer") + e);
		}
	}

	@Override
	public void setMessageReceiver (IMessageReceiver messageReceiver) {
		myReceiver = messageReceiver;
	}

	@Override
	public void closeConnection () {
		myRunning = false;
		try {
			myOutput.close();
			myInput.close();
			mySocket.close();
		}
		catch (IOException e) {
			myLogger.log(Level.WARNING,
					NetworkBundle.getString("ClosingConnectionsFailed"));
		}
	}

}

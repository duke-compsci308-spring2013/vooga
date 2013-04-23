package util.Chat;
import java.util.*;
import java.io.*;
 
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
/**
 * A class which, provided a functioning XMPP Chat Server, allows users to chat with each other using
 * a jabber protocol.
 * @author Josh Waldman (Mainly Based on JabberSmackApi)
 * I have added the Observer pattern to this to handle incoming messages,
 * and have improved the way that Incoming Messages are handled on the back end
 * to make it easier for the user to access the content.
 *
 */
public class ChatController implements MessageListener, Subject{
 
    XMPPConnection myConnection;
    private ArrayList<Observer> myObservers =new ArrayList<Observer>();
    private IncomingMessage myIncomingMessage= null;
    /**
     * Takes in a userName and userPassword, and logs the user into a functioning XMPP server.
     * @param userName
     * @param userPassword
     * @throws XMPPException
     */
    public void login(String userName, String userPassword) throws XMPPException
    {
    //The server in the configuration Constructor here will have to be changed to one with XMPP installed.
    ConnectionConfiguration configuredConnection = new ConnectionConfiguration("im.jabber.org",5222, "Work");
    myConnection = new XMPPConnection(configuredConnection);
    myConnection.connect();
    myConnection.login(userName, userPassword);
    }
    /**
     * Takes in a message, and a userName to send this message to, and completes the pathway between the two,
     * and sends the message.
     * @param message
     * @param to
     * @throws XMPPException
     */
    public void sendMessage(String message, String sendToUser) throws XMPPException
    {
    Chat chatWithUser = myConnection.getChatManager().createChat(sendToUser, this);
    chatWithUser.sendMessage(message);
    }
    /**
     * Creates a collection of RosterEntries, which represents the users currently online and available to chat.
     * Use the .getUser() method on a RosterEntry to return the userName string associated with that entry,
     */
    public Collection<RosterEntry> displayBuddyList()
    {
    Roster roster = myConnection.getRoster();
    Collection<RosterEntry> entries = roster.getEntries();
    return entries;
    }
    /**
     * Close the connection between the user and the jabber 
     */
    public void disconnect()
    {
    myConnection.disconnect();
    }
    /**
     * Will notify the user when the IncomingMessage has been changed using the Observer/Observable pattern.
     * When this occurs, user should call return IncomingMessage.
     */
    public void processMessage(Chat chatWithUser, Message incomingMessage)
    {
    if(incomingMessage.getType() == Message.Type.chat)
    {
    	myIncomingMessage=new IncomingMessage(chatWithUser, incomingMessage);
    	notifyObservers();
    }
    }
    /**
     *    @Override 
     *	  Registers a new observer for this object.
     */
    public void registerObserver(Observer observer)
    {
    	myObservers.add(observer);
    }
    /**
     * @Override
     * Removes an observer from this object
     */
    public void removeObserver(Observer observer)
    {
    	myObservers.remove(observer);
    }
    /**
     * @Override
     * Notifies the observer that a new incoming message has been received.
     */
    public void notifyObservers()
    {
    	for (Observer singleObserver: myObservers)
    	{
    		singleObserver.update(myIncomingMessage);
    	}
    }
}

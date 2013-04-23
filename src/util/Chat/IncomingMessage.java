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
 * Represents an incoming message, using types more easily understood by the user.
 * @author Josh Waldman
 *
 */
public class IncomingMessage{
	private String myUserMessageFrom;
	private String myMessageBody;
	/**Converts JavaSmack specific objects to Strings to be handled by user
	 * @param chatWithUser
	 * @param incomingMessage
	 */
	public IncomingMessage(Chat chatWithUser, Message incomingMessage)	
	{
		myUserMessageFrom=chatWithUser.getParticipant();
		myMessageBody=incomingMessage.getBody();
	}
	public String getParticipant()
	{
		return myUserMessageFrom;
	}
	public String getMessage()
	{
		return myMessageBody;
	}
}

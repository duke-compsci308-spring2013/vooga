package util.Chat;
/**
 * 
 * @author Josh Waldman
 *
 */
public interface Observer {
	/**
	 * User should use this method to display a new message when one arrives.
	 * @param incomingMessage
	 */
	public void update(IncomingMessage incomingMessage);

}

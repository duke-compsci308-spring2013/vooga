package util;

/**
 * Exception thrown when a State attempts to update after having already completed.
 * 
 * @author James Wei
 *
 */
public class StateExpiredException extends Exception {

    /**
     * Auto-generated serial version UID.
     */
    private static final long serialVersionUID = -3303024874243370034L;

    /**
     * Constructs a new <code>StateExpiredException</code>.
     */
    public StateExpiredException() {
        super();
    }
    
    /**
     * Constructs a new <code>StateExpiredException</code> with the given message.
     */
    public StateExpiredException(String msg) {
        super(msg);
    }
        
}

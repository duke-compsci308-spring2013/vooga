package util;

/**
 * Exception thrown when an access on a disabled parameter in a State is attempted.
 * 
 * @author James Wei
 *
 */
public class StateParameterDisabledException extends Exception {

    /**
     * Auto-generated serial version UID.
     */
    private static final long serialVersionUID = -5712721667880072918L;

    /**
     * Constructs a new <code>StateParameterDisabledException</code>.
     */
    public StateParameterDisabledException() {
        super();
    }
    
    /**
     * Constructs a new <code>StateParameterDisabledException</code> with the given message.
     */
    public StateParameterDisabledException(String msg) {
        super(msg);
    }
    
}

package vooga.rts.commands;

/**
 * This class is a packet of information that can be sent to the appropriate
 * class which will carry out the action linked with the keyboard press, the 
 * mouse click, or the on-screen button that is pressed.
 * 
 * @author Challen Herzberg-Brovold
 * 
 */
public class Command {

    private String myInputType;

    /**
     * 
     * @param inputName the name of the input
     */
    public Command (String inputName) {
        myInputType = inputName;
    }

    /**
     * 
     * @return the name of the input
     */
    public String getMethodName () {
        return myInputType;
    }
}

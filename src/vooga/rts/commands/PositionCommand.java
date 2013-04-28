package vooga.rts.commands;

import util.input.PositionObject;
import vooga.rts.util.Location;


/**
 * A Position Command is different from a Command in that it stores the position
 * of where the command took place
 * 
 * @author Challen Herzberg-Brovold
 * 
 */
public class PositionCommand extends Command {

    public static String MOUSE_MOVE = "mousemove";

    private Location myPosition;
    private double myX;
    private double myY;
    
    /**
     * 
     * @param inputName
     * @param position the position of the command
     */
    public PositionCommand (String inputName, PositionObject position) {
        super(inputName);
        myX = position.getX();
        myY = position.getY();
        myPosition = new Location(myX, myY);
    }

    /**
     * 
     * @return the position of the command.
     */
    public Location getPosition () {
       return new Location(myX, myY);
    }
}

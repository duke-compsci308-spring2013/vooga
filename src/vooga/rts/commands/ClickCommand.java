package vooga.rts.commands;

import java.io.Serializable;
import util.input.PositionObject;


/**
 * A ClickCommand is different from a Command in that in addition the name of
 * the action (right or left click) it also holds the position of the click
 * 
 * @author Challen Herzberg-Brovold
 * 
 */
public class ClickCommand extends PositionCommand implements Serializable {
    
    private static final long serialVersionUID = 5590514033413227369L;
    public static String LEFT_CLICK = "leftclick";
    public static String RIGHT_CLICK = "rightclick";

    public ClickCommand (String inputName, PositionObject position) {
        super(inputName, position);
    }

}

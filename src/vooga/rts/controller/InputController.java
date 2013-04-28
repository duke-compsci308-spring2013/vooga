package vooga.rts.controller;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Queue;
import util.Location;
import util.input.*;
import vooga.rts.commands.ClickCommand;
import vooga.rts.commands.Command;
import vooga.rts.commands.DragCommand;
import vooga.rts.commands.PositionCommand;
import vooga.rts.state.State;
import vooga.rts.util.Camera;


/**
 * This controller
 * sends the formatted inputs to the main state, which relays them to the appropriate
 * state.
 * 
 * The Input Controller is responsible for routing Input Events to the correct place.
 * It turns input events into Commands that can be processed by the respective states
 * in order to turn them into actions.
 * 
 * It also manages the dragging input of a mouse. This will need to be passed in and
 * painted by the game state.
 * 
 * 
 * @author Challen Herzberg-Brovold
 * @author Jonathan Schmidt
 * 
 */
@InputClassTarget
public class InputController implements Controller {

    private State myState;

    private Location myLeftMouse;

    private Rectangle2D myDrag;

    // Use this to store commands that are only processed on a frame update.
    private Queue<Command> myProcessQueue;

    public InputController (State state) {
        myState = state;
        myProcessQueue = new LinkedList<Command>();
    }

    @Override
    public void sendCommand (Command command) {
        if (command.getMethodName().equals(ClickCommand.RIGHT_CLICK)) {
            myState.receiveCommand(command);
        }
        else
        {
            myProcessQueue.add(command);
        }
    }

    /*
     * All the following methods are called via reflection by the Input class,
     * based on the Input.properties file, and send the appropriate command.
     */

    @InputMethodTarget(name = "onLeftMouseDown")
    public void onLeftMouseDown (PositionObject o) {
        myLeftMouse = new Location(o.getPoint2D());
    }

    @InputMethodTarget(name = "onLeftMouseUp")
    public void onLeftMouseUp (PositionObject o) {
        if (myDrag == null) {
            sendCommand(new ClickCommand(ClickCommand.LEFT_CLICK, o));
        }
        else {
            sendCommand(new DragCommand(null, null));
        }
        myLeftMouse = null;
        myDrag = null;
    }

    @InputMethodTarget(name = "onRightMouseUp")
    public void onRightMouseUp (PositionObject o) {
        sendCommand(new ClickCommand(ClickCommand.RIGHT_CLICK, o));
        myLeftMouse = null;
        myDrag = null;
        sendCommand(new DragCommand(null, null));
    }

    @InputMethodTarget(name = "onMouseMove")
    public void mouseMove (PositionObject o) {
        sendCommand(new PositionCommand(PositionCommand.MOUSE_MOVE, o));
    }

    @InputMethodTarget(name = "onMouseDrag")
    public void onMouseDrag (PositionObject o) {
        if (!(myLeftMouse == null)) {
            double uX = o.getX() > myLeftMouse.getX() ? myLeftMouse.getX() : o.getX();
            double uY = o.getY() > myLeftMouse.getY() ? myLeftMouse.getY() : o.getY();
            double width = Math.abs(o.getX() - myLeftMouse.getX());
            double height = Math.abs(o.getY() - myLeftMouse.getY());
            myDrag = new Rectangle2D.Double(uX, uY, width, height);
            sendCommand(new DragCommand(Camera.instance().viewtoWorld(myDrag), myDrag));
        }
    }

    public void processCommands () {
        while (myProcessQueue.size() > 0) {
            Command c = myProcessQueue.poll();
            if (c !=  null) {
                myState.receiveCommand(c);
            }
        }
    }

}

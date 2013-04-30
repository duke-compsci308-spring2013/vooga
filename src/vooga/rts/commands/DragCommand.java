package vooga.rts.commands;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;


/**
 * This command is used for when a drag event is created in the game.
 * There are two components to the DragCommand, one being the visual rectangle
 * and the other being the Rectangle in World Coordinates.
 * 
 * @author Jonathan Schmidt
 * @author Challen Herzberg-Brovold
 */
public class DragCommand extends Command {

    public static final String DRAG = "drag";
    public Shape myWorldRectangle;
    public Rectangle2D myScreenRectangle;

    public DragCommand (Shape world, Rectangle2D screen) {
        super(DRAG);
        myWorldRectangle = world;
        myScreenRectangle = screen;
    }

    /**
     * @return The Shape of the Drag in World Coordinates.
     */
    public Shape getWorldRectangle () {
        return myWorldRectangle;
    }

    /**
     * @return The Rectangle of the Drag that can be drawn on the screen.
     */
    public Rectangle2D getScreenRectangle () {
        return myScreenRectangle;
    }
}

package vooga.rts.manager.actions;

import java.awt.Shape;
import vooga.rts.action.ManagerAction;
import vooga.rts.commands.Command;
import vooga.rts.commands.DragCommand;
import vooga.rts.manager.Manager;

/**
 * This class is the action by which the manager selects units when the mouse
 * is dragged across the screen.s
 * 
 * @author Challen Herzberg-Brovold
 *
 */
public class DragSelectAction extends ManagerAction {
    
    /**
     * The rectangle formed by the drag, within which all the units will be selected.
     */
    private Shape myRectangle;

    public DragSelectAction (Manager manager) {
        super(manager);
    }
    
    @Override
    public void apply () {
        getManager().select(myRectangle);             
    }

    @Override
    public void update (Command command) {
       DragCommand drag = (DragCommand) command;
       myRectangle = drag.getWorldRectangle();
       apply();
    }

}

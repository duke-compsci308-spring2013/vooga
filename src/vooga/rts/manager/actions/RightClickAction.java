package vooga.rts.manager.actions;

import vooga.rts.action.ManagerAction;
import vooga.rts.commands.ClickCommand;
import vooga.rts.commands.Command;
import vooga.rts.gamedesign.sprite.gamesprites.GameEntity;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.manager.Manager;
import vooga.rts.state.GameState;
import vooga.rts.util.Camera;
import vooga.rts.util.Location3D;


/**
 * This class needs to be pushed into any classes that can be moved, and be
 * mapped to the right click there.
 * 
 * @author Challen Herzberg-Brovold.
 * @author Ryan Fishel
 * 
 */
public class RightClickAction extends ManagerAction {

    private Location3D myLocation;

    public RightClickAction (Manager manager) {
        super(manager);
    }

    @Override
    public void apply () {
        if (myLocation != null) {
            for (final InteractiveEntity ie : getManager().getSelected()) {
                GameEntity enemy = GameState.getMap().getEntity(myLocation);
                if (enemy != null) {
                    ie.recognize(enemy);
                }
                else {
                    Thread t = new Thread(new Runnable() {            
                        @Override
                        public void run () {
                            ie.move(myLocation);                
                        }
                    });
                    t.start();
                }
            }
        }
    }

    @Override
    public void update (Command command) {
        ClickCommand click = (ClickCommand) command;
        myLocation = Camera.instance().viewtoWorld(click.getPosition());
        if (myLocation.getX() < 0 || myLocation.getY() < 0) {
            myLocation = null;
        }
    }

}

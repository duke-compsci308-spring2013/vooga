package vooga.rts.action;

import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Location;
import vooga.rts.action.Action;
import vooga.rts.commands.Command;


/**
 * This class is the subclass of Action that will deal with
 * Production actions, ie, actions that will create a new
 * interactiveEntity
 * 
 * @author Junho Oh
 * 
 */
public class ProductionAction implements Action {
    private Location3D toProduceFrom;

    public ProductionAction (String name, Pixmap image, String description, Location3D from) {
        super();
        toProduceFrom = new Location3D(from);
    }

    /**
     * Returns the location from where the production will take place.
     * 
     * @return toProduceFrom the location of where the production will take place
     */
    public Location3D getProducedFrom () {
        return toProduceFrom;
    }

	@Override
	public void apply() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Command command) {
		// TODO Auto-generated method stub
		
	}

}

package vooga.rts.gamedesign.strategy.production;

import vooga.rts.action.InteractiveAction;
import vooga.rts.commands.Command;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.buildings.ProductionBuilding;
import vooga.rts.util.DelayedTask;
import vooga.rts.util.Location3D;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements ProductionStrategy and is used as an instance in
 * interactives for objects that are able to produce other interactives. The
 * produce method in this class will specify how the interactive will produce
 * other units.
 * 
 * @author Kevin Oh
 * 
 */
public class CanProduce implements ProductionStrategy {

	private List<InteractiveEntity> myProducables;
	private Location3D myRallyPoint;

	public CanProduce() {
		myProducables = new ArrayList<InteractiveEntity>();
		myRallyPoint = new Location3D();
	}

	public void setRallyPoint(Location3D rallyPoint) {
		myRallyPoint = rallyPoint;
	}

	public void addProducable(InteractiveEntity producable) {
		myProducables.add(producable);
	}

	@Override
	public void createProductionActions(final InteractiveEntity producer) {
		for (final InteractiveEntity producable : myProducables) {
			producer.addAction("I am a pony", new InteractiveAction(producer) {
				@Override
				public void update(Command command) {
				}

				@Override
				public void apply() {
					// check for resources
					final InteractiveEntity unit = producable;
					DelayedTask dt = new DelayedTask(1, new Runnable() {
						@Override
						public void run() {
							System.out.println("Creating");
							InteractiveEntity f = ((InteractiveEntity) unit)
									.copy();
							f.setWorldLocation(producer.getWorldLocation());
							producer.setChanged();
							producer.notifyObservers(f);
							f.move(myRallyPoint);

						}
					});
					producer.addTask(dt);
				}
			});
		}
	}
}

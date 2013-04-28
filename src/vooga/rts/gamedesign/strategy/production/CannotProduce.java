package vooga.rts.gamedesign.strategy.production;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.buildings.Building;
import vooga.rts.gamedesign.state.ProducingState;
import vooga.rts.gamedesign.strategy.Strategy;
import vooga.rts.util.Location3D;


/**
 * This class implements ProductionStrategy and is used as an instance in
 * interactives for objects that are not able to produce other interactives.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu
 * 
 */
public class CannotProduce implements ProductionStrategy {

    @Override
    public void createProductionActions (InteractiveEntity producer) {
    }

    public void addProducable (InteractiveEntity producable) {
        return;
    }

    @Override
    public void update (double elapsedTime) {

    }

    public void paint (Graphics2D pen) {
        return;
    }

    public void setRallyPoint (Location3D rallyPoint) {
        return;
    }

    public void setRallyPoint (InteractiveEntity entity) {
        return;
    }

    @Override
    public List<InteractiveEntity> getProducables() {
        return new ArrayList<InteractiveEntity>();
    }

    @Override
    public void setProducables (List<InteractiveEntity> producables) {

    }

    @Override
    public void affect (InteractiveEntity entity) {
        entity.setProductionStrategy(this);
    }

    @Override
    public ProducingState getProducingState () {
        return ProducingState.NOT_PRODUCING;
    }

}

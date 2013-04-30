package vooga.rts.gamedesign.strategy.production;

import java.util.ArrayList;
import java.util.List;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gamedesign.state.ProducingState;
import vooga.rts.util.Location3D;


/**
 * This class implements ProductionStrategy and is used as an instance in
 * interactives for objects that are not able to produce other interactives.
 * @author Kevin Oh
 * 
 */
public class CannotProduce implements ProductionStrategy {

    @Override
    public void createProductionActions (InteractiveEntity producer) {
    }

    public void addProducable (InteractiveEntity producable) {
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
    public void copyStrategy (InteractiveEntity entity) {
        entity.setProductionStrategy(this);
    }

    @Override
    public ProducingState getProducingState () {
        return ProducingState.NOT_PRODUCING;
    }

}

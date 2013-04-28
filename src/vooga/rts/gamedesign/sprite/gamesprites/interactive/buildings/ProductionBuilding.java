package vooga.rts.gamedesign.sprite.gamesprites.interactive.buildings;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.units.Unit;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;


/**
 * This class is a subclass of Building to deal with production buildings.
 * Has rallypoints which is where newly produced units go to.
 * 
 * @author Junho Oh
 */
public class ProductionBuilding extends Building {
    private Location3D myRallyPoint;
    private List<Unit> myProducables; // for testing really, need to make it work with xml file

    public ProductionBuilding (Pixmap image,
                               Location3D center,
                               Dimension size,
                               Sound sound,
                               int playerID,
                               int health,
                               double buildTime) {
        super(image, center, size, sound, playerID, health, buildTime);
        myRallyPoint = new Location3D(getWorldLocation().getX(), getWorldLocation().getY() + 50, 0);
        myProducables = new ArrayList<Unit>();
    }

    @Override
    public void getOccupied (Unit unit) {
        // does nothing if building isnt occupied.
    }


    /**
     * Returns the rally point of the production building.
     * Will be used to move the newly created units to
     * 
     * @return myRallyPoint, the rally point of the
     *         production building
     */
    @Override
	public Location3D getRallyPoint () {
        return myRallyPoint;
    }

    /*
     * Test method to add an interactive entity to
     */
    public void addProducable (Unit i) {
        myProducables.add(i);
    }

    /**
     * Sets the rally point of the production building
     * 
     * @param rallyPoint the location of the new rally point
     */
    @Override
	public void setRallyPoint (Location3D rallyPoint) {
        myRallyPoint = rallyPoint;
    }

    @Override
    public void addActions () {
        // TODO Auto-generated method stub

    }

    @Override
    public void paint (Graphics2D pen) {
        super.paint(pen);
    }

}

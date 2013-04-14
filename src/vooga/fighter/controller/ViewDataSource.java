package vooga.fighter.controller;

import java.awt.Dimension;
import vooga.fighter.util.Location;
import vooga.fighter.util.Paintable;


public interface ViewDataSource {

    /**
     * @return Number of Paintable objects
     */
    public int ObjectNumber ();

    /**
     * @param index
     * @return The Paintable object with given index
     */
    public Paintable getPaintable (int index);

    /**
     * @param index
     * @return The Location for the Paintable object with given index
     */
    public Location getLocation (int index);

    /**
     * @param index
     * @return The size as a Dimension for the Paintable object with given index.
     */
    public Dimension getSize (int index);
}

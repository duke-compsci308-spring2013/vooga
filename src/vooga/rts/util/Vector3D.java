package vooga.rts.util;

import java.awt.geom.Point2D;


public class Vector3D {

    // Components of the Vector
    private Location3D myComponents;

    private double myMagnitude;

    /**
     * Creates a Vector from the origin to the provided point
     * 
     * @param x X position
     * @param y Y position
     * @param z Z position
     */
    public Vector3D (double x, double y, double z) {
        myComponents = new Location3D(x, y, z);
        calculateMagnitude();
    }

    /**
     * Creates a Vector from the origin to the provided point
     * 
     * @param end
     */
    public Vector3D (Location3D end) {
        this(end.getX(), end.getY(), end.getZ());
    }

    /**
     * Creates a Vector that represents the difference between
     * the two Locations provided.
     * 
     * @param start The start location
     * @param end The end location
     */
    public Vector3D (Location3D start, Location3D end) {
        this(end.getX() - start.getX(), end.getY() - start.getY(), end.getZ() - start.getZ());
    }

    public double getMagnitude () {
        return myMagnitude;
    }

    public void setComponents(double x, double y, double z) {
        myComponents.setLocation(x, y, z);
    }
    
    private void calculateMagnitude () {
        myMagnitude = myComponents.getDistance(new Location3D(0,0,0));
    }

}

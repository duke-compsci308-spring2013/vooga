package vooga.fighter.util;


public class Physics {
    /**
     * Calculates an elastic collision given two objects of given masses and velocities.
     * 
     * @param aVelocity The velocity of object A
     * @param aMass The mass of object A
     * @param bVelocity The velocity of object B
     * @param bMass The mass of object B
     * @return Two vectors in an array, the first is the new velocity of object A and the second is
     *         the new velocity of object B.
     */
    public static Vector[] elasticCollision (Vector aVelocity,
                                             int aMass,
                                             Vector bVelocity,
                                             int bMass) {
        return partiallyInelasticCollision(1, aVelocity, aMass, bVelocity, bMass);
    }

    /**
     * Calculates a perfectly inelastic collision given two objects of given masses and velocities.
     * 
     * @param aVelocity The velocity of object A
     * @param aMass The mass of object A
     * @param bVelocity The velocity of object B
     * @param bMass The mass of object B
     * @return Two vectors in an array, the first is the new velocity of object A and the second is
     *         the new velocity of object B.
     */
    public static Vector[] inelasticCollision (Vector aVelocity,
                                               int aMass,
                                               Vector bVelocity,
                                               int bMass) {
        return partiallyInelasticCollision(0, aVelocity, aMass, bVelocity, bMass);
    }

    /**
     * Calculates a partially inelastic collision given two objects of given masses and velocities.
     * 
     * @param restitution The coefficient of restitution where 1 is perfectly elastic and 0 is
     *        perfectly inelastic
     * @param aVelocity The velocity of object A
     * @param aMass The mass of object A
     * @param bVelocity The velocity of object B
     * @param bMass The mass of object B
     * @return Two vectors in an array, the first is the new velocity of object A and the second is
     *         the new velocity of object B.
     */
    public static Vector[] partiallyInelasticCollision (double restitution,
                                                        Vector aVelocity,
                                                        int aMass,
                                                        Vector bVelocity,
                                                        int bMass) {

        double referenceAngle = Math.toDegrees(Math.atan2(aVelocity.getY() - bVelocity.getY(),
                                                          aVelocity.getX() - bVelocity.getX()));

        Vector aTurned = aVelocity.clone();
        aTurned.turn(-referenceAngle);

        Vector bTurned = bVelocity.clone();
        bTurned.turn(-referenceAngle);

        double aVectorTurnedDX =
                (restitution * bMass * (bTurned.getXChange() - aTurned.getXChange()) +
                 aMass * aTurned.getXChange() + bMass * bTurned.getXChange()) / (aMass + bMass);

        double bVectorTurnedDX =
                (restitution * aMass * (aTurned.getXChange() - bTurned.getXChange()) +
                 aMass * aTurned.getXChange() + bMass * bTurned.getXChange()) / (aMass + bMass);

        Vector[] newDirections = new Vector[2];
        newDirections[0] = new Vector(aVelocity, aVectorTurnedDX, aVelocity.getYChange());
        newDirections[1] = new Vector(bVelocity, bVectorTurnedDX, bVelocity.getYChange());

        return newDirections;
    }

    /**
     * Computes a vector after gravitational force is applied.
     * 
     * @param velocity The velocity of the object
     * @param gravitationalAcceleration The acceleration due to gravity.
     * @param terminalSpeed The max speed an object can fall down.
     * @return A vector with gravity applied for one time period of acceleration.
     */
    public static Vector gravity (Vector velocity,
                                  double gravitationalAcceleration,
                                  double terminalSpeed) {
        double dY = velocity.getYChange() - gravitationalAcceleration;
        if (dY < -terminalSpeed) {
            dY = -terminalSpeed;
        }

        return new Vector(velocity, velocity.getXChange(), dY);
    }

    /**
     * Reduces the magnitude of a vector due to friction.
     * A crude representation that does not work by opposing force.
     * 
     * @param velocity The original velocity.
     * @param coefficientOfFriction The coefficient of friction (between 0 and 1)
     * @return A reduced velocity vector.
     */
    public static Vector friction (Vector velocity, double coefficientOfFriction) {
        Vector velocityClone = velocity.clone();
        velocityClone.scale(coefficientOfFriction);

        return velocityClone;
    }

    /**
     * Applies gravity to an object in contact with something acting as the ground with a particular
     * angle.
     * 
     * @param velocity The current velocity of the object.
     * @param gravitationalAcceleration The acceleration due to gravity
     * @param terminalSpeed The maximum magnitude the velocity may have.
     * @param inclineAngle The incline of the angled slope in degrees. [0, 180)
     * @param coefficientOfFriction The coefficient of friction.
     * @return A modified velocity vector after applying gravity.
     */
    public static Vector sliding (Vector velocity,
                                  double gravitationalAcceleration,
                                  double terminalSpeed,
                                  double inclineAngle,
                                  double coefficientOfFriction) {

        double terminalXVelocity = -(Math.sin(Math.toRadians(inclineAngle)) * terminalSpeed);
        double terminalYVelocity = -(Math.cos(Math.toRadians(inclineAngle)) * terminalSpeed);

        // Represents the adjustment to the velocity by one time period. The adjustment scales
        // linearly with how
        // close the velocities are to matching and inversely with the coefficient of friction,
        // where a higher
        // coefficient represents a slower change and slower max speed.
        double gravityDXVelocity =
                -(Math.sin(Math.toRadians(inclineAngle)) * gravitationalAcceleration) *
                        (terminalSpeed * coefficientOfFriction - velocity.getMagnitude()) *
                        (1 - coefficientOfFriction);

        double gravityDYVelocity =
                (Math.cos(Math.toRadians(inclineAngle)) * gravitationalAcceleration) *
                        (terminalSpeed * coefficientOfFriction - velocity.getMagnitude()) *
                        (1 - coefficientOfFriction);

        double newXVelocity = velocity.getXChange() + gravityDXVelocity;
        double newYVelocity = velocity.getYChange() + gravityDYVelocity;

        // Don't overshoot the change and reverse direction.
        if ((terminalXVelocity - newXVelocity <= 0) != (terminalXVelocity - velocity.getXChange() <= 0)) {
            newXVelocity = terminalXVelocity;
        }
        if ((terminalYVelocity - newYVelocity <= 0) != (terminalYVelocity - velocity.getYChange() <= 0)) {
            newYVelocity = terminalYVelocity;
        }

        return new Vector(velocity, newXVelocity, newYVelocity);
    }

    /**
     * Returns the velocity vector reflected about a wall of impact. Assumes a perfect elastic
     * collision.
     * 
     * @param velocity The velocity of the object
     * @param inclineAngle The angle of the wall being hit. [0, 180)
     * @return A reflected velocity vector.
     */
    public static Vector bounce (Vector velocity, double inclineAngle) {
        Vector wall =
                new Vector(velocity, Math.cos(Math.toRadians(inclineAngle)), Math.sin(Math
                        .toRadians(inclineAngle)));

        Vector reflectedVector = wall.clone();
        reflectedVector.scale(2 * velocity.getDotProduct(wall) / wall.getDotProduct(wall));
        reflectedVector.difference(velocity);

        return reflectedVector;
    }

    /**
     * Applies a force on an object with a given velocity and mass.
     * 
     * @param velocity The object's velocity
     * @param mass The object's mass
     * @param force The force applied to the object
     * @return The new velocity with force applied
     */
    public static Vector applyForce (Vector velocity, double mass, Vector force) {
        Vector modifiableForce = force.clone();
        modifiableForce.scale(1 / mass);
        Vector newVelocity = velocity.clone();
        newVelocity.sum(modifiableForce);

        return newVelocity;
    }

    /**
     * Applies a force on an object with a given velocity and mass. Limits the
     * maximum speed of the object.
     * 
     * @param velocity The object's velocity
     * @param mass The object's mass
     * @param force The force applied to the object
     * @param terminalSpeed The maximum speed the object may have.
     * @return The new velocity with force applied
     */
    public static Vector applyForce (Vector velocity,
                                     double mass,
                                     Vector force,
                                     double terminalSpeed) {
        Vector newVelocity = applyForce(velocity, mass, force);
        if (newVelocity.getMagnitude() > terminalSpeed) {
            newVelocity.scale(terminalSpeed / newVelocity.getMagnitude());
        }

        return newVelocity;
    }
}
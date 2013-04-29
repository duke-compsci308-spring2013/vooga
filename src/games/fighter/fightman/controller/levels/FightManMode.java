package games.fighter.fightman.controller.levels;

import java.util.List;

import util.Vector;
import vooga.fighter.model.mode.LevelMode;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.util.CollisionDetector;
import vooga.fighter.util.CollisionManager;
import vooga.fighter.util.Physics;

public class FightManMode extends LevelMode {
    
    public FightManMode(CollisionManager manager) {
        super(manager);
    }
    
    @Override
    public void update() {
        loadAttacks();
        removeAppropriateObjects();
        updateHealth();
        applyForces();
        handleCollisions();
        List<GameObject> myObjects = getMyObjects();
        
        // object update() and updateState() have to be in separate loops
        for (GameObject object : myObjects) {
            object.update();
            if (object instanceof AttackObject) {
                AttackObject attOb = (AttackObject) object;
                attOb.moveToOwner();
            }
        }
        for (GameObject object : myObjects) {
            object.updateState();
            if (object instanceof CharacterObject) {
                CharacterObject charOb = (CharacterObject) object;
                if (charOb.getVelocity().equals(new Vector())) {
                    charOb.setCurrentState("stand");
                }
            }
        }
    }
    
    @Override
    /**
     * Only applies gravity.
     */
    public void applyForces() {
        for (CharacterObject ch : getCharacterObjects()) {
            // Physics values hard-coded, could be extensible by using resource
            // manager in this class
            Vector newVelocity = Physics.gravity(ch.getVelocity(), 10, 20);
            newVelocity = Physics.friction(newVelocity, .7);
            ch.getVelocity().setVectorByComponent(newVelocity.getXChange(),
                    newVelocity.getYChange());
            
        }
    }
    
    // Really I need to override this given how physics is operating
    @Override
    public void handleCollisions() {
        CollisionDetector collision = new CollisionDetector();
        
        for (GameObject obj : getMyObjects()) {
            // For now we only care if a character object has collided with
            // anything
            if (obj instanceof CharacterObject) {
                for (GameObject otherObj : getMyObjects()) {
                    // Don't check an object against itself
                    if (obj != otherObj) {
                        // If the object is the environment
                        if (otherObj instanceof EnvironmentObject) {
                            // Stop y velocity if falling into a platform
                            // If there was a distance from the top it would be
                            // useful here.
                            if (((EnvironmentObject) otherObj).getName()
                                    .equals("platform")) {
                                if (collision.hitTop(otherObj.getCurrentState()
                                        .getCurrentRectangle(), obj
                                        .getLocation().getLocation(),
                                        new Vector(), ((CharacterObject) obj)
                                                .getVelocity())) {
                                    ((CharacterObject) obj).getVelocity()
                                            .setVectorByComponent(
                                                    ((CharacterObject) obj)
                                                            .getVelocity()
                                                            .getXChange(), 0);
                                }
                            }
                            // Stop x velocity if going into a wall
                            else if (((EnvironmentObject) otherObj).getName()
                                    .equals("wall")) {
                                if (collision.hitLeft(otherObj
                                        .getCurrentState()
                                        .getCurrentRectangle(), obj
                                        .getLocation().getLocation(),
                                        new Vector(), ((CharacterObject) obj)
                                                .getVelocity())
                                        || collision.hitRight(otherObj
                                                .getCurrentState()
                                                .getCurrentRectangle(), obj
                                                .getLocation().getLocation(),
                                                new Vector(),
                                                ((CharacterObject) obj)
                                                        .getVelocity())) {
                                    ((CharacterObject) obj).getVelocity()
                                            .setVectorByComponent(
                                                    0,
                                                    ((CharacterObject) obj)
                                                            .getVelocity()
                                                            .getYChange());
                                }
                            }
                        }
                        // If the object is another player
                        if (otherObj instanceof CharacterObject) {
                            if (otherObj.checkCollision(obj)) {
                                if (obj.getCurrentStateKey().equals("dive")) {
                                    // Cancel out double attacks
                                    if (otherObj.getCurrentStateKey().equals(
                                            "dive")) {
                                        Vector newVel[] = Physics.elasticCollision(
                                                ((CharacterObject) obj)
                                                        .getVelocity(), obj
                                                        .getLocation()
                                                        .getLocation(),
                                                ((CharacterObject) obj)
                                                        .getMass(),
                                                ((CharacterObject) otherObj)
                                                        .getVelocity(),
                                                otherObj.getLocation()
                                                        .getLocation(),
                                                ((CharacterObject) otherObj)
                                                        .getMass());
                                        ((CharacterObject) obj).getVelocity().setVectorByComponent(newVel[0].getXChange(), newVel[0].getYChange());
                                        ((CharacterObject) otherObj).getVelocity().setVectorByComponent(newVel[1].getXChange(), newVel[1].getYChange());
                                    }
                                    //Else deal damage
                                    else {
                                        ((CharacterObject) otherObj).getHealth().changeHealth(-10000);
                                    }
                                }
                            }
                        }
                    }
                }// End inner loop
            }
        }
    }
}

package vooga.fighter.model.objects;

/**
 * Represents an environment object like a block or platform.
 * 
 * More behavior will be added.
 * 
 * @author James Wei, alanni
 * 
 */
public class EnvironmentObject extends GameObject {

    /**
     * Constructs a new EnvironmentObject with the given image, center, and size.
     * In the future this will use the object loader to read from XML.
     */
    public EnvironmentObject() {
        super();
        // setLoader(new EnvironmentObjectLoader(objectId));
    }

    /**
     * Updates the environment object. Behavior to be added.
     */
    public void update() {
        super.update();
    }

    /**
     * Returns false for now.
     */
    public boolean shouldBeRemoved() {
        return false;
    }
    
    /**
     * Dispatches a colliding object to allow for proper collision handling. 
     */
    public void dispatchCollision(GameObject other) {
        other.handleCollision(this);
    }
    
    /**
     * Collision with another CharacterObject.
     */
    public void handleCollision(CharacterObject other) {
        System.out.println("EnvironmentObject handleCollision : Environment collided with character");
    }
    
    /**
     * Collision with an AttackObject.
     */
    public void handleCollision(AttackObject other) {
        System.out.println("EnvironmentObject handleCollision : Environment collided with attack");
    }
    
    /**
     * Collision with an EnvironmentObject.
     */
    public void handleCollision(EnvironmentObject other) {
        System.out.println("EnvironmentObject handleCollision : Environment collided with environment");
    }

}

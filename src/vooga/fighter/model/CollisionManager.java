package vooga.fighter.model;

import java.util.List;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MapObject;

/**
 * Detects collisions between all the game objects. Collision handling is achieved
 * in the game objects themselves, and 
 * 
 * @author James Wei, alanni
 * 
 */
public class CollisionManager {

    /**
     * Checks for collisions between the game objects.
     */
    public void checkCollisions(List<GameObject> myObjects) {
        for (int i = 0; i < myObjects.size() - 1; i++) {
            for (int j = i + 1; j < myObjects.size(); j++) {
                GameObject o1 = myObjects.get(i);
                GameObject o2 = myObjects.get(j);
                if (o1 instanceof MapObject || o2 instanceof MapObject) {
                    continue;
                }
                if (o1.getCurrentState().getCurrentRectangle()
                        .intersects(o2.getCurrentState().getCurrentRectangle())) {
                    handleCollisions(o1, o2);
                }
            }
        }
    }

    /**
     * Applies collisions between collided game objects. Collisions are handled
     * through an implementation of the visitor design pattern.
     */
    public void handleCollisions(GameObject o1, GameObject o2) {
        o1.dispatchCollision(o2);
    }    

}

package vooga.fighter.model;

import java.util.List;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MapObject;

/**
 * Detects collisions between all the game objects
 * 
 * @author alanni
 * 
 */
public class CollisionManager {

    /**
     * Checks for collisions between the game objects.
     */
    public static void checkCollisions(List<GameObject> myObjects) {
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
     * Applies collisions between collided game objects. Will refactor to make less ugly
     * later on.
     */
    public static void handleCollisions(GameObject o1, GameObject o2) {
        if (o1 instanceof CharacterObject) {
            CharacterObject obj1 = (CharacterObject) o1;
            if (o2 instanceof CharacterObject) {
                CharacterObject obj2 = (CharacterObject) o2;
                handleCollisions(obj1, obj2);
            } else if (o2 instanceof EnvironmentObject) {
                EnvironmentObject obj2 = (EnvironmentObject) o2;
                handleCollisions(obj1, obj2);
            } else if (o2 instanceof AttackObject) {
                AttackObject obj2 = (AttackObject) o2;
                handleCollisions(obj1, obj2);
            }
        }
        if (o1 instanceof AttackObject) {
            AttackObject obj1 = (AttackObject) o1;
            if (o2 instanceof CharacterObject) {
                CharacterObject obj2 = (CharacterObject) o2;
                handleCollisions(obj2, obj1);
            } else if (o2 instanceof EnvironmentObject) {
                EnvironmentObject obj2 = (EnvironmentObject) o2;
                handleCollisions(obj1, obj2);
            } else if (o2 instanceof AttackObject) {
                AttackObject obj2 = (AttackObject) o2;
                handleCollisions(obj1, obj2);
            }
        }
        if (o1 instanceof EnvironmentObject) {
            EnvironmentObject obj1 = (EnvironmentObject) o1;
            if (o2 instanceof CharacterObject) {
                CharacterObject obj2 = (CharacterObject) o2;
                handleCollisions(obj2, obj1);
            } else if (o2 instanceof EnvironmentObject) {
                EnvironmentObject obj2 = (EnvironmentObject) o2;
                handleCollisions(obj1, obj2);
            } else if (o2 instanceof AttackObject) {
                AttackObject obj2 = (AttackObject) o2;
                handleCollisions(obj2, obj1);
            }
        }
    }
    
    /**
     * Applies collisions between two characters.
     */
    public static void handleCollisions(CharacterObject o1, CharacterObject o2) {
        o1.setHealth(0);
        o2.setHealth(0);
        System.out.printf("Characters collided!\n");
    }
    
    /**
     * Applies collisions between a character and an environment.
     */
    public static void handleCollisions(CharacterObject o1, EnvironmentObject o2) {
        
    }
    
    /**
     * Applies collisions between a character and an attack.
     */
    public static void handleCollisions(CharacterObject o1, AttackObject o2) {
        
    }
    
    /**
     * Applies collisions between two attacks.
     */
    public static void handleCollisions(AttackObject o1, AttackObject o2) {
        
    }
    
    /**
     * Applies collisions between an attack and an environment.
     */
    public static void handleCollisions(AttackObject o1, EnvironmentObject o2) {
        
    }
    
    /**
     * Applies collisions between two environments.
     */
    public static void handleCollisions(EnvironmentObject o1, EnvironmentObject o2) {
        
    }

}

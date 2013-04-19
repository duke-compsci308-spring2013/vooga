package vooga.fighter.model;


import java.util.List;
import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.EnvironmentObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.model.objects.MouseClickObject;


public class MenuCollisionManager extends CollisionManager {
    
//    public MenuCollisionManager() {
//        
//    }
//    
    public void handleCollisions(GameObject o1, GameObject o2) {
        super.handleCollisions(o1, o2);
        if (o1 instanceof MenuObject) {
            MenuObject obj1 = (MenuObject) o1;
            if (o2 instanceof MouseClickObject) {
                MouseClickObject obj2 = (MouseClickObject) o2;
                handleCollisions(obj1, obj2);
            }
        }
        else if (o1 instanceof MouseClickObject) {
            MouseClickObject obj1 = (MouseClickObject) o1;
            if (o2 instanceof MenuObject) {
                MenuObject obj2 = (MenuObject) o2;
                handleCollisions(obj2, obj1);
            }
        }

    }

    /**
     * Applies collisions between a character and an attack.
     */
    public void handleCollisions(MenuObject o1, MouseClickObject o2) {
        o1.tellDelegate();
    }
}
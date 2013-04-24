package vooga.scroller.util.physics;

import java.util.ArrayList;
import java.util.List;
import vooga.scroller.util.Sprite;

public class ForceBundle {
    
    List<Force> forces;
    
    public ForceBundle (Sprite sprite) {
        forces = new ArrayList<Force>();
        forces.add(new Gravity(sprite));
        forces.add(new Viscosity(sprite));
    }
    
    public void apply() {
        for (Force force : forces) {
            force.apply();
        }
    }

}
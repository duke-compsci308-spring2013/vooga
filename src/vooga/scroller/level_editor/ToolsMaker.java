package vooga.scroller.level_editor;

import java.util.HashMap;
import java.util.Map;
import vooga.scroller.util.Sprite;

public class ToolsMaker {

    ISpriteLibrary lib;
    
    public ToolsMaker(ISpriteLibrary l) {
        lib = l;
    }
    
    public Map<Integer, Sprite> getTools () {
        int i=0;
        Map<Integer, Sprite> res = new HashMap<Integer, Sprite>();
        for (Class<? extends Sprite> c:lib.getSpritesClasses()) {
            Sprite sprite;
            try {
                sprite = (Sprite) c.newInstance();
                res.put(i, sprite);
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        return res;
    }  
}
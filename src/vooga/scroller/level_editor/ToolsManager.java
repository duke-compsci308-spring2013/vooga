package vooga.scroller.level_editor;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import vooga.scroller.util.Sprite;
import vooga.scroller.viewUtil.RadioGroup;

public class ToolsManager {

    ISpriteLibrary spriteLib;
    IBackgroundLibrary backgroundLib;
    Map<Integer, Sprite> spriteMap;
    Map<String, Sprite> nameMap;
    LETools viewTools;
    
    public ToolsManager(ISpriteLibrary l, IBackgroundLibrary bgLib) {
        spriteLib = l;
        backgroundLib = bgLib;
        setTools();
    }
    
    private void setTools () {
        int i=0;
        spriteMap = new HashMap<Integer, Sprite>();
        nameMap = new HashMap<String, Sprite>();
        viewTools = new LETools();
        viewTools.addBackgrounds(backgroundLib.getBackgrounds());
        for (Class<? extends Sprite> c:spriteLib.getSpritesClasses()) {
            Sprite sprite;
            try {
                sprite = (Sprite) c.newInstance();
                spriteMap.put(i, sprite);
                nameMap.put(c.getCanonicalName(), sprite);
                viewTools.addSpriteOption(sprite, i);
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
    
    public LETools getViewTools() {
        return viewTools;
    }
    
    public Map<Integer, Sprite> getSpriteMap() {
        return spriteMap;
    }
    
    public Map<String,Sprite> getNameMap() {
        return nameMap;
    }
}

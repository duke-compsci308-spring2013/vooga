package vooga.scroller.level_editor.controllerSuite;

import java.util.HashMap;
import java.util.Map;
import vooga.scroller.level_editor.ILevelEditor;
import vooga.scroller.level_editor.StartPoint;
import vooga.scroller.level_editor.library.IBackgroundLibrary;
import vooga.scroller.level_editor.library.ISpriteLibrary;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.interfaces.IDoor;

/**
 * The tool manager is an utility class, performing part of the controller's
 * responsibilities, specifically setting up the necessary tools components for the 
 * view and the model.
 * @author Dagbedji Fagnisse, Danny Goodman
 *
 */
public class ToolsManager {

    private ISpriteLibrary mySpriteLib;
    private IBackgroundLibrary myBackgroundLib;
    private Map<Integer, Sprite> mySpriteMap;
    private LETools myViewTools;


    /**
     * Generate the necessary tools based on the provided sprites and background library.
     * @param l - sprites library
     * @param bgLib - backgrounds library
     */
    public ToolsManager (ISpriteLibrary l, IBackgroundLibrary bgLib) {
        myBackgroundLib = bgLib;
        mySpriteLib = l;
        setTools();
    }

    private void setTools () {

        int i = 1;
        mySpriteMap = new HashMap<Integer, Sprite>();
        myViewTools = new LETools();
        myViewTools.addBackgrounds(myBackgroundLib.getBackgrounds());
        Sprite sprite;
        for (Class<? extends Sprite> c : mySpriteLib.getSpritesClasses()) {
            try {
                sprite = (Sprite) c.newInstance();
                if (IDoor.class.isAssignableFrom(sprite.getClass())) {
                    setupTool(-i, sprite);
                }
                else {
                    setupTool(i, sprite);
                }
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        sprite = new StartPoint();
        setupTool(ILevelEditor.START_ID, sprite);
    }

    private void setupTool (int i, Sprite sprite) {
        mySpriteMap.put(i, sprite);
        myViewTools.addSpriteOption(sprite, i);
    }

    /**
     * Get the tools necessary for the view
     * @return
     */
    public LETools getViewTools () {
        return myViewTools;
    }

    /**
     * Get the Integer-to-sprite mapping for the library provided.
     * @return map of integer to Sprite
     */
    public Map<Integer, Sprite> getSpriteMap () {
        return mySpriteMap;
    }

    /**
     * Name of the encapsulating class
     * @return
     */
    public String getSpriteLibPath () {
        return mySpriteLib.getClass().getName();
    }
}

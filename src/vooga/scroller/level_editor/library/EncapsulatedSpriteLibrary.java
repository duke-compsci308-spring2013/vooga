package vooga.scroller.level_editor.library;

import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import vooga.scroller.collision_manager.VisitLibrary;
import vooga.scroller.extra_resources.sprite_interfaces.StandardPlayerCollisions;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.Pixmap;


public abstract class EncapsulatedSpriteLibrary implements ISpriteLibrary{


    private VisitLibrary myVisitLibrary;
    
    /**
     * Use this constructor to set the visit library to be used with this sprite library
     * @param lib
     */
    protected EncapsulatedSpriteLibrary (VisitLibrary lib) {
        myVisitLibrary = lib;
    }
    
    protected EncapsulatedSpriteLibrary() {
        myVisitLibrary = new StandardPlayerCollisions();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Sprite>[] getSpritesClasses () {
        return (Class<? extends Sprite>[]) this.getClass().getClasses();
    }
    
    public static Pixmap makePixmap(String directory, String fileName) {
        return new Pixmap(directory, fileName);
    }
    
    @Override
    public VisitLibrary getVisitLibrary () {
        return myVisitLibrary;
    }

}

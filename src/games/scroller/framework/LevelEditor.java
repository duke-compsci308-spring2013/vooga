
package games.scroller.framework;

import vooga.scroller.level_editor.controllerSuite.LEController;
import vooga.scroller.level_editor.library.BackgroundLib;
import vooga.scroller.sprites.test_sprites.MarioLib;


public class LevelEditor {

    /**
     * @param args
     */
    public static void main (String[] args) {
        
        String[] filenames = new String[]{"background_small.png",
                                          "background.png",
                                          "forestbackground.jpg"};
        
        LEController con = new LEController(new MarioLib(), new BackgroundLib(filenames));
        con.start();
        LevelEditor le = new LevelEditor();
        
    }
}
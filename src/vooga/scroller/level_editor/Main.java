
package vooga.scroller.level_editor;

import games.scroller.marioGame.spritesDefinitions.MarioLib;
import games.scroller.mr_fish.sprites.FishLib;
import vooga.scroller.kirbyGame.spritesDefinitions.KirbyLib;
import vooga.scroller.level_editor.controllerSuite.LEController;
import vooga.scroller.level_editor.library.BackgroundLib;


public class Main {

    /**
     * @param args
     */
    public static void main (String[] args) {
        
        String[] filenames = new String[]{"laserlevel.png",
                                          "cutterlevel.png",
                                          "bosslevel.png"};
        
        LEController.runLevelEditor(new KirbyLib(), new BackgroundLib(filenames));
        
    }
}

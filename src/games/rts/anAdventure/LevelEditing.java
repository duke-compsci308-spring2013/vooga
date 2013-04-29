package games.rts.anAdventure;

import vooga.rts.leveleditor.gui.Canvas;
import vooga.rts.resourcemanager.ImageLoader;
import vooga.rts.resourcemanager.ResourceManager;

public class LevelEditing {

    /**
     * This is the main class for level editor
     */
    public static void main(String[] args) {
        ResourceManager.getInstance().registerResourceLoader(new ImageLoader());
        ResourceManager.getInstance().setResourceBase("/vooga/rts/leveleditor/resource/");

        new Canvas();
    }
}

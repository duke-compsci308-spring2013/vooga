package games.rts.zombieCraft;

import vooga.rts.leveleditor.gui.Canvas;
import vooga.rts.resourcemanager.ImageLoader;
import vooga.rts.resourcemanager.ResourceManager;

public class LevelEditor {

	public static void main(String[] args) {
		
        ResourceManager.getInstance().registerResourceLoader(new ImageLoader());
        ResourceManager.getInstance().setResourceBase("/vooga/rts/leveleditor/resource/");

		new Canvas();
	}
}

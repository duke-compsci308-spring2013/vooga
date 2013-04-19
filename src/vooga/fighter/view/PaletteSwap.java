package vooga.fighter.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import util.Pixmap;

public class PaletteSwap {
	// palette swapping is a common practice in fighting games
	// palette swapping can be used to create new character models by recycling
	// old ones
	// palette swapping can also be used to help players distinguish each other
	// when they both
	// select the same character
	private Pixmap ObjectImage;

	public PaletteSwap() {

	}

	public void applyNewColor(Image colorImage, int width, int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.getGraphics();
		g.drawImage(colorImage, 0, 0, null);
		g.dispose();
	}
}

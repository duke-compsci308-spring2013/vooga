package vooga.rts.gamedesign.sprite.gamesprites.interactive.buildings;

import java.awt.Dimension;

import vooga.rts.gamedesign.sprite.gamesprites.interactive.units.Unit;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;

public class WoodTower extends Tower{

	 private static Pixmap DEFAULTPIX = new Pixmap("forrest.gif");
	    private static Location3D DEFAULTLOC = new Location3D(100, 100, 100);
	    private static Dimension DEFAULTSIZE = new Dimension(50, 50);
	    private static Sound DEFAULTSOUND = new Sound("squirtle.wav");
	    private static int DEFAULTHEALTH = 100;
	    private static int DEFAULTBUILDTIME = 10;
	    private static int NOTEAM = 0;

	    public WoodTower (int playerID) {
	        super(playerID);
	    }

	    public WoodTower () {
	        this(NOTEAM);
	    }

	    @Override
	    public void getOccupied (Unit unit) {

	    }
	
	
	
	
}

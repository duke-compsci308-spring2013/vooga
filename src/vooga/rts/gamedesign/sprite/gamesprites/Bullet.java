package vooga.rts.gamedesign.sprite.gamesprites;

import java.awt.Dimension;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;

/**
 * This class is an example of a projectile.
 * 
 * @author Ryan Fishel
 * @author Kevin Oh
 * @author Francesco Agosti
 * @author Wenshun Liu
 *
 */
public class Bullet extends Projectile{

    public Bullet(Pixmap pixmap, Location3D loc, Dimension size, int teamID, int damage, int health){
        super(pixmap, loc, size, teamID, damage, health);
    }

}


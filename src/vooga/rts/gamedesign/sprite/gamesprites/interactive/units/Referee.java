package vooga.rts.gamedesign.sprite.gamesprites.interactive.units;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import vooga.rts.gamedesign.sprite.gamesprites.Projectile;
import vooga.rts.gamedesign.strategy.attackstrategy.CanAttack;
import vooga.rts.gamedesign.weapon.Gun;
import vooga.rts.gamedesign.weapon.Weapon;
import vooga.rts.util.Information;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;

public class Referee extends Unit {
    
    public Referee (Pixmap image,
                 Location3D center,
                 Dimension size,
                 Sound sound,
                 int playerID,
                 int health,
                 double buildTime,
                 int speed) {
        super(image, center, size, sound, playerID, health, buildTime,speed);
    }
        
    public Referee(){
        this(new Pixmap("images/referee.gif"), new Location3D(), new Dimension(240,240), null, 0, 10000, 2,30);
        setAttackStrategy(new CanAttack());
        addWeapon(new Weapon(new Projectile(),100, new Location3D(), 2));
        Map<String, Integer> costMap = new HashMap<String,Integer>();
        costMap.put("gold", 100);
        costMap.put("wood", 100);
        Information ref = new Information("Ref", "RED CARD", "buttons/ref.jpeg", costMap);
        setInfo(ref);
    }
    


}

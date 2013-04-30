package vooga.rts.gamedesign.sprite.gamesprites.interactive.buildings;

import java.awt.Dimension;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.util.Location3D;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;

public class Stadium extends Building{
    private static final Pixmap DEFAULT_IMAGE = new Pixmap("/images/stadium.png");
    private static final Dimension DEFAULT_SIZE = new Dimension(150,150);
    private static final int DEFAULT_HEALTH = 1000;
    
    
    
    public Stadium (Pixmap image,
                    Location3D center,
                    Dimension size,
                    Sound sound,
                    int playerID,
                    int health,
                    double buildTime) {
        super(image, center, size, sound, playerID, health, buildTime);
    }

    
    public Stadium(){
        this(DEFAULT_IMAGE, InteractiveEntity.DEFAULT_LOCATION, DEFAULT_SIZE, null, 1, DEFAULT_HEALTH, InteractiveEntity.DEFAULT_BUILD_TIME);
    }
}

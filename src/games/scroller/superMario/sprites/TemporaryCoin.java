package games.scroller.superMario.sprites;

import vooga.scroller.extra_resources.sprite_interfaces.ICollectible;
import vooga.scroller.level_editor.Level;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.util.physics.Force;


public class TemporaryCoin extends TemporarySprite implements ICollectible {

    private static final int PER_SECOND = 10;
    private static final int DEFAULT_COIN_SCORE = 100;
    private static final String DEFAULT_IMG = "coin.gif";
    private static final double INITIAL_VELOCITY = 30;

    public TemporaryCoin (double d) {
        super(SuperMarioLib.makePixmap(DEFAULT_IMG), SuperMarioLib.makeSize(1, 1), d *
                                                                                   PER_SECOND);
        this.setVelocity(Sprite.UP_DIRECTION, INITIAL_VELOCITY);
    }

    @Override
    public void handleDeath (Level level) {
        level.getPlayer().incrementScore(getValue());
    }

    @Override
    public Force[] setForces () {
        // No Gravity
        return null;
    }

    @Override
    public int getValue () {
        return DEFAULT_COIN_SCORE;
    }

}

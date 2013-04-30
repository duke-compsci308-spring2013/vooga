package games.scroller.superMario.sprites;

import java.awt.Dimension;
import vooga.scroller.level_editor.Level;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.physics.Force;


public abstract class ItemBlock extends GameCharacter implements IItemBlock {

    private static final int HALF_BLOCK = 16;
    private int myHits;

    public ItemBlock (ISpriteView image, Dimension size, int hits) {
        super(image, size, 1, 0);
        setDefaultImg(SuperMarioLib.makePixmap(setDefaultPng()));
        setView(image);
        myHits = hits;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        super.update(elapsedTime, bounds);

    }

    @Override
    public int getHits () {
        return myHits;
    }

    @Override
    public void handleHit () {
        myHits--;
        setHealth(0);
    }

    @Override
    public void handleDeath (Level level) {
        addToLevel(createSprite(), level, (int) getHeight() / 2
                                          + (int) createSprite().getHeight() / 2);
        if (getHits() <= 0) {
            addToLevel(new EmptyItemBlock(), level, 0);
        }
    }

    /**
     * @param sprite
     * @param offset
     */
    private void addToLevel (Sprite sprite, Level level, int offset) {
        sprite.setCenter(this.getX(), this.getY() - offset);
        level.addSprite(sprite);
    }

    @Override
    public Force[] setForces () {
        // No Gravity
        return null;
    }

    public abstract String setDefaultPng ();

}

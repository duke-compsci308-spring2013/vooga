package games.scroller.superMario.sprites;

import java.awt.Dimension;
import vooga.scroller.level_editor.Level;
import vooga.scroller.sprites.Sprite;
import vooga.scroller.sprites.superclasses.GameCharacter;
import vooga.scroller.util.ISpriteView;
import vooga.scroller.util.physics.Force;


public abstract class ItemBlock extends GameCharacter implements IItemBlock {

    public ItemBlock (ISpriteView image, Dimension size, int hits) {
        super(image, size, hits, 0);
        setDefaultImg(SuperMarioLib.makePixmap(setDefaultPng()));
        setView(image);
    }

    @Override
    public int getHits () {
        return this.getHealth();
    }

    @Override
    public void decrementHits () {
        setHealth(getHealth() - 1);
    }

    @Override
    public void handleDeath (Level level) {
        Sprite block = new EmptyItemBlock();
        block.setCenter(this.getX(), this.getY());
        level.addSprite(block);
    }

    @Override
    public Force[] setForces () {
        // No Gravity
        return null;
    }

    public abstract String setDefaultPng ();

}

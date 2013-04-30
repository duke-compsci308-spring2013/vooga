package games.scroller.superMario.sprites;

import vooga.scroller.sprites.Sprite;


public class EmptyItemBlock extends ItemBlock {

    private static final String DEFAULT_IMG = "itemBlock_empty.png";

    public EmptyItemBlock () {
        super(SuperMarioLib.makePixmap(DEFAULT_IMG), SuperMarioLib.makeSize(1, 1), 1);
    }

    @Override
    public void handleHit () {
        // do nothing. do not decrement health.
    }

    @Override
    public Sprite createSprite () {
        return null;
    }

    @Override
    public String setDefaultPng () {
        return DEFAULT_IMG;
    }

}

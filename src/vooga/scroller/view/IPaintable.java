package vooga.scroller.view;

import java.awt.Graphics2D;

public interface IPaintable {

    /**
     * paints everything in the Renderable object
     * @param pen - used to paint
     */
    public void paint(Graphics2D pen);
}

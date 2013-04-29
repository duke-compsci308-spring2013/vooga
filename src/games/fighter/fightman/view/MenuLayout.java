package games.fighter.fightman.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import vooga.fighter.controller.interfaces.ViewDataSource;
import vooga.fighter.view.CanvasLayout;
import vooga.fighter.view.HUDText;

/**
 * Displays a title text, and potentially two text scores below it.
 * @author Wayne You
 *
 */
public class MenuLayout extends CanvasLayout {
    
    @Override
    public void paintComponents(Graphics2D pen, ViewDataSource data,
            Dimension screenSize) {
        boolean paintedTitleText = false;
        int valuesPainted = 0;
        
        for (int n = 0; n < data.ObjectNumber(); n++) {
            if (!paintedTitleText && data.getPaintable(n).getClass().equals(HUDText.class)) {
                data.getPaintable(n).paint(pen, new Point2D.Double(screenSize.getWidth() / 2, screenSize.getHeight() / 5), null);
                paintedTitleText = true;
            }
            else if (valuesPainted < 2 && data.getPaintable(n).getClass().equals(HUDValue.class)) {
                data.getPaintable(n).paint(pen, new Point2D.Double(screenSize.getWidth() / 2, screenSize.getHeight() * 2 / 5 + valuesPainted * 40), null);
                valuesPainted++;
            }
            else {
                defaultPaint(pen, data, n);
            }
        }
    }
    
}

package games.fighter.fightman.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import vooga.fighter.controller.interfaces.ViewDataSource;
import vooga.fighter.view.CanvasLayout;
import vooga.fighter.view.HUDPlayerValue;

public class MatchLayout extends CanvasLayout {
    
    @Override
    public void paintComponents(Graphics2D pen, ViewDataSource data,
            Dimension screenSize) {
        int valuesPainted = 0;
        
        for (int n = 0; n < data.ObjectNumber(); n++) {
            if (valuesPainted < 2 && data.getPaintable(n).getClass().equals(HUDPlayerValue.class)) {
                data.getPaintable(n).paint(pen, new Point2D.Double((screenSize.getWidth() - 50) * valuesPainted , 0), null);
                valuesPainted++;
            }
            else {
                defaultPaint(pen, data, n);
            }
        }
    }
    
}

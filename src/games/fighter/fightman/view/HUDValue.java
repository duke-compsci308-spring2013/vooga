package games.fighter.fightman.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;

import vooga.fighter.view.HUDText;

public class HUDValue extends HUDText {
    @Override
    public void update(Observable o, Object arg1) {
        Integer values = null;
        try {
            values = (Integer) getObservedValue(o);
        }
        catch (SecurityException e) {}
        catch (IllegalArgumentException e) {
            System.err.println("Expected Integer for HUDValue");
        }
        catch (NoSuchFieldException e) {
            System.err.println(myFieldName
                    + " is not a member of the class observed.");
        }
        catch (IllegalAccessException e) {
            System.err.println("Illegal access in HUDValue.");
        }
        
        myText.setText(myName + values);
    }
    
    @Override
    public void paint(Graphics2D pen, Point2D center, Dimension size) {
        myText.paint(pen, center, java.awt.Color.BLACK);
    }
    
}

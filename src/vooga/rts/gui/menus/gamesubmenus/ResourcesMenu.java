package vooga.rts.gui.menus.gamesubmenus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Map.Entry;
import java.util.Observable;
import util.Location;
import vooga.rts.gui.Window;
import vooga.rts.manager.IndividualResourceManager;

public class ResourcesMenu extends SubMenu {
    
    

    private IndividualResourceManager myResources;
    

    public ResourcesMenu (IndividualResourceManager irm, String image, Dimension size, Location pos) {
        super(image, size, pos);
        myResources = irm;
    }

    

    @Override
    public void processClick (int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void processHover (int x, int y) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("Arial", Font.PLAIN, 16));
        FontMetrics metrics = pen.getFontMetrics();
        int x = (int)getLocation().getX();
        for (Entry<String, Integer> resource:  myResources.getResources().entrySet()) {
            String value = toSentenceCase(resource.getKey()) + ": " + resource.getValue();
            pen.drawString(value, x, (int)getLocation().getY());
            x += metrics.stringWidth(value) + 25;
        }
        pen.setColor(Color.BLACK);
    }
    
    private String toSentenceCase(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.toUpperCase().charAt(0));
        sb.append(str.toLowerCase().substring(1));
        return sb.toString();
    }
}

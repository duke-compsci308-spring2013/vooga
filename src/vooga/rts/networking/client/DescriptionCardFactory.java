package vooga.rts.networking.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DescriptionCardFactory{
    private static ImageHelper myHelper = new ImageHelper();
    public DescriptionCardFactory(){
        //myHelper = new ImageHelper();
    }
    
    public static JPanel createCard(String imagePath){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10,10));
        
        JPanel generalInfo = new JPanel();
        generalInfo.setLayout(new GridLayout(2,1,0,20));
        generalInfo.setOpaque(false);
        
        Image scaled = myHelper.getScaledImage(imagePath, 150);
        
        JLabel c = new JLabel("Map info: ososososso");
        c.setHorizontalTextPosition(SwingConstants.CENTER);
        c.setVerticalTextPosition(SwingConstants.TOP);
        c.setAlignmentX(SwingConstants.TOP);
        c.setIcon(new ImageIcon(scaled));
        
        generalInfo.add(c);
        JPanel description = new JPanel();
        description.setOpaque(false);
        generalInfo.add(description);
        description.setLayout(new GridLayout(3,1));
        description.add(new JLabel("Server: "),BorderLayout.SOUTH);
        description.add(new JLabel("Host: "),BorderLayout.SOUTH);
        description.add(new JLabel("Max Players: "),BorderLayout.SOUTH);
        
        mainPanel.add(generalInfo, BorderLayout.WEST);
        mainPanel.add(createSpecificPanel(), BorderLayout.CENTER);
        mainPanel.setOpaque(false);
        return mainPanel;
    }
    
    private static JPanel createSpecificPanel(){
        JPanel specific = new JPanel();
        int numLabels = 10;
        specific.setLayout(new GridLayout(0,1));
        for(int i = 0 ; i<numLabels; i++){
            JLabel label = new JLabel("label number "+i);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            specific.add(label);
        }
        specific.setOpaque(false);
        return specific;
    }
}

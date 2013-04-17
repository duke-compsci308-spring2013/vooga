package vooga.rts.networking.client.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DescriptionCardFactory{
    private static final Font DEFAULT_FONT = new Font("Century Gothic", Font.PLAIN,18);
    private Font myFont = DEFAULT_FONT;
    private static DescriptionCardFactory instance;
    
    private DescriptionCardFactory(){
    }
    
    public static DescriptionCardFactory getInstance(){
        if (instance == null) {
            instance = new DescriptionCardFactory();
        }
        return instance;
    }
    
    public JPanel createCard(String imagePath){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10,10));
        
        JPanel generalInfo = new JPanel();
        generalInfo.setLayout(new GridLayout(2,1,0,20));
        generalInfo.setOpaque(false);
        
        Image scaled = ImageHelper.getScaledImage(imagePath, 150);

        JLabel c = makeLabel("Map info: ososososso");
        c.setHorizontalTextPosition(SwingConstants.CENTER);
        c.setVerticalTextPosition(SwingConstants.TOP);
        c.setAlignmentX(SwingConstants.TOP);
        c.setIcon(new ImageIcon(scaled));
        c.setFont(myFont);
        
        generalInfo.add(c);
        JPanel description = new JPanel();
        description.setOpaque(false);
        generalInfo.add(description);
        description.setLayout(new GridLayout(0,1));
        description.add(makeLabel("Server: "));
        description.add(makeLabel("Host: "));
        description.add(makeLabel("Max Players: "));
        
        mainPanel.add(generalInfo, BorderLayout.WEST);
        mainPanel.add(createSpecificPanel(), BorderLayout.CENTER);
        mainPanel.setOpaque(false);
        return mainPanel;
    }
    
    private JLabel makeLabel(String desc) {
        JLabel label = new JLabel(desc);
        label.setFont(myFont);
        return label;
    }
    
    public void setFont(Font font){
        myFont = font;
    }
    
    private JPanel createSpecificPanel(){
        JPanel specific = new JPanel();
        int numLabels = 6;
        specific.setLayout(new GridLayout(0,1));
        for(int i = 0 ; i<numLabels; i++){
            JLabel label = makeLabel("label number "+i);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            specific.add(label);
        }
        specific.setOpaque(false);
        return specific;
    }
}

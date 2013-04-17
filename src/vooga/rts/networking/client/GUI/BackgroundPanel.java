package vooga.rts.networking.client.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BackgroundPanel extends JPanel{
    Image myImage;
    JPanel myInnerPanel;
    private static final int H_GAP = 120;
    private static final int V_GAP = 50;
    private static final String IMAGE_PATH = "../../resources/Scroll1.jpg";
    private String myPath;
    
   
    public BackgroundPanel(String path) {
        myPath = path;
        ImageIcon icon = new ImageIcon(this.getClass().getResource(myPath));
        setBackgroundImage(icon);
        setLayout(new BorderLayout(0, 0));
        defineMargin(H_GAP,V_GAP);
        repaint();            
    }
    
    public BackgroundPanel() {
        this(IMAGE_PATH);                 
    }
    
    public void setBackgroundImage(Image img){
        myImage = img;
    }
    
    public void setBackgroundImage(ImageIcon img){
        myImage = img.getImage();
    }
    
    public JPanel getWorkingPanel() {
        return myInnerPanel;
    }
    
    public void defineMargin(int hgap, int vgap) {
        setBorder(new EmptyBorder(vgap, hgap, vgap, hgap));
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D pen = (Graphics2D) g;
        pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), this);
    }
    
}

package vooga.rts.networking.client;

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

public class BackgroundPanel extends JPanel{
    Image myImage;
    JPanel myInnerPanel;
    private static final String IMAGE_PATH = "../resources/Scroll1.jpg";
    
    public BackgroundPanel() {
            ImageIcon icon = new ImageIcon(this.getClass().getResource(IMAGE_PATH));
            myImage = icon.getImage();
            setLayout(new BorderLayout(0, 0));
            repaint();      
    }
    
    public JPanel getWorkingPanel() {
        return myInnerPanel;
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D pen = (Graphics2D) g;
        pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), this);
    }
    
}

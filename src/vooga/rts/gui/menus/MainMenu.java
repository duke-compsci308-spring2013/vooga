package vooga.rts.gui.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import vooga.rts.gui.Menu;
import vooga.rts.gui.buttons.ScreenButton;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Location;


public class MainMenu extends Menu {

    public static final String DEFAULT_BGIMAGE_LOCATION = "images/backgrounds/menu_bg.png";
    public BufferedImage myBGImage;
    private Dimension myDefaultButtonDimension = new Dimension(500, 122);

    private Location myDefaultButtonLocation = new Location(678, 500);

    private double myX;
    private double myY;

    private ScreenButton myStartButton;

    public MainMenu () {
        super();

        myBGImage = ResourceManager.getInstance().<BufferedImage>getFile(DEFAULT_BGIMAGE_LOCATION, BufferedImage.class);
        setImage(myBGImage);

        myStartButton =
                new ScreenButton("Start Game", myDefaultButtonDimension, myDefaultButtonLocation);

        addButton(myStartButton);
    }

    public void setScaledButton (Graphics2D pen, ScreenButton b) {
        myX = pen.getDeviceConfiguration().getBounds().getWidth();
        myY = pen.getDeviceConfiguration().getBounds().getHeight();
        double xFactor = myX / myBGImage.getWidth();
        double yFactor = myY / myBGImage.getHeight();
        b.setSize(new Dimension((int) (myDefaultButtonDimension.getWidth() * xFactor),
                                (int) (myDefaultButtonDimension.getHeight() * yFactor)));

        b.setPos(new Location((int) (myDefaultButtonLocation.getX() * xFactor),
                              (int) (myDefaultButtonLocation.getY() * yFactor)));
    }

    @Override
    public void paint (Graphics2D pen) {

        super.paint(pen);

        pen.setFont(new Font("Helvetica", Font.BOLD, 100));
        pen.setColor(Color.white);
        pen.drawString("The Vooga RTS Game", 150, 150);

        setScaledButton(pen, myStartButton);
    }
}

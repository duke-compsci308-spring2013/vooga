package vooga.rts.gui.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import vooga.rts.gui.Button;
import vooga.rts.gui.Menu;
import vooga.rts.gui.buttons.TextButton;
import vooga.rts.gui.buttons.USButton;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Location;

public class MainMenu extends Menu {
    public MainMenu() {
        super();
        setImage(ResourceManager.instance().loadFile("got1.jpg"));
        addButton(new USButton("tree.jpg", new Dimension(120, 40), new Location(100, 500)));
        addButton(new TextButton("I'm an Awesome Button", Color.WHITE, new Dimension(200, 20), new Location(400, 500)));
    }
}

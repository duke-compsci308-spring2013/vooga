package vooga.rts.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import vooga.rts.input.PositionObject;
import vooga.rts.resourcemanager.ResourceManager;


public class LoadingController extends AbstractController {

    AffineTransform myTransform;
    BufferedImage myBGImage;
    public static final String DEFAULT_BGIMAGE_LOCATION = "images/backgrounds/loading_bg.png";

    public LoadingController () {
        myBGImage = ResourceManager.getInstance().<BufferedImage> getFile(DEFAULT_BGIMAGE_LOCATION, BufferedImage.class);

    }

    @Override
    public void update (double elapsedTime) {

    }

    @Override
    public void onLeftMouseUp (PositionObject o) {
        if (!ResourceManager.getInstance().isLoading()) {
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void paint (Graphics2D pen) {
        myTransform = new AffineTransform();
        double sx = pen.getDeviceConfiguration().getBounds().getWidth();
        sx /= myBGImage.getWidth();
        double sy = pen.getDeviceConfiguration().getBounds().getHeight();
        sy /= myBGImage.getHeight();
        myTransform.scale(sx, sy);
        pen.drawImage(myBGImage, myTransform, null);

        pen.setColor(Color.white);
        pen.setFont(new Font("Georgia", Font.PLAIN, 72));
        pen.drawString("Game is Loading...", 200, 300);
        if (!ResourceManager.getInstance().isLoading()) {
            pen.setFont(new Font("Georgia", Font.PLAIN, 30));
            pen.drawString("Click to start.", 200, 380);
        }
    }

    @Override
    public void activate () {
        ResourceManager.getInstance().load();
    }

    @Override
    public MainState getGameState () {
        return MainState.Loading;
    }
}

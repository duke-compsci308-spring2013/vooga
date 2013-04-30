package vooga.rts.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import vooga.rts.resourcemanager.ResourceManager;

public class SpriteSheet {
    
    private Dimension mySpriteSize;
    private Dimension myImageSize;
    private BufferedImage mySpriteSheet;
    
    private int mySpritesX;
    private int mySpritesY;
    private String myFile;
    
    private BufferedImage[][] mySprites;
    
    public SpriteSheet(String image, Dimension spritesize) {        
        mySpriteSheet = ResourceManager.getInstance().<BufferedImage>getFile(image, BufferedImage.class);
        myFile = image.substring(image.lastIndexOf("/") + 1); 
        mySpriteSize = spritesize;
        myImageSize = new Dimension(mySpriteSheet.getWidth(), mySpriteSheet.getHeight());
        
        mySpritesX = (int) Math.floor(myImageSize.getWidth() / mySpriteSize.getWidth());
        mySpritesY = (int) Math.floor(myImageSize.getHeight() / mySpriteSize.getHeight());
        mySprites = new BufferedImage[mySpritesX][mySpritesY];
        splitImages();
    }
    
    private void splitImages() {
        for (int x = 0; x < mySpritesX; x++) {
            for (int y = 0; y < mySpritesY; y++) {
                mySprites[x][y] = mySpriteSheet.getSubimage(x, y, (int)mySpriteSize.getWidth(), (int)mySpriteSize.getHeight());
            }
        }
    }
    
    public BufferedImage getImage(int x, int y) {
        if (x >= 0 && y >= 0 && x < mySpritesX && y < mySpritesY) { 
            return mySprites[x][y];
        }
        return null;
    }
}

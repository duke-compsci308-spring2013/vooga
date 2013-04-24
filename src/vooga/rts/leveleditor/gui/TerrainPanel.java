package vooga.rts.leveleditor.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import vooga.rts.leveleditor.components.EditableTerrain;
import vooga.rts.util.Pixmap;


public class TerrainPanel extends MapComponentPanel {

    public TerrainPanel (Canvas canvas) {
        super(canvas);
    }

    @Override
    public void addButton () {
        for(int i=0; i<myFiles.length; ++i) {
            try {
                BufferedImage image = ImageIO.read(myFiles[i]);
                Pixmap image1 = new Pixmap(image);
                myPanel.add(new TerrainButton(new EditableTerrain(image1,0,0,0,i+1,"",myFiles[i].getName(),0),image,this));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void setCurrentSelectTerrain (EditableTerrain t) {
        myCanvas.setCurrentSelectTerrain(t);
        
    }

}

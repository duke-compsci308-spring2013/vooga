package vooga.rts.resourcemanager;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

public class ImageLoader extends ResourceLoader {
    
    public ImageLoader() {
        super();        
        Set<String> myUnique = new HashSet<String>(Arrays.asList(ImageIO.getReaderFormatNames()));
        for (String ext : myUnique) {
            this.registerExtension(ext);
        }
    }

    @Override
    public Class<?> getFileType () {        
        return BufferedImage.class;
    }

    @Override
    public Object loadFile (URL path) {
        try {
            BufferedImage image = ImageIO.read(path);
            return image;
        }
        catch (IOException e) {
            System.out.println("This is not an image.");
            return null;
        }
    }

}

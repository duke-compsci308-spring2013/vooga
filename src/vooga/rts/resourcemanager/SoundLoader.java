package vooga.rts.resourcemanager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;

public class SoundLoader extends ResourceLoader {
    
    public SoundLoader() {
        super();        
        this.registerExtension("wav");
        this.registerExtension("aiff");
        this.registerExtension("au");
        this.registerExtension("mid");
        this.registerExtension("rmf");
    }

    @Override
    public Class<?> getFileType () {        
        return AudioClip.class;
    }

    @Override
    public Object loadFile (URL path) {
        return Applet.newAudioClip(path);
    }

}

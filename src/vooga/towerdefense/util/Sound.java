package vooga.towerdefense.util;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.*;


/**
 * This class handles playing sounds and
 * adds some utility functions to the AudioClip class.
 * This class can play a sequence of sounds in loop,
 * augment the reality of a game
 * 
 * Note, Java only supports the formats: wav, aiff, au, mid, rmf.
 * 
 * @author Elder Massahiro Yoshida, Robert C. Duvall
 */
public class Sound {
    // OS-independent relative resource locations (like URLs)
    private static final String RESOURCE_LOCATION = "/sounds/";
    // underlying implementation
    private ArrayList<AudioInputStream> myAlbum = new ArrayList<AudioInputStream>();
    private ArrayList<String> myFileNames = new ArrayList<String>();
    private AudioClip myClip;
    
    /**
     * Construct a sound with the data referred to by the given filename.
     */
    public Sound (String filename) {
        setSound(filename);
    }
    
    /**
     * Construct a an album of songs with an array of filenames.
     * This could take a while.
     */
    public Sound (String[] filenames) {
        for (String s : filenames) {
            setSound(s);
        }
    }
    
    private AudioInputStream getAudio (String filename) {
        AudioInputStream audioIn = null;
        try {
            // Open an audio input stream.
            URL url = this.getClass().getResource(RESOURCE_LOCATION + filename);
            audioIn = AudioSystem.getAudioInputStream(url);
            return audioIn;
        }
        catch (UnsupportedAudioFileException e) {
            System.err.println("This audio file is not supported!");
        }
        catch (IOException e) {
            System.err.println("Could not read file!");
        }
        return audioIn;
    }
    
    /**
     * Set this sound to the data referred to by the given filename.
     */
    public void setSound (String filename) {
        myClip = Applet.newAudioClip(getClass().getResource(RESOURCE_LOCATION + filename));

        myAlbum.add(getAudio(filename));
        myFileNames.add(filename);
        
    }
    
    /**
     * Set this sound to the data referred to by the given filename at a desired position
     * in the <code>AudioInputStream</code> array.
     */
    public void setSoundAt (int i, String filename) {
        myAlbum.add(i, getAudio(filename));
        myFileNames.add(i, filename);

    }
    
    public String removeSound (String filename) {
        int index = myFileNames.indexOf(filename);
        String removedFileName = myFileNames.remove(index);
        myAlbum.remove(index);
        return removedFileName;
    }
    
    /**
     * Play the sound at the first index (0).
     */
    public void play () {
        myClip.play();
        /*
        Clip clip = null;
        try {
            clip.open(myAlbum.get(0));
        }
        catch (LineUnavailableException | IOException e) {
            System.err.printf("The audio could not be played most likely because" +
                              " a music file has not been loaded!");
        }
        clip.start();
        */
    }
    
    public void playStream (int[] stream) {
        
    }
    
    public void loop () {
        // myAlbum.get(0).loop();
    }
    
    /**
     * Stop playing the given sound.
     */
    public void stop () {
        
    }
    
    public String toString () {
        return myFileNames.toString();
    }
    
    class Playback extends Thread {
        private AudioInputStream myAudio;
        private volatile boolean loopOn = false;
        private Clip myClip;
        
        public Playback (AudioInputStream audio, boolean loop) {
            setDaemon(false);
            myAudio = audio;
            loopOn = loop;
        }
        
        // If loop is on then execute a while, if the loop is not on then execute only once
        @Override
        public void run () {
            play();
            while (loopOn) {
                play();
            }
        }
        
        private void play () {
            
                Line.Info linfo = new Line.Info(Clip.class);
                Line line;
                try {
                    line = AudioSystem.getLine(linfo);
                    myClip = (Clip) line;
                    myClip.open(myAudio);
                }
                catch (LineUnavailableException e) {
                    e.printStackTrace();
                    System.err.printf("The audio could not be played because" +
                                      " a music file has not been loaded!");
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.err.printf("The audio could not be played because" +
                            " a music file has not been loaded!");
                }
            
            myClip.start();
            
            /*
             * Line.Info linfo = new Line.Info(Clip.class);
             * Line line = AudioSystem.getLine(linfo);
             * myClip = (Clip) line;
             * myClip.addLineListener(this);
             * 
             * myAlbum.get(0).;
             * Line.Info linfo = new Line.Info(Clip.class);
             * Line line = AudioSystem.getLine(linfo);
             * myClip = (Clip) line;
             */
            
        }
        
        public void update (float pan, float gain) {
            FloatControl gainControl =
                    (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain); // Reduce volume by 10 decibels.
        }
        
        public void stopLoop () {
            loopOn = false;
        }
        
    }
    
}

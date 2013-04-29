package util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import vooga.fighter.model.objects.AttackObject;
import vooga.fighter.model.objects.GameObject;


/**
 * Holds a series of rectangles and images representing one state of an object.
 * Additionally holds a series of dimensions representing the size of each frame,
 * as well as a series of integers representing frame delay for each frame.
 * Useful for collision detection and handling, as well as animation.
 * 
 * @author James Wei
 * 
 */
public class State {
    
    private static final String myDisabledHitboxError = "Hitbox parameter is disabled";
    private static final String myDisabledImageError = "Image parameter is disabled";
    private static final String myDisabledSizeError = "Size parameter is disabled";
    private static final String myStateExpiredError = "Cannot update nonlooping state" +
    		"once it has completed, either reset state or set looping to true";

    public int myNumFrames;
    public int myCurrentFrame;

    /**
     * True if the animation for this state loops, false otherwise.
     */
    private boolean myLooping;

    /**
     * Maintains current frame delay count. Used to remain in the same frame of
     * an animation for more than one game loop.
     */
    private int myDelay;

    private Rectangle[] myHitboxes;
    private Pixmap[] myImages;
    private Dimension[] mySizes;
    private Integer[] myFrameDelays;
    
    /**
     * Owner of this State, i.e. the object that this State object describes.
     */
    private Object myOwner;
    
    /**
     * Booleans for whether images, shapes, and sizes are disabled. Disabling
     * one of these three will prevent the respective array from being populated,
     * and prevents accesses to that array. 
     */
    private boolean myHitboxEnabled;
    private boolean myImageEnabled;    
    private boolean mySizeEnabled;

    /**
     * Creates a new state that is a deep copy of another. The only parameters
     * not copied are current frame and delay.
     */
    public State(State other) {
        this(null, other.myNumFrames);
        myImageEnabled = other.myImageEnabled;
        myHitboxEnabled = other.myHitboxEnabled;
        mySizeEnabled = other.mySizeEnabled;
        myLooping = other.myLooping;
        myOwner = other.myOwner;
        for (int i=0; i<myNumFrames; i++) {
            try {
                Rectangle newRectangle = new Rectangle(other.myHitboxes[i]);
                populateHitbox(newRectangle, i);
            } catch (StateParameterDisabledException e) {
                
            }
            try {
                Pixmap newPixmap = new Pixmap(other.myImages[i]);
                populateImage(newPixmap, i);
            }  catch (StateParameterDisabledException e) {
                
            }
            try {
                Dimension newSize = new Dimension(other.mySizes[i]);
                populateSize(newSize, i);
            }  catch (StateParameterDisabledException e) {
                
            }
        }
    }
    
    /**
     * Creates a state with the given owner, number of frames, priority, and depth.
     * All frame delays zero by default. All state parameters (image, hitbox, and size)
     * enabled by default. Looping disabled by default.
     */
    public State(Object owner, int numFrames) {
        myOwner = owner;
        myNumFrames = numFrames;        
        myHitboxes = new Rectangle[myNumFrames];
        myImages = new Pixmap[myNumFrames];
        mySizes = new Dimension[myNumFrames];
        myFrameDelays = new Integer[myNumFrames];
        myCurrentFrame = 0;
        myDelay = 0;
        myLooping = false;
        myHitboxEnabled = true;
        myImageEnabled = true;        
        mySizeEnabled = true;
        populateAllDelays(0);
    }

    /**
     * Sets the owner of this State.
     */
    public void setOwner(Object owner) {
    	myOwner = owner;
    }
    
    /**
     * Sets looping boolean. If this method is not called, looping defaults to false.
     */
    public void setLooping(boolean looping) {
        myLooping = looping;
    }

    /**
     * Returns the number of frames in this state.
     */
    public int getNumFrames() {
        return myNumFrames;
    }
    
    /**
     * Adds a hitbox this state's hitbox array.
     */
    public void populateHitbox(Rectangle rect, int index)
            throws StateParameterDisabledException {
        if (!myHitboxEnabled) {
            throw new StateParameterDisabledException(myDisabledHitboxError);
        }
        myHitboxes[index] = rect;
    }

    /**
     * Adds a image into this state's image array.
     */
    public void populateImage(Pixmap image, int index)
            throws StateParameterDisabledException {
        if (!myImageEnabled) {
            throw new StateParameterDisabledException(myDisabledImageError);
        }
        myImages[index] = image;
    }

    /**
     * Adds a size into this state's size array.
     */
    public void populateSize(Dimension size, int index)
            throws StateParameterDisabledException {
        if (!mySizeEnabled) {
            throw new StateParameterDisabledException(myDisabledSizeError);
        }
        mySizes[index] = size;
    }
    
    /**
     * Adds a frame delay represented by an Integer into this state's Integer
     * array. By default, all frame delays are 0. If a frame has a delay greater
     * than 0, then that frame will persist for an extra number of frames equal
     * to the frame delay. Negative delays are not allowed.
     */
    public void populateFrameDelay(Integer delay, int index) {
        if (delay < 0) {
            return;
        }
        myFrameDelays[index] = delay;
    }
    
    /**
     * Shortcut method for populating the entire size array. Useful if a state
     * never changes sizes throughout its animation.
     */
    public void populateAllSizes(Dimension size)
            throws StateParameterDisabledException {
        if (!mySizeEnabled) {
            throw new StateParameterDisabledException(myDisabledSizeError);
        }
        for (int i=0; i<mySizes.length; i++) {
            mySizes[i] = size;
        }
    }
    
    /**
     * Shortcut method for populating the entire size array with the size of the
     * image stored in the same frame. This should be called after the image
     * array is populated. If this method encounters a null image, it will
     * populate the corresponding size array element with a null size.
     */
    public void populateAllSizesWithImages()
            throws StateParameterDisabledException {
        if (!mySizeEnabled) {
            throw new StateParameterDisabledException(myDisabledSizeError);
        }
        for (int i=0; i<mySizes.length; i++) {
            if (myImages[i] == null) {
                mySizes[i] = null;
            } else {
                mySizes[i] = myImages[i].getSize();
            }
        }
    }

    /**
     * Shortcut method for populating the entire delay array. Useful if a state
     * never changes frame delay throughout its animation.
     */
    public void populateAllDelays(Integer delay) {
        if (delay < 0) {
            return;
        }
        for (int i=0; i<myFrameDelays.length; i++) {
            myFrameDelays[i] = delay;
        }
    }
    
    /**
     * Returns the current active hitbox for this state. 
     */
    public Rectangle getCurrentHitbox()
            throws StateParameterDisabledException {
        if (!myHitboxEnabled) {
            throw new StateParameterDisabledException(myDisabledHitboxError);
        }
        return myHitboxes[myCurrentFrame];
    }

    /**
     * Returns the current active image for this state.
     */
    public Pixmap getCurrentImage()
            throws StateParameterDisabledException {
        if (!myImageEnabled) {
            throw new StateParameterDisabledException(myDisabledImageError);
        }
        return myImages[myCurrentFrame];
    }

    /**
     * Returns the current active size for this state.
     */
    public Dimension getCurrentSize()
            throws StateParameterDisabledException {
        if (!mySizeEnabled) {
            throw new StateParameterDisabledException(myDisabledSizeError);
        }
        return mySizes[myCurrentFrame];
    }    
        
    /**
     * Returns the owner of this state.
     */
    public Object getOwner() {
        return myOwner;
    }    

    /**
     * Returns true if hitboxes are enabled for this State.
     */
    public boolean checkHitboxEnabled() {
        return myHitboxEnabled;
    }
    
    /**
     * Returns true if images are enabled for this State.
     */
    public boolean checkImageEnabled() {
        return myImageEnabled;
    }
    
    /**
     * Returns true if sizes are enabled for this State.
     */
    public boolean checkSizeEnabled() {
        return mySizeEnabled;
    }    

    /**
     * Returns true if the state has been properly initialized, i.e. if all arrays
     * have been properly populated. Frame delays are not checked, nor are any
     * fields that have been disabled. Useful for debugging.
     */
    public boolean checkInitialization() {
        for (int i=0; i<myNumFrames; i++) {
            if (mySizes[i] == null && mySizeEnabled) {
                return false;
            }
            if (myHitboxes[i] == null && myHitboxEnabled) {
                return false;
            }
            if (myImages[i] == null && myImageEnabled) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Progresses this state to the next frame in its animation and hitbox. If the
     * state progresses past its final frame and is looping, it will reset. Note
     * that it is possible for a state to progress past its final frame--this is
     * the moment when the state is considered "complete". It is incumbent on the
     * developer to properly handle state switching when this occurs if the state
     * is not looping, or exceptions will be encountered.
     */
    public void update() throws StateExpiredException {
        if (hasCompleted() && !myLooping) {
            throw new StateExpiredException(myStateExpiredError);
        }
        if (myDelay == myFrameDelays[myCurrentFrame]) {
            myCurrentFrame++;
            myDelay = 0;
        } else {
            myDelay++;
        }
        if (hasCompleted() && myLooping) {
            resetState();
        }
    }

    /**
     * Resets the current frame to the beginning of the state.
     */
    public void resetState() {
        myCurrentFrame = 0;
    }    

    /**
     * Returns true if the state's animation has concluded, false otherwise.
     * Concluded in this sense means after the final animation has updated, i.e.
     * a concluded state has progressed to a frame beyond the number of frames
     * it actually has.
     */
    public boolean hasCompleted() {
        return (myCurrentFrame >= myNumFrames);
    }
    
}

package games.fighter.fightman.controller.levels;

import games.fighter.fightman.view.MenuDisplayInfo;
import games.fighter.fightman.view.MenuLayout;
import util.input.AlertObject;
import util.input.InputMethodTarget;
import vooga.fighter.controller.Controller;
import vooga.fighter.controller.gameinformation.GameInfo;
import vooga.fighter.controller.interfaces.ControllerDelegate;
import vooga.fighter.controller.interfaces.ModeCondition;
import vooga.fighter.model.MenuGrid;
import vooga.fighter.model.mode.MenuMode;
import vooga.fighter.model.mode.Mode;
import vooga.fighter.util.CollisionManager;
import vooga.fighter.view.Canvas;

/**
 * FightMan's Menu Class, borrows heavily from MenuController by Jack Matteucci
 * and Jerry Li
 * 
 * @author Wayne You
 * 
 */
public class FightManMenu extends Controller {
    
    private static final String INPUT_PATHWAY  = "config.menudefault";
    private String              myInputPathway;
    
    // The design is absolutely horrible for passing any sort of score around
    // easily.
    // Instead of subclassing everything in order to pass a pair of integers,
    // I'm just
    // going to add in static values. This is horrible design and I realize
    // that.
    public static String        titleText      = "WATASHI NO FIGHT MAN!";
    public static int           p1Score;
    public static int           p2Score;
    
    /**
     * The end condition for any menu is having an option selected.
     */
    private ModeCondition       myEndCondition = new ModeCondition() {
                                                   @Override
                                                   public boolean checkCondition(
                                                           Mode mode) {
                                                       return !(""
                                                               .equals(getMode()
                                                                       .peekChoice()));
                                                   }
                                               };
    
    /**
     * Initial constructor, used in reflection
     */
    public FightManMenu() {
        super();
    }
    
    /**
     * Concrete constructor that sets up a menu with the MenuLayout layout.
     * 
     * @param name
     *            name of controller
     * @param frame
     *            canvas
     * @param manager
     *            The ControllerManager
     * @param gameinfo
     *            GameInfo
     */
    public FightManMenu(String name, Canvas frame, ControllerDelegate manager,
            GameInfo gameinfo, String pathway) {
        super(name, frame, manager, gameinfo, pathway);
        myInputPathway = getHardFilePath() + INPUT_PATHWAY;
        System.out.println(myInputPathway);
        setInput(manager.getInput());
        getInput().replaceMappingResourcePath(myInputPathway);
        getInput().addListenerTo(this);
        MenuDisplayInfo loopInfo = new MenuDisplayInfo(super.getMode());
        loopInfo.p1Score = p1Score;
        loopInfo.p2Score = p2Score;
        loopInfo.titleText = titleText;
        loopInfo.generateHUD();
        setLoopInfo(loopInfo);
        frame.setLayout(new MenuLayout());
    }
    
    /**
     * Loads the associated mode of FightMenuMode
     */
    @Override
    public void loadMode() {
        Mode mode = new MenuMode(new CollisionManager(), super.getName());
        super.setMode(mode);
    }
    
    @Override
    public void initializeMode() {
        MenuGrid grid = new MenuGrid(getMode().getName(), getMode(),
                getHardFilePath());
        getMode().setMenuGrid(grid);
        getMode().setMenuObjects(grid.getMenuObjects());
        getMode().update();
    }
    
    @Override
    public void developerUpdate() {}
    
    /**
     * Move the cursor up if possible.
     * 
     * @param alObj
     */
    @InputMethodTarget(name = "up")
    public void up(AlertObject alObj) {
        if (getMode().inputReady()) {
            getMode().up();
        }
    }
    
    @InputMethodTarget(name = "up")
    public void orp(AlertObject asdf) {
        if (getMode().inputReady()) {
            getMode().up();
        }
    }
    
    /**
     * Move the cursor down if possible.
     * 
     * @param alObj
     */
    @InputMethodTarget(name = "down")
    public void down(AlertObject alObj) {
        if (getMode().inputReady()) {
            getMode().down();
        }
    }
    
    /**
     * Select the current option.
     * 
     * @param alObj
     */
    @InputMethodTarget(name = "enter")
    public void enter(AlertObject alObj) {
        if (getMode().inputReady()) {
            getMode().setChoice(getMode().getCurrentMenu().getValue());
        }
    }
    
    @Override
    public MenuMode getMode() {
        return (MenuMode) super.getMode();
    }
    
    @Override
    public Controller getController() {
        return this;
    }
    
    @Override
    public void removeListener() {
        super.removeListener();
        getInput().removeListener(this);
    }
    
    @Override
    public Controller getController(String name, Canvas frame,
            ControllerDelegate manager, GameInfo gameinfo, String filePath) {
        return new FightManMenu(name, frame, manager, gameinfo, filePath);
    }
    
    @Override
    protected void checkConditions() {
        if (myEndCondition.checkCondition(getMode())) {
            notifyEndCondition(getMode().peekChoice());
        }
    }
    
    @Override
    protected void notifyEndCondition(String choice) {
        removeListener();
        getMode().resetChoice();
        getManager().notifyEndCondition(getMode().getMenusNext(choice));
    }
    
}

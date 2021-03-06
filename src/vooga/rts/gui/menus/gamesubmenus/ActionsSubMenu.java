package vooga.rts.gui.menus.gamesubmenus;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import util.Location;
import vooga.rts.commands.InformationCommand;
import vooga.rts.gamedesign.sprite.gamesprites.interactive.InteractiveEntity;
import vooga.rts.gui.Window;
import vooga.rts.gui.buttons.ActionButton;


public class ActionsSubMenu extends SubMenu {

    private static final String ACTION_IMAGE_URL = "images/gamemenu/action_button.png";
    private static final int ACTION_BUTTON_WIDTH = 50;
    private static final int ACTION_BUTTON_HEIGHT = 50;
    private static final Dimension ACTION_BUTTON_DIMENSION = new Dimension(ACTION_BUTTON_WIDTH,
                                                                             ACTION_BUTTON_HEIGHT);
    private static final int ACTION_MENU_WIDTH = 360;
    private static final int ACTION_MENU_HEIGHT = 175;
    private static final int ACTION_X_PADDING = 30;
    private static final int ACTION_Y_PADDING = 30;
    private static final int MAX_ACTION_BUTTONS = 8;

    private ArrayList<ActionButton> myActionButtons;
    Location[] myActionButtonLocations;

    public ActionsSubMenu (String image, Dimension size, Location pos) {
        super(image, size, pos);
        myActionButtons = new ArrayList<ActionButton>();
        createActionButtonLocations();
    }

    @Override
    public void update (double elapsedTime) {
        // TODO Auto-generated method stub

    }

    @Override
    public void paint (Graphics2D pen) {
        super.paint(pen);
        for (ActionButton a : myActionButtons) {
            a.paint(pen);
        }
    }

    public void createActionButtonLocations () {
        myActionButtonLocations = new Location[MAX_ACTION_BUTTONS];
        for (int i = 0; i < 4; i++) {
            myActionButtonLocations[i] =
                    new Location((Window.D_X - ACTION_MENU_WIDTH) +
                                 (ACTION_BUTTON_WIDTH * i) +
                                 (ACTION_X_PADDING * (i + 1)),
                                 (Window.D_Y - ACTION_MENU_HEIGHT) + (ACTION_Y_PADDING));
        }

        for (int i = 0; i < 4; i++) {
            int l = 4 + i;
            myActionButtonLocations[l] =
                    new Location((Window.D_X - ACTION_MENU_WIDTH) +
                                 (ACTION_BUTTON_WIDTH * i) +
                                 (ACTION_X_PADDING * (i + 1)),
                                 (Window.D_Y - ACTION_MENU_HEIGHT) + ((ACTION_Y_PADDING) * 2) +
                                         ACTION_BUTTON_HEIGHT);
        }
    }

    private void updateActionButtons () {
        myActionButtons.clear();
        if (mySelectedEntity == null || mySelectedEntity.getCommands() == null) return;

        Iterator<InformationCommand> i = mySelectedEntity.getCommands().iterator();
        int k = 0;
        while (i.hasNext()) {
            InformationCommand command = i.next();
            ActionButton b;
            if (command.getInfo().getButtonImage() == null) {
                b =
                        new ActionButton(ACTION_IMAGE_URL, ACTION_BUTTON_DIMENSION,
                                         myActionButtonLocations[k], command);
            }
            else {
                b =
                        new ActionButton(command, ACTION_BUTTON_DIMENSION,
                                         myActionButtonLocations[k]);
            }
            myActionButtons.add(b);
            b.addObserver(this);
            k++;
        }
    }

    @Override
    public void processClick (int x, int y) {
        for (ActionButton a : myActionButtons) {
            if (a.checkWithinBounds(x, y))
                a.processClick();
        }
    }

    @Override
    public void processHover (int x, int y) {
        for (ActionButton a : myActionButtons) {
            if (a.checkWithinBounds(x, y)) {
                a.processHover();
                a.setFocused(true);
            }
            else {
                a.setFocused(false);
            }

        }
    }

    @Override
    public void setSelectedEntity (InteractiveEntity i) {
        mySelectedEntity = i;
        updateActionButtons();
    }

    @Override
    public void update (Observable o, Object a) {
        if (a instanceof InformationCommand) {
            InformationCommand b = (InformationCommand) a;
            setChanged();
            notifyObservers(b);
        }
    }

}

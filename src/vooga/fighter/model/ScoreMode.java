package vooga.fighter.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.model.objects.MouseClickObject;
import vooga.fighter.model.utils.State;


public class ScoreMode extends Mode {
    private String myMenuId;
    private List<MenuObject> myMenuObjects;
    private MouseClickObject myMouseClick;
    private MenuGrid myMenuGrid;
    private ModelDelegate myDelegate;
    private MenuCollisionManager myCollisionManager = new MenuCollisionManager();

    public ScoreMode (ModelDelegate delegate, String menuId) {
        super(delegate);
        myDelegate = delegate;
        myMenuId = menuId;
    }

    @Override
    public void update (double stepTime, Dimension bounds) {
        List<GameObject> myObjects = getMyObjects();
        handleCollisions();
        for (int i = 0; i < myObjects.size(); i++) {
            GameObject object = myObjects.get(i);
            object.update();
            if (object.shouldBeRemoved()) {
                myObjects.remove(object);
                i--;
            }
        }
    }

    @Override
    public void initializeMode () {
        myMenuGrid = new MenuGrid(myMenuId, myDelegate);
        myMenuObjects = myMenuGrid.getMenuObjects();
        for (MenuObject menu : myMenuObjects) {
            addObject(menu);
        }
    }

    public void handleCollisions() {
        //myCollisionManager.checkCollisions(getMyObjects());
    }

    @Deprecated
    public boolean shouldModeEnd () {
        return false;
    }
    
    public List<String> getMenuNames(){
        List list = new ArrayList<String>();
        for(MenuObject menu : myMenuObjects){
                list.add(menu.getChoice());
        }
        return list;
    }

    @Override
    public void addObject (GameObject object) {
        super.addObject(object);
        if (object instanceof MouseClickObject) {
            myMouseClick = (MouseClickObject) object;
        }
    }

}

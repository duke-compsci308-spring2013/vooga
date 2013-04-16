package vooga.fighter.model;

import java.awt.Dimension;
import java.util.List;

import vooga.fighter.controller.ModelDelegate;
import vooga.fighter.model.objects.CharacterObject;
import vooga.fighter.model.objects.GameObject;
import vooga.fighter.model.objects.MenuObject;
import vooga.fighter.model.objects.MouseClickObject;

public class MenuMode extends Mode {
	private int myMenuId;
	private List<MenuObject> myMenuObjects;
	private MouseClickObject myMouseClick;
	private MenuGrid myMenuGrid;

	public MenuMode(ModelDelegate cd, int menuId) {
		super(cd);
		myMenuId = menuId;
	}

	@Override
	public void update(double stepTime, Dimension bounds) {
		
	}

	@Override
	public void initializeMode() {
		myMenuGrid =new MenuGrid(myMenuObjects);	
	}

	@Override
	public boolean shouldModeEnd() {
		return false;
	}
	@Override
    public void addObject(GameObject object) {
        super.addObject(object);
        if (object instanceof MenuObject){
        	myMenuObjects.add((MenuObject)object);
        	myMenuGrid = new MenuGrid(myMenuObjects);
        }
        if (object instanceof MouseClickObject){
        	myMouseClick = (MouseClickObject)object;
        }
    }

}

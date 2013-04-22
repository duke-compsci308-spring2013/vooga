package vooga.rts.action;

import vooga.rts.manager.Manager;

/**
 * An action designed for the manager class. It stores the the manager that will 
 * be affected by this actions apply() method.
 * 
 * @author Challen Herzberg-Brovold
 *
 */
public abstract class ManagerAction implements Action {

    private Manager myManager;
    
    public ManagerAction(Manager manager) {
        myManager = manager;
    }
    
    /**
     * 
     * @return the manager which will be altered by the apply() method.
     */
    public Manager getManager() {
        return myManager;
    }
}

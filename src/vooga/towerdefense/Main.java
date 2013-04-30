package vooga.towerdefense;

import java.lang.reflect.InvocationTargetException;

import vooga.towerdefense.controller.Controller;

/**
 * Start the towerdefense game. The second parameter taken 
 * by the Controller instance should be the path of the xml file which 
 * contains the towerdefense game you plan on starting.
 * 
 * @author Leonard K. Ng'eno
 *
 */
public class Main {
    
    private Main() {
        // prevent instantiation of this class
    }
    
    public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        new Controller("English", "/src/vooga/towerdefense/resources/PokemonSwarm.xml");
    }
}


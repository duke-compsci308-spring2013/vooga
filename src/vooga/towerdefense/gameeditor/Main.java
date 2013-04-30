package vooga.towerdefense.gameeditor;

import java.awt.Dimension;
import vooga.towerdefense.gameeditor.controller.GameEditorController;

/**
 * Starts the GameEditor program
 * 
 * @author Leonard K. Ng'eno
 *
 */
public class Main {

    public static void main (String[] args) {
        new GameEditorController(new Dimension(1000, 700));
    }
}

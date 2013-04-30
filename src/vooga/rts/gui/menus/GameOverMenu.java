package vooga.rts.gui.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import vooga.rts.gui.Menu;
import vooga.rts.state.GameOver;

public class GameOverMenu extends Menu {

    private GameOver myState;
    
    public GameOverMenu(GameOver state) {
        myState = state;
    }
    
    @Override
    public void paint (Graphics2D pen) {
        pen.setFont(new Font("Helvetica", Font.BOLD, 100));
        pen.setColor(Color.red);
        pen.drawString("Game Over...", 150, 300);
        pen.setFont(new Font("Helvetica", Font.PLAIN, 72));
        if (myState == GameOver.WIN) {
            pen.drawString("You Win!", 150, 375);
        }
        else
        {
            if (myState == GameOver.LOSE) {
                pen.setColor(Color.red);
                pen.drawString("You Lost.", 150, 450);
            }
        }
        pen.setColor(Color.white);
        pen.setFont(new Font("Helvetica", Font.BOLD, 50));
        pen.drawString("Click the screen to exit.", 150, 525);
    }
    
    @Override
    public void handleMouseDown(int x, int y) {
        System.exit(0);
    }
}

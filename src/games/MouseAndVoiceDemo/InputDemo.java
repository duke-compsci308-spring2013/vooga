package games.MouseAndVocieDemo;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import util.inputExample.Canvas;

import arcade.games.ArcadeInteraction;
import arcade.games.Game;

/**
 * 
 * @author Ying Chen
 *
 */
public class InputDemo extends Game{
	public static final Dimension SIZE = new Dimension(800, 600);
    public static final String TITLE = "Race!";

	public InputDemo(ArcadeInteraction arcade) {
		super(arcade);
		setUpView();
	}
	
	private void setUpView(){
		JFrame frame = new JFrame(TITLE);
        Canvas display = new Canvas(SIZE, frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(display, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(800,650));
        frame.pack();
        frame.setVisible(true);
        display.start();	
	}
	
	@Override
	public void run() {
		
	}

}

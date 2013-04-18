package vooga.towerdefense.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import vooga.towerdefense.controller.Controller;


/**
 * Container that holds the screens for the view.
 * 
 * @author Leonard K. Ng'eno
 * @author Angelica Schwartz
 * 
 */
public class TDView {

    //TODO: read these names from the file
    private static final String TITLE = "TOWER DEFENSE";
    private static final String NEXT_BUTTON_NAME = "NEXT";
    private static final Dimension SIZE = new Dimension(1100, 800);
    private static final Dimension MAP_WINDOW_SIZE = new Dimension(800, 600);
    private static final Dimension EAST_WINDOW_SIZE = new Dimension(200, 600);
    private static final Dimension SOUTH_WINDOW_SIZE = new Dimension(1000, 200);
    private static final Dimension SPLASH_SCREEN_SIZE = new Dimension(800, 600);
    private JPanel myPanel;
    private EastWindow myEastWindow;
    private SouthWindow mySouthWindow;
    private JFrame myFrame;
    private MapScreen myMapScreen;
    private SplashScreen mySplashScreen;
    private Controller myController;
    private MapsSelectorScreen myMapSelector;
    private LevelsSelectorScreen myLevelSelector;
    private JButton myNextScreenButton;

    /**
     * constructor.
     *
     * @param controller
     */
    public TDView (Controller controller) {
        myController = controller;
        createGUI();
    }

    /**
     * creates this view.
     */
    public void createGUI () {
        myFrame = new JFrame(TITLE);
        myPanel = new JPanel();
        myFrame.setContentPane(myPanel);
        myFrame.setPreferredSize(SIZE);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mySplashScreen = new SplashScreen(SPLASH_SCREEN_SIZE, this);
        myMapScreen = new MapScreen(MAP_WINDOW_SIZE, myController);
        myEastWindow = new EastWindow(EAST_WINDOW_SIZE, myController);
        mySouthWindow = new SouthWindow(SOUTH_WINDOW_SIZE, myController);
        myFrame.getContentPane().add(nextScreenButton());

        addScreen(mySplashScreen);
    }

    /**
     * adds the new screens to the view.
     */
    public void assembleScreens() {
        myFrame.remove(myLevelSelector);
    
        myFrame.getContentPane().add(myMapScreen, BorderLayout.CENTER);
        myFrame.getContentPane().add(myEastWindow, BorderLayout.EAST);
        myFrame.getContentPane().add(mySouthWindow, BorderLayout.SOUTH);
    
        myFrame.pack();
        myFrame.setVisible(true);
        
    }

    /**
     * Removes the splash screen and displays the map choices.
     */
    public void showMapChoicesScreen() {
        myFrame.remove(mySplashScreen);
        myNextScreenButton.setVisible(false);
        myMapSelector = new MapsSelectorScreen(MAP_WINDOW_SIZE, this);
        addScreen(myMapSelector);
    }

    /**
     * Removes the map choices screen and shows the level
     *          difficulty choices screen.
     */
    public void showLevelDifficultyChoicesScreen() {
        myFrame.remove(myMapSelector);
        myLevelSelector = new LevelsSelectorScreen(MAP_WINDOW_SIZE, this);
        addScreen(myLevelSelector);
    }
    
    /**
     * adds the parameter screen to the view.
     * @param screen
     */
    private void addScreen(JPanel screen) {
        myFrame.getContentPane().add(screen, BorderLayout.CENTER);

        myFrame.pack();
        myFrame.setVisible(true);
    }

    /**
     * helper method that creates the button to move to the
     *          next screen.
     * @return the JButton
     */
    private Component nextScreenButton () {
        myNextScreenButton = new JButton(NEXT_BUTTON_NAME);
        myNextScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                showMapChoicesScreen();
            }
        });
        return myNextScreenButton;
    }

    /**
     * gets the map screen for this view.
     * @return the MapScreen
     */
    public MapScreen getMapScreen () {
        return myMapScreen;
    }

    /**
     * Gets the information screen that displays the
     *          GameElement information for this view.
     * @return the GameElementInformationScreen
     */
    public GameElementInformationScreen getGameElementInfoScreen () {
        return myEastWindow.getGameElementScreen();
    }

    /**
     * Gets the information screen that displays the
     *          player information for this view.
     * @return the InformationScreen
     */
    public InformationScreen getPlayerInfoScreen () {
        return myEastWindow.getPlayerScreen();
    }
}

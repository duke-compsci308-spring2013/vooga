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
import vooga.towerdefense.view.gamescreens.GameElementInformationScreen;
import vooga.towerdefense.view.gamescreens.GameStatsScreen;
import vooga.towerdefense.view.gamescreens.MapScreen;
import vooga.towerdefense.view.gamescreens.NextWaveScreen;
import vooga.towerdefense.view.gamescreens.ShopScreen;
import vooga.towerdefense.view.introscreens.MapsSelectorScreen;
import vooga.towerdefense.view.introscreens.ImageScreen;


/**
 * Container that holds the screens for the view.
 * 
 * @author Leonard K. Ng'eno
 * @author Angelica Schwartz
 * 
 */
public class TDView {

    // TODO: read these names from the file
    private static final String TITLE_KEYWORD = "GameTitle";
    private static final String NEXT_BUTTON_KEYWORD = "NextButtonName";
    private static final Dimension MAP_SELECTOR_SCREEN_SIZE = new Dimension(800, 600);
    private static final Dimension IMAGE_SCREEN_SIZE = new Dimension(800, 600);
    private static final String SPLASH_SCREEN_IMAGE = "splashscreen.gif";
    private static final String WIN_SCREEN_IMAGE = "winscreen.gif";
    private static final String LOSE_SCREEN_IMAGE = "losescreen.gif";
    private JPanel myPanel;
    private Dimension mySize;
    private GameStatsScreen myGameStats;
    private GameElementInformationScreen myInformationWindow;
    private ShopScreen myShopScreen;
    private JFrame myFrame;
    private MapScreen myMapScreen;
    private NextWaveScreen myNextWaveScreen;
    private ImageScreen mySplashScreen;
    private ImageScreen myWinScreen;
    private ImageScreen myLoseScreen;
    private Controller myController;
    private MapsSelectorScreen myMapSelector;
    private JButton myNextScreenButton;

    /**
     * constructor. 
     * @param controller
     */
    public TDView (Controller controller) {
        myController = controller;
        mySplashScreen = new ImageScreen(IMAGE_SCREEN_SIZE, SPLASH_SCREEN_IMAGE);
        myWinScreen = new ImageScreen(IMAGE_SCREEN_SIZE, WIN_SCREEN_IMAGE);
        myLoseScreen = new ImageScreen(IMAGE_SCREEN_SIZE, LOSE_SCREEN_IMAGE);
        myFrame = new JFrame(myController.getStringFromResources(TITLE_KEYWORD));
        myPanel = new JPanel();
        myFrame.setContentPane(myPanel);
        myFrame.getContentPane().setLayout(new BorderLayout());
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * display the game splash screen
     */
    public void showSplashScreen () {
        myFrame.getContentPane().add(nextScreenButton(), BorderLayout.EAST);
        addScreen(mySplashScreen, BorderLayout.CENTER);
    }

    /**
     * Removes the splash screen and displays the map choices.
     */
    public void showMapChoicesScreen () {
        myFrame.remove(mySplashScreen);
        mySplashScreen.setVisible(false);
        myNextScreenButton.setVisible(false);
        myMapSelector = new MapsSelectorScreen(MAP_SELECTOR_SCREEN_SIZE, this, myController);
        addScreen(myMapSelector, BorderLayout.CENTER);
    }
    
    /**
     * shows the win screen.
     */
    public void showWinScreen() {
        myFrame.getContentPane().setVisible(false);
        JPanel panel = new JPanel();
        myFrame.setContentPane(panel);
        addScreen(myWinScreen, BorderLayout.CENTER);
    }
    
    /**
     * shows the losing screen.
     */
    public void showLoseScreen() {
        myFrame.getContentPane().setVisible(false);
        JPanel panel = new JPanel();
        myFrame.setContentPane(panel);
        addScreen(myLoseScreen, BorderLayout.CENTER);
    }
    
    /**
     * Removes the map selector screen from the view
     */
    public void dismissMapSelector(){
        myMapSelector.setVisible(false);
        myFrame.remove(myMapSelector);
    }
    /**
     * adds the parameter screen to the view.
     * @param screen
     */
    public void addScreen (JPanel screen, String location) {
        myFrame.getContentPane().add(screen, location);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * sets the size of the view.
     * @param dimension
     */
    public void setSize(Dimension dimension) {
        mySize = dimension;
        myFrame.setPreferredSize(mySize);
    }

    /**
     * helper method that creates the button to move to the
     * next screen. 
     * @return the JButton
     */
    private Component nextScreenButton () {
        myNextScreenButton = new JButton(myController.getStringFromResources(NEXT_BUTTON_KEYWORD));
        myNextScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                showMapChoicesScreen();
            }
        });
        return myNextScreenButton;
    }
    
    /**
     * sets the map screen for this view.
     * @param mapscreen is the map screen
     */
    public void setMapScreen(MapScreen mapscreen) {
        myMapScreen = mapscreen;
    }

    /**
     * gets the map screen for this view.
     * @return the MapScreen
     */
    public MapScreen getMapScreen () {
        return myMapScreen;
    }
    
    /**
     * sets the next wave screen for this view.
     * @param screen is the next wave screen
     */
    public void setNextWaveScreen(NextWaveScreen screen) {
        myNextWaveScreen = screen;
    }

    /**
     * gets the next wave screen for this view.
     * @return the NextWaveScreen
     */
    public NextWaveScreen getNextWaveScreen () {
        return myNextWaveScreen;
    }

    /**
     * Gets the information screen that displays the
     * GameElement information for this view.
     * @return the GameElementInformationScreen
     */
    public GameElementInformationScreen getGameElementInfoScreen () {
        return myInformationWindow;
    }
    
    /**
     * sets the screen that displays game element information.
     * @param screen is the GameElementInformationScreen
     */
    public void setGameElementInformationScreen(GameElementInformationScreen screen) {
        myInformationWindow = screen;
    }

    /**
     * gets the screen that displays info for the player.
     * @return the GameStatsScreen
     */
    public GameStatsScreen getStatsScreen () {
        return myGameStats;
    }
    
    /**
     * sets the screen that displays info for the player.
     */
    public void setStatsScreen(GameStatsScreen screen) {
        myGameStats = screen;
    }
    
    /**
     * sets the shop screen for this view.
     * @param shopscreen is the shop screen
     */
    public void setShopScreen(ShopScreen shopscreen) {
        myShopScreen = shopscreen;
    }
    
    /**
     * Gets the shop screen for this view. 
     * @return the ShopScreen
     */
    public ShopScreen getShopScreen () {
        return myShopScreen;
    }
}

package vooga.scroller.level_editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import vooga.scroller.scrollingmanager.ScrollingManager;
import vooga.scroller.sprites.test_sprites.MarioLib;
import vooga.scroller.util.Sprite;
import vooga.scroller.view.View;


public class LevelParser {

    private static final String NEW_LINE = System.getProperty("line.seperator");
    private static final String BEGIN_LEVEL = "/level";
    private static final String BEGIN_KEY = "/key";
    private static final char SPACE = ' ';
    public Scanner myScanner;
    public Map<Character, String> myCharacterMap;
    public List<String> myLevelStrings;
    
    /**
     * Initialize instances variables.
     */
    public LevelParser() {
        myLevelStrings = new ArrayList<String>();
        myCharacterMap = new HashMap<Character, String>();
    }

    public LEGrid makeGridFromFile (File file) {
        myLevelStrings = new ArrayList<String>();
        myCharacterMap = new HashMap<Character, String>();
        try {
            myScanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        parseLevel();
        myCharacterMap=parseKey();
        return createGrid();
    }


    private void parseLevel () {
        myScanner.findWithinHorizon(BEGIN_LEVEL+NEW_LINE, 0);
        String line = myScanner.nextLine();
        System.out.println(line);
        while (!line.equals(BEGIN_KEY)) {
            myLevelStrings.add(line);
            System.out.println("----------------");
            line = myScanner.nextLine();
            System.out.println(line);
        }
    }

    private Map<Character, String> parseKey () {
        Map<Character, String> result = new HashMap<Character, String>();
        while (myScanner.hasNextLine()) {
            String line = myScanner.nextLine();
            result.put(line.charAt(0), line.substring(2));
        }
        return result;
    }

    private LEGrid createGrid () {
        if (myLevelStrings.isEmpty()) { return null; }
        System.out.println("" + myLevelStrings.size() + " " + myLevelStrings.get(1).length());
        LEGrid grid = new LEGrid(myLevelStrings.get(1).length(), myLevelStrings.size());
        for (int i = 1; i < myLevelStrings.size(); i++) {
            for (int j = 0; j < myLevelStrings.get(1).length(); j++) {
                char c = myLevelStrings.get(i).charAt(j);
                System.out.println(c);
                if (c != SPACE) {
                    String name = myCharacterMap.get(c);
                    
                    Sprite spr;
                    try {
                        spr = (Sprite) Class.forName(name).newInstance();
                        System.out.println(name);
                        System.out.println(spr);
                        grid.addSpriteToBox(j, i, spr);
                    }
                    catch (InstantiationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }//myNameMap.get(name).copy();
                }
            }
        }
        return grid;
    }
}

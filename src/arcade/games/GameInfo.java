
package arcade.games;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;


import arcade.database.Database;
import arcade.exceptions.InvalidPaymentException;
import arcade.model.Model;


public class GameInfo {
    private static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String FILEPATH = "games.";
    public static final String RESOURCE_DIR_NAME = ".resources.";

    public static final String FILE_NAME = ".example";

    public static final String GAME_NAME = "name";
    public static final String THUMBNAIL_NAME = "thumbnail";
    public static final String AD_SCREEN = "adscreen";
    public static final String AGE_LIMIT_NAME = "agepermission";
    public static final String PRICE_KEYWORD = "price";
    public static final String GAME_MAIN_CLASS_KEYWORD = "extendsGame";
    public static final String MULTIPLAYER_GAME_MAIN_CLASS_KEYWORD = "extendsMultiplayergame";
    public static final String DESCRIPTION_KEYWORD = "description";

    private ResourceBundle myResourceBundle;
    private Model myModel;
    
    private Database myDb;
    private String gameName;
    
    
    public GameInfo (String gamename, String genre, String language, Model model) throws MissingResourceException {
        String filepath = FILEPATH + genre + "." + gamename + RESOURCE_DIR_NAME + language;
        myModel = model;
        // String filepath = FILEPATH + gamename + RESOURCE_DIR_NAME + language;
        
        myResourceBundle = ResourceBundle.getBundle(filepath);
        
    }


    public GameInfo(Database database, String id) {
		myDb = database;
		gameName = id;
	}


    
    public ImageIcon getThumbnail() {
        return new ImageIcon(USER_DIRECTORY + "/src/arcade/resources/images/NoImage.gif");
        //TODO: revert this back to getting from db.
    	//return new ImageIcon(USER_DIRECTORY + myDb.getGameThumbnail(gameName));
    }
    
    public String getName(){
    	return gameName;
    }
    
    public String getDescription() {
    	return myDb.getGameDescription(gameName);
    }
    
    public ImageIcon getAdScreen() {
    	return new ImageIcon(USER_DIRECTORY + myDb.getGameAdScreen(gameName));
    }
    
    public double getRating() {
        return 5;
        //TODO: revert this back to getting from db.
    	//return myModel.getAverageRating(getName());
    }
    
    private String getSingleplayerGameClassKeyword() {
    	return myDb.getSingleplayerGameClassKeyword(gameName);
    }
    
    private String getMultiplayerGameClassKeyword() {
    	return myDb.getMultiplayerGameClassKeyword(gameName);
    }
    

    public List<String[]> getComments () {
        List<String[]> comments = new ArrayList<String[]>();
        String[] comment1 = { "subject", "theCoolestGuy", "5.0", "this game is awesome" };
        comments.add(comment1);
        return comments;
    }
    
   

    // Here, there be shiny reflective dragons . . .

    /*
     * but seriously, the reflection here is really fragile, we should add some exception handling
     * when
     * we get a chance. I just want to make sure that it works for now. This whole class makes
     * me nervous cause we use a handwritten properties file and we're really relying on the game
     * name
     * being the same as the class name, being the same as the game name thats stored in the
     * database.
     * 
     * In the future, we should consider adding on to the publish procedure a way of generating the
     * properties
     * file algorithmically, which would tighten this up a little and make it less error prone. Once
     * we do that,
     * then we should be able to preserve the game names across the class name, the database game
     * name, the
     * properties file name , and the directory name. This is a later feature though. for now, the
     * game
     * developers will have to worry about getting things exactly right :(
     */

    
    
    // untested . . . hope it works . . .
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MultiplayerGame getMultiplayerGame (Model model) {
        Class gameClass = getMultiplayerGameClass();
        try {
            Constructor con = gameClass.getConstructor(MultiplayerArcadeInteraction.class);
            try {
                return (MultiplayerGame) con.newInstance(model);
            }
            catch (IllegalArgumentException e) {
            }
            catch (InstantiationException e) {
            }
            catch (IllegalAccessException e) {
            }
            catch (InvocationTargetException e) {
            }
        }
        catch (SecurityException e) {
        }
        catch (NoSuchMethodException e) {
        }
        return null;
    }
    
 // TODO make sure this doesnt break if the game isnt multiplayer
    @SuppressWarnings("rawtypes")
    private Class getMultiplayerGameClass () {
        try {
            return Class.forName(getMultiplayerGameClassKeyword());
        }
        catch (ClassNotFoundException e) {
            // add some additional tries for letter case, then throw an exception
        }
        return null;
    }
    


    // I SAY I will add better exception handling here but . . . .
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Game getGame (Model model) {
        Class gameClass = getSingleplayerGameClass();
        try {
            Constructor con = gameClass.getConstructor(ArcadeInteraction.class);
            try {
                return (Game) con.newInstance(model);
            }
            catch (IllegalArgumentException e) {
            }
            catch (InstantiationException e) {
            }
            catch (IllegalAccessException e) {
            }
            catch (InvocationTargetException e) {
            }
        }
        catch (SecurityException e) {
        }
        catch (NoSuchMethodException e) {
        }
        return null;
    }

    // TODO make sure this doesnt break if the game isnt single player
    @SuppressWarnings("rawtypes")
    private Class getSingleplayerGameClass () {
        try {
            return Class.forName(getSingleplayerGameClassKeyword());
        }
        catch (ClassNotFoundException e) {
            // add some additional tries for letter case, then throw an exception

            return null;
        }
    }
    
    
    
    
//  // TODO make sure this doesnt break if the game isnt multiplayer
//  @SuppressWarnings("rawtypes")
//  private Class getMultiplayerGameClass () {
//      try {
//          return Class.forName(myResourceBundle.getString(MULTIPLAYER_GAME_MAIN_CLASS_KEYWORD));
//      }
//      catch (ClassNotFoundException e) {
//          // add some additional tries for letter case, then throw an exception
//      }
//      return null;
//  }
  
    
//
//    // TODO make sure this doesnt break if the game isnt single player
//    @SuppressWarnings("rawtypes")
//    private Class getGameClass () {
//        try {
//            return Class.forName(myResourceBundle.getString(GAME_MAIN_CLASS_KEYWORD));
//        }
//        catch (ClassNotFoundException e) {
//            // add some additional tries for letter case, then throw an exception
//
//            return null;
//        }
//    }

}

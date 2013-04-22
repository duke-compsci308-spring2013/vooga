package arcade.games;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import arcade.util.Pixmap;


public class GameInfo {
    public static final String FILEPATH = "games.";
    public static final String RESOURCE_DIR_NAME = ".resources.";

    public static final String FILE_NAME = ".example";
    
    public static final String GAME_NAME = "name";
    public static final String THUMBNAIL_NAME = "thumbnail";
    public static final String AD_SCREEN = "adscreen";
    public static final String AGE_LIMIT_NAME = "agepermission";
    public static final String PRICE_KEYWORD = "price";
    public static final String GAME_MAIN_CLASS_KEYWORD = "extendsGame";
    public static final String DESCRIPTION_KEYWORD = "description";
    
    private ResourceBundle myResourceBundle;

    public GameInfo (String gamename, String language) {
    	String filepath = FILEPATH + gamename + RESOURCE_DIR_NAME + language;
        myResourceBundle = ResourceBundle.getBundle(filepath);
    }

    public Pixmap getThumbnail () {
        return new Pixmap(myResourceBundle.getString(THUMBNAIL_NAME));
    }

    public String getName () {
        return myResourceBundle.getString(GAME_NAME);
    }

    public String getDescription () {
        return myResourceBundle.getString(DESCRIPTION_KEYWORD);
    }
    public Pixmap getAdScreen(){
        return new Pixmap(myResourceBundle.getString(AD_SCREEN));
    }
    

    public double getRating () {
        // TODO
        return 0;
    }
    
    public List<String[]> getComments() {
        List<String[]> comments = new ArrayList<String[]>();
        String[] comment1 = {"subject", "theCoolestGuy", "5.0", "this game is awesome"};
        comments.add(comment1);
        return comments;
        
    }
}
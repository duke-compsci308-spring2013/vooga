package arcade.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates and updates game table
 * this clearly needs to be refactored because there is duplicate code.
 * @author Natalia Carvalho
 */
public class GameTable extends Table {

    private static final String TABLE_SEPARATOR = ": ";
    
    private static final String GAMENAME_COLUMN_FIELD = "gamename";
    private static final String AUTHOR_COLUMN_FIELD = "author";
    private static final String GENRE_COLUMN_FIELD = "genre";
    private static final String THUMBNAIL_COLUMN_FIELD = "thumbnail";
    private static final String ADSCREEN_COLUMN_FIELD = "adscreen";
    private static final String AGEPERMISSION_COLUMN_FIELD = "agepermission";
    private static final String PRICE_COLUMN_FIELD = "price";
    private static final String EXTENDSGAME_COLUMN_FIELD = "extendsgame";
    private static final String EXTENDSMULTIPLAYER_COLUMN_FIELD = "extendsmultiplayegame";
    private static final String SINGLEPLAYER_COLUMN_FIELD = "singleplayer";
    private static final String MULTIPLAYER_COLUMN_FIELD = "multiplayer";
    private static final String DESCRIPTION_COLUMN_FIELD = "description";
    private static final String GAMEFILEPATH_COLUMN_FIELD = "gamefilepath";
    private static final String GAMEID_COLUMN_FIELD = "gameid";  
    
    private static final int GAMENAME_COLUMN_INDEX = 1;
    private static final int AUTHOR_COLUMN_INDEX = 2;
    private static final int GENRE_COLUMN_INDEX = 3;
    private static final int THUMBNAIL_COLUMN_INDEX = 4;
    private static final int ADSCREEN_COLUMN_INDEX = 5;
    private static final int AGEPERMISSION_COLUMN_INDEX = 6;
    private static final int PRICE_COLUMN_INDEX = 7;
    private static final int EXTENDSGAME_COLUMN_INDEX = 8; 
    private static final int EXTENDSMULTIPLAYER_COLUMN_INDEX = 9;
    private static final int SINGLEPLAYER_COLUMN_INDEX = 10;
    private static final int MULTIPLAYER_COLUMN_INDEX = 11;
    private static final int DESCRIPTION_COLUMN_INDEX = 12;
    private static final int GAMEFILEPATH_COLUMN_INDEX = 13;
    private static final int GAMEID_COLUMN_INDEX = 14;
    
    
    
    private static final String TABLE_NAME = "games";  


    private Connection myConnection;
    private PreparedStatement myPreparedStatement; 
    private ResultSet myResultSet;
    
    /**
     * GameTable constructor
     */
    public GameTable() {
        myConnection=establishConnectionToDatabase();
        myPreparedStatement=null;
        myResultSet=null;
    }

    /**
     * Closes Connection, ResultSet, and PreparedStatements once done with database
     */
    public void closeConnection() {
        try {
            if (myPreparedStatement != null) {
                myPreparedStatement.close();
            }
            if (myResultSet != null) {
                myResultSet.close();
            }
            if (myConnection != null) {
                myConnection.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if gameName already exists, false otherwise
     * @param gameName is the name of game
     */
    public boolean gameNameExists(String gameName) {
        String stm = "SELECT gamename FROM games WHERE gamename='" + gameName + "'";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet  = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Given a gameName, retrieves a gameID
     * @param gameName is the game's name
     */
    public String retrieveGameId(String gameName) {
        String stm = "SELECT * FROM " + TABLE_NAME + " WHERE " + GAMENAME_COLUMN_FIELD + "='" + gameName + "'";
        String gameid = "";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet  = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                gameid = myResultSet.getString(GAMEID_COLUMN_INDEX);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return gameid;
    }
    
    /**

     * Given the gameName, adds a game
     * @param gameName is the name of game
     * Adds a user to user table based on information
     * @param user is the username
     * @param pw is the password
     * @param firstname is firstname
     * @param lastname is lastname
     * @param dateOfBirth is date of birth

     */
    public boolean createGame(String gameName, String author, String genre, double price, 
                              String extendsGame, String extendsMultiplayerGame, int ageRating, 
                              boolean singlePlayer, boolean multiplayer, String thumbnailPath, 
                              String adscreenPath, String description) {
        if (gameNameExists(gameName)) {
            return false;
        }
        String stm = "INSERT INTO " + TABLE_NAME + "(gamename, author, genre, thumbnail, adscreen, agepermission, price, extendsgame, extendsmultiplayergame, singleplayer, multiplayer, description) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myPreparedStatement.setString(GAMENAME_COLUMN_INDEX, gameName);
            myPreparedStatement.setString(AUTHOR_COLUMN_INDEX, author);
            myPreparedStatement.setString(GENRE_COLUMN_INDEX, genre);
            myPreparedStatement.setString(THUMBNAIL_COLUMN_INDEX, thumbnailPath);
            myPreparedStatement.setString(ADSCREEN_COLUMN_INDEX, adscreenPath);
            myPreparedStatement.setInt(AGEPERMISSION_COLUMN_INDEX, ageRating);
            myPreparedStatement.setDouble(PRICE_COLUMN_INDEX, price);
            myPreparedStatement.setString(EXTENDSGAME_COLUMN_INDEX, extendsGame);
            myPreparedStatement.setString(EXTENDSMULTIPLAYER_COLUMN_INDEX, extendsMultiplayerGame);
            myPreparedStatement.setBoolean(SINGLEPLAYER_COLUMN_INDEX, singlePlayer);
            myPreparedStatement.setBoolean(MULTIPLAYER_COLUMN_INDEX, multiplayer);
            myPreparedStatement.setString(DESCRIPTION_COLUMN_INDEX, description);
            myPreparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    
    /**
     * Returns a list of all the games

     */

    public List<String> retrieveGameList() {
        String stm = "SELECT " + GAMENAME_COLUMN_FIELD + " FROM "  + TABLE_NAME;
        List<String> myGameNames = new ArrayList<String>();
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet = myPreparedStatement.executeQuery();
            while (myResultSet.next()) {
                myGameNames.add(myResultSet.getString(GAMENAME_COLUMN_INDEX));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return myGameNames;
        
    }
     
    /**
     * Given a game, deletes that game from gameTable
     * @param gameName is gameName
     */
    public void deleteGame(String gameName) {
        String stm = "DELETE FROM " + TABLE_NAME + " WHERE " + GAMENAME_COLUMN_FIELD + "='" + gameName + "'";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myPreparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    
    void printEntireTable () {
        System.out.println();
        try {
            myPreparedStatement = myConnection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            myResultSet = myPreparedStatement.executeQuery();
            while (myResultSet.next()) {
                System.out.print(myResultSet.getString(GAMENAME_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(AUTHOR_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(GENRE_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(THUMBNAIL_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(ADSCREEN_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getInt(AGEPERMISSION_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getDouble(PRICE_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(EXTENDSGAME_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(EXTENDSMULTIPLAYER_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getBoolean(SINGLEPLAYER_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getBoolean(MULTIPLAYER_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(DESCRIPTION_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(GAMEFILEPATH_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.println(myResultSet.getString(GAMEID_COLUMN_INDEX));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Given a gamename, retrieves genre
     * @param gameName is the gamename
     */
    public String getGenre(String gameName) {
        return retrieveEntryString(gameName, GENRE_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves author
     * @param gameName is the gamename
     */
    public String getAuthor(String gameName) {
        return retrieveEntryString(gameName, AUTHOR_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves thumbnail path
     * @param gameName is the gamename
     */
    public String getThumbnailPath(String gameName) {
        return retrieveEntryString(gameName, THUMBNAIL_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves adscreen path
     * @param gameName is the gamename
     */
    public String getAdScreenPath(String gameName) {
        return retrieveEntryString(gameName, ADSCREEN_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves author
     * @param gameName is the gamename
     */
    public int getAgePermission(String gameName) {
        return retrieveEntryInt(gameName, AUTHOR_COLUMN_INDEX);
    }
    
    
    /**
     * Given a gamename, retrieves price
     * @param gameName is the gamename
     */
    public double getPrice(String gameName) {
        return retrieveEntryDouble(gameName, PRICE_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves extendsgame
     * @param gameName is the gamename
     */
    public String getExtendsGame(String gameName) {
        return retrieveEntryString(gameName, EXTENDSGAME_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves extendsgamemultiplayer
     * @param gameName is the gamename
     */
    public String getExtendsGameMultiplayer(String gameName) {
        return retrieveEntryString(gameName, EXTENDSMULTIPLAYER_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves extendsgamemultiplayer
     * @param gameName is the gamename
     */
    public boolean getIsSinglePlayer(String gameName) {
        return retrieveEntryBoolean(gameName, SINGLEPLAYER_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves extendsgamemultiplayer
     * @param gameName is the gamename
     */
    public boolean getIsMultiplayer(String gameName) {
        return retrieveEntryBoolean(gameName, MULTIPLAYER_COLUMN_INDEX);
    }
    
    
    /**
     * Given a gamename, retrieves description
     * @param gameName is the gamename
     */
    public String getDescription(String gameName) {
        return retrieveEntryString(gameName, DESCRIPTION_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename, retrieves description
     * @param gameName is the gamename
     */
    public String getGameFilePath(String gameName) {
        return retrieveEntryString(gameName, GAMEFILEPATH_COLUMN_INDEX);
    }
    
    /**
     * Given a gamename and a column_index, returns that entire row entry
     * @param gameName is the gamename
     * @param columnIndex is the index that we want the information for
     */
    public String retrieveEntryString(String gameName, int COLUMN_INDEX) {
        String stm = "SELECT * FROM " +TABLE_NAME + " WHERE " + GAMENAME_COLUMN_FIELD + "='" + gameName + "'";
        String entry = "";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                entry = myResultSet.getString(COLUMN_INDEX);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }
    
    
    /**
     * Given a gamename and a column_index, returns that entire row entry
     * @param gameName is the gamename
     * @param columnIndex is the index that we want the information for
     */
    public int retrieveEntryInt(String gameName, int COLUMN_INDEX) {
        String stm = "SELECT * FROM " +TABLE_NAME + " WHERE " + GAMENAME_COLUMN_FIELD + "='" + gameName + "'";
        int entry = 0;
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                entry = myResultSet.getInt(COLUMN_INDEX);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }
    
    /**
     * Given a gamename and a column_index, returns that entire row entry
     * @param gameName is the gamename
     * @param columnIndex is the index that we want the information for
     */
    public double retrieveEntryDouble(String gameName, int COLUMN_INDEX) {
        String stm = "SELECT * FROM " +TABLE_NAME + " WHERE " + GAMENAME_COLUMN_FIELD + "='" + gameName + "'";
        double entry = 0;
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                entry = myResultSet.getDouble(COLUMN_INDEX);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }
    
    /**
     * Given a gamename and a column_index, returns that entire row entry
     * @param gameName is the gamename
     * @param columnIndex is the index that we want the information for
     */
    public boolean retrieveEntryBoolean(String gameName, int COLUMN_INDEX) {
        String stm = "SELECT * FROM " +TABLE_NAME + " WHERE " + GAMENAME_COLUMN_FIELD + "='" + gameName + "'";
        boolean entry = false;
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myResultSet = myPreparedStatement.executeQuery();
            if (myResultSet.next()) {
                entry = myResultSet.getBoolean(COLUMN_INDEX);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return entry;
    }
}
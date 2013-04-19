package arcade.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates and updates user table
 * this clearly needs to be refactored because there is duplicate code
 * @author Natalia Carvalho
 */
public class ScoreTable extends Table {

    private static final String TABLE_SEPARATOR = ": ";
    private static final String GAMEID_COLUMN_FIELD = "gameid";  
    private static final String USERID_COLUMN_FIELD = "userid";
    private static final String HIGHSCORE_COLUMN_FIELD = "highscore";
    private static final String SCOREID_COLUMN_FIELD = "scoreid";  

    
    private static final int GAMEID_COLUMN_INDEX = 1;
    private static final int USERID_COLUMN_INDEX = 2;
    private static final int HIGHSCORE_COLUMN_INDEX = 3;
    private static final int SCOREID_COLUMN_INDEX = 4;
    
    private static final String TABLE_NAME = "scores";

    private Connection myConnection;
    private PreparedStatement myPreparedStatement; 
    private ResultSet myResultSet;
    
    /**
     * Constructor but eventually I want to make this part of the abstract class
     */
    public ScoreTable() {
        establishConnectionToDatabase();
    }

    void establishConnectionToDatabase() {

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://cgi.cs.duke.edu/nrc10";
        String user = "nrc10";
        String password = "aUsg5xj2f";

        try {
            myConnection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        myPreparedStatement = null; 
        myResultSet = null;

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
     * Add new user game data to table 
     * @param gameid is game id
     * @param userid is user id
     * @param highscore of the game
     */
    public void addNewHighScore (String gameid, String userid, String highscore) {
        
        String stm = "INSERT INTO scores(gameid, userid, highscore) VALUES (?, ?, ?)";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myPreparedStatement.setString(GAMEID_COLUMN_INDEX, gameid);
            myPreparedStatement.setString(USERID_COLUMN_INDEX, userid);
            myPreparedStatement.setString(HIGHSCORE_COLUMN_INDEX, highscore);
            myPreparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printEntireTable () {
        System.out.println();
        try {
            myPreparedStatement = myConnection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            myResultSet = myPreparedStatement.executeQuery();
            while (myResultSet.next()) {
                System.out.print(myResultSet.getString(GAMEID_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.print(myResultSet.getString(USERID_COLUMN_INDEX) + TABLE_SEPARATOR);                
                System.out.print(myResultSet.getString(HIGHSCORE_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.println(myResultSet.getString(SCOREID_COLUMN_INDEX) + TABLE_SEPARATOR);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

  

}
package arcade.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Creates and updates user table
 * this clearly needs to be refactored because there is duplicate code
 * @author Natalia Carvalho
 */
public class CommentTable extends Table {

    private static final String TABLE_SEPARATOR = ": ";
    private static final String GAMEID_COLUMN_FIELD = "gameid";  
    private static final String USERID_COLUMN_FIELD = "userid";
    private static final String COMMENT_COLUMN_FIELD = "commentfield";
    private static final String COMMENTID_COLUMN_FIELD = "commentid";  

    
    private static final int GAMEID_COLUMN_INDEX = 1;
    private static final int USERID_COLUMN_INDEX = 2;
    private static final int COMMENT_COLUMN_INDEX = 3;
    private static final int COMMENTID_COLUMN_INDEX = 4;
    
    private static final String TABLE_NAME = "comments";

    private Connection myConnection;
    private PreparedStatement myPreparedStatement; 
    private ResultSet myResultSet;
    
    /**
     * Constructor but eventually I want to make this part of the abstract class
     */
    public CommentTable() {
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
     * Add new comment to table
     * @param gameid is game id
     * @param userid is user id
     * @param comment is the comment to be added
     */
    public void addNewComment (String gameid, String userid, String comment) {
        
        String stm = "INSERT INTO scores(gameid, userid, comment) VALUES (?, ?, ?)";
        try {
            myPreparedStatement = myConnection.prepareStatement(stm);
            myPreparedStatement.setString(GAMEID_COLUMN_INDEX, gameid);
            myPreparedStatement.setString(USERID_COLUMN_INDEX, userid);
            myPreparedStatement.setString(COMMENT_COLUMN_INDEX, comment);
            myPreparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getCommentByUsername(String gameid, String userid) {
        //TODO implement method
        return null;
    }
    
    public List<String> getAllCommentsForGame(String gameid) {
        //TODO implement method
        return null;
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
                System.out.print(myResultSet.getString(COMMENT_COLUMN_INDEX) + TABLE_SEPARATOR);
                System.out.println(myResultSet.getString(COMMENTID_COLUMN_INDEX) + TABLE_SEPARATOR);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

  

}
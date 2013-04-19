package arcade.database;
import java.util.List;
import arcade.games.GameData;
import arcade.games.UserGameData;

import java.util.List;

/**
 * Creates overall database
 * @author Natalia Carvalho
 */
public class Database {
    
    private GameTable myGameTable;
    private UserTable myUserTable;
    private UserGameDataTable myUserGameDataTable;
    private ScoreTable myScoreTable;
    private CommentTable myCommentTable;
    
    /**
     * Database constructor
     */
    public Database() {
        myGameTable = new GameTable();
        myUserTable = new UserTable();
        myUserGameDataTable = new UserGameDataTable();
        myScoreTable = new ScoreTable();
        myCommentTable = new CommentTable();
    }

    /**
     * Closes the database connection
     */
    public void closeDatabaseConnection() {
        myGameTable.closeConnection();
        myUserTable.closeConnection();
        myUserGameDataTable.closeConnection();
        myScoreTable.closeConnection();
        myCommentTable.closeConnection();
    }
    
    /**
     * Creates a user when gien username, pw, firstname, lastname, and dataofbirth
     * @param username is user
     * @param pw is password
     * @param firstname is first name
     * @param lastname is last name
     * @param date of birth is DOB
     */
    public boolean createUser(String username, String pw, String firstname, String lastname, String dataOfBirth) {
        return myUserTable.createUser(username, pw, firstname, lastname, dataOfBirth);
    }
    
    /**
     * Creates a user when given username, pw, firstname, lastname, and dataofbirth and avatar
     * @param username is user
     * @param pw is password
     * @param firstname is first name
     * @param lastname is last name
     * @param date of birth is DOB
     * @avatar is the filepath for the avatar
     */
    public boolean createUser(String username, String pw, String firstname, String lastname, String dataOfBirth, String filepath) {
        return myUserTable.createUser(username, pw, firstname, lastname, dataOfBirth, filepath);
    }
    
    /**
     * Creates a new game
     * @param gameName is name of name
     */
    public boolean createGame(String gameName , String genre) {
        return myGameTable.createGame(gameName , genre);
    }
    
    public void userPlaysGameFirst(String user, String gameName, String highscore) {
        myUserGameDataTable.createNewUserGameData(retrieveGameId(gameName), retrieveUserId(user));
    }
    
    public void updateAvatar(String user, String filepath) {
        myUserTable.updateAvatar(user, filepath);
    }
    
    public String retrieveAvatar(String username) {
        return myUserTable.retrieveAvatar(username);
    }
    
    public String retrieveDOB(String username) {
        return myUserTable.retrieveDOB(username);
    }
    
    public List<String> retrieveListOfGames() {
        return myGameTable.retrieveGameList();
    }
    
    public boolean authenticateUsernameAndPassword(String username, String password) {
        return myUserTable.authenticateUsernameAndPassword(username, password);
    }
    
    public void deleteUser(String username) {
        myUserGameDataTable.deleteUser(retrieveUserId(username));
        myUserTable.deleteUser(username);
    }
    
    public void deleteGame(String gameName) {
        myGameTable.deleteGame(gameName);
        //TODO delete game from other tables as well
    }
    
    public boolean usernameExists(String username) {
        return myUserTable.usernameExists(username);
    }
    
    public void addNewHighScore(String username, String gameName, String newHighScore) {
        myScoreTable.addNewHighScore(retrieveUserId(username), retrieveGameId(gameName), newHighScore);
    }
    
    public void getHighScores(int n) {
        //TODO implement method
    }
    
    public void updateUserGameFilePath(String filepath) {
        //TODO implement method
    }
        
    public void retrieveGameFilePath(String filepath) {
        //TODO implement method
    }
    
    public UserGameData getUserGameData(String gameName, String username) {
        return null;
    }
    
    public void userServer(UserGameData usd) {
        //use http method to send bits of information to send it to a script running on cgi
        //php, or write in java
    }
    
    public void insertComment(String username, String gameName, String comment) {
        myCommentTable.addNewComment(retrieveGameId(gameName), retrieveUserId(username), comment);
    }
    
    public List<String> retrieveCommentFromUsername(String username, String gameName) {
        return myCommentTable.getCommentByUsername(retrieveGameId(gameName), retrieveUserId(username));
    }
    
    public List<String> retrieveCommentsForGame(String gameName) {
        return myCommentTable.getAllCommentsForGame(retrieveGameId(gameName));
    }
    
    public GameData getGameData(String gameName) {
        return null;
    }
    
    public void printGameTable() {
        myGameTable.printEntireTable();
    }
    
    public void printUserTable() {
        myUserTable.printEntireTable();
    }
    
    public void printUserGameDataTable() {
        myUserGameDataTable.printEntireTable();
    }
    
    private String retrieveGameId(String gameName) {
        return myGameTable.retrieveGameId(gameName);
    }
    
    private String retrieveUserId(String username) {
        return myUserTable.retrieveUserId(username);
    }

    public void updateRating (String userName, String gameName, double rating) {
        
    }
    public String getGenre(String gameName){
        return myGameTable.getGenre(gameName);
    }

    public double getAverageRating (String gameName) {
        return myUserGameDataTable.getAverageRating(gameName);
    }
}
package arcade.database;

import java.util.List;
import arcade.games.GameData;
import arcade.games.UserGameData;
import java.util.List;
import util.Pixmap;


/**
 * Creates overall database
 * 
 * @author Natalia Carvalho
 */
public class Database {

    private GameTable myGameTable;
    private UserTable myUserTable;
    private UserGameDataTable myUserGameDataTable;
    private ScoreTable myScoreTable;
    private CommentTable myCommentTable;

    private S3Connections myS3Instance;

    /**
     * Database constructor
     */
    public Database () {
        myGameTable = new GameTable();
        myUserTable = new UserTable();
        myUserGameDataTable = new UserGameDataTable();
        myScoreTable = new ScoreTable();
        myCommentTable = new CommentTable();
        myS3Instance = new S3Connections();
    }

    /**
     * Closes the database connection
     */
    public void closeDatabaseConnection () {
        myGameTable.closeConnection();
        myUserTable.closeConnection();
        myUserGameDataTable.closeConnection();
        myScoreTable.closeConnection();
        myCommentTable.closeConnection();
    }

    /**
     * Creates a user when gien username, pw, firstname, lastname, and dataofbirth
     * 
     * @param username is user
     * @param pw is password
     * @param firstname is first name
     * @param lastname is last name
     * @param date of birth is DOB
     */
    public boolean createUser (String username,
                               String pw,
                               String firstname,
                               String lastname,
                               String dataOfBirth) {
        return myUserTable.createUser(username, pw, firstname, lastname, dataOfBirth);
    }

    /**
     * Creates a user when given username, pw, firstname, lastname, and dataofbirth and avatar
     * 
     * @param username is user
     * @param pw is password
     * @param firstname is first name
     * @param lastname is last name
     * @param date of birth is DOB
     * @avatar is the filepath for the avatar
     */
    public boolean createUser (String username,
                               String pw,
                               String firstname,
                               String lastname,
                               String dataOfBirth,
                               String filepath) {
        insertAvatar(username, filepath);
        return myUserTable.createUser(username, pw, firstname, lastname, dataOfBirth);
    }

    /**
     * Creates a new game
     * 
     * @param gameName is name of name
     */
    public boolean createGame (String gameName,
                               String author,
                               String genre,
                               double price,
                               String extendsGame,
                               String extendsMultiplayerGame,
                               int ageRating,
                               boolean singlePlayer,
                               boolean multiplayer,
                               String thumbnailPath,
                               String adscreenPath,
                               String description) {
        insertGameThumbnail(gameName, thumbnailPath);
        //TODO insert ad screen path as well
        return myGameTable.createGame(gameName, author, genre, price, extendsGame,
                                      extendsMultiplayerGame, ageRating, singlePlayer, multiplayer,
                                      thumbnailPath, adscreenPath, description);
    }

    public void userPlaysGameFirst (String user, String gameName, String highscore) {
        myUserGameDataTable.createNewUserGameData(retrieveGameId(gameName), retrieveUserId(user));
    }

    public void updateAvatar (String user, String filepath) {
        myUserTable.updateAvatar(user, filepath);
    }

    public String retrieveAvatar (String username) {
        return myUserTable.retrieveAvatar(username);
    }

    public String retrieveDOB (String username) {
        return myUserTable.retrieveDOB(username);
    }

    public List<String> retrieveListOfGames () {
        return myGameTable.retrieveGameList();
    }

    public boolean authenticateUsernameAndPassword (String username, String password) {
        return myUserTable.authenticateUsernameAndPassword(username, password);
    }

    public void deleteUser (String username) {
        myUserGameDataTable.deleteUser(retrieveUserId(username));
        myUserTable.deleteUser(username);
    }

    public void deleteGame (String gameName) {
        myGameTable.deleteGame(gameName);
        // TODO delete game from other tables as well
    }

    public boolean usernameExists (String username) {
        return myUserTable.usernameExists(username);
    }

    public void addNewHighScore (String username, String gameName, int newHighScore) {
        myScoreTable.addNewHighScore(retrieveUserId(username), retrieveGameId(gameName),
                                     newHighScore);
    }

    public void storeUserGameData (String gameName,
                                   String username,
                                   String tempFilePath,
                                   UserGameData usd) {
        myS3Instance.putUserGameDataIntoBucket(username, gameName, usd);
    }

    public UserGameData getUserGameData (String gameName, String username) {
        return myS3Instance.getUserGameDataFromBucket(username, gameName);
    }

    public void storeGameData (String gameName, GameData gd) {
        myS3Instance.putGameDataIntoBucket(gameName, gd);
    }

    public GameData getGameData (String gameName) {
        return myS3Instance.getGameDataFromBucket(gameName);
    }

    public void getHighScores (int n) {
        // TODO implement method

    }

    public void updateUserGameFilePath (String filepath) {
        // TODO implement method
    }

    public void retrieveGameFilePath (String filepath) {
        // TODO implement method
    }

    public void insertComment (String username, String gameName, String comment) {

        myCommentTable.addNewComment(retrieveGameId(gameName), retrieveUserId(username), comment);
    }

    public List<String> retrieveCommentFromUsername (String username, String gameName) {
        return myCommentTable.getCommentByUsername(retrieveGameId(gameName),
                                                   retrieveUserId(username));
    }

    public List<String> retrieveCommentsForGame (String gameName) {
        return myCommentTable.getAllCommentsForGame(retrieveGameId(gameName));
    }

    public void printGameTable () {

        myGameTable.printEntireTable();
    }

    public void printUserTable () {
        myUserTable.printEntireTable();
    }

    public void printUserGameDataTable () {
        myUserGameDataTable.printEntireTable();
    }

    private String retrieveGameId (String gameName) {
        return myGameTable.retrieveGameId(gameName);
    }

    private String retrieveUserId (String username) {
        return myUserTable.retrieveUserId(username);
    }

    public void updateRating (String userName, String gameName, double rating) {

    }

    public String getGenre (String gameName) {
        return myGameTable.getGenre(gameName);
    }

    public String getAuthor (String gameName) {
        return myGameTable.getAuthor(gameName);
    }

    public String getThumbnailPath (String gameName) {
        return myGameTable.getThumbnailPath(gameName);
    }

    public String getAdScreenPath (String gameName) {
        return myGameTable.getAdScreenPath(gameName);
    }

    public int getAgePermission (String gameName) {
        return myGameTable.getAgePermission(gameName);
    }

    public double getPrice (String gameName) {
        return myGameTable.getPrice(gameName);
    }

    public String getExtendsGame (String gameName) {
        return myGameTable.getExtendsGame(gameName);
    }

    public String getExtendsGameMultiplayer (String gameName) {
        return myGameTable.getExtendsGameMultiplayer(gameName);
    }

    public boolean getIsSinglePlayer (String gameName) {
        return myGameTable.getIsSinglePlayer(gameName);
    }

    public boolean getIsMultiplayer (String gameName) {
        return myGameTable.getIsMultiplayer(gameName);
    }

    public String getDescription (String gameName) {
        return myGameTable.getDescription(gameName);
    }

    public String getGameFilePath (String gameName) {
        return myGameTable.getGameFilePath(gameName);
    }

    public double getAverageRating (String gameName) {
        return myUserGameDataTable.getAverageRating(gameName);
    }

    public void listAllBuckets () {
        myS3Instance.listAllBuckets();
    }

    public void insertAvatar (String username, String filepath) {
        myS3Instance.putAvatarIntoBucket(username, filepath);
    }

    public void insertGameThumbnail (String gameName, String filepath) {
        myS3Instance.putGameThumbnailIntoBucket(gameName, filepath);
    }
    
    public void insertAdScreenPath (String gameName, String filepath) {
        myS3Instance.putAdScreenIntoBucket(gameName, filepath);
    }

    public Pixmap getGameThumbnail (String gameName) {
        return new Pixmap(myS3Instance.getThumbnail(gameName));
    }

    public Pixmap getAvatar (String username) {
        return new Pixmap(myS3Instance.getAvatar(username));
    }

    
    public String getGameDescription (String gameName) {
        // TODO Auto-generated method stub
        return null;
    }

    public Pixmap getGameAdScreen (String gameName) {
        return new Pixmap(myS3Instance.getAdScreen(gameName));
    }

    public String getSingleplayerGameClassKeyword (String gameName) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getMultiplayerGameClassKeyword (String gameName) {
        // TODO Auto-generated method stub
        return null;
    }

}

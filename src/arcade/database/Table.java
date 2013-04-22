package arcade.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates and updates table
 * @author Natalia Carvalho
 */
public abstract class Table {
    
    protected PreparedStatement myPreparedStatement;
    protected Connection myConnection;
    protected ResultSet myResultSet;
    
    Connection establishConnectionToDatabase() {

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://cgi.cs.duke.edu/nrc10";
        String user = "nrc10";
        String password = "aUsg5xj2f";
        Connection connection=null;
        try {
           connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }    
    
    abstract void closeConnection();
    
    abstract void printEntireTable();

}

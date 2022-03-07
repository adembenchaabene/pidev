package utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class DBConnection {
            final static String URL = "jdbc:mysql://localhost:3307/i-chariot"; // TODO: Change this to MYSQL
    final static String USERNAME = "root";
    final static String PWD = "";

    private Connection con;
    static DBConnection instance = null;

    private DBConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PWD);
           // Debugger.log("INFO: Database connection established.");
            System.out.println("INFO: Database connection established");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
}
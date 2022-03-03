/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_utils;

/**
 *
 * @author mariem
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection {
    final static String URL = "jdbc:mysql://localhost:3306/mariemtest"; // TODO: Change this to MYSQL
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author compu serv
 */
public class DBconnection {
            final static String URL = "jdbc:mysql://localhost:3306/testpidev"; // TODO: Change this to MYSQL
    final static String USERNAME = "root";
    final static String PWD = "";

    private Connection con;
    static DBconnection instance = null;

    private DBconnection() {
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

    public static DBconnection getInstance() {
        if (instance == null) {
            instance = new DBconnection();
        }
        return instance;
    }
}


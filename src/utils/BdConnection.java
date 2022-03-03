package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConnection {
    private final String url="jdbc:mysql://localhost:3306/i-chariot";
    private final String user="root";
    private final String pwd="";
    private static BdConnection instance;
    public Connection cnx;

    public BdConnection() {
        try {
            cnx= DriverManager.getConnection(url,user,pwd);
            System.out.println("Connected to Database");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public static BdConnection getInstance(){
        if (instance==null)
            instance=new BdConnection();
        return instance;
    }
        public  Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

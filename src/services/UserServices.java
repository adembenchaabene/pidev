/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entites.Admin;
import Entites.Client;
import Entites.User;
import utils.DBConnection;
import Entites.Roles;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.CryptWithMD5;

/**
 *
 * @author MSI
 */
public class UserServices implements Iservices<User> {

    private Connection con;
    private Statement ste;
     private Connection connection;
    private static UserServices instance;

    public UserServices() {
        DBConnection connect = DBConnection.getInstance();
        this.connection = connect.getCon();
    }

    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }

    @Override
    public void addUser(User user) {
        try {
            String query = "";
            Statement statement = connection.createStatement();
            switch (user.getRole()) {
                case Admin:
                    
                    query = "INSERT INTO `user` (`idUser`, `nom`,`prenom`,`email`,`password`,`role`) VALUES ('" + user.getIdUser() + "', '" + user.getNom() + "', '" + user.getPrenom() + "', '" + user.getEmail() + "', '" + CryptWithMD5.cryptWithMD5(user.getPassword()) + "', '" + Roles.Admin + "');";
                    System.out.println(statement.executeUpdate(query) + " Row inserted");
                    break;
                case Client:
                    
                    query = "INSERT INTO `user` (`idUser`, `nom`,`prenom`,`email`,`password`,`role`) VALUES ('" + user.getIdUser() + "', '" + user.getNom() + "', '" + user.getPrenom() + "', '" + user.getEmail() + "', '" + CryptWithMD5.cryptWithMD5(user.getPassword()) + "', '" + Roles.Client + "');";
                    System.out.println(statement.executeUpdate(query) + " Row inserted");
                    break;

                default:
                    System.out.println("Error,Role not defined");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
  @Override
    public void deleteUser(User user) {
        try {
            Statement statement=connection.createStatement();
            String query="DELETE FROM `user` WHERE idUser = "+user.getIdUser()+";";
            int x=statement.executeUpdate(query);
            System.out.println(x+" Row Deleted");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
  
@Override
    public void updateUser(int id,User user) {
        try {
            Statement statement=connection.createStatement();
            String query="";
            switch (user.getRole()){
                case Admin:
                    
                    query="UPDATE `user` SET `nom` = '"+user.getNom()+"', `prenom` = '"+user.getPrenom()+"', `email` = '"+user.getEmail()+"', `password` = '"+CryptWithMD5.cryptWithMD5(user.getPassword())+"' WHERE idUser = "+id+";";
                    System.out.println(statement.executeUpdate(query)+" Row updated");
                    break;
                case Client:
                    
                    query="UPDATE `user` SET `nom` = '"+user.getNom()+"', `prenom` = '"+user.getPrenom()+"', `email` = '"+user.getEmail()+"', `password` = '"+CryptWithMD5.cryptWithMD5(user.getPassword())+"' WHERE idUser = "+id+";";                   
                    System.out.println(statement.executeUpdate(query)+" Row updated");
                    break;
               
                default:
                    System.out.println("Error,Role not defined");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
  

    @Override
    public List<User> getUser() {
 List<User>users=new ArrayList<User>();
        try {
            Statement statement=connection.createStatement();
            String query="SELECT * FROM `user`";
            ResultSet resultSet =statement.executeQuery(query);
            while (resultSet.next()){
                switch (Roles.valueOf(resultSet.getString("role"))){
                    case Admin:
                        Admin admin=new Admin(resultSet.getInt("idUser"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("password"),Roles.Admin);
                        users.add(admin);
                        break;
                   
                    case Client:
                        Client client=new Client(resultSet.getInt("idUser"),resultSet.getString("nom"),resultSet.getString("prenom"),resultSet.getString("email"),resultSet.getString("password"),Roles.Client);
                        users.add(client);
                        break;
                   
                    default:
                        System.out.println("Error,Role not defined");
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users;
    }
    
    
    
    
    
    
    
    
    
    
        public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        String req = "SELECT * FROM user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(
                        resultSet.getInt("idUser"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                if(resultSet.getString("role").equals("Admin")){
                    u.setRole(Roles.Admin);
                }
                else{
                    u.setRole(Roles.Client);
                }
                usersList.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usersList;
    }
        
        
        
        
         public User findByMail(String email) {
        User u = null;
        String req = "SELECT * FROM user WHERE email =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 u = new User(
                        resultSet.getInt("idUser"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                if(resultSet.getString("role").equals("Admin")){
                    u.setRole(Roles.Admin);
                }
                else{
                    u.setRole(Roles.Client);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
 
         
         
              public User findByName(String name) {
        User u = null;
        String req = "SELECT * FROM user WHERE nom =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u = new User(
                        resultSet.getInt("idUser"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                if(resultSet.getString("role").equals("Admin")){
                    u.setRole(Roles.Admin);
                }
                else{
                    u.setRole(Roles.Client);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
              public User findByPrenom(String prenom) {
        User u = null;
        String req = "SELECT * FROM user WHERE prenom =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, prenom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u = new User(
                        resultSet.getInt("idUser"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                if(resultSet.getString("role").equals("Admin")){
                    u.setRole(Roles.Admin);
                }
                else{
                    u.setRole(Roles.Client);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
              public User findById(int id) {
        User u = null;
        String req = "SELECT * FROM user WHERE idUser =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u = new User(
                        resultSet.getInt("idUser"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                if(resultSet.getString("role").equals("Admin")){
                    u.setRole(Roles.Admin);
                }
                else{
                    u.setRole(Roles.Client);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
              
              
           
               
               
               
     
     
         public List<User> sortedbyId() throws SQLException {
        List<User> arr = new ArrayList<>();
        ste = connection.createStatement();
        ResultSet rs = ste.executeQuery("select * from user order by idUser desc");
        while (rs.next()) {

            String email = rs.getString("email");
            String prenom = rs.getString("prenom");
            String nom = rs.getString("nom");
            int idU = rs.getInt("idUser");
            rs.getString("password");
            User rec = new User(idU, nom, prenom, email, rs.getString("password"));
                        rs.getString("role");
                        if(rs.getString("role").equals("Admin")){
                    rec.setRole(Roles.Admin);
                }
                else{
                    rec.setRole(Roles.Client);
                }

            
            arr.add(rec);
        }
        return arr;
    }
    public boolean checklogin(String email,String password){
        try {
            ste = connection.createStatement();
            ResultSet rs = ste.executeQuery("select * from user where email='"+email+"' AND password='"+CryptWithMD5.cryptWithMD5(password)+"'");
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

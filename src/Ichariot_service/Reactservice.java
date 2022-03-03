/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_service;

import Ichariot_model.ReactArticle;
import Ichariot_utils.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author chaab
 */
public class Reactservice implements IReact<ReactArticle>{
     Connection con = DBConnection.getInstance().getCon();
    @Override
    public boolean putLikeToPost(int idusers, int idarticles) {
         try {
     Statement stmt = con.createStatement();
      String query1 ="INSERT INTO `reacts`(`idarticles`,`idusers`,`datecrea`) VALUES('" + idarticles + "', '" + idusers
               + "', current_timestamp());";
      int y = stmt.executeUpdate(query1);
      System.out.println(y + " Like added");
      return true;

    } catch (SQLException exception) {
      System.out.println(exception.getMessage());
    }
    return false;
    }

    @Override
    public boolean putUnLikeToPost(int idusers, int idarticles) {
         try {
      Statement stmt = con.createStatement();

      String query1 ="DELETE FROM `reacts` WHERE `reacts`.`idarticles` = " + idarticles + " AND `reacts`.`idusers` = " + idusers
              + ";";
      int y = stmt.executeUpdate(query1);
      System.out.println(y + " Like deleted");
      return true;
    } catch (SQLException exception) {
      System.out.println(exception.getMessage());
    }
    return false;
    }

    @Override
    public long numberOfLikesByPost(int id) {
    try {
      Statement stmt = con.createStatement();      
      String query = "SELECT `article`.`nbrreact` FROM `article` WHERE `idArticle`=" + id + ";";
      ResultSet resultSet = stmt.executeQuery(query);
      resultSet.next();

      return resultSet.getInt("nbrreact");

    } catch (SQLException exception) {
      System.out.println(exception.getMessage());
    }
    return 0;
    }
    
    
}

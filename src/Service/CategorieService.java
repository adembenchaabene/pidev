 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Categorie;
import Entites.Produit;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.DBconnection;
import java.util.Comparator;
/**
 *
 * @author compu serv
 */

public class CategorieService implements I_chariot <Categorie>{
       Connection con = DBconnection.getInstance().getCon();
  
    @Override
    public void ajouteer(Categorie C) {
String query = "INSERT INTO `categorie`(`nomCateg`, `description`) VALUES ('"+C.getNomCateg()+"','"+C.getDescription()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New categorie added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public void modifer(Categorie C) {
String query = "UPDATE categorie set  `nomCateg`='"+C.getNomCateg()+"', `description`='"+C.getDescription()+"' where idCateg='"+C.getId_categorie()+"'";
       try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: categorie Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(Categorie C) {
String query = "DELETE from categorie where idCateg ='"+C.getId_categorie()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Categorie Deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Categorie> afficher() {
List<Categorie> Categories = new ArrayList<>();
        String query = "SELECT * FROM categorie";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                Categories.add(new Categorie(
                        result.getInt("idCateg"),
                        result.getString("nomCateg"),
                        result.getString("description")
                        
                ));
            }
            //System.out.println("test");
            //System.out.println(Categories);
           // Debugger.log("INFO: Successfully fetched all users.");
          return Categories;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Categorie> Rechercher(String name) {
        List<Categorie> Categoriess = afficher();
        Categoriess.stream().filter(x -> x.getNomCateg().contains(name)).forEach(System.out::println);

        return Categoriess;
    }
  
    public List<Categorie> TriID() {
List<Categorie> Categoriess = new ArrayList<>();
        String query = "SELECT * FROM categorie";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                Categoriess.add(new Categorie(
                        result.getInt("idCateg"),
                        result.getString("nomCateg"),
                        result.getString("description")
                        
                ));
            }
            //System.out.println("test");
            //System.out.println(Categoriess);
            Categoriess.stream().sorted(Comparator.comparingInt(Categorie::getId_categorie).reversed()).forEach(System.out::println);
            
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
   
}


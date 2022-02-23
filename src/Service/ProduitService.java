 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

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
public class ProduitService implements I_chariot <Produit>{
       Connection con = DBconnection.getInstance().getCon();
 


       
      
    @Override
    public void ajouteer(Produit C) {
String query = "INSERT INTO `produit`(`nomProduit`, `quantite`, `prix`, `id_categ`) VALUES ('"+C.getNomProduit()+"','"+C.getQuantite()+"','"+C.getPrix()+"','"+C.getId_categorie()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Product added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void modifer(Produit C) {
String query = "UPDATE produit set  `nomProduit`='"+C.getNomProduit()+"', `quantite`='"+C.getQuantite()+"', `prix`='"+C.getPrix()+"', `id_categ`='"+C.getId_categorie()+"' where idProduit='"+C.getIdProduit()+"'";
       try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: produit Updated.");
        } catch (SQLException ex) {
        }
    }

    @Override
    public void supprimer(Produit C) {
 String query = "DELETE from produit where idProduit ='"+C.getIdProduit()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Produit Deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Produit> afficher() {
List<Produit> Produits = new ArrayList<>();
        String query = "SELECT * FROM produit";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                Produits.add(new Produit(
                        result.getInt("idProduit"),
                        result.getString("nomProduit"),
                        result.getInt("quantite"),
                        result.getFloat("prix"),
                        result.getInt("id_categ")
                        
                ));
            }
            System.out.println("test");
            System.out.println(Produits);
            
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Produit> Rechercher(String name) {
List<Produit> Produits = new ArrayList<>();
        String query = "SELECT * FROM produit";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                Produits.add(new Produit(
                        result.getInt("idProduit"),
                        result.getString("nomProduit"),
                        result.getInt("quantite"),
                        result.getFloat("prix"),
                        result.getInt("id_categ")
                        
                ));
            }
            //System.out.println("test");
            //System.out.println(Produits);
            Produits.stream()
                .filter(x-> x.getNomProduit().contains(name))
                .forEach(x->System.out.println(x));
            
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Produit> TriID() {
List<Produit> Produits = new ArrayList<>();
        String query = "SELECT * FROM produit";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                Produits.add(new Produit(
                        result.getInt("idProduit"),
                        result.getString("nomProduit"),
                        result.getInt("quantite"),
                        result.getFloat("prix"),
                        result.getInt("id_categ")
                        
                ));
            }
            //System.out.println("test");
            //System.out.println(Produits);
            Produits.stream().sorted(Comparator.comparingInt(Produit::getIdProduit).reversed()).forEach(System.out::println);
            
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
}

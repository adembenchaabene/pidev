 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entites.Produit;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;
import java.awt.PageAttributes;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 *
 * @author compu serv
 */
public class ProduitService implements I_chariot <Produit>{
       Connection con = DBConnection.getInstance().getCon();
 


       
      
    @Override
    public void ajouteer(Produit C) {
String query = "INSERT INTO `produit`(`nomProduit`, `quantite`, `prix`, `id_categ`, `description`, `image`) VALUES ('"+C.getNomProduit()+"','"+C.getQuantite()+"','"+C.getPrix()+"','"+C.getId_categorie()+"','"+C.getDescription()+"','"+C.getImage()+"')";
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
String query = "UPDATE produit set  `nomProduit`='"+C.getNomProduit()+"', `quantite`='"+C.getQuantite()+"', `prix`='"+C.getPrix()+"', `id_categ`='"+C.getId_categorie()+"', `description`='"+C.getDescription()+"', `image`='"+C.getImage()+"' where idProduit='"+C.getIdProduit()+"'";
       try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: produit Updated.");
        } catch (SQLException ex) {
        }
    }

    @Override
    public void supprimer(int id) {
 String query = "DELETE from produit where idProduit ='"+id+"'";
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
                        result.getInt("id_categ"),
                        result.getString("description"),
                        result.getString("image")
                        
                ));
            }
            return Produits;
            
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Produit> rechercher(String name) {
        List<Produit> produits = afficher().stream()
                .filter(x-> x.getNomProduit().contains(name))
                .collect(Collectors.toList());
            return produits;       
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
                        result.getInt("id_categ"),
                        result.getString("description"),
                        result.getString("image")
                        
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

    public void supprimer(PageAttributes.MediaType C) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public void supprimerIdP(int idP) {
        try {
            String req = "DELETE from Produit where idProduit =?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idP);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}

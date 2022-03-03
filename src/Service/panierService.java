/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Panier;
import Utils.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author compu serv
 */
public class panierService {

    Connection cnx;
    public panierService() {
        cnx = DBconnection.getInstance().getCon();
    }
    
    public void ajouteer(Panier p) {
String query = "INSERT INTO `panier`(`idPanier`, `idProduit`,)"
        + " VALUES ('"+p.getIdPanier()+"','"+p.getProduit().getIdProduit()+"')";
        try {
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }


   
    // modifier zeydaa

    
    public void supprimer(Panier p) {
 String query = "DELETE from panier where idPanier ='"+p.getIdPanier()+"'";
        try {
            Statement stmt = cnx.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: panier Deleted.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Panier> afficher() {
        List<Panier> paniers = new ArrayList<>();
        Panier p = new Panier();
        String query = "SELECT * FROM panier";

        try {
            Statement stmt = cnx.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                p.setIdPanier(result.getInt("idPanier"));
                p.setIdPanier(result.getInt("idProduit"));
                paniers.add(p);
                System.out.println(paniers.toString());
                
            }
            return paniers;
            
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
}

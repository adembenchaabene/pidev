/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author mariem
 */

import Entites.Salle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SalleService implements I_chariot<Salle>{
       Connection con = DBConnection.getInstance().getCon();

       
    @Override
    public void ajouteer(Salle C) {
        String query = "INSERT INTO `salle`( `nom`, `prixSalle`,`image`,`capacite`) VALUES ('"+C.getNom()+"','"+C.getPrix()+"','"+C.getImage()+"','"+C.getCapacite()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Salle added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

    @Override
    public List<Salle> afficher() {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                salles.add(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                        result.getString("image"),
                        result.getInt("capacite")
                        
                ));
            }
            return salles;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }
    public Salle findById(int id){
        Salle s=new Salle();
        String query = "SELECT * FROM Salle where idSalle="+id;

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                s=(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                        result.getString("image"),
                        result.getInt("capacite")
                        
                ));
            }
            return s;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; 
    }

   
     
    @Override
    public void modifer(Salle C) {
      String query = "UPDATE salle set `nom`='"+C.getNom()+"',`prixSalle`='"+C.getPrix()+"', `image`='"+C.getImage()+"',`capacite`='"+C.getCapacite()+"' where idSalle ='"+C.getIdSalle()+"'";
       //String query = "UPDATE salle set  `prixSalle`='"+C.getPrix()+"', `nom`='"+C.getNom()+"', `image`='"+C.getImage()+"',`capacite`='"+C.getCapacite()+"' where idSalle = ? ' " ;
       
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Salle Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    
   
    
    
    @Override
    public void supprimer(Salle C) {
        String query = "DELETE from Salle where idSalle ='"+C.getIdSalle()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Salle Deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}
    
    public void supprimerIdS(int idS) {
        try {
            String req = "DELETE from Salle where idSalle =?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idS);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Salle> rechercher(String name) {
        List<Salle> salles = afficher().stream()
                .filter(x-> x.getNom().contains(name))
                .collect(Collectors.toList());
            return salles;       
    }

    
    public List<Salle> triId() {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                salles.add(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                        result.getString("image"),
                        result.getInt("capacite")
                ));
            }
            //System.out.println("afficher successfully");
            //System.out.println(salles);
           // Debugger.log("INFO: Successfully fetched all users.");
            salles.stream().sorted(Comparator.comparingInt(Salle::getIdSalle).reversed()).forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }
    
       public List<Salle> getSalleByName(String name) {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM Salle where nom = '"+name+"'";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                salles.add(new Salle(
                        result.getInt("idSalle"),
                        result.getString("nom"),
                        result.getFloat("prixSalle"),
                        result.getString("image"),
                        result.getInt("capacite")
                        
                ));
            }
            return salles;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; }

    
   
    
    }

   





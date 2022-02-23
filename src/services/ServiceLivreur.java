/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Livreur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utlis.MyDB;

/**
 *
 * @author DELL
 */
public class ServiceLivreur {
      Connection cnx;

    public ServiceLivreur() {
        cnx = MyDB.getInstance().getConnection();
    }


    public void ajout(Livreur l) {
        try {
            String req = "insert into livreur (nomLivreur,numtel) values"
                    + " ( '" + l.getNom() + "', '" + l.getNumtel() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void modifier(Livreur t) {
        try {
            String req = "update livreur set nomLivreur = ? , numtel = ?  where idLivreur = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setInt(2, t.getNumtel());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void supprimer(int id) {
        try {
            String req = "delete from livreur where idLivreur = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Livreur> afficher() {
        List<Livreur> list = new ArrayList<>();
        try {
            String req ="select * from livreur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Livreur p = new Livreur();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nomLivreur"));
                p.setNumtel(rs.getInt("numtel"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
 public List <Livreur> recherche(String liv) {
List<Livreur> livreurs = afficher();
        livreurs.stream().filter(x -> x.getNom().contains(liv)).forEach(System.out::println);
         
        return livreurs;
 }
 
  public List <Livreur> tri() {
List<Livreur> list = new ArrayList<>();
        try {
            String req ="select * from livreur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Livreur p = new Livreur();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nomLivreur"));
                p.setNumtel(rs.getInt("numtel"));
                list.add(p);
            }
             
             list.stream().sorted(Comparator.comparingInt(Livreur::getId).reversed()).forEach(System.out::println);
         } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
          
    
 }
}

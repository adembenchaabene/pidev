/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entites.Livraison;
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
import utils.DBConnection;


/**
 *
 * @author DELL
 */
public class ServiceLivraison {
    Connection cnx;

    public ServiceLivraison() {
        cnx = DBConnection.getInstance().getCon();
    }


    public void ajout(Livraison l) {
        try {
            String req = "insert into livraison (type,adresse,id_produit,etat,iduser) values"
                    + " ( '" + l.getType() + "', '" + l.getIdAdresse() + "', '" + l.getIdproduit()+ "', '" +  l.getEtat()+ "','"+l.getIduser()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void modifier(Livraison t) {
        try {
            String req = "update livraison set type = ? , adresse = ? , id_produit = ? , id_livreur = ? , etat = ? where idLivraison = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getType());
            ps.setInt(2, t.getIdAdresse());
            ps.setInt(3, t.getIdproduit());
            ps.setInt(4, t.getIdlivreur());
            ps.setInt(5, t.getEtat());
            ps.setInt(6, t.getIdLivraison());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void supprimer(int id) {
        try {
            String req = "delete from livraison where idLivraison = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Livraison> afficher() {
        List<Livraison> list = new ArrayList<>();
        try {
            String req ="select * from livraison";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Livraison p = new Livraison();
                p.setIdLivraison(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setIdAdresse(rs.getInt("adresse"));
                p.setIdproduit(rs.getInt("id_produit"));
                p.setIdlivreur(rs.getInt("id_livreur"));
                p.setEtat(rs.getInt("etat"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Livraison> afficherliv(int id) {
         List<Livraison> list = new ArrayList<>();
        try {
            String req = "select * from livraison where iduser = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Livraison p = new Livraison();
                p.setIdLivraison(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setIdAdresse(rs.getInt("adresse"));
                p.setIdproduit(rs.getInt("id_produit"));
                p.setIdlivreur(rs.getInt("id_livreur"));
                p.setEtat(rs.getInt("etat"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Livraison> afficheretat() {
         List<Livraison> list = new ArrayList<>();
       try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from livraison where etat = " + 0;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                Livraison p = new Livraison();
                p.setIdLivraison(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setIdAdresse(rs.getInt("adresse"));
                p.setIdproduit(rs.getInt("id_produit"));
                p.setIdlivreur(rs.getInt("id_livreur"));
                p.setEtat(rs.getInt("etat"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
 public List <Livraison> recherche(String typ) {

List<Livraison> livraisons = afficher();
        livraisons.stream().filter(x -> x.getType().contains(typ)).forEach(System.out::println);
         
        return livraisons;
 }
    
 
 
 public List <Livraison> tri() {
List<Livraison> list = new ArrayList<>();
        try {
            String req ="select * from livraison";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Livraison p = new Livraison();
                p.setIdLivraison(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setIdAdresse(rs.getInt("adresse"));
                p.setIdproduit(rs.getInt("id_produit"));
                p.setIdlivreur(rs.getInt("id_livreur"));
                p.setEtat(rs.getInt("etat"));
                list.add(p);
            }
             list.stream().sorted(Comparator.comparingInt(Livraison::getIdLivraison).reversed()).forEach(System.out::println);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
          
    
 }
}

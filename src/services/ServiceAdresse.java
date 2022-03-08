/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entites.Adresse;
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
public class ServiceAdresse {
       Connection cnx;

    public ServiceAdresse() {
        cnx = DBConnection.getInstance().getCon();
    }


    public int ajout(Adresse l) {
         int risultato=-1;
        try {
            String req = "insert into adresse (ville,rue,numMaison,iduser) values"
                    + " ( '" + l.getVille() + "', '" + l.getRue() + "', '" + l.getNumMaison()+"', '" +l.getIduser()+ "')" ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
        if (rs.next()){
            risultato=rs.getInt(1);
        }
        rs.close();
        
            System.out.println(risultato);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return risultato;
        
    }

    
    public void modifier(Adresse t) {
        try {
            String req = "update adresse set ville = ? , rue = ? , numMaison = ? , iduser= ?  where id_adresse = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getVille());
            ps.setString(2, t.getRue());
            ps.setInt(3, t.getNumMaison());
            ps.setInt(4, t.getIduser());
            ps.setInt(5, t.getId_adresse());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void supprimer(int id) {
        try {
            String req = "delete from adresse where id_adresse = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Adresse> afficher() {
        List<Adresse> list = new ArrayList<>();
        try {
            String req ="select * from adresse";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Adresse p = new Adresse();
                p.setId_adresse(rs.getInt(1));
                p.setVille(rs.getString("ville"));
                p.setRue(rs.getString("rue"));
                p.setNumMaison(rs.getInt("numMaison"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<Adresse> afficheradd() {
        List<Adresse> list = new ArrayList<>();
        try {
            String req = " select * from adresse where iduser = " + 2;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Adresse p = new Adresse();
                p.setId_adresse(rs.getInt(1));
                p.setVille(rs.getString("ville"));
                p.setRue(rs.getString("rue"));
                p.setNumMaison(rs.getInt("numMaison"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List <Adresse> recherche(String add){


List<Adresse> adresses = afficher();
        adresses.stream().filter(x -> x.getVille().contains(add)).forEach(System.out::println);
         
        return adresses;
 
          
    
 }
 
 public List <Adresse> tri() {
List<Adresse> list = new ArrayList<>();
        try {
            String req ="select * from adresse";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Adresse p = new Adresse();
                p.setId_adresse(rs.getInt(1));
                p.setVille(rs.getString("ville"));
                p.setRue(rs.getString("rue"));
                p.setNumMaison(rs.getInt("numMaison"));
                list.add(p);
            }
             
             list.stream().sorted(Comparator.comparingInt(Adresse::getId_adresse).reversed()).forEach(System.out::println);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
          
    
 }
}

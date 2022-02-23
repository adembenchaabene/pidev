/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Adresse;
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
public class ServiceAdresse {
       Connection cnx;

    public ServiceAdresse() {
        cnx = MyDB.getInstance().getConnection();
    }


    public void ajout(Adresse l) {
        try {
            String req = "insert into adresse (ville,rue,numMaison) values"
                    + " ( '" + l.getVille() + "', '" + l.getRue() + "', '" + l.getNumMaison()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void modifier(Adresse t) {
        try {
            String req = "update adresse set ville = ? , rue = ? , numMaison = ?  where id_adresse = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getVille());
            ps.setString(2, t.getRue());
            ps.setInt(3, t.getNumMaison());
            ps.setInt(4, t.getId_adresse());
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
    
     public List <Adresse> recherche(String add){
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
             list.stream()
                .filter(x-> x.getVille().contains(add))
                .forEach(x->System.out.println(x));
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
          
    
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
            Logger.getLogger(ServiceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
          
    
 }
}

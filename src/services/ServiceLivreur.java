/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entites.Livreur;
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
import java.util.stream.Collectors;
import utils.DBConnection;


/**
 *
 * @author DELL
 */
public class ServiceLivreur {
      Connection cnx;

    public ServiceLivreur() {
        cnx = DBConnection.getInstance().getCon();
    }


    public void ajout(Livreur l) {
        try {
            String req = "insert into livreur (nomLivreur,prenom,numtel,email) values"
                    + " ( '" + l.getNom() + "', '" + l.getPrenom()+ "', '" + l.getNumtel() + "', '" + l.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void modifier(Livreur t) {
        try {
            String req = "update livreur set nomLivreur = ? , prenom = ? , numtel = ? , email = ? where idLivreur = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setInt(3, t.getNumtel());
            ps.setString(4, t.getEmail());
            ps.setInt(5, t.getId());
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
                p.setPrenom(rs.getString("prenom"));
                p.setNumtel(rs.getInt("numtel"));
                p.setEmail(rs.getString("email"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
 public List<Livreur> rechercher(String liv) {
        List<Livreur> salles = afficher().stream()
                .filter(x-> x.getNom().contains(liv))
                .collect(Collectors.toList());
            return salles;       
    }
public List<Livreur> recherche1(String type, String valeur) {
        List<Livreur> myList = new ArrayList();
        String requete = null;

        try {
            if (type.equals("nomLivreur")) {
                requete = "SELECT * from livreur where nomLivreur like '%" + valeur + "%'";
            } else if (type.equals("prenom")) {
                requete = "SELECT * from livreur where prenom like '%" + valeur + "%'";
            } else if (type.equals("numtel")) {
                requete = "SELECT * from livreur where numtel like '%" + valeur + "%'";
            } else if (type.equals("email")) {
                requete = "SELECT * from livreur where email like '%" + valeur + "%'";
            } 
             else if (type.equals("Tout")) {
                requete = "SELECT * from livreur where  nomLivreur like '%" + valeur + "%' or prenom like '%" + valeur + "%' or numtel like '%" + valeur + "%' or email like '%" + valeur + "%' ";
            }
            PreparedStatement ps = cnx.prepareStatement(requete);
            Livreur l;
            for(ResultSet rs = ps.executeQuery(requete); rs.next(); myList.add(l)) {
                l = new Livreur();
                l.setId(rs.getInt("id"));
                l.setNom(rs.getString("nomLivreur"));
                l.setNumtel(rs.getInt("numtel"));
                l.setPrenom(rs.getString("prenom"));
                l.setEmail(rs.getString("email"));
              
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
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
                p.setNom(rs.getString("prenom"));
                p.setNumtel(rs.getInt("numtel"));
                p.setNom(rs.getString("email"));
                list.add(p);
            }
             
             list.stream().sorted(Comparator.comparingInt(Livreur::getId).reversed()).forEach(System.out::println);
         } catch (SQLException ex) {
            Logger.getLogger(ServiceLivreur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
          
    
 }
}

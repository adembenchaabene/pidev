/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Ichariot_model.Commentaire;
import Ichariot_utils.DBConnection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;


/**
 *
 * @author chaab
 */
public class CommentaireService implements I_chariot<Commentaire> {
      Connection con = DBConnection.getInstance().getCon();


    

    @Override
    public void ajouteer(Commentaire C) {
 String query = "INSERT INTO `commentaire`(`auteur_c`,`id_article`,`contenu_c`) VALUES ('"+C.getAuteur_c()+"','"+C.getId_article()+"','"+C.getContenu_c()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Commentaire added.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public List<Commentaire> afficher() {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                commentaires.add(new Commentaire(
                        result.getInt("idcommentaire"),
                        result.getInt("auteur_c"),
                        result.getInt("id_article"),
                        result.getString("contenu_c"),
                        result.getDate("datepub")
                        
                ));
            }
            System.out.println(commentaires);
            return commentaires;
           
           // System.out.println(commentaires);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public void modifer(Commentaire C) {
        String query = "UPDATE commentaire set `contenu_c`='"+C.getContenu_c()+"' where idcommentaire ='"+C.getIdCommentaire()+"'";
        try {
            Statement stmt = con.createStatement();  
            stmt.executeUpdate(query);
            System.out.println("INFO: Commentaire Updated.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void supprimer(Commentaire C) {
         String query = "DELETE from commentaire where id_commentaire ='"+C.getIdCommentaire()+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Commentaire Deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Commentaire> rechercher(String rech)
    {
         List<Commentaire> commentairess = afficher();
        commentairess.stream().filter(x -> x.getContenu_c().contains(rech)).collect(Collectors.toList());
         
        return commentairess;
       
    }
    public List<Commentaire> TriId() {
            List<Commentaire> commentairess = afficher();
            commentairess.stream().sorted(Comparator.comparingInt(Commentaire::getIdCommentaire).reversed()).forEach(System.out::println);
          return commentairess;  
       
    }
    public List<Commentaire>sortCommentaireByDate(List<Commentaire>commentaires){
        Collections.sort(commentaires, new Comparator<Commentaire>() {
            @Override
            public int compare(Commentaire o1, Commentaire o2) {
                return o1.getDatepub().compareTo(o2.getDatepub());
            }
        });
        return commentaires;
    }
    public List<Commentaire> getCommentaires(int id){
        List<Commentaire> commentaires = new ArrayList<>();
        String query ="Select commentaire.* from `article` inner join `commentaire`  on article.idArticle = commentaire.id_article where article.idArticle = '"+id+"'";
              try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                commentaires.add(new Commentaire(
                        result.getInt("commentaire.idcommentaire"),
                        result.getString("commentaire.contenu_c"),
                        result.getDate("commentaire.datepub")
                      
                        
                ));
            }
           return commentaires;
                    
           // System.out.println("test");
           // System.out.println(commentaires);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
              return null;
    }
    
        public void supprimer1(int id) {
         String query = "DELETE from commentaire where idcommentaire ='"+id+"'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Commentaire Deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

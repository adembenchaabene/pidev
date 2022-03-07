/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entites.Article;
import Entites.Etat;
import utils.DBConnection;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author chaab
 */
public class ArticleService implements I_chariot <Article>{
    
    
      // comparator<Article> comparator = new comparator 
       Connection con = DBConnection.getInstance().getCon();

   

    @Override
    public void ajouteer(Article C) {
        String query = "INSERT INTO `article`(`contenu`,`auteur`,`titre`,`Etat`,`image`,`nbrreact`) VALUES ('"+C.getContenu()+"','"+C.getAuteur()+"','"+C.getTitre()+"','"+C.getEtat()+"','"+C.getImage()+"','"+C.getNbrreact()+"')";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: New Article added.");
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
    }

    @Override
    public List <Article> afficher() {
List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM `article` WHERE `Etat`<>'supprimer';";
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                articles.add(new Article(
                        result.getInt("idArticle"),
                        result.getString("contenu"),
                        result.getInt("auteur"),
                        result.getString("titre"),
                        Etat.valueOf( result.getString("Etat")),
                        result.getString("image"),
                        result.getInt("nbrreact")
                
                        
                ));
                
            }
          return articles;
          //  System.out.println("test");
           //System.out.println(articles);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
        return null;    }

    

    @Override
    public void modifer(Article C) {
        try{
            
        
Statement stmt = con.createStatement();
String query = "SELECT * FROM `article` WHERE `idArticle`="+C.getIdArticle()+";";
ResultSet result = stmt.executeQuery(query);
result.next();
if(Etat.valueOf(result.getString("Etat"))== Etat.supprimer){
    System.out.println("Article supprimer");
}
else {
 query = "UPDATE article set  `contenu`='"+C.getContenu()+"', `titre`='"+C.getTitre()+"',`image`='"+C.getImage()+"', `nbrreact`='"+C.getNbrreact()+"' where idArticle ='"+C.getIdArticle()+"'";
         stmt.executeUpdate(query);
         System.out.println("INFO: Article Updated.");
}
}
        catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }

  }

    @Override
    public void supprimer(Article C) {
        try {
            String query ="UPDATE `article` SET `Etat`='" +Etat.supprimer+ "' where `idArticle`='"+C.getIdArticle()+"'";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("INFO: Article Deleted.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }
    public List <Article> recherche(String title) {
           List<Article> articles = afficher().stream()
                .filter(x-> x.getTitre().contains(title))
                .collect(Collectors.toList());
       
            return articles;
          //  System.out.println("test");
            //System.out.println(articles);
           // Debugger.log("INFO: Successfully fetched all users.");

    } 
    
        public List <Article> triID() {
List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM article";

        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                articles.add(new Article(
                        result.getInt("idArticle"),
                        result.getString("contenu"),
                        result.getInt("auteur"),
                        result.getString("titre"),
                        Etat.valueOf( result.getString("Etat")),
                        result.getString("image"),
                        result.getInt("nbrreact")
                
                        
                ));
                
            }
            articles.stream().sorted(Comparator.comparingInt(Article::getIdArticle).reversed()).forEach(System.out::println);
               
            
          //  System.out.println("test");
            //System.out.println(articles);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
        return null;    }

    public void supprimer(int value) {
           try {
            String req = "UPDATE `article` SET `Etat`='" +Etat.supprimer+ "' where idArticle =?";
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, value);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   public Article findById(int id) {
Article a = new Article();
        String query = "SELECT * FROM `article` WHERE `Etat`<>'supprimer AND idArticle';";
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()) {
                a=(new Article(
                        result.getInt("idArticle"),
                        result.getString("contenu"),
                        result.getInt("auteur"),
                        result.getString("titre"),
                        Etat.valueOf( result.getString("Etat")),
                        result.getString("image"),
                        result.getInt("nbrreact")
                
                        
                ));
                
            }
          return a;
          //  System.out.println("test");
           //System.out.println(articles);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
        return null;    }
    
    
}


   

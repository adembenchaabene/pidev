/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entites.Article;
import Entites.Etat;
import Utils.DBConnection;
import java.util.Comparator;

/**
 *
 * @author chaab
 */
public class ArticleService implements I_chariot <Article>{
    
    
      // comparator<Article> comparator = new comparator 
       Connection con = DBConnection.getInstance().getCon();

   

    @Override
    public void ajouteer(Article C) {
        String query = "INSERT INTO `article`(`contenu`,`auteur`,`titre`,`Etat`) VALUES ('"+C.getContenu()+"','"+C.getAuteur()+"','"+C.getTitre()+"','"+C.getEtat()+"')";
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
                        Etat.valueOf( result.getString("Etat"))
                
                        
                ));
                
            }
          // return articles;
          //  System.out.println("test");
           System.out.println(articles);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
        return null;    }

    

    @Override
    public void modifer(Article C) {
String query = "UPDATE article set  `contenu`='"+C.getContenu()+"', `titre`='"+C.getTitre()+"'where idArticle ='"+C.getIdArticle()+"'";
        try {
            Statement stmt = con.createStatement();  
            stmt.executeUpdate(query);
            System.out.println("INFO: Article Updated.");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }      }

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
                        Etat.valueOf( result.getString("Etat"))
                
                        
                ));
                
            }
            
            articles.stream()
                .filter(x-> x.getTitre().contains(title))
                .forEach(x->System.out.println(x));
          //  System.out.println("test");
            //System.out.println(articles);
           // Debugger.log("INFO: Successfully fetched all users.");

        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
        return null;    }
 
    
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
                        Etat.valueOf( result.getString("Etat"))
                
                        
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
        
    
    
}


   

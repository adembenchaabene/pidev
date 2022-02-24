/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Service.ArticleService;
import Service.CommentaireService;
import Entites.Article;
import Entites.Commentaire;
import Entites.Etat;
import Utils.DBConnection;
import java.sql.Connection;
import java.util.function.Predicate;

/**
 *
 * @author chaab
 */
public class Pidevjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        Article ar =new Article("adouma",3,"salem",Etat.desarchive);
    Article an =new Article(27,"bouha",3,"sst",Etat.desarchive);
    ArticleService bb = new ArticleService();
    CommentaireService cc = new CommentaireService();
      Connection con = DBConnection.getInstance().getCon();
    // TODO code application logic here
       
    
       //Article am =new Article("helmi",5,"salut");
       //Article al =new Article(4,"fahmi",9,"chbik aa");
       Commentaire cm =new Commentaire(4,3,4,"adem behi");
       //Article as = new Article("houssem",10,"abida");
       //Commentaire cf = new Commentaire(2,5,2,"testpi");
       //bb.ajouteer(an);

      //bb.afficher();
       //bb.supprimer(an);
       //bb.afficher();
       //bb.afficher();
     //cc.afficher();
    //bb.ajouteer(ar);
     //bb.supprimer(ar);
    //cc.ajouteer(cm);
    //cc.supprimer(cm);
   // bb.deleteArticle(ar);
     // bb.deleteArticle(al);
     //ar.setContenu("sse");
     //bb.updateArticle(ar);
     //bb.getArticle();
     //cc.addCommentaire(cm);
     //bb.addArticle(as);
    // bb.getArticle();
    // cc.getCommentaire();
    //cc.deleteCommentaire(cm);
    //cc.addCommentaire(cf);
    //cf.setContenu_c("test2");
    //cc.updateCommentaire(cf);
    //cc.getCommentaire();
     // String search ="t";
     // bb.recherche("sst");
     //cc.afficher();
    // bb.triID();
        //System.out.println(cc.afficher());
       //cc.rechercher("pi");
        }
    
    
    
}

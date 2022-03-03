/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsti;

import Ichariot_service.ArticleService;
import Ichariot_service.CommentaireService;
import Ichariot_model.Article;
import Ichariot_model.Commentaire;
import Ichariot_model.Etat;
import Ichariot_model.ReactArticle;
import Ichariot_service.Reactservice;
import Ichariot_utils.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaab
 */
public class Pidevjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Connection con = DBConnection.getInstance().getCon();   
    ArticleService bb = new ArticleService();
    CommentaireService cc = new CommentaireService();
    Reactservice ss = new Reactservice();
   
   
  
    Article ar =new Article(26,"adouma",3,"salem",Etat.desarchive,"s",0);
    
     //bb.ajouteer(ar);
    //System.out.println("salem");
        //System.out.println(ss.numberOfLikesByPost(ar.getIdArticle()));
        //ReactArticle rs = new ReactArticle(1,26,1, Timestamp.valueOf(LocalDateTime.now()));
        //ss.putLikeToPost(1,26);
        //ss.putUnLikeToPost(1,26);
   // Article ah =new Article(33,"malek",9,"jemni",Etat.desarchive,"n");
    //Article an =new Article(27,"bouha",3,"sst",Etat.desarchive);
    //Article al =new Article(28,"bonjour",3,"adem",Etat.desarchive);
    //Article as =new Article("validation",3,"pi",Etat.desarchive);
        //System.out.println(bb.afficher());
      //bb.ajouteer(ah);
        //System.out.println(bb.afficher());
       // bb.ajouteer(ah);
     //bb.supprimer(ah);
        //System.out.println(bb.afficher());
    // TODO code application logic here
       //bb.ajouteer(al);
       //bb.supprimer(al);
       //bb.recherche("t");
       //Article am =new Article("helmi",5,"salut");
       //Article al =new Article(4,"fahmi",9,"chbik aa");
       Commentaire cm =new Commentaire(4,1,4,"testini behi");
       //Article as = new Article("houssem",10,"abida");
       //Commentaire cf = new Commentaire(2,5,2,"testpi");
       //bb.ajouteer(an);
      //cc.ajouteer(cm);
       cc.getCommentaires(4);
               //System.out.println(cc.afficher());
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
        //cc.sortCommentaire();
        //bb.afficher();
        //bb.ajouteer(ar);
        //al.setContenu("sssss");
        //bb.modifer(al);
       // bb.modifer(al);
      //java.util.Date myDate = new java.util.Date("01/01/2009");
        //System.out.println(myDate);
       // System.out.println(cc.sortCommentaireByDate(cc.afficher())); 
     //cc.ajouteer(cf);
        //System.out.println(cc.sortCommentaireByDate(cc.afficher()));
    }
    
    
    
}

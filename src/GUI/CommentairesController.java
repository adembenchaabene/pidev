/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Article;
import services.MyListener;
import Entites.Commentaire;
import services.ArticleService;
import services.CommentaireService;
import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author chaab
 */
public class CommentairesController implements Initializable {
    Connection cnx;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane grid2;
     @FXML
    private Label idgetter;
       @FXML
    private TextArea tfcontinu;
       
    @FXML
    private Label idcmt;

     
     MyListener myListener;
     Commentaire commentaire;
     CommentaireService cws = new CommentaireService();
     Article article;
     List<Commentaire> commentaires = cws.afficher();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
          grid2.getChildren().clear();
          
          
          ArticleService bb = new ArticleService();
          //affiche.setText(bb.afficher().toString());
         List<Article> articles = bb.afficher();
          
         
         if(articles.size() > 0){
          selectedArticle(articles.get(0));
          myListener = new MyListener() {
              @Override
              public void onClickListener(Article article) {
                selectedArticle(article);
              } 

              @Override
              public void onClickListener2(Commentaire commentaire) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
         };
                  }
        int column = 0;
        int row = 1;
        
        try {
            for (int i = 0; i < articles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/afficheclient.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheclientController itemController = fxmlLoader.getController();
                itemController.setData1(articles.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    
    }    
    
     public void selectedCmt(Commentaire commentaire){
         tfcontinu.setText(commentaire.getContenu_c());       
         idcmt.setText(String.valueOf(commentaire.getIdCommentaire()));
         
     }
    public void selectedArticle(Article article){
        grid2.getChildren().clear();
         CommentaireService cs = new CommentaireService();
         
          //affiche.setText(bb.afficher().toString());
            if(commentaires.size() > 0){
          selectedCmt(commentaires.get(0));
          myListener = new MyListener() {
              @Override
              public void onClickListener(Article article) {
                selectedArticle(article);
              } 

              @Override
              public void onClickListener2(Commentaire commentaire) {
                 selectedCmt(commentaire);                
              }
         };
                  }
         
         idgetter.setText(String.valueOf(article.getIdArticle()));
          
         List<Commentaire> commentaires = cs.getCommentaires(article.getIdArticle());
        System.out.println(commentaires);
        int column =0;
        int row = 1;
        
        
        try {
            for (int i = 0; i < commentaires.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/graphcomment.fxml"));
                AnchorPane anchorPane2 = fxmlLoader.load();

                GraphcommentController itemController = fxmlLoader.getController();
                itemController.setData3(commentaires.get(i),myListener);
                

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid2.add(anchorPane2, column++, row); //(child,column,row)
                //set grid width
                grid2.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid2.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid2.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid2.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid2.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid2.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane2, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     
    }
    
    @FXML
    void ajoutcomment(ActionEvent event) {
         
        Commentaire ccl = new Commentaire(1,44444446,Integer.parseInt(idgetter.getText()),tfcontinu.getText());
        cws.ajouteer(ccl);
        //tfcontinu.getText();
        tfcontinu.setText("");
        //System.out.println(idgetter.getText());
        
    }

    @FXML
    void modificomment(ActionEvent event) {
        cws.modifer(new Commentaire(Integer.parseInt(idcmt.getText()),1,Integer.parseInt(idgetter.getText()),tfcontinu.getText()));
     
   
    }

    @FXML
    void suppcomment(ActionEvent event) {
        cws.supprimer1(Integer.parseInt(idcmt.getText()));
         
    }

    @FXML
    private void triercom(ActionEvent event) {
        cws.sortCommentaireByDate(commentaires);
        
    }


    
}

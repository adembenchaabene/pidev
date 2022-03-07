/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Article;
import Entites.Commentaire;
import services.MyListener;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chaab
 */
public class AfficheclientController {


    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView pic;
    
    @FXML
    private Label getid;
     
    private Article article;
    private MyListener myListener;
    @FXML
    private Label labelnblike;
     
    

    
    
     public void setData1(Article article,MyListener myListener)
     {      
         this.article=article;
         this.myListener=myListener;
         
       
         nameLabel.setText(article.getContenu());
         priceLable.setText(article.getTitre());
         File f = new File("C:/Users/chaab/Desktop/3eme/java/tsti/src/Ichariot_view/design/" + article.getImage());
         Image img = new Image(f.toURI().toString());
         pic.setImage(img);
         getid.setText(String.valueOf(article.getIdArticle()));  
          labelnblike.setText("Nb like: "+article.getNbrreact());
                 }

    @FXML
    private void listercommentaires(ActionEvent event) {

    }
    
    @FXML

            void selectedarticle(MouseEvent event) {
                
                myListener.onClickListener(article);

    }
 
}

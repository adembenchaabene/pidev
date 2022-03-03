/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ichariot_view;

import Ichariot_model.Article;
import Ichariot_model.Etat;
import Ichariot_service.ArticleService;
import Ichariot_utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chaab
 */
public class SupprimArtiController implements Initializable {
   
    @FXML
    private ComboBox<String> list;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();

    @FXML
    private Button retour;

    @FXML
    private Button supp;
      @FXML
    private GridPane grid;

   @FXML
    private ScrollPane scroll;


    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCombo();
        list();
    } 
    
     @FXML
       int getname(ActionEvent event) {
        int value = Integer.parseInt(list.getValue());
        System.out.println(value);
        return value;
    }

    @FXML
    void goback(ActionEvent event) throws IOException {
          Parent root = FXMLLoader .load(getClass().getResource("/Ichariot_view/ArticFXML.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("ajouter article");

    }

    @FXML
    void supprimerarti(ActionEvent event) {
      Alert alert1 = new Alert(AlertType.CONFIRMATION);
      alert1.setTitle("Supprimer Article");
      alert1.setHeaderText("Voulez vous supprimer cet article ?");
      alert1.setContentText("Article sera supprime");

     
      Optional<ButtonType> option = alert1.showAndWait();

     if (option.isPresent() && option.get() == ButtonType.OK){
         
     int value = Integer.parseInt(list.getValue());
     System.out.println(value);
      ArticleService bb = new ArticleService();
     bb.supprimer(value);
     list();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("l article a ete supprime");
     alert.show();
     }
    }
        public void fillCombo(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from article where `Etat`='" +Etat.desarchive+ "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("idArticle"));
                
            }
            list.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimArtiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      @FXML  
      public void list(){
          ArticleService bb = new ArticleService();
      
          grid.getChildren().clear();
          
          
         List<Article> articles = bb.afficher();  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < articles.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Ichariot_view/articaffichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ArticaffichiController itemController = fxmlLoader.getController();
                itemController.setData(articles.get(i));

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
    
   
    
}

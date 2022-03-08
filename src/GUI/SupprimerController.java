/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Livreur;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ServiceLivreur;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SupprimerController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private ComboBox<String> list;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private Button supp;
    @FXML
    private Label affichel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
        list();
    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/Dashboard.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    
       
    }

    public void fillCombo(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from Livreur ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
                options.add(rs.getString("IdLivreur"));
                
            }
            list.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private int getname(ActionEvent event) {
        int value = Integer.parseInt(list.getValue());
         System.out.println(value);
        return value;
    }

    @FXML
    private void supprimerliv(ActionEvent event) {
     int value = Integer.parseInt(list.getValue());
     System.out.println(value);
     ServiceLivreur sp = new ServiceLivreur();
     Alert alert1 = new Alert(AlertType.CONFIRMATION);
     alert1.setTitle("Confirmation Dialog");
     
     alert1.setContentText("Vous voullez supprimer ce livreur");
     Optional<ButtonType> result = alert1.showAndWait();
     if (result.get() == ButtonType.OK){
     sp.supprimer(value);
     list();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("le livreur a ete supprime");
     alert.show();
     }
     else {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setContentText("Supprission annulle");
    alert.showAndWait();
}
    }

      public void list(){
       grid.getChildren().clear();
         ServiceLivreur sp= new ServiceLivreur();
         List<Livreur> livreurs = sp.afficher();  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < livreurs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/affichi.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AffichiController itemController = fxmlLoader.getController();
                itemController.setData(livreurs.get(i));

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

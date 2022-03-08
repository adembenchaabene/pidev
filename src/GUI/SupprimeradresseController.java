/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Adresse;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ServiceAdresse;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SupprimeradresseController implements Initializable {
Connection cnx;
    @FXML
    private Label affichelabel;
    @FXML
    private ComboBox<String> supp;
    @FXML
    private Button btnsupp;
    ObservableList choix = FXCollections.observableArrayList ();
    @FXML
    private Button retour;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         listsupp();
         fillselect();
    }    

    @FXML
    private void getid(ActionEvent event) {
    }

    @FXML
    private void supprimeradd(ActionEvent event) {
     int value = Integer.parseInt(supp.getValue());
     System.out.println(value);
     ServiceAdresse sp = new ServiceAdresse();
     Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
     alert1.setTitle("Confirmation Dialog");
     
     alert1.setContentText("Vous voullez supprimer ce livreur");
     Optional<ButtonType> result = alert1.showAndWait();
     if (result.get() == ButtonType.OK){
     sp.supprimer(value);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("l'adresse a ete supprime");
     alert.show();
     listsupp();
     fillselect();
     }
      else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setContentText("Supprission annulle");
    alert.showAndWait();
}
    }
    public void listsupp(){
      grid.getChildren().clear();
            ServiceAdresse sp= new ServiceAdresse();
         List<Adresse> adresses = sp.afficheradd();  
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < adresses.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/Afficheadresse.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AfficheadresseController itemController = fxmlLoader.getController();
                itemController.setData(adresses.get(i));

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
 public void fillselect(){
      try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from adresse where iduser = " + LoginController.idglobal;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            choix.add(rs.getString("id_adresse"));
                
            }
            supp.setItems(choix);
        } catch (SQLException ex) {
            Logger.getLogger(SupprimeradresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/DashboardClient.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
}

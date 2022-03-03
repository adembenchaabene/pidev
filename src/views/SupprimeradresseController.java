/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.ServiceAdresse;
import utils.MyDB;

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
     sp.supprimer(value);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("l'adresse a ete supprime");
     alert.show();
     listsupp();
     fillselect();
    }
    public void listsupp(){
      ServiceAdresse sp= new ServiceAdresse();
      affichelabel.setText(sp.afficheradd().toString());
    } 
 public void fillselect(){
      try {
            cnx = MyDB.getInstance().getConnection();
            String req = " select * from adresse where iduser = " + 2;
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
    Parent root = FXMLLoader .load(getClass().getResource("/views/client.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
}

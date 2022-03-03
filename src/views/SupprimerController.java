/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Livreur;
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
import services.ServiceLivreur;
import utils.MyDB;

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
    private Button btnaffich;

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
    Parent root = FXMLLoader .load(getClass().getResource("/views/livreur.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    
       
    }

    public void fillCombo(){
        try {
            cnx = MyDB.getInstance().getConnection();
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
     sp.supprimer(value);
     list();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Success");
     alert.setContentText("le livreur a ete supprime");
     alert.show();
     
    }

      public void list(){
      ServiceLivreur sp= new ServiceLivreur();
      affichel.setText(sp.afficher().toString());
    } 
   
}

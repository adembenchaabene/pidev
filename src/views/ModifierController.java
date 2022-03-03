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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceLivreur;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private ComboBox<String> list;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList ();
    @FXML
    private Button modifier;
    @FXML
    private Label affiche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
        list();
    }    

    @FXML
    private void retourner(ActionEvent event) throws IOException {
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
    private void getvalue(ActionEvent event) {
        try {
            int value = Integer.parseInt(list.getValue());
            System.out.println(value);
            String req = "select * from livreur where idLivreur  = " + value ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            nom.setText(rs.getString("nomLivreur"));
            prenom.setText(rs.getString("prenom"));
            num.setText(rs.getString("numtel"));
            email.setText(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifierlv(ActionEvent event) {
        ServiceLivreur sp = new ServiceLivreur();
        int value = Integer.parseInt(list.getValue());
        Livreur p = new Livreur(value,nom.getText(),prenom.getText(),Integer.parseInt(num.getText()),email.getText());
        sp.modifier(p);
        list();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le livreur a ete modifié" );
        alert.show();
    }
     public void list(){
      ServiceLivreur sp= new ServiceLivreur();
      affiche.setText(sp.afficher().toString());
    }
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Adresse;
import entities.Livraison;
import entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceAdresse;
import services.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ClientController implements Initializable {

    @FXML
    private TextField ville;
    @FXML
    private TextField rue;
    @FXML
    private TextField numm;
    @FXML
    private Button valider;
    @FXML
    private ComboBox<?> type;
    ObservableList options2 = FXCollections.observableArrayList ();
    @FXML
    private Button btnmod;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnconsulter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       affichertype();
    }    

    @FXML
    private void valider(ActionEvent event) {
        ServiceAdresse sp = new ServiceAdresse();
        Adresse p = new Adresse(ville.getText(),rue.getText(),Integer.parseInt(numm.getText()),2);
        sp.ajout(p);
        
        ServiceLivraison sp1 = new ServiceLivraison();
        Livraison p1 = new Livraison((String) type.getValue(),p.getId_adresse(),1,0);
        //sp1.ajout(p1);
    }
    
    public void affichertype(){
      options2.add("rapide");
      options2.add("normal");
      type.setItems(options2);
     
}

    @FXML
    private void redirigermod(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/supprimeradresse.fxml"));
    Stage window = (Stage) btnsupp.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }

    @FXML
    private void redirigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/modifieradresse.fxml"));
    Stage window = (Stage) btnsupp.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }

    @FXML
    private void consulter(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/livraison.fxml"));
    Stage window = (Stage) btnsupp.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }
}

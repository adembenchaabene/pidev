/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Adresse;
import Entites.Livraison;
import Entites.Livreur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
         if (Saisi() == true)
        { 
        ServiceAdresse sp = new ServiceAdresse();
        Adresse p = new Adresse(ville.getText(),rue.getText(),Integer.parseInt(numm.getText()),2);
        int getid = sp.ajout(p);
        System.out.println(getid);
         ServiceLivraison sp1 = new ServiceLivraison();
        Livraison p1 = new Livraison((String) type.getValue(),getid,1,9,0,2);
        sp1.ajout(p1);
         try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le livreur a ete ajoute");
        alert.show();
        
        }

        catch (Exception ee) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.show();
        }
    }
  }
    
    public void affichertype(){
      options2.add("rapide");
      options2.add("normal");
      type.setItems(options2);
     
}

    @FXML
    private void redirigermod(ActionEvent event) throws IOException {
   Parent root = FXMLLoader .load(getClass().getResource("/GUI/modifieradresse.fxml"));
    Stage window = (Stage) btnmod.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("modifier");
    }

    @FXML
    private void redirigersupp(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/supprimeradresse.fxml"));
    Stage window = (Stage) btnsupp.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("supprimer");
    }

    @FXML
    private void consulter(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/livraison.fxml"));
    Stage window = (Stage) btnconsulter.getScene().getWindow();
    window.setScene(new Scene(root));
    window.setTitle("consulter tes livraisons");
    }
    public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
   
    private boolean Saisi() {  

        if (ville.getText().isEmpty() || rue.getText().isEmpty() || numm.getText().isEmpty()) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]+", numm.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Vérifiez le numero de maison! ");
                return false;
            }

           if (!Pattern.matches("[A-Za-z]*", ville.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le nom ! ");
                return false;
            }
          if (!Pattern.matches("[A-Za-z]*", rue.getText())) {
                Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier ", "Vérifiez le prenom ! ");
                return false;
            }
           
        }
        return true;
         
    }
    
}

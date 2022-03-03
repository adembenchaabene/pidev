/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Adresse;
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
import services.ServiceAdresse;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifieradresseController implements Initializable {
    ObservableList choix = FXCollections.observableArrayList ();
    @FXML
    private Label affichadresse;
    @FXML
    private ComboBox<String> selectid;
    @FXML
    private TextField ville;
    @FXML
    private TextField rue;
    @FXML
    private TextField numla;
    Connection cnx;
    @FXML
    private Button modifieradd;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listadd();
        filladd();
    }    
    public void listadd(){
      ServiceAdresse sp= new ServiceAdresse();
      affichadresse.setText(sp.afficheradd().toString());
    }
     public void filladd(){
        try {
            cnx = MyDB.getInstance().getConnection();
            String req = " select * from adresse where iduser = " + 2;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            choix.add(rs.getString("id_adresse"));
                
            }
            selectid.setItems(choix);
        } catch (SQLException ex) {
            Logger.getLogger(ModifieradresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void remplirfield(ActionEvent event) {
         try {
            int value = Integer.parseInt(selectid.getValue());
            System.out.println(value);
            String req = "select * from adresse where id_adresse  = " + value ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while(rs.next()){
            ville.setText(rs.getString("ville"));
            rue.setText(rs.getString("rue"));
            numla.setText(rs.getString("numMaison"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifieradresseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modiieradd(ActionEvent event) {
        ServiceAdresse sp= new ServiceAdresse();
        int value = Integer.parseInt(selectid.getValue());
        Adresse p = new Adresse(value,ville.getText(),rue.getText(),Integer.parseInt(numla.getText()));
        sp.modifier(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("l'adresse a ete modifié" );
        alert.show();
        listadd();
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/views/client.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
}

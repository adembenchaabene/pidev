/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Adresse;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.ServiceAdresse;
import services.ServiceLivreur;
import utils.DBConnection;

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
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listadd();
        filladd();
    }    
    public void listadd(){
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
     public void filladd(){
        try {
            cnx = DBConnection.getInstance().getCon();
            String req = " select * from adresse where iduser = " + LoginController.idglobal;
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
            String req = "select * from adresse where id_adresse  = " + value  ;
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
        Adresse p = new Adresse(value,ville.getText(),rue.getText(),Integer.parseInt(numla.getText()),LoginController.idglobal);
         Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
         alert1.setTitle("Confirmation Dialog");
         alert1.setContentText("Vous voulez modifier cette adresse");
         Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
        sp.modifier(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("l'adresse a ete modifié" );
        alert.show();
        listadd();
        }
     else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information Dialog");
    alert.setContentText("Modification annulle");
    alert.showAndWait();
    }
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
    Parent root = FXMLLoader .load(getClass().getResource("/GUI/DashboardClient.fxml"));
    Stage window = (Stage) retour.getScene().getWindow();
    window.setScene(new Scene(root));
    }
}
